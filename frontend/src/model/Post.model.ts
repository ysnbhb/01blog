import { User } from "./User.model";

export interface PostReq {
    id: number;
    content: string;
    urlPhot: string;
    typePhoto: "image" | "video" | string;
    createdAt: string;
    updatedAt?: string;
    user: User;
}
