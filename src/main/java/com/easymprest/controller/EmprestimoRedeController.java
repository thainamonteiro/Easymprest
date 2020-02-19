package com.easymprest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easymprest.enums.OperacaoType;
import com.easymprest.enums.StatusType;
import com.easymprest.model.User;
import com.easymprest.model.invest.Operacao;
import com.easymprest.service.EmprestimoRedeService;
import com.easymprest.service.UserService;

@Controller
public class EmprestimoRedeController {
	
	@Autowired
	private EmprestimoRedeService emprestimoRedeService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/emprestimosRede", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		
		Operacao operacao = new Operacao();
				
		modelAndView.addObject("operacao", operacao);
		modelAndView.setViewName("emprestimosRede");
		return modelAndView;
	}

	@RequestMapping(value = "/emprestimosRede", method = RequestMethod.POST)
	public ModelAndView createProduct(@Valid Operacao operacao, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		operacao.setOperacaoType(OperacaoType.FINACIAMENTODIRETO);
		operacao.setStatus(StatusType.PENDENTE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		operacao.setUserOperacao(user);
	
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("emprestimosRede");
		} else {
		
		emprestimoRedeService.save(operacao);
		modelAndView.addObject("successMessage", "Investimento salvo om sucesso");
		modelAndView.addObject("operacao", new Operacao());
		modelAndView.setViewName("emprestimosRede");
		
		}
		return modelAndView;
	}

}
