import { Component, OnInit } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { PostComponent } from '../post/post';
import { PostForm } from '../post-form/post-form';
import { DeletePostPopup } from '../delete-post-popup/delete-post-popup';
import { ErrorShow } from '../error-show/error-show';

@Component({
  selector: 'app-posts-container',
  imports: [PostForm, PostComponent, DeletePostPopup, ErrorShow],
  templateUrl: './posts-container.html',
  styleUrl: './posts-container.css',
})
export class PostsContainer implements OnInit {
  posts: PostReq[] = [];
  offset = 0;
  post_id = 0;
  errro!: string;
  async ngOnInit(): Promise<void> {
    const token = localStorage.getItem('token');
    try {
      const res = await fetch('http://localhost:8080/api/posts', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) {
        throw new Error(`Failed to fetch posts: ${res.status}`);
      }
      const data = await res.json();
      this.posts = data;

      console.log('Posts loaded:', this.posts);
    } catch (err) {
      console.error('Error loading posts:', err);
    }
  }

  addPost(id: number) {
    this.post_id = id;
  }

  onPostDeleted(id: number) {
    if (id == 0) {
      this.post_id = 0;
    } else {
      this.posts = this.posts.filter((p) => p.id !== id);
      this.post_id = 0;
    }
  }

  onPostAdd(post: PostReq) {
    this.posts = [post, ...this.posts];
  }

  setError(error: string) {
    this.errro = error;
    this.post_id = 0;
    setTimeout(() => {
      this.errro = '';
    }, 1000);
  }
}
