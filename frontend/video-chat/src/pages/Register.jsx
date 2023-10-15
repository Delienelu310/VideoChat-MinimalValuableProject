
export default function Register(){
    
    const {register} = useAuth();

    const [error, setError] = useState(null);

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
            <input id="password" name="password" className="m-2 form-control" value={password} setPassword={(e) => setPassword(e.target.value)}/>
            <button className="m-2 btn btn-success" onClick={() => {
                register({username, password});
                setError(true);
            }}>Register</button>
        </div>
    );

}