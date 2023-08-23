package br.com.alura.testeMaven.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livros extends Produto {
	private String autor;
	private Integer numeroPaginas;
	
	public Livros (String autor, Integer numeroPaginas) {
		this.autor = autor;
		this.numeroPaginas = numeroPaginas;
	}
	
	public Livros() {
	}

	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
}
