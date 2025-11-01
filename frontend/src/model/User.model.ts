export interface UserRes {
  uuid: string;
  name: string;
  lastName: string;
  username: string;
  email?: null | string;
  urlPhoto: string;
  role: string;
  mayAcount: boolean;
  hasCon: boolean;
  status?: null | string;
  dateOfBirth: string;
  followers: number;
  following: number;
}
