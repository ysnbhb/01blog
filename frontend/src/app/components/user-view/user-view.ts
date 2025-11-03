import { Component, Input } from '@angular/core';
import { UserRes } from '../../../model/User.model';

@Component({
  selector: 'app-user-view',
  imports: [],
  templateUrl: './user-view.html',
  styleUrl: './user-view.css',
})
export class UserView {
  @Input() user!: UserRes;
}
