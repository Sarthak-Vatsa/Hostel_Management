import { Avatar, AvatarImage } from '@radix-ui/react-avatar';
import React, { useState } from 'react'
import { useSelector } from 'react-redux'
import { Button } from './ui/button';
import { Contact, Mail, Pen } from 'lucide-react';
import { Label } from '@radix-ui/react-label';
import Navbar from './Navbar';

const Profile = () => {
    const [open, setOpen] = useState(false);
    const {user} = useSelector(store=>store.auth);

    return (
        <div>
            <Navbar />
            <div className='max-w-4xl mx-auto bg-white border border-gray-200 rounded-2xl my-5 p-8'>
                <div className='flex justify-between'>
                    <div className='flex items-center gap-4'>
                        <Avatar className="h-24 w-24">
                            <AvatarImage src="https://www.shutterstock.com/image-vector/circle-line-simple-design-logo-600nw-2174926871.jpg" alt="profile" />
                        </Avatar>
                        <div>
                            <h1 className='font-medium text-xl'>{user?.fullname}</h1>
                            <p>{user?.profile?.bio}</p>
                        </div>
                    </div>
                    <Button onClick={() => setOpen(true)} className="text-right" variant="outline"><Pen /></Button>
                </div>
                <div className='my-5'>
                    <div className='flex items-center gap-3 my-2'>
                        <Mail />
                        <span>{user?.rollNo}</span>
                    </div>
                    <div className='flex items-center gap-3 my-2'>
                        <Contact />
                        <span>{user?.role}</span>
                    </div>
                </div>
            </div>
            {/* <div className='max-w-4xl mx-auto bg-white rounded-2xl'>
                <h1 className='font-bold text-lg my-5'>Applied Jobs</h1>
                {/* Applied Job Table   */}
                {/* <AppliedJobTable />
            </div> */} 
            {/* <UpdateProfileDialog open={open} setOpen={setOpen}/> */}
        </div>
    )
}

export default Profile