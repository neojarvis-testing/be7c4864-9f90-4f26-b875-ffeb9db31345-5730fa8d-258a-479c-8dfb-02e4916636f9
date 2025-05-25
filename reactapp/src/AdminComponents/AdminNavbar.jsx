import React,{useState} from 'react'; 
import '../css/Navbar.css'
const AdminNavbar = ({username,userRole,setCurrentPage}) => {
 
return (
    <div className='navbar'>
    <div className='nav-menu'>
       <div className='username'>Welcome {username}!, UserRole: {userRole}</div> 
       <div className='nav-item' onClick={()=> setCurrentPage('Dashboard')}>Home</div> 

       <div className='nav-item'>
            Colleges
            <div className='dropdown'>
                <div className='dropdown-item' onClick={()=> setCurrentPage('CollegeForm')}>Add College</div>
                <div  className='dropdown-item' onClick={()=> setCurrentPage('ViewColleges')}>View Colleges</div>
            </div>
        </div> 
      
        <div  className='nav-item'  onClick={()=> setCurrentPage('CollegeApproval')}> College Approval</div>
        <div  className='nav-item'  onClick={()=> setCurrentPage('ViewFeedback')}>View Colleges</div>
        <div  className='nav-item'  onClick={()=> setCurrentPage('Logout')}>Logout</div>
    </div>
    </div>
    ); 
};

export default AdminNavbar;
