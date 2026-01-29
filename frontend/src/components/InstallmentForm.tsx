import { useState } from "react";
import api from "../services/api";

export function InstallmentForm() {
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [dueDate, setDueDate] = useState("");

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();

    await api.post("/installments", {
      description,
      amount: Number(amount),
      dueDate,
    });

    // limpa formulário (simples por enquanto)
    setDescription("");
    setAmount("");
    setDueDate("");
  }

  return (
    <form onSubmit={handleSubmit}>
      <h3>Nova Parcela</h3>

      <input
        type="text"
        placeholder="Descrição"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        required
      />

      <input
        type="number"
        placeholder="Valor"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        required
      />

      <input
        type="date"
        value={dueDate}
        onChange={(e) => setDueDate(e.target.value)}
        required
      />

      <button type="submit">Cadastrar</button>
    </form>
  );
}
