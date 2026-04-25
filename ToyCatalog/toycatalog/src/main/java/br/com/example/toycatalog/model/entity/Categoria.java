package br.com.example.toycatalog.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias") 
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imagem_base_64;

    public Categoria(){}

    public Categoria(Integer id, String nome, String imagem_base_64){
        this.id = id;
        this.nome = nome;
        this.imagem_base_64 = imagem_base_64;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getImagem_base_64() {
        return imagem_base_64;
    }
    
    public void setImagem_base_64(String imagem_base_64) {
        this.imagem_base_64 = imagem_base_64;
    }
}
