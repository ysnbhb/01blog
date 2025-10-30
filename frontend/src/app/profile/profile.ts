import { Component, OnInit } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { ActivatedRoute, Router } from '@angular/router';
import { HaederProfile } from '../components/haeder-profile/haeder-profile';
import { PostReq } from '../../model/Post.model';
import { Post } from '../services/post';
import { PostComponent } from "../components/post/post";

@Component({
  selector: 'app-profile',
  imports: [NavBar, HaederProfile, PostComponent],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {
  user!: UserRes;
  userProfile!: UserRes;
  posts!: PostReq[];
  uuid = '';
  constructor(
    private usr: User,
    private route: ActivatedRoute,
    private router: Router,
    private post: Post
  ) {
    this.route.paramMap.subscribe((params) => {
      this.uuid = params.get('uuid') || '';
    });
  }

  ngOnInit(): void {
    this.usr.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        this.router.navigate(['/login']);
      },
    });
    this.usr.getProfile(this.uuid).subscribe({
      next: (user: UserRes) => {
        this.userProfile = user;
      },
      error: () => {},
    });

    this.post.getUserPost(this.uuid).subscribe({
      next : (data : PostReq[]) => {
        this.posts = data
      }
    })
  }
}
