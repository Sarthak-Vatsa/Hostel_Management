import React, { useState } from 'react'
import { Input } from '../components/ui/input'
import { Label } from '../components/ui/label'
import { Button } from '../components/ui/button'
import { BottomWarning } from '../components/BottomWarning'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import { useDispatch } from 'react-redux'
import { setUser } from '@/redux/authSlice'

function Signin() {
    const [rollNo, setrollNo] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const dispatch = useDispatch();
  return (
    <div className='bg-yellow-200 flex h-screen justify-center'>
        <div className='h-full flex flex-col justify-center max-w-3xl w-full items-center'>
            <div className='border-black border-2 h-min flex flex-col items-center p-4 space-y-8 max-w-xl w-full bg-white shadow-2xl rounded-lg'>
                <div className='text-center'>
                    <h1 className='text-3xl font-semibold'>Sign In</h1>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Roll No</Label>
                    <Input onChange={(e)=>{
                        setrollNo(e.target.value);
                    }} type="text" placeholder=""></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Password</Label>
                    <Input onChange={(e)=>{
                        setPassword(e.target.value);
                    }} type="password" placeholder=""></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Button onClick={ async ()=>{
                        axios.defaults.withCredentials=true;
                        const response = await axios.post("http://localhost:8080/students/signin",{
                            rollNo,
                            password
                        },{headers:{
                            withCredentials: true, // Ensures cookies are sent
                            'Content-Type':'application/json'
                        }})
                        dispatch(setUser(response.data));
                        navigate("/dashboard");// for // for app //disable whefor app
                    }}>SignIn</Button>
                </div>
                <BottomWarning label={"Doesn't have an account?"} buttonText={"Sign Up"} to={"/signup"} />
            </div>
        </div>
    </div>
  )
}

export default Signin
