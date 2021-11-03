package com.pmaresc.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmaresc.app.entity.Partido;
import com.pmaresc.app.repository.PartidoRepository;

@Service
public class PartidoServiceImpl implements PartidoService{

	@Autowired
	private PartidoRepository partidoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Partido> findAll() {
		return partidoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Partido> findAll(Pageable pageable) {
		return partidoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Partido> findById(Long idPartido) {
		return partidoRepository.findById(idPartido);
	}

	@Override
	@Transactional
	public Partido save(Partido partido) {
		return partidoRepository.save(partido);
	}

	@Override
	@Transactional
	public void deleteById(Long idPartido) {
		partidoRepository.deleteById(idPartido);
	}
	@Override
	@Transactional(readOnly = true)
	public Partido getPartido (Long idPart) {
		Partido part = (Partido) partidoRepository.getPartido(idPart);
		return part;
	}
	

}
