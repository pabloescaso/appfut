import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'appFut';

  constructor(private router:Router){}

  List(){
    this.router.navigate(["list"]);
  }
  
}
