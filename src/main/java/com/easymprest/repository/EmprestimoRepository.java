package com.easymprest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;

public interface EmprestimoRepository extends CrudRepository<Operacao, Long>{

	Operacao findById(Long id);

	Operacao findByStatus(String status);

	Page<Operacao> findAll(Pageable pageable);
	
	List<Operacao> findAll();
	
	List<Operacao> findByUserOperacao(User user);
}
