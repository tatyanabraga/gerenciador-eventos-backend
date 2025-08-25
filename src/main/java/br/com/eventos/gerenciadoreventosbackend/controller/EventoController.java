package br.com.eventos.gerenciadoreventosbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eventos.gerenciadoreventosbackend.model.Evento;
import br.com.eventos.gerenciadoreventosbackend.repository.EventoRepository;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/eventos") // Todos os endpoints aqui começarão com /eventos
public class EventoController {

    @Autowired
    private EventoRepository repository;

   
    @PostMapping
    public Evento cadastrar(@RequestBody Evento evento) {
        return repository.save(evento);
    }

    @GetMapping
    public List<Evento> listarEventosPorAdmin(@RequestParam Long adminId) {
    return repository.findAllByAdminId(adminId);
}
   @PutMapping("/{id}")
   public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento detalhesEvento) {
    Optional<Evento> eventoOptional = repository.findById(id);

    if (eventoOptional.isPresent()) {
        Evento eventoExistente = eventoOptional.get();
        eventoExistente.setData(detalhesEvento.getData());
        eventoExistente.setLocalizacao(detalhesEvento.getLocalizacao());
        
        final Evento eventoAtualizado = repository.save(eventoExistente);
        return ResponseEntity.ok(eventoAtualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}

  @DeleteMapping("/{id}")
public ResponseEntity<Void> excluirEvento(@PathVariable Long id) {
    repository.deleteById(id); // Deleta o evento do banco de dados pelo seu id
    return ResponseEntity.noContent().build(); // Retorna uma resposta de sucesso sem conteúdo (Status 204)
}
    
}