import { UserRes } from './User.model';

export interface PostReq {
  id: number;
  content: string;
  urlPhot: string;
  typePhoto?: 'image' | 'video' | string;
  createdAt: string;
  user: UserRes;
  numOflike: number;
  numOfcomment: number;
  isliked: boolean;
}
