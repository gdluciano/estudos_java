package br.com.alura.testeMaven.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.testeMaven.dao.CategoriaDao;
import br.com.alura.testeMaven.dao.ClienteDao;
import br.com.alura.testeMaven.dao.JPAUtil;
import br.com.alura.testeMaven.dao.PedidoDao;
import br.com.alura.testeMaven.dao.ProdutoDao;
import br.com.alura.testeMaven.modelo.Categoria;
import br.com.alura.testeMaven.modelo.Cliente;
import br.com.alura.testeMaven.modelo.ItemPedido;
import br.com.alura.testeMaven.modelo.Pedido;
import br.com.alura.testeMaven.modelo.Produto;
import br.com.alura.testeMaven.vo.RelatorioQuantidadeProdutosVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		cadastrosProdutos();
		
		Cliente cliente = new Cliente("Gabriel", "08184673981");
		Pedido pedido = new Pedido(cliente);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorNome("The Frame");
		
		pedido.adicionaItem(new ItemPedido(50, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();;
		
		BigDecimal valorTotalPedido = pedidoDao.getValorTotalPedido();
		System.out.println(valorTotalPedido);
		
		List<RelatorioQuantidadeProdutosVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);

	}
	
	private static void cadastrosProdutos() {
		Categoria categoria = new Categoria("GRANDE", "TELEVISÃO");
		Produto produto = new Produto("The Frame", "Tv fina que parece um quadro", new BigDecimal(2999.99), categoria);
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(produto);
		
		em.getTransaction().commit();
		em.close();
		
	}

}
