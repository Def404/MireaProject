package ru.mirea.baskakov.mireaproject.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.baskakov.mireaproject.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<History> historyList;

    HistoryAdapter(Context context,  List<History> historyList){
        this.historyList = historyList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {

        History history =  historyList.get(position);
        holder.idHistoryView.setText(String.valueOf(history.getId()));
        holder.storyView.setText(history.getStory());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView idHistoryView, storyView;
        ViewHolder(View view){
            super(view);
            idHistoryView = view.findViewById(R.id.idStoryRec);
            storyView = view.findViewById(R.id.storyTextRec);

        }
    }
}
