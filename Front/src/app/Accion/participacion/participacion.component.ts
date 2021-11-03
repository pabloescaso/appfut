import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Accion } from 'src/app/Modelo/Accion';
import { AccionesService } from '../../services/acciones.service';
import {  ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { MultiDataSet, Label } from 'ng2-charts';

@Component({
  selector: 'app-participacion',
  templateUrl: './participacion.component.html',
  styleUrls: ['./participacion.component.css']
})
export class ParticipacionComponent implements OnInit {
  
  public player: any;
  public players: any[] = [];
  public playerZ: any;
  public tiempo: any;
  public team: any;
  idPartido: any;
  aciertoPases:any;
  aciertoTiros:any;
  profundidad:any;
  longitud:any;
  incidencia:any;
  shots: Accion[] = [];
  acciones:Accion[] = [];
  accionesZ:Accion[] = [];
  pases:Accion[] = [];
  perdidas:Accion[] = [];
  recuperaciones:Accion[] = [];
  pasesZ: Accion[] = [];
  perdidasZ: Accion[] = [];
  shotsZ: Accion[] = [];
  recuperacionesZ: Accion[] = [];
  incidenciaZ: any;
  incidenciaZT: any;

  constructor(private service:AccionesService, private serviceP:AccionesService, private router:Router, private activatedRoute:ActivatedRoute) { 
    
    this.activatedRoute.params.subscribe(
      params=> {
        console.log(params['idPartido']);
        this.idPartido = params['idPartido']; 
      }
    )
   }

  ngOnInit(): void {
    
  }

  public doughnutChartLabels: Label[] = ['Pases', 'Ball Lost - Interception', 'Shot','Recoveries'];
  public doughnutChartData: MultiDataSet = [];
  public doughnutChartType: ChartType = 'doughnut';
  
  public backgroundColors: Array < any > = [{
    backgroundColor: ['#fc5858', '#19d863', '#fdf57d','#0000ff']
}];

public barChartOptions: ChartOptions = {
  responsive: true,
};
public barChartLabels: Label[] = ['Pases', 'Ball Lost - Interception', 'Shot','Recoveries'];
public barChartType: ChartType = 'bar';
public barChartLegend = false;
public barChartPlugins = [];

public barChartData: ChartDataSets[] = [
  //{ data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' }
];
public backgroundColores: Array < any > = [{
  backgroundColor: ['#fc5858', '#19d863', '#fdf57d','#0000ff']
}];

  filterPlayers(){

    this.service.getPlayers(this.idPartido,this.team)
    .subscribe(data=>{
      this.players=data;
      console.log(this.players);})
  }

   listaPlayers(){

     this.service.getPlayers(this.idPartido,'ambos')
     .subscribe(data=>{
       this.players=data;
      console.log(this.players);})
   }

  EnvioPlayer(){

    /*GRAFICO CIRCULAR INDIVIDUAL*/ 
    this.service.getPases(this.idPartido,this.player)
    .subscribe(data=>{
      this.pases=data;
      console.log(this.pases.length);

    this.service.getPerdidas(this.idPartido,this.player)
    .subscribe(data=>{
      this.perdidas=data;
      console.log(this.perdidas.length);

    this.service.getTiros(this.idPartido,this.player)
    .subscribe(data=>{
      this.shots=data;
      
      console.log(this.shots.length);

      this.service.getRecuperaciones(this.idPartido,this.player)
    .subscribe(data=>{
      this.recuperaciones=data;
      this.doughnutChartData = [[this.pases.length,this.perdidas.length,this.shots.length,this.recuperaciones.length]]
      console.log(this.recuperaciones.length);

    },error=>{console.log(error)})

    },error=>{console.log(error)})

    },error=>{console.log(error)})

    },error=>{console.log(error)})

    this.service.getAciertoPases(this.idPartido,this.player)
    .subscribe(data=>{
         this.aciertoPases=data;
         console.log(this.aciertoPases);
       },error=>{console.log(error)})
    
    this.service.getAciertoTiros(this.idPartido,this.player)
    .subscribe(data=>{
        this.aciertoTiros=data;
        console.log(this.aciertoTiros);
      },error=>{console.log(error)})

    this.service.getProfundidad(this.idPartido,this.player)
    .subscribe(data=>{
        this.profundidad=data;
        console.log(this.profundidad);
      },error=>{console.log(error)})
    
    this.service.getLongitud(this.idPartido,this.player)
    .subscribe(data=>{
        this.longitud=data;
        console.log(this.longitud);
      },error=>{console.log(error)})

    this.service.getIncidencia(this.idPartido,this.player)
    .subscribe(data=>{
        this.incidencia=data;
        console.log(this.incidencia);
      },error=>{console.log(error)})

  }

  StatsZona(startxMin:number, startxMax:number, startyMin:number, startyMax:number){

    console.log(startxMin,startxMax,startyMin,startyMax,this.playerZ,this.idPartido);
    this.service.getZonasAction(this.idPartido,this.playerZ,startxMin,startxMax,startyMin,startyMax,this.tiempo,this.team)

    .subscribe(data=>{
      this.accionesZ=data;
      console.log(this.accionesZ.length);
    },error=>{console.log(error)})
    
    

    /*GRAFICO DE BARRAS POR ZONAS*/ 

    this.service.getPasesZ(this.idPartido,this.tiempo,this.team,startxMin,startxMax,startyMin,startyMax)
    .subscribe(data=>{
      this.pasesZ=data;
      console.log(this.pasesZ.length);

    this.service.getPerdidasZ(this.idPartido,this.tiempo,this.team,startxMin,startxMax,startyMin,startyMax)
    .subscribe(data=>{
      this.perdidasZ=data;
      console.log(this.perdidasZ.length);

    this.service.getTirosZ(this.idPartido,this.tiempo,this.team,startxMin,startxMax,startyMin,startyMax)
    .subscribe(data=>{
      this.shotsZ=data;
      
      console.log(this.shotsZ.length);

      this.service.getRecuperacionesZ(this.idPartido,this.tiempo,this.team,startxMin,startxMax,startyMin,startyMax)
    .subscribe(data=>{
      this.recuperacionesZ=data;
      this.barChartData = [{data:[this.pasesZ.length,this.perdidasZ.length,this.shotsZ.length,this.recuperacionesZ.length]}]

    },error=>{console.log(error)})

    },error=>{console.log(error)})

    },error=>{console.log(error)})

    },error=>{console.log(error)})

    this.service.getIncidenciaZ(this.idPartido,this.tiempo,this.team,startxMin,startxMax,startyMin,startyMax)
    .subscribe(data=>{
        this.incidenciaZ=data;
        console.log(this.incidenciaZ);
      },error=>{console.log(error)})

      this.service.getIncidenciaZT(this.idPartido,this.tiempo,this.team,startxMin,startxMax,startyMin,startyMax)
    .subscribe(data=>{
        this.incidenciaZT=data;
        console.log(this.incidenciaZT);
      },error=>{console.log(error)})
    
  }

    
}



