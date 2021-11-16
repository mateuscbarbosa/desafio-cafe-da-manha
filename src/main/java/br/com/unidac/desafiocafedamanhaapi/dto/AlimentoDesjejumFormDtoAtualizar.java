package br.com.unidac.desafiocafedamanhaapi.dto;

import javax.validation.constraints.NotNull;

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
}
