import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { routes } from '../../app.routes';

@Component({
  selector: 'app-nav-bar',
  imports: [],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {
  constructor(private rout : Router) {

  }
  logout() {
    localStorage.removeItem("token")
    this.rout.navigate(["/login"])
  }
}
