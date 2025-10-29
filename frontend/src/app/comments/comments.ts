import { Component } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { Router } from '@angular/router';
import { User } from '../services/user';
import { UserRes } from '../../model/User.model';
import { CommentReq } from '../../model/comment.model';
import { Comments } from '../services/comments';
import { CommentView } from '../components/comment-view/comment-view';
import { throttle } from '../../utils/throttle';
import { ErrorShow } from "../components/error-show/error-show";
@Component({
  selector: 'app-comments',
  imports: [NavBar, CommentView, ErrorShow],
  templateUrl: './comments.html',
  styleUrl: './comments.css',
})
export class CommentsComponent {
  user!: UserRes;
  comments!: CommentReq[];
  offset = 0;
  error!: string;
  constructor(private rout: Router, private userser: User, private comment: Comments) {}

  handleScroll = throttle((event: Event) => {
    const target = event.target as HTMLElement;
    if (target.scrollHeight - target.scrollTop - target.clientHeight < 30) {
      this.offset += 10;
      const postId = new URLSearchParams(window.location.search).get('postId');
      if (postId) {
        this.comment.getComments(Number(postId), this.offset).subscribe({
          next: (data: CommentReq[]) => {
            this.comments = this.comments ? [...this.comments, ...data] : data;
          },
          error: (error) => {
            this.error = 'Error loading more comments';
            setTimeout(() => (this.error = ''), 3000);
            console.error('Error fetching comments:', error);
          },
        });
      }
    }
  }, 1000);

  ngOnInit(): void {
    this.userser.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        this.rout.navigate(['/login']);
      },
    });
    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get('postId');
    if (postId) {
      this.comment.getComments(Number(postId)).subscribe({
        next: (data: CommentReq[]) => {
          this.comments = data;
        },
        error: (error) => {
          this.error = 'Error loading comments';
          setTimeout(() => (this.error = ''), 3000);
          console.error('Error fetching comments:', error);
        },
      });
    }
  }
}
