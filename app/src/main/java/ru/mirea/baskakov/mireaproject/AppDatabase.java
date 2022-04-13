package ru.mirea.baskakov.mireaproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.baskakov.mireaproject.ui.history.History;
import ru.mirea.baskakov.mireaproject.ui.history.HistoryDao;

@Database(entities = {History.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();
}
