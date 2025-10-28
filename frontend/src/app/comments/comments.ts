import { Component } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { Router } from '@angular/router';
import { User } from '../services/user';
import { UserRes } from '../../model/User.model';
import { CommentReq } from '../../model/comment.model';
import { Comments } from '../services/comments';
import { CommentView } from "../components/comment-view/comment-view";

@Component({
  selector: 'app-comments',
  imports: [NavBar, CommentView],
  templateUrl: './comments.html',
  styleUrl: './comments.css',
})
export class CommentsComponent {
  user!: UserRes;
  comments!: CommentReq[];
  constructor(private rout: Router, private userser: User , private comment : Comments) {}
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
          console.error('Error fetching comments:', error);
        },
      });
    }
  }
}
