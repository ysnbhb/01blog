import { Component, signal } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  template: '<router-outlet class="h-100"></router-outlet>',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
}
