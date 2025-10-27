import {UserRes } from "./User.model";

export interface CommentReq {
    id: number;
    content: string;
    postId: number;
    user: UserRes;
    numOflike: number;
    createdAt: string;
    isliked: boolean;
}
