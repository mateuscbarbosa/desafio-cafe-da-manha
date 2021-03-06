package br.com.unidac.desafiocafedamanhaapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro500OutputDto {

	private LocalDateTime timestamp;
	private String mensagem;
	private String path;
}
