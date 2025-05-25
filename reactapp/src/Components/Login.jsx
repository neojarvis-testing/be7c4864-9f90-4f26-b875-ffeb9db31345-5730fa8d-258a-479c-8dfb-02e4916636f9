import React,{useState} from 'react';
import {useMutation} from '@tanstack/react-query'; 
import {useDispatch} from 'react-redux';
import {loginSuccess} from '../userSlice';
import {useNavigate} from 'react-router-dom';
import api from '../apiConfig' 

const Login = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

const [form,setForm] = useState({email:'',password: ''});

const loginMutation = useMutation({
    mutationFn: () => api.post('/api/users/login',form),
    onSuccess: (response)=>{
        console.log("Login Response",response);
         const {email, token, userRole} = response.data;
       //  console.log("Login Response 20",email+" "+token+" "+userRole);
         const payload = {"email":email,"token":token,"userRole":userRole};
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
        <div style={{maxWidth:400,margin:'auto',alignItems:'center'}}>
           <h2 className='mb-4'>Login</h2>
           <form onSubmit={handleSubmit} className="p-4 border rounded shadow-sm bg-light">
            <div> 
            <label htmlFor='email' className='form-label'>Email:</label>
            <input type="email" name="email" className='form-control' value={form.email} onChange={handleChange} required placeholder="Enter Email" />
            <br/>
            <label htmlFor='password' className='form-label'>Password:</label>
            <input type="passowrd" className='form-control' name="password" value={form.password} onChange={handleChange} required placeholder="Enter Password" />
            <br/>
            <button type="submit" className='btn btn-primary' disabled={loginMutation.isLoading} style={{marginTop: 15}}>
                {loginMutation.isLoading ? 'Logging in...' : 'Login'}
                </button>
            </div>
            </form>
          
        </div>);
};

export default Login;