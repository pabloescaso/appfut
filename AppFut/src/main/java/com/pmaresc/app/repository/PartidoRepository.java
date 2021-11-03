package com.pmaresc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pmaresc.app.entity.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{

	@Query("SELECT p FROM Partido p WHERE p.idPartido = ?1")
	public Partido getPartido (Long idPart);
}
