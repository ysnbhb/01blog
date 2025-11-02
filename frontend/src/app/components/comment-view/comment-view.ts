import { Component, Input} from '@angular/core';
import { CommentReq } from '../../../model/comment.model';
import { FormatDatePipe } from '../../pipe/format-date-pipe';

@Component({
  selector: 'app-comment-view',
  imports: [FormatDatePipe],
  templateUrl: './comment-view.html',
  styleUrl: './comment-view.css',
})
export class CommentView {
  @Input() comment!: CommentReq;
}
