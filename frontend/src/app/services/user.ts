import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRes } from '../../model/User.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class User {
  constructor(private http: HttpClient) {}

  getUser(): Observable<UserRes> {
    let token = localStorage.getItem('token');
    return this.http.get<UserRes>('http://localhost:8080/api/me', {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  getProfile(uuid: string): Observable<UserRes> {
    let token = localStorage.getItem('token');
    return this.http.get<UserRes>(`http://localhost:8080/api/profile/${uuid}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }
}
