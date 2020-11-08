package com.subhasmith.loadshare.presenter;



import android.util.Log;

import com.subhasmith.loadshare.datainterface.GetIFSCDataInterface;
import com.subhasmith.loadshare.model.ResponseIFSCDataAPI;
import com.subhasmith.loadshare.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIFSCDataImpl implements MainContract.GetIFSCDataIntractor {
    @Override
    public void getIFSCData(OnFinishedListener onFinishedListener, String id) {
        /** Create handle for the RetrofitInstance interface*/
        GetIFSCDataInterface ifscDataInterface = RetrofitInstance.getRetrofitInstance().create(GetIFSCDataInterface.class);

        /** Call the method with parameter in the interface to get the IFSC data*/
        Call<ResponseIFSCDataAPI> call = ifscDataInterface.getIFSCData(id);

        Log.d("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseIFSCDataAPI>() {
            @Override
            public void onResponse(Call<ResponseIFSCDataAPI> call, Response<ResponseIFSCDataAPI> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<ResponseIFSCDataAPI> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
