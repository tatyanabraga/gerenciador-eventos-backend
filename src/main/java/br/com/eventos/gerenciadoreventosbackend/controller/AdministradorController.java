package br.com.eventos.gerenciadoreventosbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eventos.gerenciadoreventosbackend.model.Administrador;
import br.com.eventos.gerenciadoreventosbackend.repository.AdministradorRepository;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdministradorController {

    // Injeta o repositório para interagir com o banco de dados
    @Autowired
    private AdministradorRepository repository;

    /**
     * Endpoint para cadastrar um novo administrador.
     * Recebe os dados do administrador e salva no banco.
     */
    @PostMapping("/cadastrar")
    public Administrador cadastrar(@RequestBody Administrador administrador) {
        return repository.save(administrador);
    }

    /**
     * Endpoint para realizar o login do administrador.
     * Recebe email e senha, verifica no banco e retorna sucesso ou falha.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Administrador administrador) {
        // Procura o administrador pelo email fornecido
        Optional<Administrador> adminOptional = repository.findByEmail(administrador.getEmail());

        // Verifica se o administrador foi encontrado E se a senha corresponde
        if (adminOptional.isPresent() && adminOptional.get().getSenha().equals(administrador.getSenha())) {
            // Retorna uma resposta de sucesso (Status 200 OK)
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            // Retorna uma resposta de não autorizado (Status 401) com uma mensagem de erro
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }
}