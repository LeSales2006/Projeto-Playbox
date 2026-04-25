package br.com.example.toycatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.toycatalog.model.entity.Brinquedo;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Integer>{

    List<Brinquedo> findByCategoriaId(Integer categoriaId);
    
    // JPQL com JOIN explícito (útil para consultas mais complexas)
    @Query("SELECT b.codigo, b.descricao, b.preco, b.marca, c.id, c.nome FROM Brinquedo b INNER JOIN b.categoria c")
    List<Object[]> listarTodosComCategoria();
} 
