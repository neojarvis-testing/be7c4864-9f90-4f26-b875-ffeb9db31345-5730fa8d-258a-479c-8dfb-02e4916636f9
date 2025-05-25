import React from "react";
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import Login from './Components/Login'
import Signup from './Components/Signup'
import Home from './Components/HomePage'

const App = () => {
    return(
    <Router>
        <Routes>    
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/home" element={<Home />} />
        </Routes>
    </Router>
    );
};

export default App;