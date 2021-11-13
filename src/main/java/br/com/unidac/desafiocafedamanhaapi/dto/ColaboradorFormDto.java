package br.com.unidac.desafiocafedamanhaapi.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;

import lombok.Getter;

@Getter
public class ColaboradorFormDto {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@CPF
	private Long cpf;
	
	@NotBlank
	private List<AlimentoDesjejumFormDto> alimentos;
}
