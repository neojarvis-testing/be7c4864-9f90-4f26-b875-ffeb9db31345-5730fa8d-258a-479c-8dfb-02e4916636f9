import React,{useState, useEffect} from 'react'; 
import {useSelector} from 'react-redux'; 
import AdminMenu from '../AdminComponents/AdminNavbar'; 
import LoanManagerMenu from '../LoanManagerComponents/LoanManagerNavbar'; 
import StudentMenu from '../StudentComponents/StudentNavbar'; 

import CollegeApproval from '../AdminComponents/CollegeApproval';
import CollegeForm from '../AdminComponents/CollegeForm';
import ViewColleges from '../AdminComponents/ViewColleges';
import ViewFeedBack from '../AdminComponents/ViewFeedback';
import Dashboard from '../AdminComponents/Dashboard';

const Home = () => {

    const user = useSelector((state)=>state.user);
    const [currentPage, setCurrentPage] = useState("Dashboard");
   
    let userRole = user?.userRole || localStorage.getItem("userRole") ;
    let username = user?.username || localStorage.getItem("username") ;  
    let userId = user?.userId || localStorage.getItem("userId") ;  
    
    var selectedMenuBar = null;
    if(userRole==="ADMIN"){selectedMenuBar = <AdminMenu username={username} userRole={userRole} setCurrentPage={setCurrentPage} />}
    else if(userRole==="LOAN_MANAGER"){selectedMenuBar = <LoanManagerMenu username={username} userRole={userRole} setCurrentPage={setCurrentPage} />}
    else if(userRole==="STUDENT"){selectedMenuBar = <StudentMenu username={username} userRole={userRole} setCurrentPage={setCurrentPage}  />}
    else{ 
    };

const renderPage = () =>{
    switch(currentPage){
        case 'Dashboard': return <Dashboard  username={username} userRole={userRole}  />;
        case 'CollegeApproval': return <CollegeApproval  username={username} userRole={userRole}  userId={userId}  />;
        case 'CollegeForm': return <CollegeForm  username={username} userRole={userRole}   userId={userId}   />;
        case 'ViewColleges': return <ViewColleges  username={username} userRole={userRole}   userId={userId}   />;
        case 'ViewFeedBack': return <ViewFeedBack  username={username} userRole={userRole}   userId={userId}   />;
        default:  return <Dashboard  username={username} userRole={userRole}   />;
    }
};

return (
    <div>
        {selectedMenuBar}
        <div className='page-content'>
            {renderPage()}
        </div>
    </div>); 
};

export default Home;