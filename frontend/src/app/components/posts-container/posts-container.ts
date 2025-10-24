import { Component, OnInit } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { PostComponent } from '../post/post';
import { PostForm } from '../post-form/post-form';

@Component({
  selector: 'app-posts-container',
  imports: [PostComponent, PostForm],
  templateUrl: './posts-container.html',
  styleUrl: './posts-container.css',
})
export class PostsContainer implements OnInit {
  posts: PostReq[] = [];
  offset = 0;
  //  setInterval(() => {
  //     console.log(this.posts);
  //   } , 1000)

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
  onPostDeleted(id: number) {
    this.posts = this.posts.filter((p) => p.id !== id);
    console.log('removed locally', id, this.posts.length);
  }

  onPostAdd(post: PostReq) {
    this.posts = [post, ...this.posts];
  }
}
