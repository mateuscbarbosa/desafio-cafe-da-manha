package br.com.unidac.desafiocafedamanhaapi.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorFormDto;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorFormDtoAtualizar;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorOutputDto;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorOutputDtoDetalhado;
import br.com.unidac.desafiocafedamanhaapi.service.ColaboradorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/colaboradores")
@Api(tags = "Colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService service;
	
	@GetMapping
	@ApiOperation("Lista com todos os Colaboradores e o que irão trazer")
	public Page<ColaboradorOutputDto> listar(Pageable paginacao){
		return service.listar(paginacao);
	}
	
	@PostMapping
	@ApiOperation("Cadastro de Colaborador e o que irá trazer sendo inserido automanticamente")
	public ResponseEntity<ColaboradorOutputDto> cadastrar(@RequestBody @Valid ColaboradorFormDto colaboradorForm, UriComponentsBuilder uriBuilder){
		ColaboradorOutputDto colaboradorOutputDto = service.cadastrar(colaboradorForm);
		
		URI uri = uriBuilder.path("/colaboradores/{id}").buildAndExpand(colaboradorOutputDto.getId()).toUri();
		return ResponseEntity.created(uri).body(colaboradorOutputDto);
	}
	
	@PutMapping
	@ApiOperation("Atualização dos dados do Colaborador")
	public ResponseEntity<ColaboradorOutputDto> atualizar(@RequestBody @Valid ColaboradorFormDtoAtualizar colaboradorForm){
		ColaboradorOutputDto colaboradorOutputDto = service.atualizar(colaboradorForm);
		
		return ResponseEntity.ok(colaboradorOutputDto);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Remoção do Colaborador escolhido e todos os alimentos que iria trazer")
	public ResponseEntity<ColaboradorOutputDto> remover(@PathVariable @NotNull Long id){
		service.remover(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Detalhamento do Colaborador escolhido")
	public ResponseEntity<ColaboradorOutputDtoDetalhado> detalher(@PathVariable @NotNull Long id) {
		ColaboradorOutputDtoDetalhado dtoDetalhado = service.detalhar(id);
		
		return ResponseEntity.ok(dtoDetalhado);
	}
}
