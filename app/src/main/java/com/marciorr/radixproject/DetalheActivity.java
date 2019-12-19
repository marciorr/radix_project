package com.marciorr.radixproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalheActivity extends AppCompatActivity {

    private String name;
    private String description;
    private TextView descricao;
    private String created_at;
    private TextView createdAt;
    private String language;
    private TextView linguagem;
    private int stargazers_count;
    private TextView starGazersCount;
    private int forks_count;
    private TextView forksCount;
    private int open_issues_count;
    private TextView openIssuesCount;
    private String html_url;
    private ImageView gitHub;
    private Toolbar toolbarDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        //Recupera os valores
        Intent i = getIntent();
        name = i.getStringExtra("name");
        description = i.getStringExtra("description");
        stargazers_count = i.getIntExtra("stargazers_count", 0);
        open_issues_count = i.getIntExtra("open_issues_count", 0);
        forks_count = i.getIntExtra("forks_count", 0);
        created_at = i.getStringExtra("created_at");
        language = i.getStringExtra("language");
        html_url = i.getStringExtra("html_url");

        //Localiza os componentes na tela
        toolbarDetalhes = findViewById(R.id.toolbar_detalhes);
        starGazersCount = findViewById(R.id.stargazers_countId);
        forksCount = findViewById(R.id.forks_countId);
        openIssuesCount = findViewById(R.id.open_issues_countId);
        descricao = findViewById(R.id.descricaoId);
        linguagem = findViewById(R.id.languageId);
        createdAt = findViewById(R.id.created_atId);
        gitHub = findViewById(R.id.githubId);

        //Cria a Toolbar
        toolbarDetalhes.setTitle(name);
        toolbarDetalhes.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar( toolbarDetalhes );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Preenche os valores nos TextViews
        starGazersCount.setText("" + stargazers_count);
        forksCount.setText("" + forks_count);
        openIssuesCount.setText("" + open_issues_count);
        if(description == null){
            descricao.setText("Não declarada");
        }else{
            descricao.setText(description);
        }
        if(language == null){
            linguagem.setText("Não declarada");
        }else{
            linguagem.setText(language);
        }
        if(created_at == null){
            createdAt.setText("Não declarada");
        }else{
            String start_dt = created_at;
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-DD");
            Date date = null;
            try {
                date = (Date)formatter.parse(start_dt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat newFormat = new SimpleDateFormat("DD/mm/yyyy");
            String finalString = newFormat.format(date);
            createdAt.setText(finalString);
        }

        //Cria o botão do GitHub e direciona à URL do projeto
        gitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(html_url);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(html_url)));
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
