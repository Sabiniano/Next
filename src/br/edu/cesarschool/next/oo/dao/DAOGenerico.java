package br.edu.cesarschool.next.oo.dao;

import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.RegistroIdentificavel;

public class DAOGenerico {
    // Attributes
    private CadastroObjetos cadastro;
    private Class tipo;

    // Constructors
    public DAOGenerico(Class tipo) { // ContaCorrente.class ou Produto.class
        this.tipo = tipo;
        cadastro = new CadastroObjetos(tipo);
    }

    // Metodos Especificos
    public boolean incluir(ContaCorrente conta) {
        RegistroIdentificavel regbusca = buscar(conta.obterChave());
        if (regbusca != null) {
            return false;
        } else {
            cadastro.incluir(conta, conta.obterChave());
            return true;
        }
    }

    public RegistroIdentificavel buscar(String codigo) {
        return (RegistroIdentificavel) cadastro.buscar(tipo, codigo);
    }

    public boolean alterar(RegistroIdentificavel reg) {
        RegistroIdentificavel regbuscado = buscar(reg.obterChave());
        if (regbuscado == null) {
            return false;
        } else {
            cadastro.alterar(reg, reg.obterChave());
            return true;
        }
    }

    public RegistroIdentificavel[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(tipo);
        RegistroIdentificavel[] contas = new RegistroIdentificavel[rets.length];
        for (int i = 0; i < rets.length; i++) {
            contas[i] = (RegistroIdentificavel) rets[i];
        //for (RegistroIdentificavel conta : contas) {
        //    contas = conta;
        }
        return contas;
    }
}
