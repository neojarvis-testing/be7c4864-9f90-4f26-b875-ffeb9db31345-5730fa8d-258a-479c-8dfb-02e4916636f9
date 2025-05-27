<<<<<<< HEAD
=======
import React,{useState} from 'react'; 
import {useDispatch} from 'react-redux';
import {loginSuccess} from '../userSlice';
import {useNavigate} from 'react-router-dom';
import {baseUrl} from '../apiConfig' 
import axios from 'axios';
import './Login.css'
import Errorpage from './ErrorPage';
const Login = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
const [form,setForm] = useState({email:'',password: ''});
const [formError,setFormError] = useState(null);
const [FieldError,setFieldFormError] = useState({});
const [loading,setLoading] = useState(false);

 
const handleChange = (e) => {
     const {name,value} = e.target;
    setForm((prev) => ({
        ...prev,
        [name]: value,
    }));

    setFormError(null);
    setFieldFormError((prev) => ({...prev,[name]: ''}));
    }; 

const handleSubmit = (e) => {
    e.preventDefault();

    //Error Handle
    const errors = {}; 
    if(form.email==="") {errors.email = "Email is required";} 
    if(form.password===""){ errors.password = "Password is required";}
    console.log(errors);
    if(Object.keys(errors).length>0){
        console.log("keys:"+errors);
        setFieldFormError(errors);
        setFormError('Please Fix the Errors Below.');
        return;
    }


    //call API
    axios.post(`${baseUrl}/api/users/login`,form)
    .then((response)=>{

        const {email, token, userRole,userId,username} = response.data; 
        const payload = {"email":email,"token":token,"userRole":userRole,"userId":userId,"username":username};
       // console.log(payload);
       dispatch(loginSuccess(payload));
       alert('Login Successfull!');
       navigate("/home");

    }).catch((error)=>{
        let message = "";
        if(error?.response?.data.status=="400"){
            message = error?.response?.data.message || "Login Failed";
        }else{
        message = error?.response?.data.status || "Login Failed";}
        setFormError(message);
    }).finally(()=>{
        setLoading(false);
    });
 
};

    return (
        <div className='form-container'>
          
           <form onSubmit={handleSubmit} className='form-box'>
           
           <h2>Login</h2>
            <div>  
            <input type="email" name="email" value={form.email} onChange={handleChange}  placeholder="Enter Email" />
            {FieldError.email && <p className='error'>{FieldError.email}</p>}
            <br/> 
            <input type="password" name="password" value={form.password} onChange={handleChange}  placeholder="Enter Password" />
            {FieldError.password && <p  className='error'>{FieldError.password}</p>}
            <br/>
            <button type="submit"  disabled={loading} >
                {loading ? 'Logging in...' : 'Login'}
                </button>
                <br/>
             <a href='/signup'>Want to Register ? <b>SignUp</b></a> 
            </div>
            </form> 
        </div>);
};

export default Login;
>>>>>>> 31f5b23b19564ba6766de4f13bc885fe42bc3612
