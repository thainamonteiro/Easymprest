package com.easymprest.model.invest;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.easymprest.model.User;

@Entity
@Table(name = "cartaoCredito")
public class CartaoCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cartao_credito", unique = true, nullable = false)
	private Long id;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private User financiador;
	
	@Column(name = "aprovado")
	private String numeroCartao;

	public Long getId() {
		return id;
	}

	public User getFinanciador() {
		return financiador;
	}

	public void setFinanciador(User financiador) {
		this.financiador = financiador;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
}
