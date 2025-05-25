import React,{useState} from 'react';
import {useMutation} from '@tanstack/react-query'; 
import {useDispatch} from 'react-redux';
import {signUpSuccess} from '../userSlice';
import {useNavigate} from 'react-router-dom';
import api from '../apiConfig'

const Signup = () => {

    
    const dispatch = useDispatch();
    const navigate = useNavigate();

const [form,setForm] = useState({email:'',password: '',username: '',mobileNumber: '', userRole: ''});


    const signupMutation = useMutation({
        mutationFn: () => api.post('/api/users/register',form),
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
    <div style={{maxWidth:400,margin:'auto',alignItems:'center'}}>
    <h2 className='mb-4'>Signup</h2>
    <form onSubmit={handleSubmit} className="p-4 border rounded shadow-sm bg-light">
            <div>
            <label htmlFor='email' className='form-label'>Email:</label> 
            <input type="email" name="email"  className='form-control'  value={form.email} onChange={handleChange} required placeholder="Enter Email" />
            <br/>
            <label htmlFor='password' className='form-label'>Password:</label>
            <input type="passowrd" name="password"  className='form-control'  value={form.password} onChange={handleChange} required placeholder="Enter Password" />
            <br/>
            <label htmlFor='username' className='form-label'>Username:</label> 
            <input type="text" name="username"  className='form-control'  value={form.username} onChange={handleChange} required placeholder="Enter Username" />
            <br/>
            <label htmlFor='mobileNumber' className='form-label'>mobile Number:</label> 
            <input type="text" name="mobileNumber"  className='form-control'  value={form.mobileNumber} onChange={handleChange} required placeholder="Enter Mobilenumber" />
            <br/>
            <label htmlFor='userRole' className='form-label'>Role(ADMIN/STUDENT/LOAN_MANAGER):</label> 
            <input type="text" name="userRole"  className='form-control'  value={form.userRole} onChange={handleChange} required placeholder="Enter UserRole" />
           <select id='userRole'  className='form-control'  name='userRole' onChange={handleChange}>
            <option value="STUDENT" selected>STUDENT</option>
            <option value="LOAN_MANAGER">LOAN_MANAGER</option>
            <option value="ADMIN">ADMIN</option>
           </select>
            <br/>
            <button type="submit"  className='btn btn-primary' disabled={form.isLoading} style={{marginTop: 15}}>
                {signupMutation.isLoading ? 'Signup Loading ..' : 'Signup'}
                </button>
            </div>
            </form>
    </div>); 
};

export default Signup;