package com.marciorr.radixproject.nerwork;

import com.marciorr.radixproject.model.RetroUser;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Define o EndPoint
public interface GetDataService {

    @GET("repos")
    Call<List<RetroUser>> getAllUsers();
}
