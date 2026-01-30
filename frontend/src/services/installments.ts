import api from "./api";

export interface Installment {
  id: number;
  description: string;
  totalAmount: number;
  totalParcels: number;
  startDate: string;
  status: string;
  category?: string | null;
}

// Listar todas as parcelas
export async function getInstallments(): Promise<Installment[]> {
    const token = localStorage.getItem("token");
    if (!token) throw new Error("Token não encontrado");

    const response = await api.get("/installments", {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    return response.data;
}

// Criar uma nova parcela
export async function createInstallment(description: string, amount: number) {
  const token = localStorage.getItem("token");
  if (!token) throw new Error("Token não encontrado");

  const body = {
    description: description,
    totalAmount: amount,
    totalParcels: 1, // valor padrão
    startDate: new Date().toISOString().split("T")[0], // YYYY-MM-DD
    categoryId: null
  };

  const response = await api.post("/installments", body, {
    headers: { Authorization: `Bearer ${token}` },
  });

  return response.data as Installment;
}