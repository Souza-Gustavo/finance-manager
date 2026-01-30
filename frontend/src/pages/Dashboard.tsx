import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { getMe } from "../services/user";
import { getInstallments } from "../services/installments";
import type { Installment } from "../services/installments";
import { InstallmentForm } from "../components/InstallmentForm";
import Header from "../components/Header";

function Dashboard() {
  const { signOut } = useContext(AuthContext);
  const [user, setUser] = useState<any>(null);
  const [installments, setInstallments] = useState<Installment[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Carrega parcelas do backend
  async function loadInstallments() {
    try {
      const data = await getInstallments();
      setInstallments(data);
    } catch (err) {
      console.error("Erro ao carregar parcelas", err);
      setError("Não foi possível carregar as parcelas");
    }
  }

  // Carrega dados do usuário e parcelas
  useEffect(() => {
    async function loadData() {
      try {
        setLoading(true);
        const userData = await getMe();
        setUser(userData);

        await loadInstallments();
      } catch (err) {
        console.error("Erro ao carregar dados", err);
        setError("Não foi possível carregar os dados do usuário");
      } finally {
        setLoading(false);
      }
    }

    loadData();
  }, []);

  if (loading) return <p>Carregando...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div style={{ padding: "20px" }}>
      <Header />

      <h2>Dashboard</h2>

      {user && (
        <>
          <p>Bem-vindo, {user.name}</p>
          <p>Email: {user.email}</p>
        </>
      )}

      <button onClick={signOut} style={{ marginBottom: "20px" }}>
        Sair
      </button>

      <hr />

      {/* Formulário de cadastro de parcelas */}
      <InstallmentForm onCreated={loadInstallments} />

      {/* Lista de parcelas */}
      {installments.length === 0 ? (
        <p>Nenhuma parcela cadastrada.</p>
      ) : (
        <ul>
          {installments.map((item) => (
            <li key={item.id}>
              {item.description} - R${" "}
              {item.totalAmount?.toFixed(2) ?? "0.00"} ({item.totalParcels} parcela
              {item.totalParcels > 1 ? "s" : ""}) - Status: {item.status}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Dashboard;
