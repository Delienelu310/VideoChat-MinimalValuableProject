import { Link } from "react-router-dom";

export default function Header(){
    return (
        <div className="header">
            <Link>Register</Link>
            <Link>Login</Link>
        </div>
    );
}