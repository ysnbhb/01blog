import { Component, OnInit } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { Router } from '@angular/router';
import { NotificationServ } from '../services/notifications';
import { ErrorShow } from '../components/error-show/error-show';
import { Notification } from '../../model/Notifaction.model';
import { NotifView } from '../components/notif-view/notif-view';

@Component({
  selector: 'app-notifications',
  imports: [NavBar, ErrorShow, NotifView],
  templateUrl: './notifications.html',
  styleUrl: './notifications.css',
})
export class Notifications implements OnInit {
  user!: UserRes;
  notifications!: Notification[];
  error = '';
  constructor(private userSer: User, private router: Router, private notifServ: NotificationServ) {}

  ngOnInit(): void {
    this.userSer.getUser().subscribe({
      next: (user: UserRes) => {
        this.user = user;
      },
      error: () => {
        this.router.navigate(['login']);
      },
    });
    this.notifServ.getNotif().subscribe({
      next: (data: Notification[]) => {
        this.notifications = data;
      },
      error: () => {},
    });
  }
  readAll() {
    this.notifServ.readAll().subscribe({
      next: () => {
        this.notifications = this.notifications.map((notif) => ({
          ...notif,
          isRead: true,
        }));
      },
      error: (err) => {
        this.setError('Failed to mark all as read');
      },
    });
  }
  setError(error: string) {
    this.error = error;
    setTimeout(() => {
      this.error = '';
    }, 2000);
  }
}
