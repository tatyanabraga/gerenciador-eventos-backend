package br.com.eventos.gerenciadoreventosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.eventos.gerenciadoreventosbackend.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    // Agora o método retorna a interface UserDetails
    UserDetails findByEmail(String email);
}