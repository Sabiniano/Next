package br.edu.cesarschool.next.oo.negocio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

public class MediatorContaCorrente {
	// Attributes
	private DAOContaCorrente daoContaCorrente = new DAOContaCorrente();

	// Constructor
	public MediatorContaCorrente() {
	}

	// Metodos Especificos
	public String incluir(ContaCorrente conta) {
		if (conta == null) {
			return "Conta n�o informada"; 
		} else if (stringNulaOuVazia(conta.getNumero())) {
			return "Número da conta n�o informado";
		} else if (!(conta.getNumero().length() >= 5 && conta.getNumero().length() <=8)) {
			return "Conta de tamanho inválido"; 
		} else if (conta.getSaldo() < 0) {
			return "Saldo inválido";
		} else if (stringNulaOuVazia(conta.getNomeCorrentista()) && conta.getNomeCorrentista().length() > 60) {
			return "Nome do correntista inválido.";
		} else if (conta instanceof ContaPoupanca) {
			ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
			if (contaPoupanca.getPercentualBonus() < 0) {
				return "Percentual de bônus inválido.";
			}
		} 
		
		boolean ret = daoContaCorrente.incluir(conta);
		if (!ret) {
			return "Conta corrente existente";
		} else {
			return null;
		}
				
	}

	public String creditar(double valor, String numero) {
		if (valor < 0) {
			return "Valor inválido";
		} else if (stringNulaOuVazia(numero)) {
			return "Número da conta inválido.";
		} 

		ContaCorrente conta = daoContaCorrente.buscar(numero);
		if (conta == null) {
			return "Conta corrente não encontrada";
		} else {
			conta.creditar(valor);
			daoContaCorrente.alterar(conta);
			return null;
		}
		
	}

	public String debitar(double valor, String numero) {
		if (valor < 0) {
			return "Valor inválido";
		} else if (stringNulaOuVazia(numero)) {
			return "Número da conta corrente não informado";
		}

		ContaCorrente conta = daoContaCorrente.buscar(numero);

		if (conta == null) {
			return "Conta corrente não encontrada";
		} else if (conta.getSaldo() < valor) {
			return "Saldo insuficiente";
		} else {
			conta.debitar(valor);
			daoContaCorrente.alterar(conta);
			return null;
		}
	}

	public ContaCorrente buscar(String numero) {
		if (stringNulaOuVazia(numero)) {
			return null; 
		} else {		
			return daoContaCorrente.buscar(numero);
		}
	}

	public List<ContaCorrente> gerarRelatorioGeral() {
		ContaCorrente[] contas = daoContaCorrente.buscarTodos();
		List<ContaCorrente> listaContas = Arrays.asList(contas);
		//Collections.sort(listaContas, new ComparadorContaCorrenteSaldo());
		listaContas.sort(new ComparadorContaCorrenteSaldo());
		return listaContas;
		
	}

	private boolean stringNulaOuVazia(String numero) {
		return numero == null || numero.trim().equals("");
	}
}
