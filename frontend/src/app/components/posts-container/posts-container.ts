import { Component, Input, OnInit } from '@angular/core';
import { PostReq } from '../../../model/Post.model';
import { PostComponent } from '../post/post';
import { PostForm } from '../post-form/post-form';
import { DeletePostPopup } from '../delete-post-popup/delete-post-popup';
import { ErrorShow } from '../error-show/error-show';
import { ReportPopup } from '../report-popup/report-popup';
import { SuccuesShow } from '../succues-show/succues-show';
import { throttle } from '../../../utils/throttle';
import { UserRes } from '../../../model/User.model';
import { Post } from '../../services/post';

@Component({
  selector: 'app-posts-container',
  imports: [PostComponent, DeletePostPopup, ErrorShow, ReportPopup, SuccuesShow],
  templateUrl: './posts-container.html',
  styleUrl: './posts-container.css',
})
export class PostsContainer implements OnInit {
  posts: PostReq[] = [];
  offset = 0;
  post_id = 0;
  isreport: boolean = false;
  errro!: string;
  succues!: string;
  @Input() user!: UserRes;
  @Input() subscription = false;

  constructor(private post: Post) {}
  ngOnInit(): void {
    this.GetPost();
  }

  handleScroll = throttle((event: Event) => {
    const target = event.target as HTMLElement;
    if (target.scrollHeight - target.scrollTop - target.clientHeight < 30) {
      this.offset += 10;
      this.GetPost();
    }
  }, 1000);
  GetPost() {
    if (!this.subscription) {
      this.post.getPosts(this.offset).subscribe({
        next: (data) => {
          this.posts.push(...data);
        },
        error: () => {
          this.setError('Error loading posts');
        },
      });
    } else {
      this.post.getSubPosts(this.offset).subscribe({
        next: (data) => {
          this.posts.push(...data);
        },
        error: () => {
          this.setError('Error loading posts');
        },
      });
    }
  }
  setSuccues(succues: string) {
    this.succues = succues;
    this.errro = '';
    this.post_id = 0;
    setTimeout(() => {
      this.succues = '';
    }, 2000);
  }

  setReport(id: number) {
    this.post_id = id;
    this.isreport = true;
  }
  addPost(id: number) {
    this.post_id = id;
  }

  confirmDelete(id: boolean) {
    if (id) {
      this.removepost();
    } else {
      this.post_id = 0;
    }
  }

  reported(_: boolean) {
    this.post_id = 0;
    this.isreport = false;
  }

  onPostDeleted(id: number) {
    if (id == 0) {
      this.post_id = 0;
    } else {
      this.posts = this.posts.filter((p) => p.id !== id);
      this.post_id = 0;
    }
  }

  setError(error: string) {
    this.errro = error;
    this.post_id = 0;
    setTimeout(() => {
      this.errro = '';
    }, 2000);
  }

  removepost() {
    this.post.removePost(this.post_id).subscribe({
      next: () => {
        this.onPostDeleted(this.post_id);
        this.setSuccues('Post deleted successfully.');
      },
      error: (err) => {
        this.setError(err.error || 'Error deleting post');
      },
    });
  }
}
