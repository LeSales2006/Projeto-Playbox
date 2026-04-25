package br.com.example.toycatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.toycatalog.dto.BrinquedoCodigoRequestDTO;
import br.com.example.toycatalog.dto.BrinquedoEditRequestDTO;
import br.com.example.toycatalog.dto.BrinquedoRequestDTO;
import br.com.example.toycatalog.model.entity.Brinquedo;
import br.com.example.toycatalog.service.BrinquedoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/brinquedos")
public class BrinquedoController {
    @Autowired
	private BrinquedoService brinquedoService;

	
	@GetMapping("/")
	public ResponseEntity<?> listar() { 
		try{
			List<Brinquedo> brinquedos = brinquedoService.listarTodos(); 
			return ResponseEntity.ok(brinquedos);
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body("Erro ao editar: " + e.getMessage());
		} 
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao editar: " + e.getMessage());
		}
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscar(@Valid @RequestBody BrinquedoCodigoRequestDTO brinquedoRequest) { 
		try{
			Brinquedo brinquedo = brinquedoService.buscarBrinquedo(brinquedoRequest); 
			return ResponseEntity.ok(brinquedo);
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body("Erro ao editar: " + e.getMessage());
		} 
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao editar: " + e.getMessage());
		}
	}
	
	// @GetMapping("/nome/{nome}")
	// public List<Brinquedo> buscarPorNome(@PathVariable String nome) { 
	// 	return alunoService.buscarBrinquedoPorNome(nome); 
	// }
	
	@PostMapping("/")
	public ResponseEntity<?> inserir(@Valid @RequestBody BrinquedoRequestDTO brinquedoRequest) {
		try{
			brinquedoService.inserirBrinquedo(brinquedoRequest); 
			return ResponseEntity.ok("Brinquedo cadastrado com sucesso!");
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body("Erro ao cadastrar: " + e.getMessage());
		} 
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao cadastrar: " + e.getMessage());
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> editar(@Valid @RequestBody BrinquedoEditRequestDTO brinquedoRequest) { 
		try{
			brinquedoService.editarBrinquedo(brinquedoRequest); 
			return ResponseEntity.ok("Brinquedo editado com sucesso!");
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body("Erro ao editar: " + e.getMessage());
		} 
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao editar: " + e.getMessage());
		}
	}

	@PutMapping("/acessos")
	public ResponseEntity<?> editarAcesso(@Valid @RequestBody BrinquedoCodigoRequestDTO brinquedoRequest) { 
		try{
			brinquedoService.editarAcessoBrinquedo(brinquedoRequest); 
			return ResponseEntity.ok("Brinquedo editado com sucesso!");
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body("Erro ao editar: " + e.getMessage());
		} 
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao editar: " + e.getMessage());
		}
	}
	
	
	@DeleteMapping("/")
	public ResponseEntity<?> excluir(@Valid @RequestBody BrinquedoCodigoRequestDTO brinquedoRequest) { 
		try{
			brinquedoService.excluirBrinquedo(brinquedoRequest); 
			return ResponseEntity.ok("Brinquedo excluído com sucesso!");
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body("Erro ao editar: " + e.getMessage());
		} 
		catch(Exception e){
			return ResponseEntity.internalServerError()
				.body("Erro ao editar: " + e.getMessage());
		}
	}
}
