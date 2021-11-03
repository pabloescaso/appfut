package com.pmaresc.app.service;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pmaresc.app.entity.Accion;
import com.pmaresc.app.entity.Partido;
import com.pmaresc.app.repository.AccionRepository;

@Service
public class AccionServiceImpl implements AccionService{

	@Autowired
	private AccionRepository accionRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Accion> findAll() {
		return accionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Accion> findAll(Pageable pageable) {
		return accionRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Accion> findById(Long idAccion) {
		return accionRepository.findById(idAccion);
	}

	@Override
	@Transactional
	public Accion save(Accion accion) {
		return accionRepository.save(accion);
	}

	@Override
	@Transactional
	public void deleteById(Long idAccion) {
		accionRepository.deleteById(idAccion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accion> getParticipacionP(String player, Partido idPart) {
		List<Accion> acciones = (List<Accion>) accionRepository.getParticipacionP(player,idPart);
        return acciones;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accion> getParticipacionT (String team,Partido idPart) {
		List<Accion> acciones = (List<Accion>) accionRepository.getParticipacionT ( team, idPart);
        return acciones;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getPases (String tipo, String player, Partido idPart){
		List<Accion> pases = (List<Accion>) accionRepository.getPases (tipo, player,idPart);
        return pases;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accion> getPasesFallados (String tipo, String subtipo, String player, Partido idPart) {
		List<Accion> pases = (List<Accion>) accionRepository.getPasesFallados (tipo, subtipo, player,idPart);
        return pases;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getShots (String tipo, String player, Partido idPart) {
		List<Accion> pases = (List<Accion>) accionRepository.getShots (tipo, player,idPart);
        return pases;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getRecuperaciones (String tipo, String player, Partido idPart) {
		List<Accion> pases = (List<Accion>) accionRepository.getRecuperaciones (tipo, player,idPart);
        return pases;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getZonaActions (Partido idPart, int tiempo, String team, String player, Float xMin, Float xMax, Float yMin, Float yMax) {
		List<Accion> accionesZona = (List<Accion>) accionRepository.getZonaActions (idPart,  tiempo,  team, player,xMin, xMax, yMin, yMax);
        return accionesZona;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getZonaActionsAmbos (Partido idPart, int tiempo, String player, Float xMin, Float xMax, Float yMin, Float yMax) {
		List<Accion> accionesZona = (List<Accion>) accionRepository.getZonaActionsAmbos (idPart, tiempo, player,xMin, xMax, yMin, yMax);
        return accionesZona;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getZonaActionsF (Partido idPart, String team, String player, Float xMin, Float xMax, Float yMin, Float yMax) {
		List<Accion> accionesZona = (List<Accion>) accionRepository.getZonaActionsF (idPart, team, player,xMin, xMax, yMin, yMax);
        return accionesZona;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getZonaActionsFull (Partido idPart, String player, Float xMin, Float xMax, Float yMin, Float yMax) {
		List<Accion> accionesZona = (List<Accion>) accionRepository.getZonaActionsFull (idPart,player,xMin, xMax, yMin, yMax);
        return accionesZona;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getShotsFallados (String tipo, String player, Partido idPart, String subtipo1, String subtipo2, String subtipo3) {
		List<Accion> getShotsFallados = (List<Accion>) accionRepository.getShotsFallados ( tipo,  player,  idPart,  subtipo1,  subtipo2,  subtipo3);
        return getShotsFallados;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getAccionesPart (Partido idPart) {
		List<Accion> getAccionesPart = (List<Accion>) accionRepository.getAccionesPart (idPart);
        return getAccionesPart;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getJugadores (String team, Partido idPart){
		List<String> getJugadores = (List<String>) accionRepository.getJugadores (team,  idPart);
        return getJugadores;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getJugadoresAll (Partido idPart){
		List<String> getJugadoresAll = (List<String>) accionRepository.getJugadoresAll (idPart);
        return getJugadoresAll;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getParticipacionZonaTeam (Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period){
		List<Accion> getParticipacionZonaT = (List<Accion>) accionRepository.getParticipacionZonaTeam ( idPart,  xMin,  xMax,  yMin,  yMax, team, period);
        return getParticipacionZonaT;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getPasesZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period){
		List<Accion> getPasesZ = (List<Accion>) accionRepository.getPasesZ ( tipo, idPart, xMin, xMax, yMin, yMax, team, period);
        return getPasesZ;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getPasesFalladosZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String subtipo, String team, int period){
		List<Accion> getPasesFalladosZ = (List<Accion>) accionRepository.getPasesFalladosZ ( tipo, idPart, xMin, xMax, yMin, yMax, subtipo, team, period);
        return getPasesFalladosZ;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getShotsZ (String tipo, String team, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, int period){
		List<Accion> getShotsZ = (List<Accion>) accionRepository.getShotsZ ( tipo, team, idPart, xMin, xMax, yMin, yMax, period);
        return getShotsZ;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Accion> getRecuperacionesZ (String tipo, Partido idPart, Float xMin, Float xMax, Float yMin, Float yMax, String team, int period){
		List<Accion> getRecuperacionesZ = (List<Accion>) accionRepository.getRecuperacionesZ ( tipo, idPart, xMin, xMax, yMin, yMax, team, period);
        return getRecuperacionesZ;
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }
}
