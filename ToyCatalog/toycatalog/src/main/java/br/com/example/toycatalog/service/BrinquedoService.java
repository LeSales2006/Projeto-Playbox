package br.com.example.toycatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.toycatalog.dto.BrinquedoCodigoRequestDTO;
import br.com.example.toycatalog.dto.BrinquedoEditRequestDTO;
import br.com.example.toycatalog.dto.BrinquedoRequestDTO;
import br.com.example.toycatalog.model.entity.Brinquedo;
import br.com.example.toycatalog.model.entity.Categoria;
import br.com.example.toycatalog.repository.BrinquedoRepository;

@Service
public class BrinquedoService {
    @Autowired
    private BrinquedoRepository brinquedoRepository;

    @Autowired
    private CategoriaService categoriaService;
    
    public List<Brinquedo> listarTodos() { 
        return brinquedoRepository.findAll(); 
    }
    
    public Brinquedo buscarBrinquedo(BrinquedoCodigoRequestDTO brinquedoRequest){
        return brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
    }
    
    public Brinquedo inserirBrinquedo(BrinquedoRequestDTO brinquedoRequest) {

        Categoria categoria = categoriaService.buscarCategoria(brinquedoRequest.getId_categoria());
        
        if (categoria == null) {
            throw new RuntimeException("Categoria inválida");
        }

        Brinquedo brinquedo = new Brinquedo();
        brinquedo.setCodigo(brinquedoRequest.getCodigo());
        brinquedo.setDescricao(brinquedoRequest.getDescricao());

        if (brinquedoRequest.getDescricaoDetalhada() != null && !brinquedoRequest.getDescricaoDetalhada().isEmpty()) {
            brinquedo.setDescricaoDetalhada(brinquedoRequest.getDescricaoDetalhada());
        } else {
            brinquedo.setDescricaoDetalhada(""); // valor padrão
        }
        
        brinquedo.setPreco(brinquedoRequest.getPreco());

        if (brinquedoRequest.getMarca() != null && !brinquedoRequest.getMarca().isEmpty()) {
            brinquedo.setMarca(brinquedoRequest.getMarca());
        } else {
            brinquedo.setMarca(""); // valor padrão
        }

        brinquedo.setImagem_base_64(brinquedoRequest.getImagem_base_64());
        
        // CORREÇÃO AQUI: seta a categoria inteira, não o id
        brinquedo.setCategoria(categoria);
        
        brinquedo.setAcessos(0); // Valor padrão

        return brinquedoRepository.save(brinquedo);
    }
    
    public Brinquedo editarBrinquedo(BrinquedoEditRequestDTO brinquedoRequest) {

        Brinquedo brinquedo = brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
        if (brinquedo == null) {
            throw new RuntimeException("Brinquedo inválido");
        }

        Categoria categoria = categoriaService.buscarCategoria(brinquedoRequest.getId_categoria());
        if (categoria == null) {
            throw new RuntimeException("Categoria inválida");
        }

        brinquedo.setCodigo(brinquedoRequest.getCodigo());
        brinquedo.setDescricao(brinquedoRequest.getDescricao());
        brinquedo.setDescricaoDetalhada(brinquedoRequest.getDescricaoDetalhada());
        brinquedo.setPreco(brinquedoRequest.getPreco());
        brinquedo.setMarca(brinquedoRequest.getMarca());
        brinquedo.setImagem_base_64(brinquedoRequest.getImagem_base_64());
        
        // CORREÇÃO AQUI: seta a categoria inteira, não o id
        brinquedo.setCategoria(categoria);
        
        brinquedo.setAcessos(brinquedo.getAcessos()); // mantém o acesso atual

        return brinquedoRepository.save(brinquedo);
    }

    public Brinquedo editarAcessoBrinquedo(BrinquedoCodigoRequestDTO brinquedoRequest) {

        Brinquedo brinquedo = brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
        if (brinquedo == null) {
            throw new RuntimeException("Brinquedo inválido");
        }

        // Não precisa setar todos os campos, apenas incrementar o acesso
        brinquedo.setAcessos(brinquedo.getAcessos() + 1);

        return brinquedoRepository.save(brinquedo);
    }
    
    public void excluirBrinquedo(BrinquedoCodigoRequestDTO brinquedoRequest) {
        Brinquedo brinquedo = brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
        if (brinquedo == null) {
            throw new RuntimeException("Brinquedo inválido");
        }

        brinquedoRepository.deleteById(brinquedo.getCodigo());
    }
    
    // NOVO MÉTODO: Buscar brinquedos com detalhes da categoria
    public List<Brinquedo> buscarBrinquedosPorIdCategoria(Integer categoriaId) {
        return brinquedoRepository.findByCategoriaId(categoriaId);
    }
}