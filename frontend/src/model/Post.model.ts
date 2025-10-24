import { User } from './User.model';

export interface PostReq {
  id: number;
  content: string;
  urlPhot: string;
  typePhoto?: 'image' | 'video' | string;
  createdAt: string;
  user: User;
  numOflike: number;
  numOfcomment: number;
  isliked: boolean;
}
