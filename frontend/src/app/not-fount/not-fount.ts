import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-fount',
  imports: [],
  templateUrl: './not-fount.html',
  styleUrl: './not-fount.css',
})
export class NotFount {
  constructor(private router: Router) {}

  goHome() {
    this.router.navigate(['/']);
  }

}
