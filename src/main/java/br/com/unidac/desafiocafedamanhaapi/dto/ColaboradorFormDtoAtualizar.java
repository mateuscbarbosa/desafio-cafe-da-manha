package br.com.unidac.desafiocafedamanhaapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorFormDtoAtualizar{

	@NotNull
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@CPF
	@Pattern(regexp = "[0-9]{11}")
	private String cpf;
}
