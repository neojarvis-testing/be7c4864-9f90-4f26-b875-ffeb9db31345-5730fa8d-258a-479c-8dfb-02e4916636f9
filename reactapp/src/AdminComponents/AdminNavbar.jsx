import React,{useState} from 'react'; 
import './AdminNavbar.css'
const AdminNavbar = () => {
 
return (
    <div className='navbar'>
    <div className='nav-links'>
        <a href='/CollegeForm'>Home</a> 
        <div className='dropdown'>
            <button className='bropbtn'>College
            <div className='dropdown-content'>
                <a href='/addCollege'>Add College</a>
                <a href='/viewColleges'>View Colleges</a>
            </div>
            </button>
        </div>
        <a href='/VideColleges'>Adminion Approval</a>
        <a href='/VideColleges'>Feedback</a>
    </div>
    </div>
    ); 
};

export default AdminNavbar;
