import { createContext, useState } from "react";
import type { ReactNode } from "react";
import api from "../services/api";

interface AuthContextData {
  isAuthenticated: boolean;
  signIn: (email: string, password: string) => Promise<void>;
  signOut: () => void;
}

export const AuthContext = createContext<AuthContextData>(
  {} as AuthContextData
);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    return !!localStorage.getItem("token");
  });

  async function signIn(email: string, password: string) {
    try {
    const response = await api.post("/users/login", {
      email,
      senha: password,
    });

    const { token } = response.data;
    localStorage.setItem("token", token);
    setIsAuthenticated(true);

  } catch (error: any) {
    console.error(error?.response?.data);
    alert("Login inválido");
  }
}

  function signOut() {
    localStorage.removeItem("token");
    setIsAuthenticated(false);
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        signIn,
        signOut,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
