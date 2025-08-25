package br.com.eventos.gerenciadoreventosbackend.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import br.com.eventos.gerenciadoreventosbackend.model.Administrador;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    // Gera uma chave secreta segura para o algoritmo HS256
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerarToken(Administrador administrador) {
        try {
            // Cria o token usando a biblioteca correta (JJWT)
            String token = Jwts.builder()
                .setIssuer("gerenciador-eventos-api") // Identifica quem emitiu o token
                .setSubject(administrador.getEmail()) // O "assunto" do token (o usuário)
                .setExpiration(Date.from(gerarDataExpiracao()))   // Define a data de validade
                .signWith(secretKey) // Assina o token com a chave secreta
                .compact();

            return token;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token) {
    try {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    } catch (Exception e) {
        return ""; // Retorna vazio se o token for inválido
    }

}
}
