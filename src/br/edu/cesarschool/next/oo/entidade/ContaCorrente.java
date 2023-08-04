package br.edu.cesarschool.next.oo.entidade;

import java.io.Serializable;

public class ContaCorrente implements Serializable {
    // Attributes
    private String numero;
    private Double saldo;
    private String nomeCorrentista;

    // Constructors
    public ContaCorrente() {
    }

    public ContaCorrente(String numero, Double saldo, String nomeCorrentista) {
        this.numero = numero;
        this.saldo = saldo;
        this.nomeCorrentista = nomeCorrentista;
    }

    // Metodos Especificos
    public void creditar(double valor) {
        saldo += valor;
    }

    public void debitar(double valor) {
        saldo -= valor;
    }

    // getters e setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    @Override
    public String toString() {
        return "ContaCorrente [numero=" + numero + ", saldo=" + saldo + ", nomeCorrentista=" + nomeCorrentista + "]";
    }

    
}
