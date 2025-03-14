export interface LoginResponse {
    user: User;
    token: string;
}

export interface User {
    id: number;
    username: string;
    name: string;
    isAdmin: boolean
}