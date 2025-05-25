import React,{useState} from 'react'; 
import './CollegeForm.css'
const CollegeForm = () => {
 
return (

   <div className='form-container'>
    <form className='form-box'>
        <h2>Add College</h2>
        <input type='text' placeholder='College Name' required />
        <input type='text' placeholder='College address' required />
        <br/>
        <button type='submit'>Submit</button>
    </form>
   </div>

);

};

export default CollegeForm;