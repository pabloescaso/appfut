import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Accion } from '../Modelo/Accion';

@Injectable({
  providedIn: 'root'
})
export class AccionesService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8080/api/acciones";

  getAcciones(idPartido:any,player:any){
    return this.http.get<Accion[]>(this.Url + "/participacion/" + idPartido + "/" + player);
    
  }

  getTiros(idPartido:any,player:any){
    return this.http.get<Accion[]>(this.Url + "/shots/" + idPartido + "/" + player);
    
  }
  getPases(idPartido:any,player:any){
    return this.http.get<Accion[]>(this.Url + "/pasesCorrectos/" + idPartido + "/" + player);
    
  }
  getPerdidas(idPartido:any,player:any){
    return this.http.get<Accion[]>(this.Url + "/pasesFallados/" + idPartido + "/" + player);
    
  }
  getRecuperaciones(idPartido:any,player:any){
    return this.http.get<Accion[]>(this.Url + "/recuperaciones/" + idPartido + "/" + player);
    
  }

  getZonasAction(idPartido:any, playerZ:any ,startxMin:number, startxMax:number, startyMin:number, startyMax:number,tiempo:any,team:any){
    return this.http.get<Accion[]>(this.Url + `/zonas/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}/${playerZ}`);
  }

  getAciertoPases(idPartido:any,player:any){
    return this.http.get<number>(this.Url + "/aciertoPases/" + idPartido + "/" + player);
    
  }

  getAciertoTiros(idPartido:any,player:any){
    return this.http.get<number>(this.Url + "/aciertoShot/" + idPartido + "/" + player);
    
  }

  getProfundidad(idPartido:any,player:any){
    return this.http.get<number>(this.Url + "/profPass/" + idPartido + "/" + player);
    
  }

  getLongitud(idPartido:any,player:any){
    return this.http.get<number>(this.Url + "/longPass/" + idPartido + "/" + player);
    
  }

  getIncidencia(idPartido:any,player:any){
    return this.http.get<number>(this.Url + "/incidencia/" + idPartido + "/" + player);
    
  }

  getPlayers(idPartido:any,team:any){
    return this.http.get<String[]>(this.Url + "/jugadores/" + idPartido + "/" + team);
    
  }

  getPasesZ(idPartido:any, tiempo:any ,team:any, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<Accion[]>(this.Url + `/zonas/pases/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }

  getPerdidasZ(idPartido:any, tiempo:any ,team:any, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<Accion[]>(this.Url + `/zonas/pasesFallados/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }

  getTirosZ(idPartido:any, tiempo:any ,team:any, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<Accion[]>(this.Url + `/zonas/tiros/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }

  getRecuperacionesZ(idPartido:any, tiempo:any ,team:any, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<Accion[]>(this.Url + `/zonas/recoveries/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }

  getIncidenciaZ(idPartido:any, tiempo:any ,team:any, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<number>(this.Url + `/zonas/participacion/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }

  getIncidenciaZT(idPartido:any, tiempo:any ,team:any, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<number>(this.Url + `/zonas/participacionT/${idPartido}/${tiempo}/${team}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }

  getDatosP(idPartido:any, tiempo:any ,team:any,playerZ:any,checkedPases:boolean,checkedFallados:boolean,checkedShots:boolean,checkedRecuperaciones:boolean, startxMin:number, startxMax:number, startyMin:number, startyMax:number){
    return this.http.get<Accion[]>(this.Url + `/datos/participacion/${idPartido}/${tiempo}/${team}/${playerZ}/${checkedPases}/${checkedFallados}/${checkedShots}/${checkedRecuperaciones}/${startxMin}/${startxMax}/${startyMin}/${startyMax}`);
  }
}
