import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserRes } from '../../../model/User.model';
import { Admin } from '../../services/admin';

@Component({
  selector: 'app-user-view',
  imports: [],
  templateUrl: './user-view.html',
  styleUrl: './user-view.css',
})
export class UserView {
  @Input() user!: UserRes;
  @Output() ban = new EventEmitter<string>();
  @Output() delete = new EventEmitter<string>()
  banUser() {
    this.ban.emit(this.user.uuid);
  }

  DeleteUser() {
    this.delete.emit(this.user.uuid);
  }
}
