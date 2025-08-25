package br.com.eventos.gerenciadoreventosbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.eventos.gerenciadoreventosbackend.model.Administrador;
import br.com.eventos.gerenciadoreventosbackend.repository.AdministradorRepository;
import br.com.eventos.gerenciadoreventosbackend.security.TokenService; // 1. Importe o TokenService


@RestController
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private AdministradorRepository repository;

    @Autowired // 2. Injete o TokenService
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    public Administrador cadastrar(@RequestBody Administrador administrador) {
        return repository.save(administrador);
    }

    @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Administrador dadosLogin) {
    // Busca o usuário no banco. O repositório agora retorna UserDetails.
    UserDetails user = repository.findByEmail(dadosLogin.getEmail());

    // Verifica se o usuário foi encontrado E se a senha da requisição bate com a senha salva
    if (user != null && dadosLogin.getSenha().equals(user.getPassword())) {
        // Se tudo estiver certo, gera o token.
        // Precisamos converter UserDetails de volta para Administrador.
        String token = tokenService.gerarToken((Administrador) user);
        return ResponseEntity.ok(token);
    } else {
        // Se não, retorna erro.
        return ResponseEntity.status(401).body("Email ou senha inválidos.");
    }
}
}