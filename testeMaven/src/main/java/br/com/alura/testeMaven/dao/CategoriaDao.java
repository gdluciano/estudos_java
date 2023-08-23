package br.com.alura.testeMaven.dao;

import javax.persistence.EntityManager;

import br.com.alura.testeMaven.modelo.Categoria;

public class CategoriaDao {
	
	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Categoria categoria) {
		em.persist(categoria);
	}

}
