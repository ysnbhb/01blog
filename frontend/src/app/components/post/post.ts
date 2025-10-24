import { Component, EventEmitter, Input, input, Output } from '@angular/core';
import { PostReq } from '../../../model/Post.model';

@Component({
  selector: 'app-post',
  imports: [],
  templateUrl: './post.html',
  styleUrl: './post.css',
})
export class PostComponent {
  @Input() post!: PostReq;
  @Output() deleted = new EventEmitter<number>();

  async removepost() {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('No token found');
      return;
    }

    try {
      console.log('Deleting post ID:', this.post.id);

      const res = await fetch(`http://localhost:8080/api/delete_post?postId=${this.post.id}`, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (res.ok) {
        console.log('Post deleted successfully');
        // if (this.removePost) this.removePost(this.post.id);
        this.deleted.emit(this.post.id);
      } else {
        const text = await res.text(); // safer than res.json() here
        console.error('Failed to delete post:', text);
      }
    } catch (err) {
      console.error('Error deleting post:', err);
    }
  }
}
