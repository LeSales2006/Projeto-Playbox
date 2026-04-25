package br.com.example.toycatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CategoriaRequestDTO {
    
    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Imagem é obrigatória")
    @NotNull(message = "Imagem é obrigatória")
    @jakarta.validation.constraints.Pattern(regexp = "^[A-Za-z0-9+/]+=*$", message = "Formato de imagem inválido")
    private String imagem_base_64;
    
    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getImagem_base_64() {
        return this.imagem_base_64;
    }
    
    public void setImagem_base_64(String imagem_base_64) {
        this.imagem_base_64 = imagem_base_64;
    }
}