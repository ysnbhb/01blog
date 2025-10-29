import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentReq } from '../../model/comment.model';

@Injectable({
  providedIn: 'root',
})
export class Comments {
  constructor(private http: HttpClient) {}

  createComment(postId: number, content: string): Observable<CommentReq> {
    const req = {
      content,
      postId,
    };
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    };
    return this.http.post<CommentReq>(
      'http://localhost:8080/api/creat_comment',
      JSON.stringify(req),
      { headers }
    );
  }

  getComments(postId: number, offste: number = 0): Observable<CommentReq[]> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    return this.http.get<CommentReq[]>(`http://localhost:8080/api/comments?postId=${postId}&offset=${offste}`, {
      headers,
    });
  }
}
