import { Component } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { Router } from '@angular/router';
import { UpdateForm } from "../components/update-form/update-form";

@Component({
  selector: 'app-update-post',
  imports: [NavBar, UpdateForm],
  templateUrl: './update-post.html',
  styleUrl: './update-post.css',
})
export class UpdatePost {
  user!: UserRes;
  constructor(private userget: User, private rout: Router) {}
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
