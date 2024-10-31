package br.com.senai.CepApi.Repository;

import br.com.senai.CepApi.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository <Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.cep = :cep")
    Optional<Endereco> findByCepBuscado(String cep);

    @Query("SELECT e FROM Endereco e WHERE e.cidade = :cidade")
    List<Endereco> findByCidadeBuscada(String cidade);

    @Query("SELECT e FROM Endereco e WHERE e.uf = :uf")
    List<Endereco> findByUfBuscada(String uf);
}
