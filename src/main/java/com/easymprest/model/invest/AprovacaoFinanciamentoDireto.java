package com.easymprest.model.invest;

import java.math.BigDecimal;

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
@Table(name = "aprovacaoFinanciamentoDireto")
public class AprovacaoFinanciamentoDireto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aprovacao_financiamento", unique = true, nullable = false)
	private Long id;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private User financiador;
	
	@Column(name = "aprovado")
	private boolean aprovado;
	
	@Column(name = "valorTotalEmprestado")
	private BigDecimal valorTotalEmprestado;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private CartaoCredito cartaoCredito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFinanciador() {
		return financiador;
	}

	public void setFinanciador(User financiador) {
		this.financiador = financiador;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public BigDecimal getValorTotalEmprestado() {
		return valorTotalEmprestado;
	}

	public void setValorTotalEmprestado(BigDecimal valorTotalEmprestado) {
		this.valorTotalEmprestado = valorTotalEmprestado;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	
	
	
}
