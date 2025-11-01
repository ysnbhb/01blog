import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { getTimeDifferenceInHours } from '../../../utils/formatDate';
import { CommonModule } from '@angular/common';
import { UserRes } from '../../../model/User.model';
import { MarkdownModule } from 'ngx-markdown';
import { Comments } from '../../services/comments';
import { ErrorShow } from '../error-show/error-show';
import { SuccuesShow } from '../succues-show/succues-show';
import { RouterLink } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-post',
  imports: [CommonModule, MarkdownModule, ErrorShow, SuccuesShow, RouterLink, FormsModule],
  templateUrl: './post.html',
  styleUrl: './post.css',
})
export class PostComponent implements OnInit {
  @Input() post!: PostReq;
  @Output() remove = new EventEmitter<number>();
  @Output() report = new EventEmitter<number>();
  @Input() user!: UserRes;
  @Input() showAll!: boolean;
  errro!: String;
  succes!: String;
  showImagePopup = false;
  currentImageIndex = 0;
  constructor(private commint: Comments) {}

  removepost() {
    this.remove.emit(this.post.id);
  }

  reportpost() {
    this.report.emit(this.post.id);
  }

  ngOnInit(): void {
    this.post.createdAt = getTimeDifferenceInHours(this.post.createdAt);
    if (!this.showAll && this.post.content.length > 200) {
      this.post.content = this.post.content.slice(0, 200);
    }
  }
  async submitComment(form: NgForm) {
    const comment = form.value;
    this.commint.createComment(this.post.id, comment.content).subscribe({
      next: (_) => {
        form.reset();
        this.succes = 'Comment submitted successfully';
        this.errro = '';
        this.post.numOfcomment++;
        setTimeout(() => {
          this.succes = '';
        }, 1000);
        ('Comment created successfully');
      },
      error: (_) => {
        this.errro = 'Error submitting comment';
        this.succes = '';
        setTimeout(() => {
          this.errro = '';
        }, 1000);
      },
    });
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

  openImagePopup(index: number): void {
    this.currentImageIndex = index;
    this.showImagePopup = true;
    document.body.style.overflow = 'hidden';
    document.addEventListener('keydown', this.handleKeyPress);
  }

  closeImagePopup(): void {
    this.showImagePopup = false;
    document.body.style.overflow = '';

    document.removeEventListener('keydown', this.handleKeyPress);
  }

  nextImage(event: Event): void {
    event.stopPropagation();
    const imageCount = this.getImageCount();
    this.currentImageIndex = (this.currentImageIndex % imageCount) + 1;
    if (this.currentImageIndex == imageCount) {
      this.currentImageIndex = 0;
    }
  }

  previousImage(event: Event): void {
    event.stopPropagation();
    if (this.currentImageIndex > 0) {
      this.currentImageIndex--;
    }
  }
  getImageCount(): number {
    return this.post.images.filter((img: any) => img.type === 'image').length;
  }

  handleKeyPress = (event: KeyboardEvent): void => {
    if (!this.showImagePopup) return;

    switch (event.key) {
      case 'Escape':
        this.closeImagePopup();
        break;
      case 'ArrowRight':
        if (this.currentImageIndex < this.getImageCount() - 1) {
          this.currentImageIndex++;
        }
        break;
      case 'ArrowLeft':
        if (this.currentImageIndex > 0) {
          this.currentImageIndex--;
        }
        break;
    }
  };

  // Clean up on component destroy
  ngOnDestroy(): void {
    document.removeEventListener('keydown', this.handleKeyPress);
    document.body.style.overflow = '';
  }
}
