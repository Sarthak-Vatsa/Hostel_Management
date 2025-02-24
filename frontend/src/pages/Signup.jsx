import React, { useState } from 'react'
import { Input } from "../components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from '../components/ui/button'
import { BottomWarning } from '../components/BottomWarning'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import Navbar from '@/components/Navbar'

function Signup() {
    const [name,setName] = useState("");
    const [rollNo,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [branch,setBranch] = useState("");
    const [role,setRole] = useState("");
    const [room,setRoom] = useState("");
    const [mobileNo,setMobileNo] = useState("");
    const navigate = useNavigate();
  return (
    <div>
        <Navbar></Navbar>
    <div className=' flex h-screen justify-center'>
        <div className='h-full flex flex-col justify-center max-w-3xl w-full items-center'>
            <div className='border-gray border-2 h-min flex flex-col p-4 space-y-8 max-w-xl w-full bg-white shadow-2xl rounded-lg'>
                <div className='text-center'>
                    <h1 className='text-3xl font-semibold'>Sign Up</h1>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label >Name</Label>
                    <Input onChange={(e)=>{
                        setName(e.target.value);
                    }} type="text" placeholder="John dean"></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Roll No</Label>
                    <Input onChange={(e)=>{
                        setUsername(e.target.value);
                    }} type="text" placeholder=""></Input>
                </div>
                <div className='flex space-x-4'>
                    <div className="grid w-full items-center gap-1.5">
                        <Label>Branch</Label>
                        <Input onChange={(e)=>{
                            setBranch(e.target.value);
                        }} type="text" placeholder=""></Input>
                    </div>
                </div>
                <div className='flex space-x-4'>
                    <div className="grid w-full items-center gap-1.5">
                        <Label>Role</Label>
                        <Input onChange={(e)=>{
                            setRole(e.target.value);
                        }} type="text" placeholder=""></Input>
                    </div>
                </div>
                <div className='flex space-x-4'>
                    <div className="grid w-full items-center gap-1.5">
                        <Label>Room</Label>
                        <Input onChange={(e)=>{
                            setRoom(e.target.value);
                        }} type="text" placeholder=""></Input>
                    </div>
                </div>
                <div className='flex space-x-4'>
                    <div className="grid w-full items-center gap-1.5">
                        <Label>Phone Number</Label>
                        <Input onChange={(e)=>{
                            setMobileNo(e.target.value);
                        }} type="text" placeholder=""></Input>
                    </div>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Password</Label>
                    <Input onChange={(e)=>{
                        setPassword(e.target.value);
                    }} type="password" placeholder=""></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Button onClick={ async ()=>{
                        const response = await axios.post("http://localhost:8080/students/signup",{
                            rollNo,
                            password,
                            name,
                            branch,
                            role,
                            room,
                            mobileNo
                        },{headers:{
                            'Content-Type':'application/json'
                        }})
                        console.log(response);
                        navigate("/dashboard");
                    }}>SignUp</Button>
                </div>
                <BottomWarning label={"Already have an account?"} buttonText={"Sign In"} to={"/signin"} />
            </div>
        </div>
    </div>
    </div>
  )
}

export default Signup