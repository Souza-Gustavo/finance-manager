import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
});

export async function login(email: string, password: string) {
    const response = await api.post("/auth/login", {
        email,
        password,
    });

    return response.data;
}

export default api;