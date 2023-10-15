import { Link } from "react-router-dom";
import { useAuth } from "../security/AuthContext";

export default function Header(){
    
    const {username, isAuthorised, logout} = useAuth();
    
    return (
        <div className="header">
            {isAuthorised ?
                
                    <>
                        <button className="m-2" onClick={logout}>Log out</button>
                        <Link className="m-2" >Account</Link>
                    </>
                    :
                    <>
                        <Link className="m-2" to="/register">Register</Link>
                        <Link className="m-2" to="/login">Login</Link>
                    </>
            }
            <hr/>
        
        </div>
    );
}