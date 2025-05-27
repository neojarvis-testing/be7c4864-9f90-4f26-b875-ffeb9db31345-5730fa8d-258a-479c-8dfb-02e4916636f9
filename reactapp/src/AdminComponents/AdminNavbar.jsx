import React,{useState} from 'react'; 
import {useDispatch} from "react-redux"
import {useNavigate} from "react-router-dom"
import '../css/Navbar.css'
import {logout} from '../userSlice'



const AdminNavbar = ({username,userRole,setCurrentPage}) => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleLogout = () => {
        dispatch(logout());
        navigate("/login");
    }


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
        <div  className='nav-item'  onClick={()=> setCurrentPage('ViewFeedback')}>Student Feedbacks</div>
        <div  className='nav-item'  onClick={handleLogout}>Logout</div>
    </div>
    </div>
    ); 
};

export default AdminNavbar;
