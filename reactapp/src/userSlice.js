import { createSlice } from "@reduxjs/toolkit"; 

const userSlice = createSlice({
    name:"user",
    initialState:{
        email: null,
        token: null,
        userRole: null,
        userId: null,
        username: null,
        isAuthenicated: false,
    },
    reducers:{
        loginSuccess: (state, action)=>{
           // console.log(action.payload) 
            const {email,token,userRole,userId,username} = action.payload;
           
            state.email = email;
            state.token = token; 
            state.userRole =userRole;
            state.userId = userId; 
            state.username =username;
            state.isAuthenicated = true; 
            localStorage.setItem("email",email);
            localStorage.setItem("token", token);
            localStorage.setItem("userRole", userRole);
            localStorage.setItem("userId", userId);
            localStorage.setItem("username", username);
            localStorage.setItem("isAuthenicated", true);
        },
        signUpSuccess: (state, action)=>{
            state.username = action.payload.username; 
            state.email = action.payload.email; 
            state.isAuthenicated = false;
        },
        logout: (state)=>{
            state.email = null;
            state.token = null; 
            state.userRole =null;
            state.userId = null; 
            state.username =null;
            state.isAuthenicated = false; 
 
            localStorage.removeItem("email");
            localStorage.removeItem("token");
            localStorage.removeItem("userRole");
            localStorage.removeItem("userId");
            localStorage.removeItem("username");
            localStorage.removeItem("isAuthenicated");
        }
    }
});

export const {loginSuccess,signUpSuccess,logout} = userSlice.actions;
export default userSlice.reducer;