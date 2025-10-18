import { Component } from '@angular/core';
import { User } from '../../../model/User.model';
import { PostReq } from '../../../model/Post.model';

@Component({
  selector: 'app-post',
  imports: [],
  templateUrl: './post.html',
  styleUrl: './post.css',
})
export class Post {
  post: PostReq = {
    id: 1,
    content:
      'Just finished building my first Spring Boot + Angular project! 🚀\n hgshdghjsgdhjsdhfghfds sd hgdhsgfsdhgf dfsgfsdhghsdgfhgsdh gs dhgfsdhgfhdsgfghf dshgfsdhgfsdh gfhgsdf hgfsdhfhgfsdf hgfhsdgfhgfsdhhgsdf hgfhgsd',
    urlPhot: 'https://picsum.photos/600/400', // random placeholder image
    typePhoto: 'image',
    createdAt: '2025-10-15T10:30:00Z',
    numbofcomment: 10,
    numboflike: 10,
    user: {
      username: 'dev_master',
      name: 'John',
      lastName: 'Doe',
      uuid: '123e4567-e89b-12d3-a456-426614174000',
      urlPhoto: 'https://i.pravatar.cc/150?img=12',
      email: 'john.doe@example.com',
      role: 'USER',
      followersCount: 0,
      followingCount: 10,
      dateOfBirth: '',
    },
  };
}
