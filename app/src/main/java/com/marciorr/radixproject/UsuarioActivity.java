package com.marciorr.radixproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.marciorr.radixproject.adapter.CustomAdapter;
import com.marciorr.radixproject.model.RetroUser;
import com.marciorr.radixproject.nerwork.GetDataService;
import com.marciorr.radixproject.nerwork.RetrofitClientInstance;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioActivity extends AppCompatActivity {

    public String loginUsuario;
    private String avatarUrl;
    private ImageView avatar;
    private TextView loginUsuarioTV;
    public static String enviaUsuario;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private Toolbar toolbarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        //Recupera as Intents
        Intent i = getIntent();
        loginUsuario = i.getStringExtra("loginUsuario");
        avatarUrl = i.getStringExtra("avatarUrl");

        //Localiza os componentes na tela
        avatar = findViewById(R.id.avatarId);
        loginUsuarioTV = findViewById(R.id.loginUsuarioId);
        toolbarUsuario = findViewById(R.id.toolbar_usuario);

        //Cria a Toolbar
        toolbarUsuario.setTitle(loginUsuario);
        toolbarUsuario.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar( toolbarUsuario );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Carregar avatar
        Picasso.get()
                .load(avatarUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .resize(250, 0)
                .into(avatar);

        //Envia o nome do Usuário para a instância do Retrofit
        enviaUsuarioRFCI();

        loginUsuarioTV.setText(loginUsuario);

        progressDoalog = new ProgressDialog(UsuarioActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        //Cria um handle para a instância do Retrofit
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);


        Call<List<RetroUser>> call = service.getAllUsers();
        call.enqueue(new Callback<List<RetroUser>>() {

            @Override
            public void onResponse(Call<List<RetroUser>> call, Response<List<RetroUser>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroUser>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(UsuarioActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método para gerar a lista usando o RecyclerView com o CustomAdapter
    private void generateDataList(List<RetroUser> userList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UsuarioActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void enviaUsuarioRFCI(){
        enviaUsuario  = loginUsuario;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
