package br.com.unidac.desafiocafedamanhaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumOutputDto;
import br.com.unidac.desafiocafedamanhaapi.service.AlimentoDesjejumService;

@RestController
@RequestMapping("/alimentos")
public class AlimentoDesjejumController {

	@Autowired
	private AlimentoDesjejumService service;
	
	@GetMapping
	public Page<AlimentoDesjejumOutputDto> listar(Pageable paginacao){
		return service.listar(paginacao);
	}
}
