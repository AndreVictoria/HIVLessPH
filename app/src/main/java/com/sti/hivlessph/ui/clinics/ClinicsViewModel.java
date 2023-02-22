package com.sti.hivlessph.ui.clinics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClinicsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ClinicsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}