package br.com.unidac.desafiocafedamanhaapi.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class ColaboradorOutputDto {

	private Long id;
	private String nome;
	private List<AlimentoDesjejumOutputDto> alimentos;
}
