import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-ban-popup',
  imports: [],
  templateUrl: './ban-popup.html',
  styleUrl: './ban-popup.css',
})
export class BanPopup {
  @Output() confirm = new EventEmitter<boolean>();

  reset(confirm: boolean) {
    this.confirm.emit(confirm);
  }
}
