import { Component, EventEmitter, Input, input, OnInit, Output } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { getTimeDifferenceInHours } from '../../../utils/formatDate';
import { CommonModule } from '@angular/common';
import { UserRes } from '../../../model/User.model';
import { FormsModule, NgForm } from '@angular/forms';
import { MarkdownModule } from 'ngx-markdown';

@Component({
  selector: 'app-post',
  imports: [CommonModule, FormsModule , MarkdownModule],
  templateUrl: './post.html',
  styleUrl: './post.css',
})
export class PostComponent implements OnInit {
  @Input() post!: PostReq;
  @Output() remove = new EventEmitter<number>();
  @Output() report = new EventEmitter<number>();
  @Input() user!: UserRes;
  errro!: String;
  removepost() {
    this.remove.emit(this.post.id);
  }

  async submitComment(form: NgForm) {
    const comment = form.value;
    let req = {
      content: comment.content,
      postId: this.post.id,
    };
    form.reset();
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(`http://localhost:8080/api/creat_comment`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(req),
      });

      if (res.ok) {
        this.post.numOfcomment += 1;
      } else {
        let data = await res.json();
        this.errro = data.error;
        setTimeout(() => {
          this.errro = '';
        }, 1000);
      }
    } catch (err) {
      this.errro = 'Error submitting comment';
      setTimeout(() => {
        this.errro = '';
      }, 1000);
    }
  }

  reportpost() {
    this.report.emit(this.post.id);
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
