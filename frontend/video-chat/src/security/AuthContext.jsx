import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { useNavigate } from "react-router-dom";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}){

    // const navigate = useNavigate();

    function register({username, password}){
        console.log(password);
        console.log(username);
        return apiClient.post(`/register`, {username, password})
            .then(response => {
                if(response.status != 200){
                    logout();
                    return false;
                }
                return login({username, password});
            }).catch(error => {
                console.log(error);
                logout();
                return false;
            });
    }

    function login({username, password}){
        return apiClient.post(`/login`, {username, password})
            .then(response => {
                if(response.status != 200){
                    logout();
                    return false;
                };

                setUsername(username);
                setAuthorised(true);

                return true;
            }).catch(error => {
                logout();
                return false;
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