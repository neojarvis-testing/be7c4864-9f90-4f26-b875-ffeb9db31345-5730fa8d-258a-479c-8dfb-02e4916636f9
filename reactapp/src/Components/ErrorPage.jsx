import React,{useState} from 'react';  
import './ErrorPage.css';

const Errorpage = ({message}) => {
     console.log("Error page says : "+message);
    if(!message) return null;
return (
    <div>
    <p className='error'>Oops! Something Went Wrong: {message}</p>
    <div>Please try again later.</div>
    </div>
);
};

export default Errorpage;