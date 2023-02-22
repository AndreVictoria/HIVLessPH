package com.sti.hivlessph.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mFirstName;
    private final MutableLiveData<String> mMiddleName;
    private final MutableLiveData<String> mLastName;
    private final MutableLiveData<String> mDateOfBirth;

    public ProfileViewModel() {
        mFirstName = new MutableLiveData<>();
        mMiddleName = new MutableLiveData<>();
        mLastName = new MutableLiveData<>();
        mDateOfBirth = new MutableLiveData<>();
    }

    public LiveData<String> getFirstName() {
        return mFirstName;
    }

    public LiveData<String> getMiddleName() {
        return mMiddleName;
    }

    public LiveData<String> getLastName() {
        return mLastName;
    }

    public LiveData<String> getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setFirstName(String value) {
        mFirstName.setValue(value);
    }

    public void setMiddleName(String value) {
        mMiddleName.setValue(value);
    }

    public void setLastName(String value) {
        mLastName.setValue(value);
    }

    public void setDateOfBirth(String value) {
        mDateOfBirth.setValue(value);
    }

}