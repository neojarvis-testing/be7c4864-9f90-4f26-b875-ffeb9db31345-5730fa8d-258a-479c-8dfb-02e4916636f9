import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name:"userLogin",
    initialState:{
        email: null,
        token: null,
        isAuthenicated: false,
    },
    reducers:{
        loginSuccess: (state, action)=>{
            state.email = action.payload.email;
            state.token = action.payload.token;
            state.isAuthenicated = true;
        },
        logout: (state)=>{
            state.email=null;
            state.token=null;
            state.isAuthenicated=false;
        }
    }
});

export const {loginSuccess,logout} = userSlice.actions;
export default userSlice.reducer;