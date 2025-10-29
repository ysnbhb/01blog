import { Component, OnInit } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { User } from '../services/user';
import { UserRes } from '../../model/User.model';
import { PostReq } from '../../model/Post.model';
import { Post } from '../services/post';
import { Router, RouterLink } from '@angular/router';
import { PostComponent } from '../components/post/post';
import { ReportPopup } from '../components/report-popup/report-popup';
import { ErrorShow } from "../components/error-show/error-show";
import { DeletePostPopup } from "../components/delete-post-popup/delete-post-popup";
import { SuccuesShow } from "../components/succues-show/succues-show";

@Component({
  selector: 'app-post-view',
  imports: [NavBar, PostComponent, ReportPopup, ErrorShow, DeletePostPopup, SuccuesShow, RouterLink],
  templateUrl: './post-view.html',
  styleUrl: './post-view.css',
})
export class PostView implements OnInit {
  user!: UserRes;
  post!: PostReq;
  isreport: boolean = false;
  error!: string;
  succues!: string;
  delete: boolean = false;
  setSuccues(succues: string) {
    this.succues = succues;
    this.error = '';
    setTimeout(() => {
      this.succues = '';
    }, 2000);
  }
  constructor(private userser: User, private postgeter: Post, private rout: Router) {}
  confirmDelete(id: boolean) {
    if (id) {
      this.removepost();
    }else {
      this.delete = false;
    }
  }
  addPost(_: number) {
    this.delete = true;
  }
  
  ngOnInit(): void {
    this.userser.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        console.error('Error fetching user data');
        this.rout.navigate(['/login']);
      },
    });
    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get('postId');
    if (postId) {
      this.postgeter.getPost(Number(postId)).subscribe({
        next: (data: PostReq) => {
          this.post = data;
        },
        error: (error) => {
          // this.error = error.error;
          this.setError(error.error);
          console.error('Error fetching post data:', error);
        },
      });
    }
  }

  setReport(_: number) {
    this.isreport = true;
  }
  reported(_: boolean) {
    this.isreport = false;
  }

  setError(error: string) {
    this.error = error;
    setTimeout(() => {
      this.error = '';
    }, 2000);
  }
  async removepost() {
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(`http://localhost:8080/api/delete_post?postId=${this.post.id}`, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (res.ok) {
        this.rout.navigate(['/']);
      } else {
        let data = await res.json();
        this.setError(data.error);
      }
    } catch (err) {
      this.setError('Error deleting post');
    }
  }
}
