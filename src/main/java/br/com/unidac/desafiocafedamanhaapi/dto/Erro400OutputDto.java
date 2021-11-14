package br.com.unidac.desafiocafedamanhaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro400OutputDto {

	private String campo;
	private String mensagem;
}
