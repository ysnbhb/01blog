import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-delete-post-popup',
  imports: [],
  templateUrl: './delete-post-popup.html',
  styleUrl: './delete-post-popup.css',
})
export class DeletePostPopup {
  @Output() confirm = new EventEmitter<boolean>();

  reset(confirm: boolean) {
    this.confirm.emit(confirm);
  }
}
