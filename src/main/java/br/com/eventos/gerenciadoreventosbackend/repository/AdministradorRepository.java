package br.com.eventos.gerenciadoreventosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.eventos.gerenciadoreventosbackend.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

}