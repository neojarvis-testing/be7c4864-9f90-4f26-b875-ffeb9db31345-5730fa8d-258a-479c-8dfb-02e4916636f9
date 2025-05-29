import React,{useState,useEffect} from 'react'; 
import {useSelector} from 'react-redux'; 
import AdminMenu from '../AdminComponents/AdminNavbar'; 
import LoanManagerMenu from '../LoanManagerComponents/LoanManagerNavbar'; 
import StudentMenu from '../StudentComponents/StudentNavbar'; 
import {useNavigate} from "react-router-dom"
import {useDispatch} from "react-redux"
import {logout} from '../userSlice'
import ErrorPage from "./ErrorPage"

import CollegeApproval from '../AdminComponents/CollegeApproval';
import CollegeForm from '../AdminComponents/CollegeForm';
import ViewColleges from '../AdminComponents/ViewColleges';
import AdminViewFeedBack from '../AdminComponents/ViewFeedback';

//loan Components
import LoanForm from "../LoanManagerComponents/LoanForm";
import ViewLoans from "../LoanManagerComponents/ViewLoans";
import LoanRequest from "../LoanManagerComponents/LoanRequest";
import ViewFeedback from "../LoanManagerComponents/ViewFeedback";

//student Components
import ViewAllStudentColleges from "../StudentComponents/ViewAllColleges";
import ViewAllStudentLoans from "../StudentComponents/ViewAllLoans";
import StudentAppliedColleges from "../StudentComponents/AppliedColleges";
import StudentAppliedLoans from "../StudentComponents/AppliedLoans";
import StudentPostFeedback from "../StudentComponents/StudentPostFeedback";
import StudentMyFeedback from "../StudentComponents/StudentMyFeedback";

import Dashboard from './Dashboard';

const Home = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const user = useSelector((state)=>state.user);
    const [currentPage, setCurrentPage] = useState("Dashboard");
   
    let userRole = user?.userRole || localStorage.getItem("userRole");
    let username = user?.username || localStorage.getItem("username");  
    let userId = user?.userId || localStorage.getItem("userId");  
    let isAuthenicated = user?.isAuthenicated || localStorage.getItem("isAuthenicated");  
    let token = user?.tiken || localStorage.getItem("token");  

    //look for not auth!
    useEffect(()=>{
        if(!isAuthenicated){
            dispatch(logout());  //temp for ease in dev
            navigate("/login");
    }
    },[isAuthenicated,navigate]);


    var selectedMenuBar = null;

    if(userRole==="Admin"){selectedMenuBar = <AdminMenu username={username} userRole={userRole} setCurrentPage={setCurrentPage} />}
    else if(userRole==="LoanManager"){selectedMenuBar = <LoanManagerMenu username={username} userRole={userRole} setCurrentPage={setCurrentPage} />}
    else if(userRole==="Student"){selectedMenuBar = <StudentMenu username={username} userRole={userRole} setCurrentPage={setCurrentPage}  />}
    else{ 
        <ErrorPage message="UNAUTHORIZED !" />
    };

const renderPage = () =>{
    switch(currentPage){
        //common
        case 'Dashboard': return <Dashboard  username={username} userRole={userRole}  />;
        //Admin
        case 'CollegeApproval': return <CollegeApproval  username={username} userRole={userRole}  userId={userId}    token={token}  />;
        case 'CollegeForm': return <CollegeForm  username={username} userRole={userRole}   userId={userId}  token={token} />;
        case 'ViewColleges': return <ViewColleges  username={username} userRole={userRole}   userId={userId}   token={token}    />;
        case 'AdminViewFeedBack': return <AdminViewFeedBack  username={username} userRole={userRole}   userId={userId}   token={token}   />;

        //LoanManager 
        case 'LoanForm': return <LoanForm  username={username} userRole={userRole}  userId={userId}    token={token}  />;
        case 'ViewLoans': return <ViewLoans  username={username} userRole={userRole}  userId={userId}    token={token}  />;
        case 'LoanRequest': return <LoanRequest  username={username} userRole={userRole}  userId={userId}   token={token}   />;
        case 'ViewFeedback': return <ViewFeedback  username={username} userRole={userRole}  userId={userId}   token={token}  />;

        //Student
        case 'ViewAllStudentColleges': return <ViewAllStudentColleges  username={username} userRole={userRole}  userId={userId} token={token} />;
        case 'ViewAllStudentLoans': return <ViewAllStudentLoans  username={username} userRole={userRole}  userId={userId}  token={token}   />;
        case 'StudentAppliedColleges': return <StudentAppliedColleges  username={username} userRole={userRole}  userId={userId} token={token} />;
        case 'StudentAppliedLoans': return <StudentAppliedLoans  username={username} userRole={userRole}  userId={userId} token={token} />;
        case 'StudentPostFeedback': return <StudentPostFeedback  username={username} userRole={userRole}  userId={userId} token={token} />;
        case 'StudentMyFeedback': return <StudentMyFeedback  username={username} userRole={userRole}  userId={userId} token={token} />;

        default:  return <Dashboard  username={username} userRole={userRole}   />;
    }
};

return (
    <div>
        {selectedMenuBar}
        <div className='page-content'>
         
            {renderPage()}
            <div>
            <h1>EDUINVESTOR</h1> |  <h1>Contact Us</h1>
            </div>
          
        </div>
    </div>); 
};

export default Home;