package br.com.senai.CepApi;

import br.com.senai.CepApi.Main.ExibeMenu;
import br.com.senai.CepApi.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CepApiApplication implements CommandLineRunner {
	@Autowired
	private EnderecoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CepApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ExibeMenu exibeMenu = new ExibeMenu(repository);
		exibeMenu.rodarAplicacao();
	}
}
