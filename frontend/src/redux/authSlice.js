import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name:"auth",
    initialState:{
        user:null,
        allNotices:[],
    },
    reducers:{
        setUser:(state,action)=>{
            state.user = action.payload;
        },
        setAllNotices:(state,action)=>{
            state.allNotices = action.payload;
        }
    }
})

export const {setUser,setAllNotices} = authSlice.actions;
export default authSlice.reducer;