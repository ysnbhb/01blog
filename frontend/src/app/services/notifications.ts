import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Notifications {
  constructor(private http: HttpClient) {}
  getcount(): Observable<number> {
    let token = localStorage.getItem('token');
    return this.http.get<number>('http://localhost:8080/api/countNotif', {
      headers: { Authorization: `Bearer ${token}` },
    });
  }
}
