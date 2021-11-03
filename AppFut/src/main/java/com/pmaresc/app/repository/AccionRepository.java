package com.pmaresc.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmaresc.app.entity.Accion;
import com.pmaresc.app.entity.Partido;

@Repository
public interface AccionRepository extends JpaRepository<Accion, Long>{
	
	@Query("SELECT a FROM Accion a WHERE a.fromP = ?1 AND a.idPartido = ?2")
	public List<Accion> getParticipacionP (String player,Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.team = ?1 AND a.idPartido = ?2")
	public List<Accion> getParticipacionT (String team,Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.idPartido = ?1 AND a.startX > ?2 AND a.startX < ?3 AND a.startY > ?4 AND a.startY < ?5 AND a.team = ?6 AND a.periodAccion = ?7")
	public List<Accion> getParticipacionZonaTeam (Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period);
	
	@Query("SELECT a FROM Accion a WHERE a.idPartido = ?1")
	public List<Accion> getAccionesPart (Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.fromP = ?2 AND a.idPartido = ?3")
	public List<Accion> getPases (String tipo, String player, Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.idPartido = ?2 AND a.startX > ?3 AND a.startX < ?4 AND a.startY > ?5 AND a.startY < ?6 AND a.team = ?7 AND a.periodAccion = ?8")
	public List<Accion> getPasesZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.subtype = ?2 AND a.fromP = ?3 AND a.idPartido = ?4")
	public List<Accion> getPasesFallados (String tipo, String subtipo, String player, Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.idPartido = ?2 AND a.startX > ?3 AND a.startX < ?4 AND a.startY > ?5 AND a.startY < ?6 AND a.subtype = ?7 AND a.team = ?8 AND a.periodAccion = ?9")
	public List<Accion> getPasesFalladosZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String subtipo, String team, int period);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.fromP = ?2 AND a.idPartido = ?3")
	public List<Accion> getShots (String tipo, String player, Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.team = ?2 AND a.idPartido = ?3 AND a.startX > ?4 AND a.startX < ?5 AND a.startY > ?6 AND a.startY < ?7 AND a.periodAccion = ?8")
	public List<Accion> getShotsZ (String tipo, String team, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, int period);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.fromP = ?2 AND a.idPartido = ?3 AND (a.subtype = ?4 OR a.subtype = ?5 OR a.subtype = ?6)")
	public List<Accion> getShotsFallados (String tipo, String player, Partido idPart, String subtipo1, String subtipo2, String subtipo3);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.fromP = ?2 AND a.idPartido = ?3 ")
	public List<Accion> getRecuperaciones (String tipo, String player, Partido idPart);
	
	@Query("SELECT a FROM Accion a WHERE a.typeAccion = ?1 AND a.idPartido = ?2 AND a.startX > ?3 AND a.startX < ?4 AND a.startY > ?5 AND a.startY < ?6 AND a.team = ?7 AND a.periodAccion = ?8")
	public List<Accion> getRecuperacionesZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period);
	
	@Query("SELECT a FROM Accion a WHERE a.idPartido = ?1 AND a.periodAccion = ?2 AND a.team = ?3 AND a.fromP = ?4 AND a.startX > ?5 AND a.startX < ?6 AND a.startY > ?7 AND a.startY < ?8 ")
	public List<Accion> getZonaActions (Partido idPart, int tiempo, String team, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	@Query("SELECT a FROM Accion a WHERE a.idPartido = ?1 AND a.periodAccion = ?2 AND a.fromP = ?3 AND a.startX > ?4 AND a.startX < ?5 AND a.startY > ?6 AND a.startY < ?7 ")
	public List<Accion> getZonaActionsAmbos (Partido idPart, int tiempo, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	@Query("SELECT a FROM Accion a WHERE a.idPartido = ?1 AND a.team = ?2 AND a.fromP = ?3 AND a.startX > ?4 AND a.startX < ?5 AND a.startY > ?6 AND a.startY < ?7 ")
	public List<Accion> getZonaActionsF (Partido idPart, String team, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	@Query("SELECT a FROM Accion a WHERE a.idPartido = ?1 AND a.fromP = ?2 AND a.startX > ?3 AND a.startX < ?4 AND a.startY > ?5 AND a.startY < ?6 ")
	public List<Accion> getZonaActionsFull (Partido idPart, String player, Float xMin, Float xMax, Float yMin, Float yMax);
	
	@Query("SELECT DISTINCT fromP FROM Accion a WHERE a.team = ?1 AND a.idPartido = ?2")
	public List<String> getJugadores (String team, Partido idPart);
	
	@Query("SELECT DISTINCT fromP FROM Accion a WHERE a.idPartido = ?1")
	public List<String> getJugadoresAll (Partido idPart);
}
