import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import AuthProvider from './security/AuthContext';

import Login from './pages/Login';
import Register from './pages/Register';
import Welcome from './pages/Welcome';
import Room from './pages/Room';
import Header from './components/Header';

function App() {
    return (
      <div className="App">
        <AuthProvider>
          <BrowserRouter>
            <Header/>
            <Routes>
              <Route path="/" element={<Welcome/>}/>
              <Route path="/register" element={<Register/>}/>
              <Route path="/login" element={<Login/>}/>
              <Route path="/rooms/:roomId" element={<Room/>}/>
          
            </Routes>
          </BrowserRouter>
        </AuthProvider>
        
      </div>
    );
}

export default App;
