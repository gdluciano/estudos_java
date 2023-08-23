package br.com.alura.testeMaven.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.testeMaven.modelo.Pedido;
import br.com.alura.testeMaven.vo.RelatorioQuantidadeProdutosVo;

public class PedidoDao {
	
	private EntityManager em;
	
	public PedidoDao (EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		em.persist(pedido);
	}
	
	public BigDecimal getValorTotalPedido() {
		String jpql = "SELECT p.valorTotal FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	public List<RelatorioQuantidadeProdutosVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.testeMaven.vo.RelatorioQuantidadeProdutosVo ("
					+ "produto.nome, "
					+ "SUM(item.quantidade), "
					+ "MAX(pedido.data)) "
					+ "FROM Pedido pedido "
					+ "JOIN pedido.itens item "
					+ "JOIN item.produto produto "
					+ "GROUP BY produto.nome "
					+ "ORDER BY SUM(item.quantidade) DESC";
		
		return em.createQuery(jpql, RelatorioQuantidadeProdutosVo.class)
				.getResultList();
	}
	
	//Quando a relação é LAZY, após em.close() não será possível acessar a entidade relacionada
	//Para corrigir, deve-se criar um método com JOIN FETCH, que fará o join
	public Pedido buscarPedidocomCliente(long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
		return em.createQuery(jpql, Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
}
