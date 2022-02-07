import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Partido } from 'src/app/Modelo/Partido';
import { PartidosService } from '../../services/partidos.service';
import { AccionesService } from '../../services/acciones.service';
import { Accion } from 'src/app/Modelo/Accion';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  partidos:Partido[] = [];
  acciones:Accion[] = [];
  constructor(private service:PartidosService,private serviceA:AccionesService, private router:Router) { }

  ngOnInit(): void {
    this.service.getPartidos()
    .subscribe(data=>{
      this.partidos=data;
      console.log(data);
    })
  } 
  Participacion(partido:Partido){
    //localStorage.setItem("idPartido",partido.idPartido.toString());
    this.router.navigate(["participacion",partido.idPartido]);
  }

}
