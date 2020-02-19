package com.easymprest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;

public interface EmprestimoService {

	public Operacao findById(Long id);

	public Operacao findByStatusType(String statusType);

	public Page<Operacao> findAll(Pageable pageable);
	
	public void save(Operacao product);
	
	public List<Operacao> findAll();

	List<Operacao> filtrarOperacoesDeInvestimento(User user);

	void update(Operacao operacao);
	
	List<Operacao> findByUserId(User user);
	
}
