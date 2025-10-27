import { Component, Input } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UserRes } from '../../../model/User.model';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterLink],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {
  @Input() user!: UserRes;

  constructor(private rout : Router) {

  }
  logout() {
    localStorage.removeItem("token")
    this.rout.navigate(["/login"])
  }
}
