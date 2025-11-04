import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRes } from '../../model/User.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Admin {
  constructor(private http: HttpClient) {}
  getDashboardStatus() {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<any>(`http://localhost:8080/admin/dashboardStatus`, {
      headers,
    });
  }

  getUsers(offset = 0, limit: number = 6): Observable<UserRes[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<UserRes[]>(
      `http://localhost:8080/admin/users?offset=${offset}&limit=${limit}`,
      {
        headers,
      }
    );
  }

  getReportUsers(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<any[]>(
      `http://localhost:8080/admin/reported?type=user`,
      {
        headers,
      }
    );
  }

  banUser(uuid: string): Observable<boolean> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.post<boolean>(`http://localhost:8080/admin/banne_user?uuid=${uuid}`, null, {
      headers,
    });
  }
}
