import React,{useState} from 'react'; 
import {useDispatch} from "react-redux"
import {useNavigate} from "react-router-dom"
import '../css/Navbar.css'
import {logout} from '../userSlice'


const StudentNavbar = ({username,userRole,setCurrentPage}) => {

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
       <div  className='nav-item'  onClick={()=> setCurrentPage('ViewAllStudentColleges')}>Colleges</div>
       <div  className='nav-item'  onClick={()=> setCurrentPage('ViewAllStudentLoans')}>Student Loans</div>
       <div className='nav-item'>
            Applied
            <div className='dropdown'>
                <div className='dropdown-item' onClick={()=> setCurrentPage('StudentAppliedColleges')}>Applied Colleges</div>
                <div  className='dropdown-item' onClick={()=> setCurrentPage('StudentAppliedLoans')}>Applied Loans</div>
            </div>
        </div> 
        <div  className='nav-item'>
            Feedback
             <div className='dropdown'>
                <div className='dropdown-item' onClick={()=> setCurrentPage('StudentMyFeedback')}>My Feedbacks</div>
                <div  className='dropdown-item' onClick={()=> setCurrentPage('StudentPostFeedback')}>Post Feedback</div>
            </div>
        </div>
        <div  className='nav-item'  onClick={handleLogout}>Logout</div>
    </div>
    </div>
    ); 
};

export default StudentNavbar;
