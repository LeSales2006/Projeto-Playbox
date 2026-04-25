package br.com.example.toycatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.toycatalog.dto.CategoriaRequestDTO;
import br.com.example.toycatalog.model.entity.Categoria;
import br.com.example.toycatalog.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> listarTodos() { 
		return categoriaRepository.findAll(); 
	}
	
	public Categoria buscarCategoria(int id){
		return categoriaRepository.findById(id).orElse(null);
	}
	
	// public List<Brinquedo> buscarBrinquedoPorNome(String nome) {
	// 	return brinquedoRepository.findByNomeContains(nome);
	// }
	
	public Categoria inserirCategoria(CategoriaRequestDTO categoriaRequest) {
		Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequest.getNome());
		categoria.setImagem_base_64(categoriaRequest.getImagem_base_64());
		return categoriaRepository.save(categoria);
	}
	
	public Categoria editarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void excluirCategoria(int id) {
		categoriaRepository.deleteById(id);
	}
}
