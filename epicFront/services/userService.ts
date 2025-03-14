import type { $Fetch } from 'ofetch';
import type { LoginResponse, User } from '~/types';

export default class AuthService {
    http: $Fetch;

    constructor(http: $Fetch | any) {
        this.http = http;
    }

    async list(): Promise<User> {
        return await this.http("/user")
    }
}