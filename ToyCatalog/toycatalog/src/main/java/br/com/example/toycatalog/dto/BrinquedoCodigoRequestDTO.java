package br.com.example.toycatalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class BrinquedoCodigoRequestDTO {
    
    @NotNull(message = "Código é obrigatório")
    @Positive(message = "Código deve ser positivo")
    @Min(value = 1, message = "Código deve ser diferente de zero")
    private Integer codigo;

    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }    
}