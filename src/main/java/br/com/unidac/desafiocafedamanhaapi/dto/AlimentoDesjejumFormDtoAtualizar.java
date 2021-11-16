package br.com.unidac.desafiocafedamanhaapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlimentoDesjejumFormDtoAtualizar extends AlimentoDesjejumFormDto{

	@NotNull
	private Long id;
	
	@NotBlank
	@CPF
	@Pattern(regexp = "[0-9]{11}")
	@JsonAlias(value = "cpf")
	private String colaboradorCpf;
}
