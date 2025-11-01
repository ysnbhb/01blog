import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRes } from '../../model/User.model';
import { Observable } from 'rxjs';
import { PostReq } from '../../model/Post.model';

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

  reportUser(form: Object) {
    let token = localStorage.getItem('token');
    return this.http.post('http://localhost:8080/api/reportUser', JSON.stringify(form), {
      headers: { Authorization: `Bearer ${token}`, 'Content-Type': 'application/json' },
    });
  }

  follow(uuid: string): Observable<any> {
    let token = localStorage.getItem('token');
    return this.http.post<any>(`http://localhost:8080/api/follow?uuid=${uuid}`, null, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  getProfile(uuid: string): Observable<UserRes> {
    let token = localStorage.getItem('token');
    return this.http.get<UserRes>(`http://localhost:8080/api/profile/${uuid}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }
}
