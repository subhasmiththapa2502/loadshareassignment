package com.subhasmith.loadshare.datainterface;

import com.subhasmith.loadshare.model.ResponseIFSCDataAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetIFSCDataInterface {

    @GET("{id}")
    Call<ResponseIFSCDataAPI> getIFSCData(@Path("id") String id);
}
