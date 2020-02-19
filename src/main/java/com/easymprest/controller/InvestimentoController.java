package com.easymprest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easymprest.enums.OperacaoType;
import com.easymprest.enums.StatusType;
import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;
import com.easymprest.service.InvestimentoService;
import com.easymprest.service.UserService;

@Controller
public class InvestimentoController {
	
	@Autowired
	private InvestimentoService investimentoService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/investimentos", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		
		Operacao operacao = new Operacao();
				
		modelAndView.addObject("operacao", operacao);
		modelAndView.setViewName("investimento");
		return modelAndView;
	}

	@RequestMapping(value = "/investimentos", method = RequestMethod.POST)
	public ModelAndView createProduct(@Valid Operacao operacao, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		operacao.setOperacaoType(OperacaoType.INVESTIMENTO);
		operacao.setStatus(StatusType.PENDENTE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		operacao.setUserOperacao(user);
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("investimento");
		} else {
		
		investimentoService.save(operacao);
		modelAndView.addObject("successMessage", "Investimento salvo om sucesso");
		modelAndView.addObject("operacao", new Operacao());
		modelAndView.setViewName("investimento");
		
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/aprovarEmprestimo", method = RequestMethod.POST)
	public ModelAndView aprovarInvestimento(@RequestParam(name="idOperacao") int idOperacao) {
		
		Operacao operacaoInvestimento = investimentoService.findById((long)idOperacao);
		operacaoInvestimento.setStatus(StatusType.ACEITA);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Operacao operacaoEmrpestimo = investimentoService.findByAmigo(user);
		operacaoEmrpestimo.setStatus(StatusType.ACEITA);
		
		investimentoService.save(operacaoInvestimento);
		investimentoService.save(operacaoEmrpestimo);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("successMessage", "Investimento salvo om sucesso");
		modelAndView.setViewName("minhas-operacoes");
		
		return new ModelAndView("redirect:/minhasOperacoes");
	}

}
