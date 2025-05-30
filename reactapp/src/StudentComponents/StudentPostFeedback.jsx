import React,{useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './StudentMyFeedback.css'

// import { freezeDraftable } from '@reduxjs/toolkit/dist/utils';


const StudentPostFeedback = ({token}) => {
    
const [loading,setLoading] = useState(false);
 
    const [formData, setFormData] = useState(
        {
            collegeName : '',
            address : '',
            contactNumber : '',
            email : '',
            website : '',
            courses : '',
            status : ''
        }
    );

    const [errors, setErrors] = useState(
        {
            name : '',
            address : '',
            contactNumber : '',
            email : '',
            website : '',
            courses : '',
            status : ''
        }
    );

    const handleChange = (e) =>{
        const {name, value} = e.target;
        setFormData((prevData) => ({
            ...prevData, [name] : value
        }))

        if(errors[name]){
            setErrors((prevError) => ({
                ...prevError,
                [name] : undefined
            }))
        }
    }

    const validateForm = () => {
        let newErrors = {};
        if(!formData.collegeName.trim()){
            newErrors.name = "College name is required"
        }
        if(!formData.address.trim()){
            newErrors.address = "Address is required"
        }
        if(!formData.contactNumber.trim()){
            newErrors.contactNumber = "Contact number is required"
        }
        if(!formData.courses.trim()){
            newErrors.courses = "Courses are required"
        }
        setErrors(newErrors)
        return Object.keys(newErrors).length === 0;
    }

    const [check, setCheck] = useState(false);

    const handleSubmit = (e) => {
        
        e.preventDefault();
        setCheck(true)
        const isValid = validateForm();
        if(isValid){
            // Call API 
const headers = {
    "Authorization":`Bearer ${token}`,
    "Content-Type":"application/json"
}
            axios.post(`${baseUrl}/api/colleges`,formData,{ headers })
            .then((response)=>{
        
                const {collegeId} = response.data;
                alert("Success !"+collegeId);
            }).catch((error)=>{
                let message = "";
                alert(error);
                if(error?.response?.data.status=="400"){
                    message = error?.response?.data.message ||"Failed";
                }else{
                message = error?.response?.data.status || "Failed";}
         
            }).finally(()=>{
                setLoading(false);
            });
        }else{
            // logic
            alert("Validation Error !");
        }
    }
 
return (
<>
   <div className='form-container'>
     
    <form className='form-box' onSubmit={handleSubmit}>
        <h2>Create Feedback</h2>
        <input type='text' name='collegeName' placeholder='Feedback Title' value={formData.collegeName} onChange={handleChange} />
        {errors.name && <div>{errors.name}</div>}
        <input type='text'  name='address' placeholder='Feedback Content' value={formData.address} onChange={handleChange}  />
        {errors.address && <div>{errors.address}</div>}
        
        <br/>
        <button type='submit' name="Submit Your Feedback" role='button'  disabled={loading} >
        {loading ? 'Logging in...' : 'Submit Your Feedback'}
        </button>
    </form>
   </div>
</>
);

};

export default StudentPostFeedback;