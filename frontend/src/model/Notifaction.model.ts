export interface Notification {
  isRead: boolean;
  postId: null | number;
  createdAt: string;
  toUsername: string;
  toUuid: string;
  fromUsername: string;
  fromUuid: string;
  id: number;
  type: string;
  content: string;
  url: string;
}
