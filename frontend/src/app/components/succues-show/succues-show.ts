import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-succues-show',
  imports: [],
  templateUrl: './succues-show.html',
  styleUrl: './succues-show.css',
})
export class SuccuesShow {
  @Input() succues!: String;
}
