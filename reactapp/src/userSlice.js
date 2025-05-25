import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name:"user",
    initialState:{
        email: null,
        token: null,
        userRole: null,
        isAuthenicated: false,
    },
    reducers:{
        loginSuccess: (state, action)=>{
           // console.log(action.payload) 
            const {email,token,userRole} = action.payload;
             
            
            state.email = email;
            state.token = token; 
            state.userRole =userRole; 
            state.isAuthenicated = true;
            // console.log(email+" "+state.email);
            // console.log(token+" "+state.token);
            // console.log(role+" "+state.userRole);
            localStorage.setItem("email",email);
            localStorage.setItem("token", token);
            localStorage.setItem("userRole", userRole);
        },
        signUpSuccess: (state, action)=>{
            state.name = action.payload.name; 
            state.email = action.payload.email; 
            state.isAuthenicated = false;
        },
        logout: (state)=>{
            state.email=null;
            state.token=null;
            state.isAuthenicated=false;
        }
    }
});

export const {loginSuccess,signUpSuccess,logout} = userSlice.actions;
export default userSlice.reducer;