import { Component, Input } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { UserRes } from '../../../model/User.model';
import { NotificationServ } from '../../services/notifications';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {
  @Input() user!: UserRes;
  @Input() countofnotifs: number | null = null;
  ngOnInit(): void {
    if (this.countofnotifs == null) {
      this.loadNotificationsCount();
    }
  }
  loadNotificationsCount(): void {
    this.notif.getcount().subscribe({
      next: (count: number) => {
        this.countofnotifs = count;
      },
      error: (err) => {
        console.error('Error fetching notifications count:', err);
      },
    });
  }
  constructor(private rout: Router, private notif: NotificationServ) {}
  logout() {
    localStorage.removeItem('token');
    this.rout.navigate(['/login']);
  }
}
