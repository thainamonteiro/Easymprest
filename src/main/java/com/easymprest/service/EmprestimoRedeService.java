package com.easymprest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easymprest.model.invest.Operacao;

public interface EmprestimoRedeService {

	public Operacao findById(Long id);

	public Operacao findByStatusType(String statusType);

	public Page<Operacao> findAll(Pageable pageable);
	
	public void save(Operacao product);
	
	public List<Operacao> findAll();

}
