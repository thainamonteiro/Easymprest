package com.easymprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easymprest.enums.OperacaoType;
import com.easymprest.enums.StatusType;
import com.easymprest.model.User;
import com.easymprest.model.invest.Emprestimo;
import com.easymprest.model.invest.Operacao;
import com.easymprest.service.EmprestimoService;
import com.easymprest.service.UserService;

@Controller
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/emprestimos", method = RequestMethod.GET)
	public ModelAndView registration() {

		User user = getUsuarioAtual();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("operacoes", emprestimoService.filtrarOperacoesDeInvestimento(user));
		modelAndView.setViewName("emprestimos");
		return modelAndView;
	}

	@RequestMapping(value = "/emprestimos", method = RequestMethod.POST)
	public ModelAndView associaEmprestimos(@RequestParam(name = "idEmprestimo") int idEmprestimo,
			@RequestParam(name = "idUsuario") int idUsuario) {

		ModelAndView modelAndView = new ModelAndView();

		// carrego a investimento selecionado e mudo o status dele para aceita
		Operacao operacaoInvestimento = emprestimoService.findById((long) idEmprestimo);
		operacaoInvestimento.setStatus(StatusType.ACEITA);

		User user = getUsuarioAtual();

		// crio um emprestimo
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setMotivo(operacaoInvestimento.getInvestimento().getMotivo());
		emprestimo.setValorTotalSolicitado(operacaoInvestimento.getInvestimento().getValorInvestido());

		// crio uma nova operação so que do tipo emprestimo
		Operacao operacaoEmprestimo = new Operacao();
		operacaoEmprestimo.setOperacaoType(OperacaoType.EMPRESTIMO);
		operacaoEmprestimo.setUserOperacao(user);
		operacaoEmprestimo.setStatus(StatusType.EMANALISE);
		operacaoEmprestimo.setEmprestimo(emprestimo);

		emprestimoService.save(operacaoEmprestimo);
		emprestimoService.update(operacaoInvestimento);

		modelAndView.addObject("successMessage", "Emprestimo cadastrado com sucesso");
		modelAndView.addObject("operacoes", emprestimoService.filtrarOperacoesDeInvestimento(user));
		modelAndView.setViewName("emprestimos");

		return modelAndView;
	}

	private User getUsuarioAtual() {
		// pego o usuario logado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findUserByEmail(auth.getName());
	}

}
