package com.easymprest.model.invest;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.easymprest.enums.OperacaoType;
import com.easymprest.enums.StatusType;
import com.easymprest.model.User;

@Entity
@Table(name = "operacao")
public class Operacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_operacao", unique = true, nullable = false)
	private Long id;

//	@NotNull(message = "*A data é obrigatória")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dataOperacao")
	private Date dataOperacao = new Date();

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusType status;

	@Column(name = "operacaoType")
	@Enumerated(EnumType.STRING)
	private OperacaoType operacaoType;

	@OneToOne(cascade = CascadeType.DETACH)
	private User userOperacao;

	@OneToOne(cascade = CascadeType.ALL)
	private FinanciamentoDireto financiamentoDireto;

	@OneToOne(cascade = CascadeType.ALL)
	private Emprestimo emprestimo;

	@OneToOne(cascade = CascadeType.ALL)
	private Investimento investimento;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private User amigo;
	
	@Transient
	private String amigo1;
	
	@Transient
	private String amigo2;
	
	
	@Transient
	private String amigo3;
	
	@Transient
	private String amigo4;
	
	@Transient
	private String amigo5;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public OperacaoType getOperacaoType() {
		return operacaoType;
	}

	public void setOperacaoType(OperacaoType operacaoType) {
		this.operacaoType = operacaoType;
	}

	public User getUserOperacao() {
		return userOperacao;
	}

	public void setUserOperacao(User userOperacao) {
		this.userOperacao = userOperacao;
	}

	public FinanciamentoDireto getFinanciamentoDireto() {
		return financiamentoDireto;
	}

	public void setFinanciamentoDireto(FinanciamentoDireto financiamentoDireto) {
		this.financiamentoDireto = financiamentoDireto;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	public String getAmigo1() {
		return amigo1;
	}

	public void setAmigo1(String amigo1) {
		this.amigo1 = amigo1;
	}

	public String getAmigo2() {
		return amigo2;
	}

	public void setAmigo2(String amigo2) {
		this.amigo2 = amigo2;
	}

	public String getAmigo3() {
		return amigo3;
	}

	public void setAmigo3(String amigo3) {
		this.amigo3 = amigo3;
	}

	public String getAmigo4() {
		return amigo4;
	}

	public void setAmigo4(String amigo4) {
		this.amigo4 = amigo4;
	}

	public String getAmigo5() {
		return amigo5;
	}

	public void setAmigo5(String amigo5) {
		this.amigo5 = amigo5;
	}

	public User getAmigo() {
		return amigo;
	}

	public void setAmigo(User amigo) {
		this.amigo = amigo;
	}

	
	
	
}
