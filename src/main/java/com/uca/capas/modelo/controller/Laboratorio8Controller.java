package com.uca.capas.modelo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.modelo.domain.Cliente;
import com.uca.capas.modelo.service.ClienteService;

@Controller
public class Laboratorio8Controller {

	@Autowired
	private ClienteService clienteService;

	// MENU PRINCIPAL LABORATORIO 8
	@RequestMapping("/indexLaboratorio8")
	public ModelAndView indexLaboratorio8() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Laboratorio/indexLaboratorio8");
		return mav;
	}

	// INSERTAR CLIENTE
	@RequestMapping("/insertcliente")
	public ModelAndView nuevoCliente() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cliente", new Cliente());
		mav.setViewName("Laboratorio/agregarCliente");
		return mav;
	}

	@RequestMapping(value = "/savecliente", method = RequestMethod.POST)
	public ModelAndView saveCliente(@Valid @ModelAttribute("cliente") Cliente c, BindingResult r) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cliente", new Cliente());
		if (r.hasErrors()) {
			mav.addObject("resultado", 0);
			mav.setViewName("Laboratorio/agregarCliente");
		} else {
			Integer key = null;
			if (c.getCcliente() == null) {
				mav.addObject("resultado", 1);
				key = clienteService.insertClienteAutoId(c);
			} else {
				mav.addObject("resultado", 1);
				clienteService.updateCliente(c);
			}
			mav.addObject("resultado", key);
			mav.setViewName("Laboratorio/indexLaboratorio8");
		}
		return mav;
	}

	// PROCEDIMIENTO ALMACENDADO
	@RequestMapping("/procAlmacenadoJdbc")
	public ModelAndView procAlmacenadoJdbc() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("usuario", new Cliente());
		mav.setViewName("Laboratorio/procedimiento");
		return mav;
	}
	
	
}
