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

  hidePost(postid: number): Observable<boolean> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.put<boolean>(`http://localhost:8080/admin/hide_post?postId=${postid}`, null, {
      headers,
    });
  }

  gitReasone(uuid: string): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<any[]>(`http://localhost:8080/admin/reasone/user?uuid=${uuid}`, {
      headers,
    });
  }

  gitReasonePost(uuid: string): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<any[]>(`http://localhost:8080/admin/reasone/post?postid=${uuid}`, {
      headers,
    });
  }

  getReportUsers(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<any[]>(`http://localhost:8080/admin/reported?type=user`, {
      headers,
    });
  }

  getReportPost(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<any[]>(`http://localhost:8080/admin/reported?type=post`, {
      headers,
    });
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
