import { Component } from '@angular/core';
import { NavBar } from "../components/nav-bar/nav-bar";
import { Post } from "../components/post/post";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavBar, Post],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {}
