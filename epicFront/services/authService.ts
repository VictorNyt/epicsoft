import type { $Fetch } from 'ofetch';
import type { LoginResponse, User } from '~/types';

export default class AuthService {
    http: $Fetch;

    constructor(http: $Fetch | any) {
        this.http = http;
    }

    async login(body: any): Promise<LoginResponse> {
        return await this.http("/auth/login", { method: "POST", body })
    }

    async profile(): Promise<User> {
        return await this.http("/auth/profile")
    }

    async changepass(body: any): Promise<void> {
        return await this.http("/auth/update", { method: "PUT", body })
    }
}