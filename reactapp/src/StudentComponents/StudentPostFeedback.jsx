import React,{useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './StudentMyFeedback.css'

// import { freezeDraftable } from '@reduxjs/toolkit/dist/utils';


const StudentPostFeedback = ({token}) => {
    
const [loading,setLoading] = useState(false);
 
    const [formData, setFormData] = useState(
        {
            feedbackText : '',
            
        }
    );

    const [errors, setErrors] = useState(
        {
            feedbackText : '',
            
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
        if(!formData.feedbackText.trim()){
            newErrors.feedbackText = "Feedback required"
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
            axios.post(`${baseUrl}/api/feedback`,formData,{ headers })
            .then((response)=>{
        
                const {feedbackId} = response.data;
                alert("Success !, Id: "+feedbackId);
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
        {errors.feedbackText && <div>{errors.feedbackText}</div>} 
        <input type='text'  name='feedbackText' placeholder='Feedback Text' value={formData.feedbackText} onChange={handleChange}  />
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