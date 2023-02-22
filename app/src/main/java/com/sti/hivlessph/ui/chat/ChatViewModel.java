package com.sti.hivlessph.ui.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import model.Chat;

public class ChatViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Chat>> mItems;

    public ChatViewModel() {
        mItems = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Chat>> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<Chat> value) {
        mItems.setValue(value);
    }
}
