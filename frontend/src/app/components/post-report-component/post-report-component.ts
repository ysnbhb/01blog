import { Component, Input } from '@angular/core';
import { Admin } from '../../services/admin';
import { Router } from '@angular/router';
import { Post } from '../../services/post';
import { HidePostPopup } from '../hide-post-popup/hide-post-popup';
import { DeletePostPopup } from '../delete-post-popup/delete-post-popup';

@Component({
  selector: 'app-post-report-component',
  imports: [HidePostPopup, DeletePostPopup],
  templateUrl: './post-report-component.html',
  styleUrl: './post-report-component.css',
})
export class PostReportComponent {
  posts: any[] = [];
  idPost!: number;
  ishide!: boolean;
  isLoading = false;
  errro!: string;
  succues!: string;
  constructor(private adminService: Admin, private router: Router, private post: Post) {}

  ngOnInit() {
    this.loadReportedPosts();
  }

  confirmhide(action: boolean) {
    if (action) {
      this.adminService.hidePost(this.idPost).subscribe({
        next: (hide) => {
          console.log(hide);

          this.posts = this.posts.map((post) => {
            if (post.postid == this.idPost) {
              return { ...post, hide };
            }
            return post;
          });
          this.ishide = false;
          this.idPost = 0;
        },
        error: () => {
          this.setError('fieled to hide post');
          this.ishide = false;
          this.idPost = 0;
        },
      });
    } else {
      this.ishide = false;
      this.idPost = 0;
    }
  }

  loadReportedPosts() {
    this.isLoading = true;
    this.adminService.getReportPost().subscribe({
      next: (data) => {
        this.posts = data;
      },
      error: (error) => {
        console.log(error);
      },
    });
    this.isLoading = false;
  }

  viewPost(postId: number) {
    this.router.navigate(['admin/reports/post', postId]);
  }

  deletePost(postId: number) {
    this.idPost = postId;
  }

  hidePost(postId: number) {
    this.ishide = true;
    this.idPost = postId;
    console.log(this.idPost);
  }

  onPostDeleted(id: number) {
    if (id == 0) {
      this.idPost = 0;
    } else {
      this.posts = this.posts.filter((p) => p.postid !== id);
      this.idPost = 0;
    }
  }
  removepost(action: boolean) {
    if (action) {
      this.post.removePost(this.idPost).subscribe({
        next: () => {
          this.onPostDeleted(this.idPost);
          this.setSuccues('Post deleted successfully.');
        },
        error: (err) => {
          this.setError(err.error || 'Error deleting post');
        },
      });
    } else {
      this.idPost = 0;
    }
  }

  setSuccues(succues: string) {
    this.succues = succues;
    this.errro = '';
    this.idPost = 0;
    setTimeout(() => {
      this.succues = '';
    }, 2000);
  }

  setError(error: string) {
    this.errro = error;
    this.idPost = 0;
    setTimeout(() => {
      this.errro = '';
    }, 2000);
  }
}
