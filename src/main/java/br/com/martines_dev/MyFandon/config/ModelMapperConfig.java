package br.com.martines_dev.MyFandon.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.martines_dev.MyFandon.util.NullAwareBeanUtils;


@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper() ;
	}
	
	@Bean
	public NullAwareBeanUtils copy() {
		return new NullAwareBeanUtils();
	}
}
