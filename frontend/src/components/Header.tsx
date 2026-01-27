import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";

function Header() {
    const { signOut } = useContext(AuthContext);
    const navigate = useNavigate();

    function handleLogout() {
        signOut();
        navigate("/login");
    }

    return (
        <header>
            <h3>Finance Manager</h3>
            <button onClick={handleLogout}>Sair</button>
        </header>
    );
}

export default Header;