package br.com.unidac.desafiocafedamanhaapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlimentoDesjejumFormDto {
	
	@NotBlank
	private String nome;
	
	
	@NotBlank
	@CPF
	@Pattern(regexp = "[0-9]{11}")
	@JsonAlias(value = "cpf")
	private String colaboradorCpf;
}
