import { createContext, useEffect, useState } from "react";
import type { ReactNode } from "react";
import { Navigate } from "react-router-dom";
import api from "../services/api";


interface User {
  id: number;
  name: string;
  email: string;
}

interface AuthContextData {
  isAuthenticated: boolean;
  user: User | null;
  signIn: (email: string, password: string) => Promise<void>;
  signOut: () => void;
}



export const AuthContext = createContext<AuthContextData>(
  {} as AuthContextData
);


export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const navigate = useNavigate();
  const isAuthenticated = !!user;

  async function loadUser() {
    try {
      const response = await api.get("/users/me");
      setUser(response.data);
    } catch {
      signOut();
    }
  }

  async function signIn(email: string, password: string) {
    try {
      const response = await api.post("/users/login", {
        email,
        senha: password,
      });

      const { token } = response.data;

      localStorage.setItem("token", token);

      await loadUser();
    } catch (error) {
      console.error(error);
      throw new Error("Login inválido");
    }
  }

  function signOut() {
    localStorage.removeItem("token");
    setIsAuthenticated(false);
    navigate("/login");
  }

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (token) {
      loadUser();
    }
  }, []);

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        user,
        signIn,
        signOut,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
