package br.com.example.toycatalog.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class BrinquedoEditRequestDTO {
    
    @NotNull(message = "Código é obrigatório")
    @Positive(message = "Código deve ser positivo")
    @Min(value = 1, message = "Código deve ser diferente de zero")
    private Integer codigo;

    // @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    // @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    @NotNull(message = "Descrição detalhada é obrigatória")
    private String descricao_detalhada;
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private Float preco;

    @NotNull(message = "Marca é obrigatório")
    private String marca;
    
    @NotNull(message = "Imagem é obrigatória")
    @jakarta.validation.constraints.Pattern(regexp = "^[A-Za-z0-9+/]+=*$", message = "Formato de imagem inválido")
    private String imagem_base_64;
    
    @NotNull(message = "ID da categoria é obrigatório")
    @Positive(message = "ID da categoria inválido")
    private Integer id_categoria;
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricaoDetalhada() {
        return descricao_detalhada;
    }
    
    public void setDescricaoDetalhada(String descricao_detalhada) {
        this.descricao_detalhada = descricao_detalhada;
    }
    
    public Float getPreco() {
        return preco;
    }
    
    public void setPreco(Float preco) {
        this.preco = preco;
    }
    
    public String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }


    public String getImagem_base_64() {
        return imagem_base_64;
    }
    
    public void setImagem_base_64(String imagem_base_64) {
        this.imagem_base_64 = imagem_base_64;
    }
    
    public Integer getId_categoria() {
        return id_categoria;
    }
    
    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }
}