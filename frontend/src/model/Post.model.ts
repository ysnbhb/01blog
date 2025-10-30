import { image } from './images';
import { UserRes } from './User.model';

export interface PostReq {
  id: number;
  content: string;
  title: string;
  urlPhot: string;
  typePhoto?: 'image' | 'video' | string;
  createdAt: string;
  user: UserRes;
  numOflike: number;
  images: image[];
  numOfcomment: number;
  isliked: boolean;
}
