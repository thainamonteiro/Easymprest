package com.easymprest.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;
import com.easymprest.service.EmprestimoService;
import com.easymprest.service.UserService;

@Controller
public class MinhasOperacoesController {

	@Autowired
	private EmprestimoService emprestimoService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/minhasOperacoes", method = RequestMethod.GET)
	public ModelAndView registration() {

		// pego o usuario logado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		List<Operacao> listaOperacoes = emprestimoService.findByUserId(user);

		Optional<BigDecimal> optinalValorTotalInvestimento = listaOperacoes	.stream()
						.map( e -> e.getInvestimento() != null ? e.getInvestimento().getValorInvestido() : BigDecimal.ZERO)
						.reduce((subtotal, proximo) -> 	subtotal.add(proximo));
		
		Optional<BigDecimal> optinalValorTotalEmprestimos = listaOperacoes	.stream()
				.map( e -> e.getEmprestimo() != null ? e.getEmprestimo().getValorTotalSolicitado() : BigDecimal.ZERO)
				.reduce((subtotal, proximo) -> 	subtotal.add(proximo));
		
		BigDecimal valorTotalInvestimento = optinalValorTotalInvestimento.orElse(BigDecimal.ZERO);
		BigDecimal valorTotalEmprestimo = optinalValorTotalEmprestimos.orElse(BigDecimal.ZERO);
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("operacoes", listaOperacoes);
		modelAndView.addObject("valorCredito", valorTotalInvestimento);
		modelAndView.addObject("valorDebito", valorTotalEmprestimo);

		modelAndView.setViewName("minhas-operacoes");
		return modelAndView;
	}

}
