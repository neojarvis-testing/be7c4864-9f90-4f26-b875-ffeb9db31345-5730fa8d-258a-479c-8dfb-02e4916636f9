import { createSlice } from "@reduxjs/toolkit";
import jwt_decode from "jwt-decode";

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
             console.log(token);
            if(token){
                try{
                    console.log("decoding data");
                    const decodedata = jwt_decode(token);
                    console.log("data>"+decodedata);
                }catch(e){
                    console.error(e);
                }
               
            }
            
            state.email = email;
            state.token = token; 
            state.userRole =userRole;
            state.userId = userId; 
            state.username =username;
            state.isAuthenicated = true;

            // console.log(email+" "+state.email);
            // console.log(token+" "+state.token);
            // console.log(role+" "+state.userRole);
            localStorage.setItem("email",email);
            localStorage.setItem("token", token);
            localStorage.setItem("userRole", userRole);
            localStorage.setItem("userId", userId);
            localStorage.setItem("username", username);
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