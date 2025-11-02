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
  reportUser(form: Object) {
    let token = localStorage.getItem('token');
    return this.http.post('http://localhost:8080/api/report_post', JSON.stringify(form), {
      headers: { Authorization: `Bearer ${token}`, 'Content-Type': 'application/json' },
    });
  }
  create_post(post: FormData): Observable<PostReq> {
    let token = localStorage.getItem('token');
    return this.http.post<PostReq>('http://localhost:8080/api/creat_post', post, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  updatePost(post: FormData) {
    let token = localStorage.getItem('token');
    return this.http.put('http://localhost:8080/api/updatePost', post, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  getUserPost(uuid: string, offset: number = 0): Observable<PostReq[]> {
    let token = localStorage.getItem('token');
    console.log(uuid);

    return this.http.get<PostReq[]>(
      `http://localhost:8080/api/user_post?uuid=${uuid}&offset=${offset}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  getPosts(offset: number = 0): Observable<PostReq[]> {
    let token = localStorage.getItem('token');

    return this.http.get<PostReq[]>(`http://localhost:8080/api/posts?offset=${offset}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  removePost(id: number): Observable<number> {
    const token = localStorage.getItem('token');
    return this.http.delete<number>(`http://localhost:8080/api/delete_post?postId=${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
