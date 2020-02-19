package com.easymprest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;
import com.easymprest.repository.InvestimentoRepository;

@Service
public class InvestimentoServiceImpl implements InvestimentoService{

	@Autowired
	private InvestimentoRepository investimentoRepository;

	@Override
	public Operacao findById(Long id) {

		return investimentoRepository.findById(id);

	}

	@Override
	public Operacao findByStatusType(String status) {

		return investimentoRepository.findByStatus(status);

	}

	@Override
	public Page<Operacao> findAll(Pageable pageable) {

		Page<Operacao> productsPageInfo = investimentoRepository.findAll(pageable);
		List<Operacao> operacoes = productsPageInfo.getContent();

		return new PageImpl<Operacao>(operacoes, pageable, productsPageInfo.getTotalElements());

	}

	@Override
	public void save(Operacao operacao) {
		
		investimentoRepository.save(operacao);
		
	}

	@Override
	public List<Operacao> findAll() {
		
		return investimentoRepository.findAll();
	}

	@Override
	public Operacao findByAmigo(User user) {
		return investimentoRepository.findByAmigo(user);
	}




}
