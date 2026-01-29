import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { getMe } from "../services/user";
import { getInstallments } from "../services/installments";
import { InstallmentForm } from "../components/InstallmentForm";
import Header from "../components/Header";

interface Installment {
  id: number;
  description: string;
  amount: number;
}

function Dashboard() {
  const [user, setUser] = useState<any>(null);
  const { signOut } = useContext(AuthContext);
  const [installments, setInstallments] = useState<Installment[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
  async function loadData() {
    try {
      const userData = await getMe();
      setUser(userData);

      const data = await getInstallments();
      setInstallments(data);
    } catch (error) {
      console.error("Erro ao carregar dados", error);
    } finally {
      setLoading(false);
    }
  }

  loadData();
}, []);

  if (loading) return <p>Carregando...</p>;

return (
  <div>
    <Header />

    <h2>Dashboard</h2>

    {user && (
      <>
        <p>Bem-vindo, {user.name}</p>
        <p>Email: {user.email}</p>
      </>
    )}

    <button onClick={signOut}>Sair</button>

    <hr />

    <InstallmentForm />

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
