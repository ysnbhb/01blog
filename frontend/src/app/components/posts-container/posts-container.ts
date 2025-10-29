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
  async ngOnInit(): Promise<void> {
    await this.GetPost();
  }

  handleScroll = throttle((event: Event) => {
    const target = event.target as HTMLElement;
    if (target.scrollHeight - target.scrollTop - target.clientHeight < 30) {
      this.offset += 10;
      this.GetPost();
    }
  }, 1000);
  async GetPost() {
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(`http://localhost:8080/api/posts?offset=${this.offset}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) {
        throw new Error(`Failed to fetch posts: ${res.status}`);
      }
      const data = await res.json();
      this.posts.push(...data);
    } catch (err) {
      this.setError('Error loading posts');
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

  async removepost() {
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(`http://localhost:8080/api/delete_post?postId=${this.post_id}`, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (res.ok) {
        this.onPostDeleted(this.post_id);
        this.setSuccues('Post deleted successfully.');
      } else {
        let data = await res.json();
        this.setError(data.error);
      }
    } catch (err) {
      this.setError('Error deleting post');
    }
  }
}
