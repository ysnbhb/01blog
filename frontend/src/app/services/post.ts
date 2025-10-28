import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostReq } from '../../model/Post.model';

@Injectable({
  providedIn: 'root',
})
export class Post {
  constructor(private http: HttpClient) {}
  getPost(id: number): Observable<PostReq> {
    let token = localStorage.getItem('token');
    return this.http.get<PostReq>('http://localhost:8080/api/post?postId=' + id, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
