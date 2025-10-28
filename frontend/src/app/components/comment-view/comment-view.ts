import { Component, Input, OnInit } from '@angular/core';
import { CommentReq } from '../../../model/comment.model';
import { getTimeDifferenceInHours } from '../../../utils/formatDate';

@Component({
  selector: 'app-comment-view',
  imports: [],
  templateUrl: './comment-view.html',
  styleUrl: './comment-view.css',
})
export class CommentView implements OnInit {
  @Input() comment!: CommentReq;
  ngOnInit(): void {
    this.comment.createdAt = getTimeDifferenceInHours(this.comment.createdAt);
  }
}
