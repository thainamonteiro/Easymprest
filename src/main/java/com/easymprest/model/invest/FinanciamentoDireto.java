package com.easymprest.model.invest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;

import com.easymprest.enums.StatusFinanciamentoDiretoType;

@Entity
@Table(name = "financiamentoDireto")
public class FinanciamentoDireto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_financiamento_direto", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "dataLimite")
	private Date dataLimite;
	
	@Column(name = "motivo")
	private String motivo;
	
	@NumberFormat(pattern="#0.00")
	@Column(name = "valorTotalSolicitado")
	private BigDecimal valorTotalSolicitado;	
	
	/**
	 * O status só muda para aprovado se todos os envolvidos aprovarem o valor solicitado
	 * Se atingir a data limite sem aprovação de todos o status muda para reprovado
	 */
	@Column(name = "statusFinanciamentoDiretoType")
	@Enumerated(EnumType.STRING)
	private StatusFinanciamentoDiretoType statusFinanciamentoDiretoType;
	
	/**
	 * Se operacão do tipo financiamento direto havera necessidade de aprovação
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<AprovacaoFinanciamentoDireto> aprovacaoFinanciamentos = new ArrayList<AprovacaoFinanciamentoDireto>();

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

	public StatusFinanciamentoDiretoType getStatusFinanciamentoDiretoType() {
		return statusFinanciamentoDiretoType;
	}

	public void setStatusFinanciamentoDiretoType(StatusFinanciamentoDiretoType statusFinanciamentoDiretoType) {
		this.statusFinanciamentoDiretoType = statusFinanciamentoDiretoType;
	}

	public List<AprovacaoFinanciamentoDireto> getAprovacaoFinanciamentos() {
		return aprovacaoFinanciamentos;
	}

	public void setAprovacaoFinanciamentos(List<AprovacaoFinanciamentoDireto> aprovacaoFinanciamentos) {
		this.aprovacaoFinanciamentos = aprovacaoFinanciamentos;
	}

}
