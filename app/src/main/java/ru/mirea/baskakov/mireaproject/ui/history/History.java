package ru.mirea.baskakov.mireaproject.ui.history;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String story;

    public long getId(){
        return this.id;
    }

    public String getStory(){
        return this.story;
    }
}
