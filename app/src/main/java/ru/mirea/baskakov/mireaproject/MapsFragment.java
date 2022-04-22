package ru.mirea.baskakov.mireaproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment {

    private EditText searchEditText;
    protected static GoogleMap googleMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            MapsFragment.googleMap = googleMap;

            LatLng mirea1 = new LatLng(55.6695953,37.4798824);
            String TitleMirea1 = "Кампус на Проспекте Вернадского, 78";
            String SnippetMirea1 = "Адрес: Проспект Вернадского, д. 78 " +
                    "55.6695953,37.4798824";
            googleMap.addMarker(new MarkerOptions().position(mirea1).title(TitleMirea1).snippet(SnippetMirea1));


            LatLng mirea2 = new LatLng(55.6618971,37.4745255);
            String titleMirea2 = "Кампус на Проспекте Вернадского, 86";
            String snippetMirea2 = "Адрес: Проспект Вернадского, д. 86 " +
                    "55.6618971,37.4745255";
            googleMap.addMarker(new MarkerOptions().position(mirea2).title(titleMirea2).snippet(snippetMirea2));


            LatLng mirea3 = new LatLng(55.7938058,37.7000664);
            String titleMirea3 = "Кампус на улице Стромынка";
            String snippetMirea3 = "Адрес: ул. Стромынка, д.20 " +
                    "55.7938058,37.7000664";
            googleMap.addMarker(new MarkerOptions().position(mirea3).title(titleMirea3).snippet(snippetMirea3));


            LatLng mirea4 = new LatLng(55.7317977,37.5745506);
            String titleMirea4 = "Кампус на улице Малая Пироговская";
            String snippetMirea4 = "Адрес: ул. Малая Пироговская, д. 1, стр. 5 " +
                    "55.7317977,37.5745506";
            googleMap.addMarker(new MarkerOptions().position(mirea4).title(titleMirea4).snippet(snippetMirea4));


            LatLng mirea5 = new LatLng(55.7648399,37.7392163);
            String titleMirea5 = "Кампус на улице Соколиная Гора";
            String snippetMirea5 = "Адрес: 5-я улица Соколиной Горы, д. 22 " +
                    "55.7648399,37.7392163";
            googleMap.addMarker(new MarkerOptions().position(mirea5).title(titleMirea5).snippet(snippetMirea5));

            LatLng mirea6 = new LatLng(55.7250254,37.6304868);
            String titleMirea6 = "Колледж";
            String snippetMirea6 = "Адрес: 1-й Щипковский переулок, д. 23 " +
                    "55.7250254,37.6304868";
            googleMap.addMarker(new MarkerOptions().position(mirea6).title(titleMirea6).snippet(snippetMirea6));


            LatLng mirea7 = new LatLng(55.728676,37.5708812);
            String titleMirea7 = "ВУЦ";
            String snippetMirea7 = "Адрес: ул. Усачева, д.7/1 " +
                    "55.728676,37.5708812" ;
            googleMap.addMarker(new MarkerOptions().position(mirea7).title(titleMirea7).snippet(snippetMirea7));

            LatLng mirea8 = new LatLng(45.0508385,41.9097125);
            String titleMirea8 = "Филиал в г. Ставрополе";
            String snippetMirea8 = "Адрес: Проспект Кулакова, д. 8 в квартале 601 "+
                    "45.0508385,41.9097125";
            googleMap.addMarker(new MarkerOptions().position(mirea8).title(titleMirea8).snippet(snippetMirea8));

            LatLng mirea9 = new LatLng(55.9604333,38.049562);
            String titleMirea9 = "Филиал в г. Фрязино";
            String snippetMirea9 = "Адрес: ул. Вокзальная, д.2а" +
                    "55.9604333,38.049562";
            googleMap.addMarker(new MarkerOptions().position(mirea9).title(titleMirea9).snippet(snippetMirea9));

            LatLng moscow = new LatLng(55.5815244,36.8251221);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(moscow));
            googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        searchEditText = view.findViewById(R.id.searchEditText);
        view.findViewById(R.id.searchBtn).setOnClickListener(this::onClickSearch);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void onClickSearch(View view){
        String location = searchEditText.getText().toString();

        if(location == null){
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }else {

            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
                List<Address>  listAddress = geocoder.getFromLocationName(location,1);

                if (listAddress.size() > 0){
                    LatLng latLng = new LatLng(listAddress.get(0).getLatitude(), listAddress.get(0).getLongitude());
                    String searchTitle = "Search";
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(searchTitle));
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 5);
                    googleMap.animateCamera(cameraUpdate);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}