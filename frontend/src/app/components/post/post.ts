import { Component, EventEmitter, Input, input, OnInit, Output } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { getTimeDifferenceInHours } from '../../../utils/formatDate';
import { CommonModule } from '@angular/common';
// import { NgClass } from "@angular/common/common_module.d";

@Component({
  selector: 'app-post',
  imports: [CommonModule],
  templateUrl: './post.html',
  styleUrl: './post.css',
})
export class PostComponent implements OnInit {
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
        this.deleted.emit(this.post.id);
      } else {
        console.error('Failed to delete post:');
      }
    } catch (err) {
      console.error('Error deleting post:', err);
    }
  }

  ngOnInit(): void {
      this.post.createdAt = getTimeDifferenceInHours(this.post.createdAt)
  }
}
