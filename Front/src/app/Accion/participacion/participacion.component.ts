import { Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Accion } from 'src/app/Modelo/Accion';
import { AccionesService } from '../../services/acciones.service';
import {  ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { MultiDataSet, Label } from 'ng2-charts';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-participacion',
  templateUrl: './participacion.component.html',
  styleUrls: ['./participacion.component.scss']
})
export class ParticipacionComponent implements OnInit {
  
  @ViewChild('myCanvas',{static:false}) myCanvas:ElementRef;
  @ViewChild('myImage',{static:false}) myImage:ElementRef;

  public ctx: CanvasRenderingContext2D;

  public player: any;
  public players: any[] = [];
  public playerZ: any;
  public tiempo: any;
  public tiempos: any[] = [1,2];
  public team: any;
  public teams: any[] = ["Home","Away"];
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
  checkedPases:any = false;
  checkedFallados:any = false;
  checkedShots:any = false;
  checkedRecuperaciones:any = false;

 

  constructor( public dialog: MatDialog,  private service:AccionesService, private serviceP:AccionesService, private router:Router, private activatedRoute:ActivatedRoute) { 
    
    this.activatedRoute.params.subscribe(
      params=> {
        console.log(params['idPartido']);
        this.idPartido = params['idPartido']; 
      }
    )
   }

  ngOnInit(): void {
    
  }

  public onImageLoad(){
    this.ctx = this.myCanvas.nativeElement.getContext('2d');
    this.myCanvas.nativeElement.width = this.myImage.nativeElement.width;
    this.myCanvas.nativeElement.height = this.myImage.nativeElement.height;

    this.ctx.drawImage(this.myImage.nativeElement, 0,0,this.myImage.nativeElement.width,this.myImage.nativeElement.height);
    this.lineasDivisorias();

    
    
  }

  public lineasDivisorias(){

    // line
    this.ctx.strokeStyle = '#00000';
    this.ctx.lineWidth = 3;
    this.ctx.beginPath();
    this.ctx.moveTo(310.38, 517);
    this.ctx.lineTo(310.38, 0);
    this.ctx.stroke();

    this.ctx.strokeStyle = '#00000';
    this.ctx.lineWidth = 3;
    this.ctx.beginPath();
    this.ctx.moveTo(650.76, 517);
    this.ctx.lineTo(650.76, 0);
    this.ctx.stroke();

    this.ctx.restore();
  }

  public drawPunto(x:any,y:any,color:any){
    this.ctx.fillStyle = color;
    this.ctx.fillRect(x,y,11,11);
  }

  public drawArrow(p1:any, p2:any, size:any, color:any){
    let angle = Math.atan2((p2.y - p1.y) , (p2.x - p1.x));
    let hyp = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));

    this.ctx.save();
    this.ctx.translate(p1.x, p1.y);
    this.ctx.rotate(angle);

    // line
    this.ctx.strokeStyle = color;
    this.ctx.lineWidth = size;
    this.ctx.beginPath();
    this.ctx.moveTo(0, 0);
    this.ctx.lineTo(hyp - size, 0);
    this.ctx.stroke();

    // triangle
    this.ctx.fillStyle = color;
    this.ctx.beginPath();
    this.ctx.lineTo(hyp - size*1.25, size*1.25);
    this.ctx.lineTo(hyp, 0);
    this.ctx.lineTo(hyp - size*1.25, -size*1.25);
    this.ctx.fill();

    this.ctx.restore();
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
      console.log(this.tiempo);
      console.log(this.team);
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

  envioDatos(zona:string){
    if(zona == 'ini'){
      this.service.getDatosP(this.idPartido, this.tiempo, this.team, this.playerZ, this.checkedPases, this.checkedFallados, this.checkedShots, this.checkedRecuperaciones , 0,0.34,0,1.01)
     .subscribe(data=>{
       this.accionesZ=data;
       this.drawData(data);
      console.log(this.accionesZ);})
    } else if(zona == 'crea'){
      this.service.getDatosP(this.idPartido, this.tiempo, this.team, this.playerZ, this.checkedPases, this.checkedFallados, this.checkedShots, this.checkedRecuperaciones , 0.34,0.68,0,1.01)
     .subscribe(data=>{
       this.accionesZ=data;
       this.drawData(data);
      console.log(this.accionesZ);})
    } else if(zona == 'fin'){
      this.service.getDatosP(this.idPartido, this.tiempo, this.team, this.playerZ, this.checkedPases, this.checkedFallados, this.checkedShots, this.checkedRecuperaciones , 0.68,1.01,0,1.01)
     .subscribe(data=>{
       this.accionesZ=data;
       this.drawData(data);
      console.log(this.accionesZ);})
    }
  }

  public drawData(data:Accion[]){
    this.ctx.clearRect(0,0,this.myCanvas.nativeElement.width,this.myCanvas.nativeElement.height);
    this.ctx.drawImage(this.myImage.nativeElement, 0,0,this.myImage.nativeElement.width,this.myImage.nativeElement.height);
    this.lineasDivisorias();

    data.forEach(accion => {
      if(accion.typeAccion == 'PASS'){
        this.drawArrow({x:Math.abs(accion.startX * 957),y:Math.abs(accion.startY * 517)},{x:Math.abs(accion.endX * 957) ,y:Math.abs(accion.endY * 517)},5,"green");
      }
      if(accion.typeAccion == 'BALL LOST'){
        if(accion.endX && accion.endY)
        this.drawArrow({x:Math.abs(accion.startX * 957),y:Math.abs(accion.startY * 517)},{x:Math.abs(accion.endX * 957) ,y:Math.abs(accion.endY * 517)},5,"red");
        else
        this.drawPunto(Math.abs(accion.startX * 957),Math.abs(accion.startY * 517),"red");
      }
      if(accion.typeAccion == 'SHOT'){
        this.drawArrow({x:Math.abs(accion.startX * 957),y:Math.abs(accion.startY * 517)},{x:Math.abs(accion.endX * 957) ,y:Math.abs(accion.endY * 517)},5,"blue");
      }
      if(accion.typeAccion == 'RECOVERY'){
        this.drawPunto(Math.abs(accion.startX * 957),Math.abs(accion.startY * 517),"purple");
      }
          

          
        
    })
  }


  public openLeyenda(){
    this.dialog.open(ChartDialog);  
  }

}

@Component({
  selector: 'chart-dialog',
  templateUrl: './chart-dialog.html'
})
export class ChartDialog{
  constructor(){}
}


