package br.com.alura.testeMaven.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	//Para criar entidades com PrimaryKeys compostas, se cria uma classe com os atributos PK
	@EmbeddedId
	private CategoriaId id;

	public Categoria() {
	}
	
	public Categoria(String tamanho, String nome) {
		this.id = new CategoriaId(tamanho, nome);
	}
	
	public void setNome(String nome) {
		id.setNome(nome);
	}
	
	
	
}
