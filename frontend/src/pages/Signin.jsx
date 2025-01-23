import React, { useState } from 'react'
import { Input } from '../components/ui/input'
import { Label } from '../components/ui/label'
import { Button } from '../components/ui/button'
import { BottomWarning } from '../components/BottomWarning'
import { useNavigate } from 'react-router-dom'

function Signin() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
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
                        setUsername(e.target.value);
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
                        const response = await axios.post("http://localhost:8080/students/signin",{
                            username,
                            password
                        },{headers:{
                            'Content-Type':'application/x-ww-form-urlencoded'
                        }})
                        console.log(response);
                        navigate("/dashboard");
                    }}>SignIn</Button>
                </div>
                <BottomWarning label={"Doesn't have an account?"} buttonText={"Sign Up"} to={"/signup"} />
            </div>
        </div>
    </div>
  )
}

export default Signin
