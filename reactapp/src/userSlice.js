import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name:"user",
    initialState:{
        user: null,
        isAuthenicated: false,
    },
    reducers:{
        login: (stage, action)=>{
            state.user = action.payload;
            state.isAuthenicated = true;
        },
        logout: (state)=>{
            state.user=null;
            state.isAuthenicated=false;
        }
    }
});

export const {login,logout} = userSlice.actions;
export default userSlice.reducer;