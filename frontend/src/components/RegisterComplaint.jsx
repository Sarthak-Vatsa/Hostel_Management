import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useDispatch } from 'react-redux'
import Navbar from './Navbar';
import { Input } from './ui/input';
import { Button } from './ui/button';

const RegisterComplaint = () => {
    const [input, setInput] = useState("");
    const navigate = useNavigate();
    const dispatch = useDispatch();

    return (
        <div>
            <Navbar />
            <div className='max-w-6xl mx-auto my-10'>
                <div className='flex items-center justify-between my-5'>
                    <Input
                        className="w-fit"
                        placeholder="Filter by name"
                        onChange={(e) => setInput(e.target.value)}
                    />
                    <Button onClick={()=>{navigate('/register/complaint')}}>Register</Button>
                </div>
            </div>
        </div>
    )
}

export default RegisterComplaint