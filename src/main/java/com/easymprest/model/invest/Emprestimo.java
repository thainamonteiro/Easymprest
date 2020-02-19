package com.easymprest.model.invest;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_emprestimo", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "dataLimite")
	private Date dataLimite;
	
	@Column(name = "motivo")
	private String motivo;
	
	@Column(name = "valorTotalSolicitado")
	private BigDecimal valorTotalSolicitado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public BigDecimal getValorTotalSolicitado() {
		return valorTotalSolicitado;
	}

	public void setValorTotalSolicitado(BigDecimal valorTotalSolicitado) {
		this.valorTotalSolicitado = valorTotalSolicitado;
	}	
	


}
