import React from 'react'; 
import {useSelector} from 'react-redux'; 
import AdminMenu from '../AdminComponents/AdminNavbar'; 
import LoanManagerMenu from '../LoanManagerComponents/LoanManagerNavbar'; 
import StudentMenu from '../StudentComponents/StudentNavbar'; 


const Home = () => {

    const user = useSelector((state)=>state.user);
    console.log(user);
   
    const userRole = user?.userRole; 
    
    var selectedMenuBar = null;

    if(userRole==="ADMIN"){selectedMenuBar = <AdminMenu />}
    else if(userRole==="LOAN_MANAGER"){selectedMenuBar = <LoanManagerMenu />}
    else if(userRole==="STUDENT"){selectedMenuBar = <StudentMenu />}
    else{ 
    }

return (
    <div style={{maxWidth:400,margin:'auto'}}>
        <h2>Menu Bar: </h2>
        {selectedMenuBar}
    </div>); 
};

export default Home;