package br.com.alura.testeMaven.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RelatorioQuantidadeProdutosVo {
	
	private String nomeProduto;
	private Long QuantidadeProdutosVendidos;
	private LocalDate ultimaVenda;
	
	public RelatorioQuantidadeProdutosVo (String nomeProduto, Long QuantidadeProdutosVendidos, LocalDate ultimaVenda) {
		this.nomeProduto = nomeProduto;
		this.QuantidadeProdutosVendidos = QuantidadeProdutosVendidos;
		this.ultimaVenda = ultimaVenda;
	}
	
	
	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeProdutosVendidos() {
		return QuantidadeProdutosVendidos;
	}

	public LocalDate getUltimaVenda() {
		return ultimaVenda;
	}

	@Override
	public String toString() {
		return "[Produto: "+ this.getNomeProduto() + 
				" - Quantidade de Produtos Vendidos: " + this.getQuantidadeProdutosVendidos() + 
				" - Ultima Venda: " + this.getUltimaVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "]";
	}

}
