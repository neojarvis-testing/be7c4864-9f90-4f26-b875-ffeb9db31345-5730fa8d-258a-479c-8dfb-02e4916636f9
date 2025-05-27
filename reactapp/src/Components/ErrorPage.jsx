import React,{useState} from 'react';  
import './ErrorPage.css';

const Errorpage = ({message}) => {
     console.log("Error page says : "+message);
  
return (
    <div>
    <p className='error'>Oops! Something Went Wrong</p>
    <p className='error'>fix: {message} and</p>
    <p>Please try again later.</p>
    </div>
);
};

export default Errorpage;