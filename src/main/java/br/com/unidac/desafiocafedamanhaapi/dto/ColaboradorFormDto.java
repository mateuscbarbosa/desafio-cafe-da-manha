package br.com.unidac.desafiocafedamanhaapi.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;

import lombok.Getter;

@Getter
public class ColaboradorFormDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@CPF
	@Pattern(regexp = "[0-9]{3}[0-9]{3}[0-9]{3}[0-9]{2}")
	private String cpf;
	
	@NotNull
	private List<AlimentoDesjejumFormDto> alimentos;
}
