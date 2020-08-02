package br.com.martines_dev.MyFandon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyFandonApplication {

	public static void main(String[] args) {

		

		SpringApplication.run(MyFandonApplication.class, args);

	}
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	  return application.sources(MyFandonApplication.class);
//	 }
	 
	@Bean
	public static void Default() {
		
		
		System.out.println( "Hello My Little Friend! ");
	}

}
