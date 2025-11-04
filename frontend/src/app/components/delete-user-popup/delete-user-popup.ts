import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-delete-user-popup',
  imports: [],
  templateUrl: './delete-user-popup.html',
  styleUrl: './delete-user-popup.css',
})
export class DeleteUserPopup {
  @Output() confirm = new EventEmitter<boolean>();

  reset(confirm: boolean) {
    this.confirm.emit(confirm);
  }
}
