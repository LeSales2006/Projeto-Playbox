package br.com.example.toycatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.toycatalog.dto.CategoriaRequestDTO;
import br.com.example.toycatalog.model.entity.Categoria;
import br.com.example.toycatalog.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    @Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/")
	public List<Categoria> listar() { 
		return categoriaService.listarTodos(); 
	}
	
	@GetMapping("/{id}")
	public Categoria buscar(@PathVariable int id) { 
		return categoriaService.buscarCategoria(id); 
	}
	
	// @GetMapping("/nome/{nome}")
	// public List<Categoria> buscarPorNome(@PathVariable String nome) { 
	// 	return alunoService.buscarCategoriaPorNome(nome); 
	// }
	
	@PostMapping("/")
	public ResponseEntity<?> inserir(@Valid @RequestBody CategoriaRequestDTO categoriaRequest) { 
		try{
			categoriaService.inserirCategoria(categoriaRequest); 
			return ResponseEntity.ok("Categoria cadastrada com sucesso!");
		}
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao cadastrar: " + e.getMessage());
		}
	}
	
	@PutMapping("/")
	public Categoria editar(@RequestBody Categoria categoria) { 
		return categoriaService.editarCategoria(categoria); 
	}
	
	@DeleteMapping("/{id}")
	public String excluir(@PathVariable int id) { 
		categoriaService.excluirCategoria(id); 
		return "Categoria excluida com sucesso!";
	}
}
