package br.com.unidac.desafiocafedamanhaapi.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlimentoDesjejumFormDto {
	
	@NotBlank
	private String nome;

}
