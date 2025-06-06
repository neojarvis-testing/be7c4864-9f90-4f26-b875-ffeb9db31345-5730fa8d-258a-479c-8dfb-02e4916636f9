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
// loan
import LoanForm from './LoanManagerComponents/LoanForm'
// import LoanRequest from '../LoanManagerComponents/LoanRequest';
// import ViewLoans from '../LoanManagerComponents/ViewLoans';
import ViewAllLoans from './StudentComponents/ViewAllLoans';
import LoanApplicationForm from './StudentComponents/LoanApplicationForm';
import StudentPostFeedback from './StudentComponents/StudentPostFeedback';
import AppliedLoans from './StudentComponents/AppliedLoans';

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

            <Route path='/loan-form' element={<LoanForm/>} />
            {/* <Route path='/loan-request' element={<LoanRequest/>}/>
            <Route path='/view-loans' element={<ViewLoans/>}/> */}

            <Route path='/applied-loans' element={<AppliedLoans/>}/>
            <Route path='/student-postfeedback' element={<StudentPostFeedback/>}/>
            <Route path='/loan-application' element={<LoanApplicationForm/>}/>
            <Route path='/view-allloans' element={<ViewAllLoans/>}/>
            
            

        </Routes>
    </Router>
    );
};

export default App;