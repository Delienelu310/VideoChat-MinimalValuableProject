import { Link } from "react-router-dom";
import { useAuth } from "../security/AuthContext";

export default function Header(){
    
    const {username, isAuthorised, logout} = useAuth();
    
    return (
        <div className="header">
            {isAuthorised ?
                <>
                    <Link to="/register">Register</Link>
                    <Link to="/login">Login</Link>
                </>    
                :
                <>
                    <button onClick={logout}>Log out</button>
                    <Link>Account</Link>
                </>
            }
        
        </div>
    );
}