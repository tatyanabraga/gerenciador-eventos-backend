package br.com.eventos.gerenciadoreventosbackend.repository;

import br.com.eventos.gerenciadoreventosbackend.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Adicione esta importação

public interface EventoRepository extends JpaRepository<Evento, Long> {

    // O Spring Data JPA entende o nome do método e cria a busca por 'adminId' automaticamente
    List<Evento> findAllByAdminId(Long adminId);
}