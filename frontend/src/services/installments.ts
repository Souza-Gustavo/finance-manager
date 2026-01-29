import api from "./api";

export interface installment {
    id: number;
    description: string;
    amount: number;
}

export async function getInstallments(): Promise<installment[]> {
    const response = await api.get("/installments");
    return response.data;
}