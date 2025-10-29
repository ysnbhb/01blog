import { Component, OnInit } from '@angular/core';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import {  Router } from '@angular/router';
import { NavBar } from "../components/nav-bar/nav-bar";
import { PostForm } from "../components/post-form/post-form";

@Component({
  selector: 'app-create-post',
  imports: [NavBar, PostForm],
  templateUrl: './create-post.html',
  styleUrl: './create-post.css',
})
export class CreatePost implements OnInit {
  user!: UserRes;
  constructor(private userget: User , private rout : Router) {}
  ngOnInit(): void {
    this.userget.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        this.rout.navigate(['/login']);
        console.error('Error fetching user data');
      },
    });
  }
}
