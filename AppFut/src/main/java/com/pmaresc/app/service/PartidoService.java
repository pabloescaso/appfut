package com.pmaresc.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pmaresc.app.entity.Partido;

public interface PartidoService {

	public Iterable<Partido> findAll();

	public Page<Partido> findAll(Pageable pageable);

	public Optional<Partido> findById(Long idPartido);

	public Partido save(Partido partido);

	public void deleteById(Long idPartido);
	
	public Partido getPartido (Long idPart);
}
