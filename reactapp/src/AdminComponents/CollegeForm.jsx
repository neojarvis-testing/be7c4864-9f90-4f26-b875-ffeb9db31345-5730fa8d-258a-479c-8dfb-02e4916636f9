import React,{useState} from 'react'; 
import './CollegeForm.css'
const CollegeForm = () => {
 
return (

   <div className='form-container'>
    <form className='form-box'>
        <h2>Add College</h2>
        <input type='text' name='name' placeholder='College Name' required />
        <input type='text'  name='address' placeholder='College Address' required />
        <input type='text'  name='contact Number' placeholder='Contact Number' required />
        <input type='text'  name='email' placeholder='Email' required />
        <input type='text'  name='website' placeholder='Website' required />
        <input type='text'  name='courses' placeholder='Courses' required />
        <input type='text'  name='status' placeholder='Status' required />
        <br/>
        <button type='submit'>Submit</button>
    </form>
   </div>

);

};

export default CollegeForm;