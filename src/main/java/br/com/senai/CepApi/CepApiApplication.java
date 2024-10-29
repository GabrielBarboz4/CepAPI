package br.com.senai.CepApi;

import br.com.senai.CepApi.Main.ExibeMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepApiApplication.class, args);
		ExibeMenu exibeMenu = new ExibeMenu();
		exibeMenu.rodarAplicacao();
	}
}
