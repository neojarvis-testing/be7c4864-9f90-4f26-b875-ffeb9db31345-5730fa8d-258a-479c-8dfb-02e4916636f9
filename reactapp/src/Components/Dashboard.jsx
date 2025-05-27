import React,{useState} from 'react'; 
import '../css/body.css'
const Dashboard = ({username,userRole}) => {
 
return (

    <div className='form-container'>
    <div className='form-box'>
        <h2>Dashboard</h2>
        <h3>Welcome {username}, your Role: {userRole}</h3> 
        </div>
        </div> 

);

};

export default Dashboard;