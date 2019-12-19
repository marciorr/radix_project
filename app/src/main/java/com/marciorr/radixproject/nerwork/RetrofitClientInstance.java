package com.marciorr.radixproject.nerwork;

import com.marciorr.radixproject.UsuarioActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static String BASE_URL = "https://api.github.com/users/";

    //Cria a instância do Retrofit e completa a URL com o Usuário
    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            String userRecebido = UsuarioActivity.enviaUsuario;
            BASE_URL = BASE_URL + userRecebido + "/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }else{
            String userRecebido = UsuarioActivity.enviaUsuario;
            BASE_URL = BASE_URL + userRecebido + "/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        BASE_URL = "https://api.github.com/users/";
        return retrofit;
    }
}
