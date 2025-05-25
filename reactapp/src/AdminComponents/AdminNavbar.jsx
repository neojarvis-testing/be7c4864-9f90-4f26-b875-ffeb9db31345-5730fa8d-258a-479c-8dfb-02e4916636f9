import React,{useState} from 'react'; 
import './AdminNavbar.css'
const AdminNavbar = () => {
 
return (
    <div className='navbar'>
    <div className='nav-links'>
        <a href='/home'>Home</a> 
        <div className='dropdown'>
            <button className='bropbtn'>College
            <div className='dropdown-content'>
                <a href='/college-form'>Add College</a>
                <a href='/view-college'>View Colleges</a>
            </div>
            </button>
        </div>
        <a href='/college-approval'>Adminion Approval</a>
        <a href='/view-feedback'>Feedback</a>
    </div>
    </div>
    ); 
};

export default AdminNavbar;
