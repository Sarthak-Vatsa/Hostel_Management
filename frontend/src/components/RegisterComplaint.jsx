import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useDispatch } from 'react-redux'
import Navbar from './Navbar';
import { Input } from './ui/input';
import { Button } from './ui/button';
import axios from 'axios';

const RegisterComplaint = () => {
    const [input, setInput] = useState("");
    const navigate = useNavigate();
    const dispatch = useDispatch();

    return (
        <div>
            <Navbar />
            <div className='max-w-6xl mx-auto my-10'>
                <div className='flex items-center justify-between my-5 gap-10'>
                    <Input
                        className=""
                        placeholder="Description"
                        onChange={(e) => setInput(e.target.value)}
                    />
                    <Button onClick={async ()=>{
                        const response = await axios.post(
                            "https://backend-hostel-ao00.onrender.com/students/registerComplaint",
                            {
                                type:input
                            },
                            {
                              headers: {
                                withCredentials: true, // Ensures cookies are sent
                                "Content-Type": "application/json",
                              },
                            }
                          );
                          console.log(response);
                    }}>Register</Button>
                </div>
            </div>
        </div>
    )
}

export default RegisterComplaint