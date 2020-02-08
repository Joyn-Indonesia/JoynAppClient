package com.example.joynappclient.ui.inprogress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joynappclient.ui.booking.checkout.model.CheckOutModel;

public class InProgressViewModel extends ViewModel {

    private MutableLiveData<CheckOutModel> checkOut = new MutableLiveData<>();

    public LiveData<CheckOutModel> getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(CheckOutModel model) {
        checkOut.postValue(model);
    }
}
