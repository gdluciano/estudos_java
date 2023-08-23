package br.com.alura.testeMaven.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.testeMaven.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		em.persist(produto);
	}
	
	public Produto buscarPorId(int id) {
		return em.find(Produto.class, id);
	}
	
	public Produto buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		//devolve resultado único da query 
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	public List<Produto> buscarTodos(){
		//Tipo de SQL por objetos
		//				* = objeto	 objeto (alias)
		String jpql = "SELECT p FROM Produto p";
		//		  APENAS cria query				  CRIA A LIST
		return em.createQuery(jpql,Produto.class).getResultList();
	}
	
	public BigDecimal buscarPrecoPeloNome(String nome){
		//Tipo de SQL por objetos
		//			     objeto.parametro  objeto (alias)			: = uma variavel
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		//devolve resultado único da query 
		return em.createQuery(jpql,BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscarTodosPeloNomeCategoria(String nome){
		//Tipo de SQL por objetos
		//				* = objeto	 objeto (alias)	obj.parametro.parametro = para acessar tabelas com relacionamentos
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		//		  APENAS cria query				  CRIA A LIST
		return em.createQuery(jpql,Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	//Busca com parâmetros dinâmicos com Criteria API
	//
	public List<Produto> buscaComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery();
		Root select = query.from(Produto.class);
		Predicate filtro = builder.and();
		
		if(nome != null && nome.trim().isEmpty()) {
			filtro = builder.and(filtro, builder.equal(select.get("nome"), nome));
		}
		if(preco != null) {
			filtro = builder.and(filtro, builder.equal(select.get("preco"), preco));
		}
		if(dataCadastro != null) {
			filtro = builder.and(filtro, builder.equal(select.get("dataCadastro"), dataCadastro));
		}
		CriteriaQuery search = query.where(filtro);
		return em.createQuery(search).getResultList();
		
		
	}

}
