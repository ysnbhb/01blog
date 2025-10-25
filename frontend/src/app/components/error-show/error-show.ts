import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-error-show',
  imports: [],
  templateUrl: './error-show.html',
  styleUrl: './error-show.css',
})
export class ErrorShow {
  @Input() error!: String;
}
