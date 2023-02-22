package com.sti.hivlessph.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import model.Notification;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Notification>> mItems;

    public NotificationsViewModel() {
        mItems = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Notification>> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<Notification> value) {
        mItems.setValue(value);
    }
}