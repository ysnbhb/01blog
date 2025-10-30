export interface UserRes {
  uuid: string;
  name: string;
  lastName: string;
  username: string;
  email?: null | string;
  urlPhoto: string;
  role: string;
  mayAcount: boolean;
  hascon: boolean;
  status?: null | string;
  dateOfBirth: string;
  followersCount: number;
  followingCount: number;
}
