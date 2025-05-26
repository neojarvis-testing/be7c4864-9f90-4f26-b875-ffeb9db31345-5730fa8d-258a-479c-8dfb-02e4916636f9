import React from "react";
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import Login from './Components/Login'
import Signup from './Components/Signup'
import Home from './Components/HomePage'
// admin
import ViewColleges from './AdminComponents/ViewColleges'
import CollegeForm from './AdminComponents/CollegeForm'
import ViewFeedback from './AdminComponents/ViewFeedback'
import CollegeApproval from './AdminComponents/CollegeApproval'

const App = () => {
    return(
    <Router>
        <Routes>    
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/home" element={<Home />} />


            <Route path="/view-colleges" element={<ViewColleges />} />
            <Route path="/college-form" element={<CollegeForm />} />
            <Route path="/view-feedback" element={<ViewFeedback />} />
            <Route path="/college-approval" element={<CollegeApproval />} />

            

        </Routes>
    </Router>
    );
};

export default App;