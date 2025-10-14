import { User } from "./User.model";

export interface CommentReq {
    id: number;
    content: string;
    postId: number;
    user: User;
    numOflike: number;
    createdAt: string;
    isliked: boolean;
}
