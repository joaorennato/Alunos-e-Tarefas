package com.xso.alunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarAlunoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText ra;
    private EditText serie;
    private AlunoDAO dao;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);

        nome = findViewById(R.id.editTextNome);
        ra = findViewById(R.id.editTextRA);
        serie = findViewById(R.id.editTextSerie);
        dao = new AlunoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("aluno")){
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome().toString());
            ra.setText(aluno.getRa().toString());
            serie.setText(aluno.getSerie().toString());
        }
    }

    public void salvar(View view) {

        if(aluno == null){

            aluno = new Aluno();
            aluno.setNome(nome.getText().toString());
            aluno.setRa(ra.getText().toString());
            aluno.setSerie(serie.getText().toString());

            long id = dao.inserir(aluno);
            Toast.makeText(this, "Aluno cadastrado com id: " + id, Toast.LENGTH_SHORT).show();

        } else {

            aluno.setNome(nome.getText().toString());
            aluno.setRa(ra.getText().toString());
            aluno.setSerie(serie.getText().toString());

            dao.atualizar(aluno);
            Toast.makeText(this, "Aluno atualizado.", Toast.LENGTH_SHORT).show();

        }

        Intent it = new Intent(this, ListarAlunosActivity.class);
        startActivity(it);

    }

}
