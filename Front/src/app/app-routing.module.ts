import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParticipacionComponent } from './Accion/participacion/participacion.component';
import { ListComponent } from './Partido/list/list.component';

const routes: Routes = [
  {path:'list', component:ListComponent},
  {path:'participacion/:idPartido', component:ParticipacionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
