package com.pmaresc.app.service;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pmaresc.app.entity.Accion;
import com.pmaresc.app.entity.Partido;

public interface AccionService {
	
	public Iterable<Accion> findAll();

	public Page<Accion> findAll(Pageable pageable);

	public Optional<Accion> findById(Long idAccion);

	public Accion save(Accion accion);

	public void deleteById(Long idAccion);
	
	public List<Accion> getParticipacionP(String player, Partido idPart);
	
	public List<Accion> getPases (String tipo, String player, Partido idPart);
	
	public List<Accion> getPasesFallados (String tipo, String subtipo, String player, Partido idPart);
	
	public List<Accion> getShots (String tipo, String player, Partido idPart);
	
	public List<Accion> getRecuperaciones (String tipo, String player, Partido idPart);
	
	public List<Accion> getZonaActions (Partido idPart, int tiempo, String team, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	public List<Accion> getZonaActionsAmbos (Partido idPart, int tiempo, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	public List<Accion> getZonaActionsF (Partido idPart, String team, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	public List<Accion> getZonaActionsFull (Partido idPart, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	public List<Accion> getShotsFallados (String tipo, String player, Partido idPart, String subtipo1, String subtipo2, String subtipo3);
	
	public List<Accion> getAccionesPart (Partido idPart);
	
	public List<String> getJugadores (String team, Partido idPart);
	
	public List<String> getJugadoresAll (Partido idPart);
	
	public List<Accion> getParticipacionZonaTeam (Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period);
	
	public List<Accion> getPasesZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period);
	
	public List<Accion> getPasesFalladosZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String subtipo, String team, int period);
	
	public List<Accion> getShotsZ (String tipo, String team, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, int period);
	
	public List<Accion> getRecuperacionesZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period);
	
	public List<Accion> getParticipacionT (String team,Partido idPart);
	
	public List<Accion> getPasesZP (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period, String player);
	
	public List<Accion> getPasesFalladosZP (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String subtipo, String team, int period, String player);
	
	public List<Accion> getShotsZP (String tipo, String team, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, int period, String player);
	
	public List<Accion> getRecuperacionesZP (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period, String player);
}
