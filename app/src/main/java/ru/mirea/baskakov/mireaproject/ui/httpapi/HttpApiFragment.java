package ru.mirea.baskakov.mireaproject.ui.httpapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ru.mirea.baskakov.mireaproject.R;

public class HttpApiFragment extends Fragment {

    private EditText userIdVkEditText;
    private TextView userStatusTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_http_api, container, false);

        userIdVkEditText = view.findViewById(R.id.idVklEditText);
        userStatusTextView = view.findViewById(R.id.statusTextView);

        view.findViewById(R.id.getStatusBtn).setOnClickListener(this::onClickGetStatus);
        return view;
    }

    private void onClickGetStatus(View view) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = null;
        if (connectivityManager != null) {
            networkinfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkinfo != null && networkinfo.isConnected()) {
            String url = "https://api.vk.com/method/status.get?user_id="+userIdVkEditText.getText().toString()+"&access_token=1d345b93fe64630096843cb00ae305850afdc7d2f23275814b4986b419f8c2bce295fef0ce8b5d85df052&v=5.131";
            new DownloadPageTask().execute(url); // запускаем в новом потоке
        } else {
            Toast.makeText(getActivity(), "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }

        public class DownloadPageTask extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                userStatusTextView.setText("Загружаем...");
            }
            @Override
            protected String doInBackground(String... urls) {
                try {
                    return downloadIpInfo(urls[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                }
            }
            @Override
            protected void onPostExecute(String result) {


                try {
                    JSONObject responseJson = new JSONObject(result);

                    String status = responseJson.getJSONObject("response").getString("text");
                    userStatusTextView.setText(status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onPostExecute(result);
            }
    }
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();
                data = new String(result);
            } else {
                data = connection.getResponseMessage() + " . Error Code : " + responseCode;
            }
            connection.disconnect();
            //return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}