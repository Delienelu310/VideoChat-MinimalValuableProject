import { createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}){

    const [username, setUsername] = useState("");
    const [isAuthorised, setAuthorised] = useState(false);

    return (
        <AuthContext.Provider value={{username, isAuthorised}}>
            {children}
        </AuthContext.Provider>
    );
}