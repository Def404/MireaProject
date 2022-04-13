package ru.mirea.baskakov.mireaproject.ui.history;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import ru.mirea.baskakov.mireaproject.App;
import ru.mirea.baskakov.mireaproject.AppDatabase;
import ru.mirea.baskakov.mireaproject.R;


public class HistoryFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        //view.findViewById(R.id.addHistoryBtn).setOnClickListener(this::onClickAddHistory);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHistoryFragment addHistoryFragment = new AddHistoryFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), addHistoryFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        AppDatabase db = App.getInstance().getDatabase();
        HistoryDao historyDao =  db.historyDao();

        List<History> historyList = historyDao.getAll();

        HistoryAdapter adapter =  new HistoryAdapter(getActivity(), historyList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onClickAddHistory(View view){

        AddHistoryFragment addHistoryFragment = new AddHistoryFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), addHistoryFragment, "findThisFragment")
                .addToBackStack(null)
                .commit();

        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}