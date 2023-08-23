package br.com.alura.testeMaven.dao;

import javax.persistence.EntityManager;

import br.com.alura.testeMaven.modelo.Cliente;

public class ClienteDao {

	private EntityManager em;
	
	public ClienteDao (EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		em.persist(cliente);
	}
}
