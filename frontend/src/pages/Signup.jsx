import React from 'react'
import { Input } from "../components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from '../components/ui/button'
import { BottomWarning } from '../components/BottomWarning'

function Signup() {
  return (
    <div className='bg-yellow-200 flex h-screen justify-center'>
        <div className='h-full flex flex-col justify-center max-w-3xl w-full items-center'>
            <div className='border-black border-2 h-min flex flex-col p-4 space-y-8 max-w-xl w-full bg-white shadow-2xl rounded-lg'>
                <div className='text-center'>
                    <h1 className='text-3xl font-semibold'>Sign Up</h1>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label >Name</Label>
                    <Input type="text" placeholder="John dean"></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Email</Label>
                    <Input type="email" placeholder="abc@gmail.com"></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Roll No</Label>
                    <Input type="number" placeholder=""></Input>
                </div>
                <div className='flex space-x-4'>

                    <div className="grid w-full items-center gap-1.5">
                        <Label>Branch</Label>
                        <Input type="text" placeholder=""></Input>
                    </div>
                    <div className="grid w-full items-center gap-1.5">
                        <Label>Year</Label>
                        <Input type="text" placeholder=""></Input>
                    </div>

                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Hostel Name</Label>
                    <Input type="text" placeholder=""></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Label>Roll No</Label>
                    <Input type="password" placeholder=""></Input>
                </div>
                <div className="grid w-full items-center gap-1.5">
                    <Button>SignUp</Button>
                </div>
                <BottomWarning label={"Already have an account?"} buttonText={"Sign In"} to={"/signin"} />
            </div>
        </div>
    </div>
  )
}

export default Signup