import { Component, Input } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Comments } from '../../services/comments';

@Component({
  selector: 'app-comment-form',
  imports: [FormsModule],
  templateUrl: './comment-form.html',
  styleUrl: './comment-form.css',
})
export class CommentForm {
  @Input() post!: number;
  @Input() image !: string;
  constructor(private commint: Comments) {}
  async submitComment(form: NgForm) {
    const comment = form.value;
    this.commint.createComment(this.post, comment.content).subscribe({
      next: (_) => {
        form.reset();
        console.log('Comment created successfully');
      },
      error: (error) => {},
    });
  }
}
