package com.easymprest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easymprest.enums.OperacaoType;
import com.easymprest.enums.StatusType;
import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;
import com.easymprest.repository.EmprestimoRepository;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimo;

	@Override
	public Operacao findById(Long id) {

		return emprestimo.findById(id);

	}

	@Override
	public Operacao findByStatusType(String status) {

		return emprestimo.findByStatus(status);

	}

	@Override
	public Page<Operacao> findAll(Pageable pageable) {

		Page<Operacao> productsPageInfo = emprestimo.findAll(pageable);
		List<Operacao> operacoes = productsPageInfo.getContent();

		return new PageImpl<Operacao>(operacoes, pageable, productsPageInfo.getTotalElements());

	}

	@Override
	public void save(Operacao operacao) {

		emprestimo.save(operacao);

	}

	@Override
	public List<Operacao> findAll() {

		return emprestimo.findAll();
	}

	@Override
	public List<Operacao> filtrarOperacoesDeInvestimento(User usuarioAtual) {

		return this.findAll().stream().filter(operacao -> operacao.getOperacaoType().equals(OperacaoType.INVESTIMENTO)
				&& !operacao.getUserOperacao().equals(usuarioAtual)
				&& operacao.getStatus().equals(StatusType.PENDENTE)).collect(Collectors.toList());
	}

	@Override
	public void update(Operacao operacao) {
		
		emprestimo.save(operacao);
	}

	@Override
	public List<Operacao> findByUserId(User user) {

		return emprestimo.findByUserOperacao(user);
	}

}
