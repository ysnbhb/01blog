import { Component } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { PostsContainer } from '../components/posts-container/posts-container';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavBar, PostsContainer],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {}
