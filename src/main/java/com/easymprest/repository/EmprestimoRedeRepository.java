package com.easymprest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.easymprest.model.invest.Operacao;

public interface EmprestimoRedeRepository extends CrudRepository<Operacao, Long>{

	Operacao findById(Long id);

	Operacao findByStatus(String status);

	Page<Operacao> findAll(Pageable pageable);
	
	List<Operacao> findAll();
	
}
