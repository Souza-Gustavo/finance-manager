import { useEffect, useState } from "react";
import api from "../services/api";
import Header from "../components/Header";

interface Installment {
  id: number;
  description: string;
  amount: number;
}

function Dashboard() {
  const [installments, setInstallments] = useState<Installment[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadInstallments() {
      try {
        const response = await api.get("/installments");
        setInstallments(response.data);
      } catch (error) {
        console.error("Erro ao carregar parcelas", error);
      } finally {
        setLoading(false);
      }
    }

    loadInstallments();
  }, []);

  if (loading) return <p>Carregando...</p>;

  return (
    <div>
      <Header />

      <h2>Dashboard</h2>

      {installments.length === 0 ? (
        <p>Nenhuma parcela cadastrada.</p>
      ) : (
        <ul>
          {installments.map(item => (
            <li key={item.id}>
              {item.description} - R$ {item.amount}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Dashboard;
