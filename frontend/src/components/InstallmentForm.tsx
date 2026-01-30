import { useState } from "react";
import { createInstallment } from "../services/installments";

interface InstallmentFormProps {
  onCreated: () => void; // Função para recarregar a lista no Dashboard
}

export function InstallmentForm({ onCreated }: InstallmentFormProps) {
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState<number | "">("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    setError("");

    // Validação simples
    if (!description || !amount || amount <= 0) {
      setError("Preencha todos os campos corretamente.");
      return;
    }

    try {
      setLoading(true);

      // Chama o serviço atualizado que envia todos os campos corretos
      await createInstallment(description, Number(amount));

      // Limpa os campos do formulário
      setDescription("");
      setAmount("");

      // Atualiza a lista de parcelas no Dashboard
      onCreated();
    } catch (err: any) {
      console.error("Erro ao criar parcela:", err);

      // Exibe mensagem do backend se houver
      if (err.response?.data?.message) {
        setError(err.response.data.message);
      } else {
        setError("Não foi possível cadastrar a parcela");
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <form onSubmit={handleSubmit} style={{ margin: "20px 0" }}>
      <div style={{ marginBottom: "10px" }}>
        <label>Descrição:</label>
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          style={{ marginLeft: "10px" }}
        />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Valor:</label>
        <input
          type="number"
          value={amount}
          onChange={(e) =>
            setAmount(e.target.value ? Number(e.target.value) : "")
          }
          style={{ marginLeft: "10px" }}
        />
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <button type="submit" disabled={loading}>
        {loading ? "Cadastrando..." : "Cadastrar"}
      </button>
    </form>
  );
}
