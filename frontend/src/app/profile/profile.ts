import { Component, OnInit } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { ActivatedRoute, Router } from '@angular/router';
import { HaederProfile } from '../components/haeder-profile/haeder-profile';
import { PostReq } from '../../model/Post.model';
import { Post } from '../services/post';
import { PostComponent } from '../components/post/post';
import { throttle } from '../../utils/throttle';
import { ReportPopup } from '../components/report-popup/report-popup';
import { DeletePostPopup } from '../components/delete-post-popup/delete-post-popup';
import { ErrorShow } from '../components/error-show/error-show';
import { SuccuesShow } from '../components/succues-show/succues-show';

@Component({
  selector: 'app-profile',
  imports: [
    NavBar,
    HaederProfile,
    PostComponent,
    ReportPopup,
    DeletePostPopup,
    ErrorShow,
    SuccuesShow,
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {
  user!: UserRes;
  userProfile!: UserRes;
  posts!: PostReq[];
  uuid = '';
  offset = 0;
  post_id!: number;
  errro!: string;
  succues!: string;
  isreport = false;
  constructor(
    private usr: User,
    private route: ActivatedRoute,
    private router: Router,
    private post: Post
  ) {}

  handleScroll = throttle((event: Event) => {
    console.log('scroll');

    const target = event.target as HTMLElement;
    if (target.scrollHeight - target.scrollTop - target.clientHeight < 30) {
      this.offset += 10;
      this.GetPost();
    }
  }, 1000);

  setReport(id: number) {
    this.post_id = id;
    this.isreport = true;
  }

  reported(_: boolean) {
    this.post_id = 0;
    this.isreport = false;
  }
  addPost(id: number) {
    this.post_id = id;
  }
  GetPost() {
    this.post.getUserPost(this.uuid, this.offset).subscribe({
      next: (data: PostReq[]) => {
        if (this.posts) {
          this.posts.push(...data);
        } else {
          this.posts = data;
        }
      },
    });
  }
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.uuid = params.get('uuid') || '';
      this.usr.getProfile(this.uuid).subscribe({
        next: (user: UserRes) => {
          this.userProfile = user;
        },
        error: () => {},
      });
      this.GetPost();
    });
    this.usr.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        this.router.navigate(['/login']);
      },
    });
  }
  setSuccues(succues: string) {
    this.succues = succues;
    this.errro = '';
    this.post_id = 0;
    setTimeout(() => {
      this.succues = '';
    }, 2000);
  }
  confirmDelete(id: boolean) {
    if (id) {
      this.removepost();
    } else {
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

  onPostDeleted(id: number) {
    if (id == 0) {
      this.post_id = 0;
    } else {
      this.posts = this.posts.filter((p) => p.id !== id);
      this.post_id = 0;
    }
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
