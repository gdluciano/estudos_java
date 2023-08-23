package br.com.alura.testeMaven.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//Deve-se incluir a class no "persistence.xml" na tag <class>br.com.alura.testeMaven.modelo.Produto</class>
//Porem o Hibernate faz autom�tico
//Se incluir uma, dever� incluir todas. Se n�o incluir, o hibernate faz.
@Entity
@Table(name = "produtos")
//Classes que herdam de Produto, ser�o geradas em suas pr�prias tabelas (JOINED) ou todas em uma �nica (SINGLE-TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private int id;
		private String nome;
		private String descricao;
		private BigDecimal preco;
		private LocalDate dataCadastro = LocalDate.now();
		
		// Em casos de ENUMs, caso n�o for informado o @Enumerated, ser� salvo apenas o n�mero da ordem da constante informada (ORDINAL)
		// STRING imprime o nome da constante do Enum.
		//@Enumerated(EnumType.STRING)
		
		@ManyToOne //Cardinalidade da rela��o
		private Categoria categoria;
		
		public Produto() {
		}
		
		public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
			this.nome = nome;
			this.descricao = descricao;
			this.preco = preco;
			this.categoria = categoria;
		}
		
		public String getNome() {
			return this.nome;
		}
		
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}

		public LocalDate getDataCadastro() {
			return dataCadastro;
		}

		public void setDataCadastro(LocalDate dataCadastro) {
			this.dataCadastro = dataCadastro;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		
		public BigDecimal getPreco() {
			return this.preco;
		}
		
		@Override
		public String toString() {
			return "Nome: " + this.getNome() + "; Pre�o: " + this.getPreco();
		}
		
}
