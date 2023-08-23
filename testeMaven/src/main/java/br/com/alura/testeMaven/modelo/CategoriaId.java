package br.com.alura.testeMaven.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tamanho;
	private String nome;
	
	public CategoriaId() {
	}
	
	public CategoriaId(String tamanho, String nome) {
		this.tamanho = tamanho;
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
