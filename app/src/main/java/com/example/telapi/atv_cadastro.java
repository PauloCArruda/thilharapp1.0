package com.example.telapi;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class atv_cadastro extends AppCompatActivity implements View.OnClickListener {

    Button btnGravar, btnExcluir;
    ImageButton btnVoltar;
    EditText edtDescricao, edtValor, edtVencimento;
    String acao;
    Despesa d;
    DespesaDao dao;

    CheckBox chkPago;

    private void criarComponentes(){
        btnGravar = findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        btnGravar.setText(acao);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);

        if(acao.equals("Inserir"))
            btnExcluir.setVisibility(View.INVISIBLE);
        else btnExcluir.setVisibility(View.VISIBLE);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtVencimento = findViewById(R.id.edtVencimento);

        chkPago = findViewById(R.id.chkPago);
        if(acao.equals("Inserir"))
            btnGravar.setText("INSERIR");
        else
            btnGravar.setText("ATUALIZAR");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_cadastro);

        acao = getIntent().getExtras().getString("acao");
        dao = new DespesaDao(this);
        criarComponentes();

        if(getIntent().getExtras().getSerializable("obj")!= null){
            d = (Despesa) getIntent().getExtras().getSerializable("obj");
            edtDescricao.setText(d.getDescricao());
            edtValor.setText(String.format("R$ %.2f", d.getValor()));
            edtVencimento.setText(d.getVencimento());
            chkPago.setChecked(d.getPago()==1);
        }
    }
    @Override
    public void onClick(View v){
        if (v==btnVoltar){
            finish();
        } else if (v == btnExcluir) {
            long id = dao.excluir(d);
            Toast.makeText(this,"Despesa"+ d.getDescricao()+" foi excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        } else if (v == btnGravar) {
            if (acao.equals("inserir")) {
                d = new Despesa();
            }
            d.setDescricao(edtDescricao.getText().toString());
            String valortext = edtValor.getText().toString();
// remove os caracteres não numéricos (como "r$" e ",") da string
            valortext = valortext.replace("R$", "").replaceAll(",", ".");



            d.setVencimento(edtVencimento.getText().toString());
            d.setPago(chkPago.isChecked()?1:0);

            if (acao.equals("inserir")){
                long id = dao.inserir(d);
                Toast.makeText(this,"despesa"+ d.getDescricao()+" foi inserido com sucesso"+ id,Toast.LENGTH_SHORT).show();
                 }
            else {
                long id = dao.alterar(d);
                Toast.makeText(this, "despesa\"+d.getDescricao()+\" foi alterado com sucesso!", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}