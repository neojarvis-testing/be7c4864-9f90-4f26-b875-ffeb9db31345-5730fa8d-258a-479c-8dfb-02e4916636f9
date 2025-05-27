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
import ViewFeedBack from '../AdminComponents/ViewFeedback';

//loan Components
import LoanForm from "../LoanManagerComponents/LoanForm";
import ViewLoans from "../LoanManagerComponents/ViewLoans";
import LoanRequest from "../LoanManagerComponents/LoanRequest";
import ViewFeedback from "../LoanManagerComponents/ViewFeedback";

//student Components
import StudentViewColleges from "../StudentComponents/ViewAllColleges";
import StudentViewLoans from "../StudentComponents/ViewAllLoans";
import StudentAppliedColleges from "../StudentComponents/AppliedColleges";
import StudentAppliedLoans from "../StudentComponents/AppliedLoans";
import StudentPostFeedBack from "../StudentComponents/StudentPostFeedback";
import StudentViewFeedBacks from "../StudentComponents/StudentMyFeedback";

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
            dispatch(logout());
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
        case 'CollegeApproval': return <CollegeApproval  username={username} userRole={userRole}  userId={userId}  />;
        case 'CollegeForm': return <CollegeForm  username={username} userRole={userRole}   userId={userId}  token={token} />;
        case 'ViewColleges': return <ViewColleges  username={username} userRole={userRole}   userId={userId}   />;
        case 'ViewFeedBack': return <ViewFeedBack  username={username} userRole={userRole}   userId={userId}   />;

        //LoanManager 
        case 'LoanForm': return <LoanForm  username={username} userRole={userRole}  userId={userId}  />;
        case 'ViewLoans': return <ViewLoans  username={username} userRole={userRole}  userId={userId}  />;
        case 'LoanRequest': return <LoanRequest  username={username} userRole={userRole}  userId={userId}  />;
        case 'ViewFeedback': return <ViewFeedback  username={username} userRole={userRole}  userId={userId}  />;

        //Student
        case 'StudentViewColleges': return <StudentViewColleges  username={username} userRole={userRole}  userId={userId}  />;
        case 'StudentViewLoans': return <StudentViewLoans  username={username} userRole={userRole}  userId={userId}  />;
        case 'StudentAppliedColleges': return <StudentAppliedColleges  username={username} userRole={userRole}  userId={userId}  />;
        case 'StudentAppliedLoans': return <StudentAppliedLoans  username={username} userRole={userRole}  userId={userId}  />;
        case 'StudentPostFeedBack': return <StudentPostFeedBack  username={username} userRole={userRole}  userId={userId}  />;
        case 'StudentViewFeedBacks': return <StudentViewFeedBacks  username={username} userRole={userRole}  userId={userId}  />;

        default:  return <Dashboard  username={username} userRole={userRole}   />;
    }
};

return (
    <div>
        {selectedMenuBar}
        <div className='page-content'>
            <h1>EDUINVESTOR</h1>
            <h1>Contact Us</h1>
            {renderPage()}
        </div>
    </div>); 
};

export default Home;