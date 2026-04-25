package br.com.example.toycatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.toycatalog.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
} 
