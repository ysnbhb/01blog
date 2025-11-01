import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Notification } from '../../model/Notifaction.model';

@Injectable({
  providedIn: 'root',
})
export class NotificationServ {
  constructor(private http: HttpClient) {}
  getcount(): Observable<number> {
    let token = localStorage.getItem('token');
    return this.http.get<number>('http://localhost:8080/api/countNotif', {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  getNotif(): Observable<Notification[]> {
    let token = localStorage.getItem('token');
    return this.http.get<Notification[]>('http://localhost:8080/api/notifacition', {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  readAll() {
    let token = localStorage.getItem('token');
    return this.http.put('http://localhost:8080/api/raedNotifAll', null, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  readOneNotif(id: number) {
    let token = localStorage.getItem('token');
    return this.http.put(`http://localhost:8080/api/raedNotif/${id}`, null, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }
}
