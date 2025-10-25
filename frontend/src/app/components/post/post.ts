import { Component, EventEmitter, Input, input, OnInit, Output } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { getTimeDifferenceInHours } from '../../../utils/formatDate';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post',
  imports: [CommonModule],
  templateUrl: './post.html',
  styleUrl: './post.css',
})
export class PostComponent implements OnInit {
  @Input() post!: PostReq;
  @Output() addPost = new EventEmitter<number>();
  errro!: String;
  async removepost() {
    this.addPost.emit(this.post.id);
  }

  ngOnInit(): void {
    this.post.createdAt = getTimeDifferenceInHours(this.post.createdAt);
  }

  async like() {
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(`http://localhost:8080/api/like?postId=${this.post.id}`, {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (res.ok) {
        let data = await res.json();
        this.post.isliked = data.liked;
        this.post.numOflike = data.countOfLikes;
      } else {
        let data = await res.json();
        this.errro = data.error;
        setTimeout(() => {
          this.errro = '';
        }, 1000);
      }
    } catch (err) {
      console.error('Error deleting post:', err);
    }
  }
}
