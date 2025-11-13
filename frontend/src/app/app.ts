import { Component, OnInit, signal } from '@angular/core';
import { NavigationError, Router, RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  template: '<router-outlet class="h-100"></router-outlet>',
  styleUrl: './app.css',
})
export class App implements OnInit {
  protected readonly title = signal('frontend');

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationError) {
        this.router.navigate(['/404']);
      }
    });
  }
}
