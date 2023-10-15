import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { useNavigate } from "react-router-dom";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}){

    const navigate = useNavigate();

    function register({username, password}){
        return apiClient.post(`/register`, {username, password})
            .then(response => {
                if(response.status != 200){
                    throw response;
                }
                login({username, password});
            }).catch(error => {
                logout();
            });
    }

    function login({username, password}){
        return apiClient.post(`/login/${username}`, {value: password})
            .then(response => {
                if(response.status != 200) throw response;

                setUsername(username);
                setAuthorised(true);
                navigate("/");
            }).catch(error => {
                logout();
            });
    }

    function logout(){
        setAuthorised(false);
        setUsername(null);
    }

    const [username, setUsername] = useState("");
    const [isAuthorised, setAuthorised] = useState(false);

    return (
        <AuthContext.Provider value={{username, isAuthorised, register, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
}