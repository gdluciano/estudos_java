package br.com.alura.testeMaven.modelo;

import javax.persistence.Embeddable;

//Não é uma entidade
//Deve ser um class embutível, onde for implementada, deve ser embutida com @Embedded
@Embeddable
public class DadosPf {
	
	private String nome;
	private String cpf;
	
	public DadosPf(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public DadosPf() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
