package com.example.adcms_mobileapp_v2.ui.patient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PatientViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PatientViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is patient fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}