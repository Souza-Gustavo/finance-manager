import api from "./api";

export interface User {
    id: number;
    name: string;
    email: string;
}

export async function getMe(): Promise<User> {
    const token = localStorage.getItem("token");
    if (!token) throw new Error("Token não encontrado");

    const response = await api.get("/users/me", {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    return response.data;
}