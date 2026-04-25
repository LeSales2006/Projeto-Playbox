package br.com.example.toycatalog.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "brinquedos") 
public class Brinquedo {

    @Id
    private Integer codigo;
    private String descricao;
    
    @Column(name = "descricao_detalhada")  // Nome correto da coluna
    private String descricao_detalhada;
    
    private Float preco;
    private String marca;
    
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imagem_base_64;
 
    private Integer acessos;
    
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // Construtores
    public Brinquedo(){}

    public Brinquedo(Integer codigo, String descricao, String descricao_detalhada, 
                     Float preco, String marca, String imagem_base_64, 
                     Integer acessos, Categoria categoria){
        this.codigo = codigo;
        this.descricao = descricao;
        this.descricao_detalhada = descricao_detalhada;
        this.preco = preco;
        this.marca = marca;
        this.imagem_base_64 = imagem_base_64;
        this.acessos = acessos;
        this.categoria = categoria;
    }

    // Getters e Setters
    public Integer getCodigo(){
        return this.codigo;
    }

    public void setCodigo(Integer codigo){
        this.codigo = codigo;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricaoDetalhada(){
        return this.descricao_detalhada;
    }

    public void setDescricaoDetalhada(String descricao_detalhada){
        this.descricao_detalhada = descricao_detalhada;
    }

    public Float getPreco(){
        return this.preco;
    }

    public void setPreco(Float preco){
        this.preco = preco;
    }

    public String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public Integer getAcessos(){
        return this.acessos;
    }

    public void setAcessos(Integer acessos){
        this.acessos = acessos;
    }

    public String getImagem_base_64() {
        return imagem_base_64;
    }
    
    public void setImagem_base_64(String imagem_base_64) {
        this.imagem_base_64 = imagem_base_64;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}