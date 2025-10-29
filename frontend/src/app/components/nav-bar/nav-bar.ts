import { Component, Input } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { UserRes } from '../../../model/User.model';
import { Notifications } from '../../services/notifications';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {
  @Input() user!: UserRes;
  countofnotifs: number = 10;
  ngOnInit(): void {
    this.loadNotificationsCount();
  }
  loadNotificationsCount(): void {
    this.notif.getcount().subscribe({
      next: (count: number) => {
        // this.countofnotifs = count;
      },
      error: (err) => {
        console.error('Error fetching notifications count:', err);
      },
    });
  }
  constructor(private rout: Router, private notif: Notifications) {}
  logout() {
    localStorage.removeItem('token');
    this.rout.navigate(['/login']);
  }
}
