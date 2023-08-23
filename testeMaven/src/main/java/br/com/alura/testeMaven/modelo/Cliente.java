package br.com.alura.testeMaven.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Não é entidade, deve ser embutida com @Embedded
	@Embedded
	private DadosPf dadosPf;
	
	public Cliente(String nome, String cpf) {
		this.dadosPf = new DadosPf(nome, cpf);
	}

	public Cliente() {
	}



}
