import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Partido } from '../Modelo/Partido';

@Injectable({
  providedIn: 'root'
})
export class PartidosService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8080/api/partidos";

  getPartidos(){
    return this.http.get<Partido[]>(this.Url);
  }

}
