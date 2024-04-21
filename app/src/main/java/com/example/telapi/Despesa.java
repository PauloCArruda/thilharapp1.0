package com.example.telapi;

import java.io.Serializable;
import java.util.Date;

public class Despesa implements Serializable {

    Long id;
    String descricao, vencimento;
    double valor;
    int pago;


    public Despesa(Long id, String descricao, double valor, String vencimento, int pago) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vencimento = vencimento;
        this.pago = pago;
    }

    public Despesa(){

    }
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }
    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    @Override
    public String toString(){
          return  descricao + "\n" +
            "Valor: "+  String.format("R$%.2f", valor) + "\n" +
            "Vencimento: "+ vencimento;
}
}
