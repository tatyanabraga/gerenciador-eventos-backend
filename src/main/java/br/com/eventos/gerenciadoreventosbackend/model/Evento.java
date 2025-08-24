package br.com.eventos.gerenciadoreventosbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Diz ao Spring que esta classe é uma tabela no banco de dados
@Data   // Anotação do Lombok para criar getters, setters, etc. automaticamente
public class Evento {

    @Id // Marca este campo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para gerar o valor do ID automaticamente
    private Long id;

    private String titulo;
    private String data;
    private String localizacao;
    private String imagem;

    // Campo para associar o evento a um administrador
    private Long adminId;
}