package com.easymprest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easymprest.enums.OperacaoType;
import com.easymprest.enums.StatusType;
import com.easymprest.model.User;
import com.easymprest.model.invest.AprovacaoFinanciamentoDireto;
import com.easymprest.model.invest.Emprestimo;
import com.easymprest.model.invest.Investimento;
import com.easymprest.model.invest.Operacao;
import com.easymprest.repository.EmprestimoRedeRepository;
import com.easymprest.repository.InvestimentoRepository;

@Service
public class EmprestimoRedeServiceImpl implements EmprestimoRedeService{

	@Autowired
	private EmprestimoRedeRepository emprestimoRedeRepository;
	
	@Autowired
	private InvestimentoRepository investimentoRedeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmprestimoService emprestimoService;

	@Override
	public Operacao findById(Long id) {

		return emprestimoRedeRepository.findById(id);

	}

	@Override
	public Operacao findByStatusType(String status) {

		return emprestimoRedeRepository.findByStatus(status);

	}

	@Override
	public Page<Operacao> findAll(Pageable pageable) {

		Page<Operacao> productsPageInfo = emprestimoRedeRepository.findAll(pageable);
		List<Operacao> operacoes = productsPageInfo.getContent();

		return new PageImpl<Operacao>(operacoes, pageable, productsPageInfo.getTotalElements());

	}

	@Override
	public void save(Operacao operacao) {
		
		//Organiza amigos
		String [] amigosArray = new String[] {operacao.getAmigo1(),operacao.getAmigo2(),operacao.getAmigo3(),operacao.getAmigo4(),operacao.getAmigo5()};
		List<User> amigos = new ArrayList<User>();
		
		for (String amigo : amigosArray) {
			
			if (amigo != null && !"".equals(amigo)) {
				User financiador = userService.findUserByEmail(amigo);
				if (financiador!=null) {
					amigos.add(financiador);
				}else {
					
					User user = new User();
					user.setName("usuario provisório");
					user.setEmail(amigo);
					user.setPassword("usuario provisório");
					user.setRegistration("usuario provisório");
					user.setRole("USER");
					
					userService.saveUserProvisorio(user);
					amigos.add(user);
				}
			}
			
		}
		

		//Divide valor
		int divisao = amigos.size();
		BigDecimal valorDividido = operacao.getFinanciamentoDireto().getValorTotalSolicitado().divide(new BigDecimal(divisao));

		for (User financiador : amigos) {
			
			AprovacaoFinanciamentoDireto aprovacaoFinanciamentoDireto = new AprovacaoFinanciamentoDireto();
			
			aprovacaoFinanciamentoDireto.setFinanciador(financiador);
			aprovacaoFinanciamentoDireto.setValorTotalEmprestado(valorDividido);
			
			operacao.getFinanciamentoDireto().getAprovacaoFinanciamentos().add(aprovacaoFinanciamentoDireto);
			
		}
				
		
		//1 Investimento para cada amigo
		for(User amigo : amigos) {
			
			Investimento investimento = new Investimento();
			investimento.setMotivo(operacao.getFinanciamentoDireto() != null ? operacao.getFinanciamentoDireto().getMotivo() : "");
			investimento.setValorInvestido(valorDividido);
			
			Operacao operacaoTmp = new Operacao();
			operacaoTmp.setStatus(StatusType.AGUARDANDOAPROVACAO);
			operacaoTmp.setOperacaoType(OperacaoType.INVESTIMENTO);
			operacaoTmp.setInvestimento(investimento);
			operacaoTmp.setUserOperacao(amigo);
			
			Operacao investimentoSalvo = investimentoRedeRepository.save(operacaoTmp);
			
			//n Emprestimos para o usuario de cada amigo
			
			User usuario = operacao.getUserOperacao();
			
			// crio um emprestimo
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setMotivo(operacao.getFinanciamentoDireto().getMotivo());
			emprestimo.setValorTotalSolicitado(valorDividido);

			// crio uma nova operação so que do tipo emprestimo
			Operacao operacaoEmprestimo = new Operacao();
			operacaoEmprestimo.setOperacaoType(OperacaoType.EMPRESTIMO);
			operacaoEmprestimo.setUserOperacao(usuario);
			operacaoEmprestimo.setStatus(StatusType.AGUARDANDOAPROVACAO);
			operacaoEmprestimo.setEmprestimo(emprestimo);
			operacaoEmprestimo.setAmigo(amigo);

			emprestimoService.save(operacaoEmprestimo);
			
		}
		
		//emprestimoRedeRepository.save(operacao);
		
	}

	@Override
	public List<Operacao> findAll() {
		
		return emprestimoRedeRepository.findAll();
	}




}
