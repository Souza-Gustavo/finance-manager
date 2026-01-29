import api from "./api";

export interface User {
    id: number;
    name: string;
    email: string;
}

export async function getME(): Promise<User> {
    const response = await api.get("/users/me");
    return response.data;
}