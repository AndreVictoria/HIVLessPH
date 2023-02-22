package com.sti.hivlessph.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mHtml;

    public HomeViewModel() {
        mHtml = new MutableLiveData<>();
    }

    public LiveData<String> getHtml() {
        return mHtml;
    }


    public void setHtml(String value) {
        mHtml.setValue(value);
    }
}