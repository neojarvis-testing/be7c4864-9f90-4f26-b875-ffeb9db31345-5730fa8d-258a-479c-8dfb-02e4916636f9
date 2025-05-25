import React,{useState} from 'react';
import {useMutation} from '@tanstack/react-query'; 
import {useDispatch} from 'react-redux';
import {loginSuccess} from '../userSlice';
import {Form, useNavigate} from 'react-router-dom';
import api from '../apiConfig' 

const Home = () => {

return (
    <div style={{maxWidth:400,margin:'auto'}}>
        <h2>Home</h2>
    </div>); 
};

export default Home;