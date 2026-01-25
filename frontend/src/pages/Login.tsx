import { useState } from "react";
import { login } from "../services/api";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit(event: React.FormEvent) {
        event.preventDefault();

        try {
        const response = await login(email, password);
        console.log("Resposta do backend:", response.data);
        } catch (error) {
        console.error("Erro no login:", error);
        }
    }

    return (
        <div>
            <h2>Login</h2>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Email</label>
                    <input 
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div>
                    <label>Senha</label>
                    <input 
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                <button type="submit">Entrar</button>
            </form>
        </div>
    );
}

export default Login