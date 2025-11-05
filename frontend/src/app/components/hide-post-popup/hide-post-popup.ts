import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-hide-post-popup',
  imports: [],
  templateUrl: './hide-post-popup.html',
  styleUrl: './hide-post-popup.css',
})
export class HidePostPopup {
  @Output() confirm = new EventEmitter<boolean>();
  reset(confirm: boolean) {
    this.confirm.emit(confirm);
  }
}
