import './App.css';
import { BrowserRouter } from 'react-router-dom';

import AuthProvider from './security/AuthContext';

import Login from './pages/Login';
import Register from './pages/Register';
import Welcome from './pages/Welcome';

function App() {
    return (
      <div className="App">
        <AuthProvider>
          <BrowserRouter>
            <Header/>
            <Routes>
              <Route path="/" component={Welcome}/>
              <Route path="/register" component={Register}/>
              <Route path="/login" component={Login}/>

            </Routes>
          </BrowserRouter>
        </AuthProvider>
        
      </div>
    );
}

export default App;
