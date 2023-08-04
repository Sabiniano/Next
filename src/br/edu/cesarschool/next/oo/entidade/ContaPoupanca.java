package br.edu.cesarschool.next.oo.entidade;

public class ContaPoupanca extends ContaCorrente {
    // Attributes
    private double percentualBonus;

    // Constructors
    public ContaPoupanca() {
    }

    public ContaPoupanca(double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }

    public ContaPoupanca(String numero, Double saldo, String nomeCorrentista, double percentualBonus) {
        super(numero, saldo, nomeCorrentista);
        this.percentualBonus = percentualBonus;
    }

    // Metodos Especificos
    @Override
    public void creditar(double valor) {
        super.creditar(valor * (1 + percentualBonus/100));
    }
    
    // getters e setters
    public double getPercentualBonus() {
        return percentualBonus;
    }

    public void setPercentualBonus(double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }

    @Override
    public String toString() {
        return super.toString() + "ContaPoupanca [percentualBonus=" + percentualBonus + "]";
    }

    
}