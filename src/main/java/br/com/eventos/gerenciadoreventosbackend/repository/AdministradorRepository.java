package br.com.eventos.gerenciadoreventosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.eventos.gerenciadoreventosbackend.model.Administrador;
import java.util.Optional; // Adicione esta importação

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    // O Spring Data JPA entende o nome do método e cria a busca por email automaticamente
    Optional<Administrador> findByEmail(String email);

}