import { Component, OnInit } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { PostsContainer } from '../components/posts-container/posts-container';
import { Router } from '@angular/router';
import { User } from '../services/user';
import { UserRes } from '../../model/User.model';
import { ReportPopup } from "../components/report-popup/report-popup";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavBar, PostsContainer],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home implements OnInit {
  user!: UserRes;
  constructor(private rout: Router, private userser: User) {}
  ngOnInit(): void {
    this.userser.getUser().subscribe({
      next: (data: UserRes) => {        
        this.user = data;
      },
      error: (_) => {
        this.rout.navigate(['/login']);
      },
    });
  }

  scroll(){
    console.log('scrolled home');
  }
}
