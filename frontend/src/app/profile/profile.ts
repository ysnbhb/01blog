import { Component } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [NavBar],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile {
  user!: UserRes;
  userProfile!: UserRes;

  constructor(private usr: User, private route: ActivatedRoute, private router: Router) {}
}
