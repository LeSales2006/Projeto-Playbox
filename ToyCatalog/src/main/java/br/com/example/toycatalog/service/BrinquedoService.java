package br.com.example.toycatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    public Brinquedo inserirBrinquedo(BrinquedoRequestDTO brinquedoRequest) {
        System.out.println("=== INSERINDO BRINQUEDO ===");
        System.out.println("Descrição: " + brinquedoRequest.getDescricao());
        System.out.println("Descrição detalhada: " + brinquedoRequest.getDescricao_detalhada());
        System.out.println("Preço: " + brinquedoRequest.getPreco());
        System.out.println("ID Categoria: " + brinquedoRequest.getId_categoria());

        Categoria categoria = categoriaService.buscarCategoria(brinquedoRequest.getId_categoria());
        
        if (categoria == null) {
            throw new RuntimeException("Categoria inválida");
        }

        Brinquedo brinquedo = new Brinquedo();
        brinquedo.setCodigo(brinquedoRequest.getCodigo());
        brinquedo.setDescricao(brinquedoRequest.getDescricao());
        
        // CORREÇÃO IMPORTANTE: Salvar a descrição detalhada corretamente
        String descricaoDetalhada = brinquedoRequest.getDescricao_detalhada();
        if (descricaoDetalhada != null && !descricaoDetalhada.isEmpty()) {
            brinquedo.setDescricaoDetalhada(descricaoDetalhada);
            System.out.println("Descrição detalhada salva: " + descricaoDetalhada);
        } else {
            brinquedo.setDescricaoDetalhada("");
            System.out.println("Descrição detalhada vazia");
        }
        
        brinquedo.setPreco(brinquedoRequest.getPreco());

        if (brinquedoRequest.getMarca() != null && !brinquedoRequest.getMarca().isEmpty()) {
            brinquedo.setMarca(brinquedoRequest.getMarca());
        } else {
            brinquedo.setMarca("");
        }

        brinquedo.setImagem_base_64(brinquedoRequest.getImagem_base_64());
        brinquedo.setCategoria(categoria);
        brinquedo.setAcessos(0);

        Brinquedo saved = brinquedoRepository.save(brinquedo);
        System.out.println("Brinquedo salvo com ID: " + saved.getCodigo());
        System.out.println("Descrição detalhada salva no banco: " + saved.getDescricaoDetalhada());
        
        return saved;
    }
    
    @Transactional
    public Brinquedo editarBrinquedo(BrinquedoEditRequestDTO brinquedoRequest) {
        System.out.println("=== EDITANDO BRINQUEDO ===");
        System.out.println("Código: " + brinquedoRequest.getCodigo());
        System.out.println("Descrição detalhada recebida: " + brinquedoRequest.getDescricao_detalhada());

        Brinquedo brinquedo = brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
        if (brinquedo == null) {
            throw new RuntimeException("Brinquedo não encontrado");
        }

        Categoria categoria = categoriaService.buscarCategoria(brinquedoRequest.getId_categoria());
        if (categoria == null) {
            throw new RuntimeException("Categoria inválida");
        }

        brinquedo.setDescricao(brinquedoRequest.getDescricao());
        
        // CORREÇÃO IMPORTANTE: Atualizar a descrição detalhada
        String descricaoDetalhada = brinquedoRequest.getDescricao_detalhada();
        if (descricaoDetalhada != null && !descricaoDetalhada.isEmpty()) {
            brinquedo.setDescricaoDetalhada(descricaoDetalhada);
            System.out.println("Nova descrição detalhada: " + descricaoDetalhada);
        } else {
            brinquedo.setDescricaoDetalhada("");
            System.out.println("Descrição detalhada removida");
        }
        
        brinquedo.setPreco(brinquedoRequest.getPreco());
        brinquedo.setMarca(brinquedoRequest.getMarca());
        brinquedo.setImagem_base_64(brinquedoRequest.getImagem_base_64());
        brinquedo.setCategoria(categoria);

        Brinquedo updated = brinquedoRepository.save(brinquedo);
        System.out.println("Brinquedo atualizado. Descrição detalhada no banco: " + updated.getDescricaoDetalhada());
        
        return updated;
    }

    public Brinquedo editarAcessoBrinquedo(BrinquedoCodigoRequestDTO brinquedoRequest) {
        Brinquedo brinquedo = brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
        if (brinquedo == null) {
            throw new RuntimeException("Brinquedo não encontrado");
        }
        
        brinquedo.setAcessos(brinquedo.getAcessos() + 1);
        return brinquedoRepository.save(brinquedo);
    }
    
    @Transactional
    public void excluirBrinquedo(BrinquedoCodigoRequestDTO brinquedoRequest) {
        Brinquedo brinquedo = brinquedoRepository.findById(brinquedoRequest.getCodigo()).orElse(null);
        if (brinquedo == null) {
            throw new RuntimeException("Brinquedo não encontrado");
        }
        brinquedoRepository.deleteById(brinquedo.getCodigo());
    }
    
    public List<Brinquedo> buscarBrinquedosPorIdCategoria(Integer categoriaId) {
        return brinquedoRepository.findByCategoriaId(categoriaId);
    }
}