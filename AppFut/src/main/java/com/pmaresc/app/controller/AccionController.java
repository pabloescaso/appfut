package com.pmaresc.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmaresc.app.entity.Accion;
import com.pmaresc.app.entity.Partido;
import com.pmaresc.app.service.AccionService;
import com.pmaresc.app.service.PartidoService;

@RestController
@RequestMapping("/api/acciones")
public class AccionController {
	
	@Autowired
	private AccionService AccionService;
	@Autowired
	private PartidoService partidoService;
	
	//Create a new Accion
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Accion accion){
		return ResponseEntity.status(HttpStatus.CREATED).body(AccionService.save(accion));
	}
	
	//Read a Accion
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable(value = "id") Long idAccion){
		Optional<Accion> oAccion = AccionService.findById(idAccion);
		
		if(!oAccion.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oAccion);
	}
	
	// Update an Accion
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Accion accionDetails, @PathVariable(value = "id") Long idAccion) {
			Optional<Accion> accion = AccionService.findById(idAccion);
			
			if(!accion.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			// BeanUtils.copyProperties(AccionDetails, Accion.get());
			accion.get().setEndFrame(accionDetails.getEndFrame());
			accion.get().setEndTime(accionDetails.getEndTime());
			accion.get().setEndX(accionDetails.getEndX());
			accion.get().setEndY(accionDetails.getEndY());
			accion.get().setFromP(accionDetails.getFromP());
			accion.get().setIdPartido(accionDetails.getIdPartido());
			accion.get().setPeriodAccion(accionDetails.getPeriodAccion());
			accion.get().setStartFrame(accionDetails.getStartFrame());
			accion.get().setStartTime(accionDetails.getStartTime());
			accion.get().setStartX(accionDetails.getStartX());
			accion.get().setStartY(accionDetails.getStartY());
			accion.get().setSubtype(accionDetails.getSubtype());
			accion.get().setTeam(accionDetails.getTeam());
			accion.get().setToP(accionDetails.getToP());
			accion.get().setTypeAccion(accionDetails.getTypeAccion());

			
			return ResponseEntity.status(HttpStatus.CREATED).body(AccionService.save(accion.get()));
			
		}
		
		// Delete an Accion
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable(value = "id") Long idAccion) {
			
			if(!AccionService.findById(idAccion).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			AccionService.deleteById(idAccion);
			return ResponseEntity.ok().build();
		}
		
		// Read all Accion
		@GetMapping("/all")
		public List<Accion> readAll () {
			
			List<Accion> acciones = StreamSupport
					.stream(AccionService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return acciones;
		}
		
		 //Participación de los jugadores en juego
		@GetMapping("/participacion/{idPart}/{player}")
		public List<Accion> particip(/*Model model, @RequestParam String team,*/ @PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> acciones = (List<Accion>) AccionService.getParticipacionP(player,part);

		    return acciones;
		}
		
		@GetMapping("/participacionT/{idPart}/{team}")
		public List<Accion> participT(/*Model model, @RequestParam String team,*/ @PathVariable(value = "idPart") Long idPart, @PathVariable(value = "team") String team) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> acciones = (List<Accion>) AccionService.getParticipacionT(team,part);

		    return acciones;
		}
		
		@GetMapping("/all/{idPart}")
		public List<Accion> getAccionesPart(@PathVariable(value = "idPart") Long idPart) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> acciones = (List<Accion>) AccionService.getAccionesPart(part);

		    return acciones;
		}
		
		@GetMapping("/pasesCorrectos/{idPart}/{player}")
		public List<Accion> getPasesCorrectos(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "PASS";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pases = (List<Accion>) AccionService.getPases(tipo,player,part );

		    return pases;
		}
		
		@GetMapping("/pasesFallados/{idPart}/{player}")
		public List<Accion> getPasesFallados(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "BALL LOST";
			String subtipo = "INTERCEPTION";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pases = (List<Accion>) AccionService.getPasesFallados(tipo,subtipo,player,part);

		    return pases;
		}
		
		@GetMapping("/shots/{idPart}/{player}")
		public List<Accion> getShots(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "SHOT";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> shots = (List<Accion>) AccionService.getShots(tipo,player,part);

		    return shots;
		}
		
		@GetMapping("/recuperaciones/{idPart}/{player}")
		public List<Accion> getRecuperaciones(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "RECOVERY";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pases = (List<Accion>) AccionService.getRecuperaciones(tipo,player,part);

		    return pases;
		}
		
		@GetMapping("/zonas/{idPart}/{tiempo}/{team}/{xMin}/{xMax}/{yMin}/{yMax}/{player}")
		public List<Accion> getZonaActions(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "tiempo") int tiempo , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax, @PathVariable(value = "player") String player) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> accionesZona =  null; //(List<Accion>) AccionService.getZonaActions ( part,tiempo,team,  player,  xMin,  xMax,  yMin,  yMax);
			
			if(team.equals("ambos") && tiempo == 3) {
				accionesZona = (List<Accion>) AccionService.getZonaActionsFull ( part, player,  xMin,  xMax,  yMin,  yMax);
			} else if(team.equals("ambos")) {
				accionesZona = (List<Accion>) AccionService.getZonaActionsAmbos ( part,tiempo, player,  xMin,  xMax,  yMin,  yMax);
			} else if(tiempo == 3) {
				accionesZona = (List<Accion>) AccionService.getZonaActionsF ( part,team, player,  xMin,  xMax,  yMin,  yMax);
			} else {
				accionesZona = (List<Accion>) AccionService.getZonaActions ( part,tiempo,team,  player,  xMin,  xMax,  yMin,  yMax);
			}

		    return accionesZona;
		}
		
		@GetMapping("/aciertoShot/{idPart}/{player}")
		public float getShotsFallados(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "SHOT";
			String subtipo1 = "OFF TARGET-OUT";
			String subtipo2 = "HEAD-OFF TARGET-OUT";
			String subtipo3 = "OFF TARGET";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> shotsFallados = (List<Accion>) AccionService.getShotsFallados ( tipo,  player,  part,  subtipo1,  subtipo2,  subtipo3);
			List<Accion> shots = (List<Accion>) AccionService.getShots(tipo,player,part);
			Integer shotsfalladosL = shotsFallados.size();
			Integer shotsL = shots.size();
			float acierto = 0;
			
			if(shotsL != 0) {
			acierto =  100 * (((float)shotsL - shotsfalladosL) / shotsL);
			}

		    return acierto;
		}
		
		@GetMapping("/aciertoPases/{idPart}/{player}")
		public float getAciertoPases(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "PASS";
			String tipo1 = "BALL LOST";
			String subtipo = "INTERCEPTION";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pasesA = (List<Accion>) AccionService.getPases(tipo,player,part);
			List<Accion> pasesF = (List<Accion>) AccionService.getPasesFallados(tipo1,subtipo,player,part);
			Integer pasesAL = pasesA.size();
			Integer pasesFL = pasesF.size();
			Integer pasesT = pasesAL + pasesFL;
			
			float aciertoP = 100 * (float)pasesAL/pasesT;

		    return aciertoP;
		}
		
		@GetMapping("/profPass/{idPart}/{player}")
		public float profPass(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "PASS";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pases = (List<Accion>) AccionService.getPases(tipo,player,part);
			Iterator<Accion> it = pases.iterator();
			List<Float> lp = new ArrayList<Float>();
			float start = 0;
			float end = 0;
			
			while(it.hasNext()) {
				Accion action = it.next();
				start = 100 *  action.getStartX();
				end = 100 * action.getEndX();
				lp.add(end - start);
				
			}
			
			float longitudTotal = 0;
			for(int i = 0; i < lp.size(); i++) {
				longitudTotal += Math.abs(lp.get(i)); 
			}
			
			float longitudMedia = longitudTotal/lp.size();
			

		    return longitudMedia;
		}
		
		@GetMapping("/longPass/{idPart}/{player}")
		public float longPass(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			String tipo = "PASS";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pases = (List<Accion>) AccionService.getPases(tipo,player,part);
			Iterator<Accion> it = pases.iterator();
			List<Float> lp = new ArrayList<Float>();
			float startX = 0;
			float endX = 0;
			float startY = 0;
			float endY = 0;
			float x = 0;
			float y = 0;
			float dist = 0;
			
			while(it.hasNext()) {
				Accion action = it.next();
				startX = 100 *  action.getStartX();
				endX = 100 * action.getEndX();
				startY = 100 *  action.getStartY();
				endY = 100 * action.getEndY();
				
				x = (endX - startX);
				y = (endY - startY);
				
				dist = (float) Math.sqrt(Math.pow(x, 2) + (Math.pow(y, 2)));
				
				
				lp.add(dist);
				
			}
			
			float longitudTotal = 0;
			for(int i = 0; i < lp.size(); i++) {
				longitudTotal += Math.abs(lp.get(i)); 
			}
			
			float longitudMedia = longitudTotal/lp.size();
			

		    return longitudMedia;
		}
		
		@GetMapping("/incidencia/{idPart}/{player}")
		public float incidencia(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "player") String player) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> acciones = (List<Accion>) AccionService.getAccionesPart(part);
			List<Accion> accionesPlayer = (List<Accion>) AccionService.getParticipacionP(player,part);
			
			Integer accionesL = acciones.size();
			Integer accionesPlayerL = accionesPlayer.size();
			
			float incidencia =  100 * (((float)accionesPlayerL / accionesL));

		    return incidencia;
		}
		
		@GetMapping("/incidencia/{idPart}/{team}")
		public float incidenciaT(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "team") String team) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> acciones = (List<Accion>) AccionService.getAccionesPart(part);
			List<Accion> accionesTeam = (List<Accion>) AccionService.getParticipacionT(team,part);
			
			Integer accionesL = acciones.size();
			Integer accionesTeamL = accionesTeam.size();
			
			float incidencia =  100 * (((float)accionesTeamL / accionesL));

		    return incidencia;
		}
		
		@GetMapping("/jugadores/{idPart}/{team}")
		public List<String> getJugadores(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "team") String team) {
			
			Partido part = partidoService.getPartido(idPart);
			
			List<String> jugadores = null;
			
			if(team.equals("ambos")) {
				jugadores = (List<String>) AccionService.getJugadoresAll(part);
			} else {
				jugadores = (List<String>) AccionService.getJugadores(team,part);
			}

		    return jugadores;
		}
		
		@GetMapping("/zonas/pases/{idPart}/{period}/{team}/{xMin}/{xMax}/{yMin}/{yMax}")
		public List<Accion> getPasesZ(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			String tipo = "PASS";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pasesZ = (List<Accion>) AccionService.getPasesZ(tipo, part, xMin, xMax, yMin, yMax, team, period);

		    return pasesZ;
		}
		
		@GetMapping("/zonas/participacion/{idPart}/{period}/{team}/{xMin}/{xMax}/{yMin}/{yMax}")
		public float getParticipacionZonaTeamTotal(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> participZ = (List<Accion>) AccionService.getParticipacionZonaTeam ( part,  xMin,  xMax,  yMin,  yMax,  team,  period);
			List<Accion> acciones = (List<Accion>) AccionService.getAccionesPart(part);
			
			Integer accionesL = acciones.size();
			Integer participZL = participZ.size();
			
			float incidencia =  100 * (((float)participZL / accionesL));

		    return incidencia;
		}
		
		@GetMapping("/zonas/participacionT/{idPart}/{period}/{team}/{xMin}/{xMax}/{yMin}/{yMax}")
		public float getParticipacionZonaTeam(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			Partido part = partidoService.getPartido(idPart);
			List<Accion> participZ = (List<Accion>) AccionService.getParticipacionZonaTeam ( part,  xMin,  xMax,  yMin,  yMax,  team,  period);
			List<Accion> acciones = (List<Accion>) AccionService.getParticipacionT(team,part);
			
			Integer accionesL = acciones.size();
			Integer participZL = participZ.size();
			
			float incidencia =  100 * (((float)participZL / accionesL));

		    return incidencia;
		}
		
		@GetMapping("/zonas/pasesFallados/{idPart}/{period}/{team}/{xMin}/{xMax}/{yMin}/{yMax}")
		public List<Accion> getPasesFalladosZ(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			String tipo = "BALL LOST";
			String subtipo = "INTERCEPTION";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> pasesFZ = (List<Accion>) AccionService.getPasesFalladosZ ( tipo,  part,  xMin,  xMax,  yMin,  yMax,  subtipo,  team,  period);

		    return pasesFZ;
		}
		
		@GetMapping("/zonas/tiros/{idPart}/{period}/{team}/{xMin}/{xMax}/{yMin}/{yMax}")
		public List<Accion> getShotsZ(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			String tipo = "SHOT";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> getShotsZ = (List<Accion>) AccionService.getShotsZ ( tipo,  team,  part,  xMin,  xMax,  yMin,  yMax,  period);

		    return getShotsZ;
		}
		
		@GetMapping("/zonas/recoveries/{idPart}/{period}/{team}/{xMin}/{xMax}/{yMin}/{yMax}")
		public List<Accion> getRecuperacionesZ(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team , @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			String tipo = "RECOVERY";
			Partido part = partidoService.getPartido(idPart);
			List<Accion> getRecuperacionesZ = (List<Accion>) AccionService.getRecuperacionesZ ( tipo,  part,  xMin,  xMax,  yMin,  yMax,  team,  period);

		    return getRecuperacionesZ;
		}
		
		@GetMapping("/datos/participacion/{idPart}/{period}/{team}/{player}/{checkedPases}/{checkedFallados}/{checkedShots}/{checkedRecuperaciones}/{xMin}/{xMax}/{yMin}/{yMax}")
		public List<Accion> getDatosP(@PathVariable(value = "idPart") Long idPart, @PathVariable(value = "period") int period , @PathVariable(value = "team") String team ,@PathVariable(value = "player") String player, @PathVariable(value = "checkedPases") boolean checkedPases, @PathVariable(value = "checkedFallados") boolean checkedFallados, @PathVariable(value = "checkedRecuperaciones") boolean checkedRecuperaciones, @PathVariable(value = "checkedShots") boolean checkedShots, @PathVariable(value = "xMin") Float xMin, @PathVariable(value = "xMax") Float xMax, @PathVariable(value = "yMin") Float yMin, @PathVariable(value = "yMax") Float yMax) {
			
			Set <Accion> getDatosP = new HashSet<Accion>();
			List<Accion> getDatosAux;
			Partido part = partidoService.getPartido(idPart);
			if(checkedPases == true) {
				getDatosAux = (List<Accion>) AccionService.getPasesZP ( "PASS",  part,  xMin,  xMax,  yMin,  yMax,  team,  period, player);
				getDatosP.addAll(getDatosAux);
			}
			if(checkedFallados == true) {
				getDatosAux = (List<Accion>) AccionService.getPasesFalladosZP ( "BALL LOST",  part,  xMin,  xMax,  yMin,  yMax,  "INTERCEPTION",  team,  period, player);
				getDatosP.addAll(getDatosAux);
			}
			if(checkedShots == true) {
				getDatosAux = (List<Accion>) AccionService.getShotsZP ( "SHOT",  team,  part,  xMin,  xMax,  yMin,  yMax,  period, player);
				getDatosP.addAll(getDatosAux);
			}
			if(checkedRecuperaciones == true) {
				getDatosAux = (List<Accion>) AccionService.getRecuperacionesZP ( "RECOVERY",  part,  xMin,  xMax,  yMin,  yMax,  team,  period, player);
				getDatosP.addAll(getDatosAux);
			}

		    return new ArrayList<Accion>(getDatosP);
		}
		
}