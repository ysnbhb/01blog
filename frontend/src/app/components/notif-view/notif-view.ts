import { Component, Input, OnInit } from '@angular/core';
import { Notification } from '../../../model/Notifaction.model';
import { RouterLink } from '@angular/router';
import { FormatDatePipe } from '../../pipe/format-date-pipe';

@Component({
  selector: 'app-notif-view',
  imports: [RouterLink , FormatDatePipe],
  templateUrl: './notif-view.html',
  styleUrl: './notif-view.css',
})
export class NotifView {
  @Input() notif!: Notification;
}
