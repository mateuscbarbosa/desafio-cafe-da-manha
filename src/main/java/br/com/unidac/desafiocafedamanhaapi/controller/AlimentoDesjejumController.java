package br.com.unidac.desafiocafedamanhaapi.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumFormDtoAtualizar;
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
	
	@PutMapping
	public ResponseEntity<AlimentoDesjejumOutputDto> atualizar(@RequestBody @Valid AlimentoDesjejumFormDtoAtualizar alimentoFormDto) {
		AlimentoDesjejumOutputDto alimentoOutputDot = service.atualizar(alimentoFormDto);
		
		return ResponseEntity.ok(alimentoOutputDot);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AlimentoDesjejumOutputDto> remover(@PathVariable @NotNull Long id, @NotBlank @CPF String cpf){
		service.remover(id, cpf);
		
		return ResponseEntity.noContent().build();
	}
	
}
