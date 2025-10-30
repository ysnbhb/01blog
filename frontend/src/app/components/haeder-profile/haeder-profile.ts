import { Component, Input } from '@angular/core';
import { UserRes } from '../../../model/User.model';

@Component({
  selector: 'app-haeder-profile',
  imports: [],
  templateUrl: './haeder-profile.html',
  styleUrl: './haeder-profile.css',
})
export class HaederProfile {
  @Input() user!: UserRes;
  isLoading = false;
  toggleFollow() {
    this.isLoading = true;
    const action = this.user.hascon ? 'unfollow' : 'follow';

  }
}
