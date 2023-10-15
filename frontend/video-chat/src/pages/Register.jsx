import { useState } from "react";
import { useAuth } from "../security/AuthContext";
import { useNavigate } from "react-router-dom";

export default function Register(){
    
    const {register} = useAuth();

    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const [username, setUsername] = useState();
    const [password, setPassword] = useState();

    return (
        <div className="login">
            {error &&
                <div style={{color: "red"}}>
                    {error}
                </div>
            }
            <label for="username">Username</label>
            <input id="username" name="username" className="m-2 form-control" value={username} onChange={(e) => setUsername(e.target.value)}/>
            <label for="password">Password</label>
            <input id="password" name="password" className="m-2 form-control" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <button className="m-2 btn btn-success" onClick={() => {
                register({username, password})
                    .then(response => {
                        if(response){
                            setError(false);
                            navigate("/");
                        }else{
                            setError(true);
                        }
                    });
            }}>Register</button>
        </div>
    );

}