package br.com.unidac.desafiocafedamanhaapi.controller;

import java.net.URI;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumFormDtoAtualizar;
import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumFormDtoCpf;
import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumOutputDto;
import br.com.unidac.desafiocafedamanhaapi.service.AlimentoDesjejumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/alimentos")
@Api(tags = "Alimentos Desjejum")
public class AlimentoDesjejumController {

	@Autowired
	private AlimentoDesjejumService service;
	
	@GetMapping
	@ApiOperation("Lista com todos Alimentos já registrados")
	public Page<AlimentoDesjejumOutputDto> listar(Pageable paginacao){
		return service.listar(paginacao);
	}
	
	@PostMapping
	@ApiOperation("Cadastro de novos alimentos que podem ser trazidos pelos colaboradores. Não permite inserir alimentos já registrados previamente")
	public ResponseEntity<AlimentoDesjejumOutputDto> cadastrar(@RequestBody @Valid AlimentoDesjejumFormDtoCpf alimentoForm, UriComponentsBuilder uriBuilder){
		AlimentoDesjejumOutputDto alimentoDto = service.cadastrar(alimentoForm);
		
		URI uri = uriBuilder.path("/alimentos/{id}").buildAndExpand(alimentoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(alimentoDto);
	}
	
	@PutMapping
	@ApiOperation("Atualização de algum alimento já inserido antes. Precisa do CPF do colaborador para a atualização, e não pode estar na lista de já informados")
	public ResponseEntity<AlimentoDesjejumOutputDto> atualizar(@RequestBody @Valid AlimentoDesjejumFormDtoAtualizar alimentoFormDto) {
		AlimentoDesjejumOutputDto alimentoOutputDot = service.atualizar(alimentoFormDto);
		
		return ResponseEntity.ok(alimentoOutputDot);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Remoção de Alimento da lista, precisa do CPF do colaborador para isso, e ele precisa estar registrado a aquele colaborador")
	public ResponseEntity<AlimentoDesjejumOutputDto> remover(@PathVariable @NotNull Long id, @NotBlank @CPF String cpf){
		service.remover(id, cpf);
		
		return ResponseEntity.noContent().build();
	}
	
}
