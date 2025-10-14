export interface User {
    uuid: string;
    name: string;
    lastName: string;
    username: string;
    email?: null | string;
    urlPhoto: string;
    role: string;
    status?: null | string;
    dateOfBirth: string;
    followersCount: number;
    followingCount: number;
}

