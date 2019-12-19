package com.marciorr.radixproject;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.marciorr.radixproject.nerwork.RemoteFetch;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText caixaBusca;
    private Button botaoBusca;
    Handler handler;
    public static String enviaUsuario;
    private Toolbar toolbarPrincipal;

    public MainActivity(){
        handler = new Handler();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        caixaBusca.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Localizar componentes na tela
        caixaBusca = findViewById(R.id.caixa_busca);
        botaoBusca = findViewById(R.id.botao_busca);
        toolbarPrincipal = findViewById(R.id.toolbar_principal);

        //Criar a Toolbar
        toolbarPrincipal.setTitle(R.string.app_name);
        toolbarPrincipal.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar( toolbarPrincipal );

        //Criar botão Buscar
        botaoBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeUsuario = caixaBusca.getText().toString();
                if(nomeUsuario.isEmpty()){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(botaoBusca.getWindowToken(), 0);
                    Toast.makeText(MainActivity.this, "Digite um nome de usuário!", Toast.LENGTH_LONG).show();
                }else{
                    enviaUsuario = "";
                    buscaUsuarioGit(nomeUsuario);
                }
            }
        });
    }

    //Faz a busca do objeto JSON
    private void buscaUsuarioGit(final String usuario){
        new Thread(){
            public void run(){
                final JSONObject json = RemoteFetch.getJSON(MainActivity.this, usuario);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(botaoBusca.getWindowToken(), 0);
                            Toast.makeText(MainActivity.this,
                                    "Usuário não encontrado!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderUser(json);
                        }
                    });
                }
            }
        }.start();
    }

    //Renderiza objeto JSON e envia para UsuarioActivity
    private void renderUser(JSONObject json){
        try {

            Intent intent = new Intent(getApplicationContext(), UsuarioActivity.class);
            intent.putExtra("loginUsuario", json.getString("login"));
            intent.putExtra("avatarUrl", json.getString("avatar_url"));
            startActivity( intent );

        }catch(Exception e){
            Log.e("Radix Project", "One or more fields not found in the JSON data");
        }
    }
}