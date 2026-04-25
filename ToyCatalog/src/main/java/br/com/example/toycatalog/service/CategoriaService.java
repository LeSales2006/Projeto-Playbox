package br.com.example.toycatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.example.toycatalog.dto.CategoriaRequestDTO;
import br.com.example.toycatalog.model.entity.Categoria;
import br.com.example.toycatalog.repository.BrinquedoRepository;
import br.com.example.toycatalog.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private BrinquedoRepository brinquedoRepository;
    
    public List<Categoria> listarTodos() { 
		return categoriaRepository.findAll(); 
	}
	
	public Categoria buscarCategoria(int id){
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public Categoria inserirCategoria(CategoriaRequestDTO categoriaRequest) {
		Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequest.getNome());
		categoria.setImagem_base_64(categoriaRequest.getImagem_base_64());
		return categoriaRepository.save(categoria);
	}
	
	public Categoria editarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Transactional
	public void excluirCategoriaComProdutos(int id) {
		Categoria categoria = buscarCategoria(id);
		if (categoria == null) {
			throw new RuntimeException("Categoria não encontrada");
		}
		
		// Primeiro, deleta todos os produtos da categoria
		List<br.com.example.toycatalog.model.entity.Brinquedo> brinquedos = brinquedoRepository.findByCategoriaId(id);
		if (!brinquedos.isEmpty()) {
			brinquedoRepository.deleteAll(brinquedos);
		}
		
		// Depois, deleta a categoria
		categoriaRepository.deleteById(id);
	}
}