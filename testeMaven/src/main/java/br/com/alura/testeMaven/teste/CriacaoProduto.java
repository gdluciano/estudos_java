package br.com.alura.testeMaven.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.testeMaven.dao.CategoriaDao;
import br.com.alura.testeMaven.dao.JPAUtil;
import br.com.alura.testeMaven.dao.ProdutoDao;
import br.com.alura.testeMaven.modelo.Categoria;
import br.com.alura.testeMaven.modelo.CategoriaId;
import br.com.alura.testeMaven.modelo.Produto;

public class CriacaoProduto {
	public static void main(String[] args) {
		cadastros();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		//Consulta em DB
		//Um produto específico. Exp: ID
		System.out.println("ID(2): " + produtoDao.buscarPorId(2).getNome());
		
		//Todos os itens de uma tabela
		List<Produto> todosProdutos = produtoDao.buscarTodos();
		todosProdutos.forEach(System.out::println);
		
	}

	private static void cadastros() {
		//Estado de entidade:
		//	TRANSIENT - entidade ainda não identificada pelo hibernate
		Categoria smartphones = new Categoria("PEQUENO", "SMARTPHONE");
		Produto celular = new Produto("Redmi Note 11", "Super bom e barato", new BigDecimal("1500"), smartphones);
		Produto celular2 = new Produto("Redmi Note 14", "O melhor", new BigDecimal("4990"), smartphones);
		
		//Para persistir a entidade (salvar classe no banco de dados) é necessário criar da seguinte forma:
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
		//EntityManager em = emf.createEntityManager();
		//em.persist(class to add on DB);
		
		//Utilizando o DAO Pattern:
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		//Estado de entidade:
		//	MANAGED - entidade que é persistida (EntityManager.persist()), está em estado de gerenciamento pelo Hibernate, qualquer alteração será registrada
		//			  estado anterior ao commit/flush e close/clear.
		categoriaDao.cadastrar(smartphones);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(celular2);
		//Estado de UPDATE da entidade - deve estar em estado MANAGED
		celular.setDescricao("Bom, bonito e barato");
		celular.setPreco(new BigDecimal("1100"));
		//Estado de entidade:
		//	REMOVED - deve estar em estado MANAGED
		//em.remove(celular);
		
		//Estado de entidade:
		//	COMMIT/FLUSH - envio dos dados ao DB
		em.flush();
		em.getTransaction().commit();
		//Estado de entidade:
		//	DETACHED - após manipulações, entidade desanexada, não poderá mais ser manipulada
		em.clear();
		em.getTransaction().begin();
		// Retorna o estado da entidade de DETACHED para MANAGED, com o .merge (MERGED)
		smartphones = em.merge(smartphones);
		smartphones.setNome("Celular");
		
		em.getTransaction().commit();
		em.close();
	}
}
