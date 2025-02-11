import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useNavigate, useParams } from 'react-router-dom'
import { useSelector } from 'react-redux'
import Navbar from './Navbar'
import { Button } from './ui/button'
import { ArrowLeft } from 'lucide-react'
import { Label } from '@radix-ui/react-label'
import { Input } from './ui/input'

const ComplaintSetup = () => {
    const params = useParams();
    const [input, setInput] = useState({
        description: "",
        number: "",
        room: "",
    });
    const navigate = useNavigate();

    const changeEventHandler = (e) => {
        setInput({ ...input, [e.target.name]: e.target.value });
    }


    const submitHandler = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("name", input.name);
        formData.append("description", input.description);
        formData.append("website", input.website);
        formData.append("location", input.location);
        try {
            const res = await axios.put(``, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                withCredentials: true
            });
        } catch (error) {
            console.log(error);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <Navbar />
            <div className='max-w-xl mx-auto my-10'>
                <form onSubmit={submitHandler}>
                    <div className='flex items-center gap-5 p-8'>
                        <Button onClick={() => navigate("/complaint")} variant="outline" className="flex items-center gap-2 text-gray-500 font-semibold">
                            <ArrowLeft />
                            <span>Back</span>
                        </Button>
                        <h1 className='font-bold text-xl'>Register Complaint</h1>
                    </div>
                    <div className='grid grid-cols-1 gap-4'>
                        <div>
                            <Label>Description</Label>
                            <Input
                                type="text"
                                name="description"
                                value={input.description}
                                onChange={changeEventHandler}
                            />
                        </div>
                        <div>
                            <Label>Phone Number</Label>
                            <Input
                                type="text"
                                name="website"
                                value={input.number}
                                onChange={changeEventHandler}
                            />
                        </div>
                        <div>
                            <Label>Room No</Label>
                            <Input
                                type="text"
                                name="location"
                                value={input.room}
                                onChange={changeEventHandler}
                            />
                        </div>
                    </div>
                
                    <Button type="submit" className="w-full my-4">Register</Button>
                    
                </form>
            </div>

        </div>
    )
}

export default ComplaintSetup