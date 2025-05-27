import React,{useState} from 'react';
import {useMutation} from '@tanstack/react-query'; 
import {useDispatch} from 'react-redux';
import {signUpSuccess} from '../userSlice';
import {useNavigate} from 'react-router-dom';
import {baseUrl} from '../apiConfig' 
import axios from 'axios';
import Errorpage from './ErrorPage';

const Signup = () => {    
    const dispatch = useDispatch();
    const navigate = useNavigate();
 
const [form,setForm] = useState({email:'',password: '',confirmPassword:'',username: '',mobileNumber: '', userRole: ''});
const [formError,setFormError] = useState(null);
const [FieldError,setFieldFormError] = useState({});
const [loading,setLoading] = useState(false);


const handleChange = (e) => {
    const {name,value} = e.target;
   setForm((prev) => ({
       ...prev,
       [name]: value,
   }));
   }; 
    
    const handleSubmit = (e) => {
        e.preventDefault();
        //error handle
        const errors = {}; 
        if(form.email==="") {errors.email = "Email is required";} 
        if(form.username==="") {errors.username = "User Name is required";} 
        if(form.password==="") {errors.password = "Password is required";}  
        if(form.confirmPassword==="") {errors.confirmPassword = "Confirm Password is required";} 
        if(form.mobileNumber==="") {errors.mobileNumber = "Mobile Number is required";} 

        //password validation
        if(form.password!=form.confirmPassword){
            errors.confirmPassword = "Passwords do not match";
        }

        console.log(errors);
        if(Object.keys(errors).length>0){
            console.log("keys:"+errors);
            setFieldFormError(errors);
            setFormError('Please Fix the Errors Below.');
            return;
        }

        //api call
        axios.post(`${baseUrl}/api/users/register`,form)
        .then((response)=>{

            const {name, email} = response.data;  
            dispatch(signUpSuccess(name,email));
            alert('SignUp Successfull!'); 
            navigate("/Login");
    
        }).catch((error)=>{
            alert(error);
            let message = "";
            if(error?.response?.data.status=="400"){
                message = error?.response?.data.message || "Register Failed";
            }else{
            message = error?.response?.data.status || "Register Failed";}
        
            setFormError(message);
        }).finally(()=>{
            setLoading(false);
        });
    };



return (
    <div className='form-container'>
    <form onSubmit={handleSubmit} className='form-box'>
     
    <h2>Signup</h2>
     <div>  
           {FieldError.email && <p className='error'>{FieldError.email}</p>}
           <input type="email" name="email"   value={form.email} onChange={handleChange} placeholder="Enter Email" />
            

           {FieldError.username && <p className='error'>{FieldError.username}</p>}
            <input type="text" name="username"    value={form.username} onChange={handleChange} placeholder="Enter Name" />
            
            {FieldError.password && <p className='error'>{FieldError.password}</p>}
            <input type="password" name="password"   value={form.password} onChange={handleChange} placeholder="Password" />
            
            {FieldError.confirmPassword && <p className='error'>{FieldError.confirmPassword}</p>}
            <input type="passowrd" name="confirmPassword"   value={form.confirmPassword} onChange={handleChange} placeholder="Confirm Password" />
            
            {FieldError.mobileNumber && <p className='error'>{FieldError.mobileNumber}</p>}
            <input type="text" name="mobileNumber"   value={form.mobileNumber} onChange={handleChange} placeholder="Enter Mobilenumber" />
            
           <select id='userRole'  value={form.userRole}   name='userRole' onChange={handleChange}>
            <option value="STUDENT" selected>STUDENT</option>
            <option value="LOAN_MANAGER">LOAN_MANAGER</option>
            <option value="ADMIN">ADMIN</option>
           </select>
           <button type="submit"  disabled={loading} >
                {loading ? 'Logging in...' : 'Submit'}
                </button>
            </div>
            <br/>
            
             <a href='/'>Want to Login?<b>Login</b></a> 

            </form>
    </div>); 
};

export default Signup;