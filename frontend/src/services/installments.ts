import api from "./api";

export async function getInstallments() {
    const response = await api.get("/installments");
    return response.data;
}