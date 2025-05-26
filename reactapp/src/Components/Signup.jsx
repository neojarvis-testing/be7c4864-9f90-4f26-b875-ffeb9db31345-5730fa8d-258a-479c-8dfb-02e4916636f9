import React,{useState} from 'react';
import {useMutation} from '@tanstack/react-query'; 
import {useDispatch} from 'react-redux';
import {signUpSuccess} from '../userSlice';
import {useNavigate} from 'react-router-dom';
import {baseUrl} from '../apiConfig' 
import axios from 'axios';

const Signup = () => {

    
    const dispatch = useDispatch();
    const navigate = useNavigate();
 
const [form,setForm] = useState({email:'',password: '',username: '',mobileNumber: '', userRole: ''});


    const signupMutation = useMutation({
        mutationFn: () => axios.post(`${baseUrl}/api/users/register`,form),
        onSuccess: (response)=>{
            const {name, email} = response.data;
            dispatch(signUpSuccess(name,email));
            alert('SignUp Successfull!');
            navigate("/Login");
        },onError: (onError)=>{
            alert("Register Failed !");
            console.log(onError.message);
        }
    });
    
    
    const handleChange = (e) => {
         const {name,value} = e.target;
        setForm((prev) => ({
            ...prev,
            [name]: value,
        }));
        }; 
    
    const handleSubmit = (e) => {
        e.preventDefault();
        signupMutation.mutate();
    };



return (
    <div className='form-container'>
    <form onSubmit={handleSubmit} className='form-box'>
    <h2>Signup</h2>
     <div>  <input type="email" name="email"   value={form.email} onChange={handleChange} required placeholder="Enter Email" />
            <input type="text" name="username"    value={form.username} onChange={handleChange} required placeholder="Enter Name" />
            <input type="passowrd" name="password"   value={form.password} onChange={handleChange} required placeholder="Enter Password" />
            <input type="passowrd" name="password"   value={form.password} onChange={handleChange} required placeholder="Enter Password Again" />
           
            <input type="text" name="mobileNumber"   value={form.mobileNumber} onChange={handleChange} required placeholder="Enter Mobilenumber" />
             
           <select id='userRole'  value={form.userRole}   name='userRole' onChange={handleChange}>
            <option value="STUDENT" selected>STUDENT</option>
            <option value="LOAN_MANAGER">LOAN_MANAGER</option>
            <option value="ADMIN">ADMIN</option>
           </select>
          
            <button type="submit"   disabled={form.isLoading} style={{marginTop: 15}}>
                {signupMutation.isLoading ? 'Signup Loading ..' : 'Signup'}
                </button>
            </div>
            </form>
    </div>); 
};

export default Signup;