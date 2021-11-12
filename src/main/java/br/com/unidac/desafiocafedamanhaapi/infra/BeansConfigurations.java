package br.com.unidac.desafiocafedamanhaapi.infra;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfigurations {

	@Bean
	public ModelMapper getModel() {
		return new ModelMapper();
	}
}
