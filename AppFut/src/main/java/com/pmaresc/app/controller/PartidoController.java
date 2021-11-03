package com.pmaresc.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmaresc.app.entity.Partido;
import com.pmaresc.app.service.PartidoService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/partidos")
public class PartidoController {
	
	@Autowired
	private PartidoService partidoService;
	
	//Create a new Partido
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Partido partido){
		return ResponseEntity.status(HttpStatus.CREATED).body(partidoService.save(partido));
	}
	
	//Read a Partido
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable(value = "id") Long idPartido){
		Optional<Partido> oPartido = partidoService.findById(idPartido);
		
		if(!oPartido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oPartido);
	}
	
	// Update an partido
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Partido partidoDetails, @PathVariable(value = "id") Long idPartido) {
			Optional<Partido> partido = partidoService.findById(idPartido);
			
			if(!partido.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			// BeanUtils.copyProperties(partidoDetails, partido.get());
			partido.get().setNombre(partidoDetails.getNombre());
			partido.get().setAwayClub(partidoDetails.getAwayClub());
			partido.get().setHomeClub(partidoDetails.getHomeClub());
			partido.get().setDescripcion(partidoDetails.getDescripcion());
			partido.get().setResultado(partidoDetails.getResultado());
			partido.get().setFecha(partidoDetails.getFecha());


			
			return ResponseEntity.status(HttpStatus.CREATED).body(partidoService.save(partido.get()));
			
		}
		
		// Delete an partido
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable(value = "id") Long idPartido) {
			
			if(!partidoService.findById(idPartido).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			partidoService.deleteById(idPartido);
			return ResponseEntity.ok().build();
		}
		
		// Read all partido
		@GetMapping
		public List<Partido> readAll () {
			
			List<Partido> partidos = StreamSupport
					.stream(partidoService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return partidos;
		}
		
		
		public Partido getPartido(Long idPart) {
			
			Partido part = (Partido) partidoService.getPartido(idPart);

		    return part;
		}
	
}
