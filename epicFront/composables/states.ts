import type { User } from "~/types";

export function usePrincipal() {
    return useState<User | null>('principal', () => null);
}