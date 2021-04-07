package com.webplate.viewmodel;

import android.util.Log;

import com.webplate.database.DaoAccess;
import com.webplate.model.UserData;
import com.webplate.retrofit.RetrofitUtil;
import com.webplate.retrofit.UserService;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    String TAG = this.getClass().getName();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isError = new MutableLiveData<>();
    MutableLiveData<ArrayList<UserData>> userMutableLiveData = new MutableLiveData<>();
    Call<ArrayList<UserData>> apiCallUser;
    private UserService userService;

    public LiveData<PagedList<UserData>> concertList;
    private DaoAccess concertDao;


    public LiveData<Boolean> error() {
        Log.d(TAG, "return isError  : " + isError);
        return isError;
    }

    public LiveData<ArrayList<UserData>> userDataReceived() {
        Log.d(TAG, "return onHomeDataReceive  : " + userMutableLiveData);
        return userMutableLiveData;
    }

    public LiveData<Boolean> isLoading() {
        Log.d(TAG, "return isLoading  : " + isLoading);
        return isLoading;
    }

    public void getUserData() {
        userService = RetrofitUtil.getInstance().getRetrofit().create(UserService.class);
        isLoading.setValue(true);
        apiCallUser = userService.getAllUserData();
        Log.d(TAG, "Start getHome call 2 : ");

        apiCallUser.enqueue(new Callback<ArrayList<UserData>>() {
            @Override
            public void onResponse(Call<ArrayList<UserData>> call, Response<ArrayList<UserData>> response) {
                Log.d(TAG, " getHome call onResponse 0 : ");

                if (response.body() != null)
                    Log.d(TAG, " getHomeByID call onResponse 1 : " + response.body().toString());


                if (response.code() == 200 && response.body() != null) {
                    Log.d(TAG, " getHome call onResponse 2 : " + response.body().toString());

                    isError.setValue(false);
                    userMutableLiveData.setValue(response.body());

                } else {
                    isError.setValue(true);
                    userMutableLiveData.setValue(null);
                }
                isLoading.setValue(false);
                apiCallUser = null;

            }

            @Override
            public void onFailure(Call<ArrayList<UserData>> call, Throwable t) {
                Log.d(TAG, " getHome call onFailure : " + t.toString());
                isError.setValue(true);
                isLoading.setValue(false);
                apiCallUser = null;
            }
        });
    }




    public LiveData<PagedList<UserData>> concertViewModel(DaoAccess concertDao) {
        this.concertDao = concertDao;
        concertList = new LivePagedListBuilder<>(
                concertDao.concertsByDate(), 4).build();
        return concertList;
    }
}
