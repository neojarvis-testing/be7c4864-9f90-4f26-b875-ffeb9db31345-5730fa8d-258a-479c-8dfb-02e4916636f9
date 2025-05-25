import React,{useState} from 'react';
import {useMutation} from '@tanstack/react-query'; 
import {useDispatch} from 'react-redux';
import {loginSuccess} from '../userSlice';
import {useNavigate} from 'react-router-dom';
import api from '../apiConfig' 
import './Login.css'
const Login = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

const [form,setForm] = useState({email:'',password: ''});

const loginMutation = useMutation({
    mutationFn: () => api.post('/api/users/login',form),
    onSuccess: (response)=>{
        console.log("Login Response",response);
         const {email, token, userRole,userId,username} = response.data; 
         const payload = {"email":email,"token":token,"userRole":userRole,"userId":userId,"username":username};
        // console.log(payload);
        dispatch(loginSuccess(payload));
        alert('Login Successfull!');
        navigate("/home");
    },onError: (onError)=>{
        alert("Login Failed: ");
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
    loginMutation.mutate();
};

    return (
        <div className='form-container'>
           <form onSubmit={handleSubmit} className='form-box'>
           <h2>Login</h2>
            <div>  
            <input type="email" name="email" value={form.email} onChange={handleChange} required placeholder="Enter Email" />
            <br/> 
            <input type="passowrd" name="password" value={form.password} onChange={handleChange} required placeholder="Enter Password" />
            <br/>
            <button type="submit"  disabled={loginMutation.isLoading} style={{marginTop: 15}}>
                {loginMutation.isLoading ? 'Logging in...' : 'Login'}
                </button>
            </div>
            </form> 
        </div>);
};

export default Login;