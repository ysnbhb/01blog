import { Component, Input, OnInit } from '@angular/core';
import { Notification } from '../../../model/Notifaction.model';
import { getTimeDifferenceInHours } from '../../../utils/formatDate';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-notif-view',
  imports: [RouterLink],
  templateUrl: './notif-view.html',
  styleUrl: './notif-view.css',
})
export class NotifView implements OnInit {
  @Input() notif!: Notification;
  ngOnInit(): void {
    console.log(this.notif);
    this.notif.createdAt = getTimeDifferenceInHours(this.notif.createdAt);
  }
}
