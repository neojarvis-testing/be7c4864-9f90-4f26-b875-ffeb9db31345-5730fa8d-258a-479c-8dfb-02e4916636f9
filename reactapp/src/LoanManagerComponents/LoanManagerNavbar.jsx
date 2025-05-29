import React,{useState} from 'react'; 
import {useDispatch} from "react-redux"
import {useNavigate} from "react-router-dom"
import '../css/Navbar.css'
import {logout} from '../userSlice'

const LoanMangerNavbar = ({username,userRole,setCurrentPage}) => {

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
            Loan Management
            <div className='dropdown'>
                <div className='dropdown-item' onClick={()=> setCurrentPage('LoanForm')}>new Loan</div>
                <div  className='dropdown-item' onClick={()=> setCurrentPage('ViewLoans')}>View Loans</div>
            </div>
        </div> 
      
        <div  className='nav-item'  onClick={()=> setCurrentPage('LoanRequest')}>Loan Requested</div>
        <div  className='nav-item'  onClick={()=> setCurrentPage('ViewFeedback')}>Feedbacks</div>
        <div  className='nav-item'  onClick={handleLogout}>Logout</div>
    </div>
    </div>
    ); 
};
export default LoanMangerNavbar;
