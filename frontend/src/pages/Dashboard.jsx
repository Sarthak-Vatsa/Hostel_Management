import React, { useEffect, useState } from 'react'
import Sidebar from '../components/Sidebar'
import Navbar from '../components/Navbar'
import Card from '../components/Card'
import { FaBox, FaCog, FaShoppingCart, FaUsers } from 'react-icons/fa'
import Notification from '../components/Notification'
import axios from 'axios'

const Dashboard = () => {
    const [notification,setNotification] = useState([]);

    useEffect(()=>{
        const fetchNotices = async () => {
                try {
                    const resp = await axios.get("http://localhost:8080/students/viewNotices", {
                        withCredentials: true, // Ensures cookies are sent
                        headers: {
                            'Accept': 'application/json',
                        },
                    });
                    console.log(resp.data);
                    setNotification(resp.data);
                    // console.log(notification);
                } catch (error) {
                    console.error("Error fetching notices:", error);
                }
            };
            fetchNotices()

        },[])

    console.log(notification);
  return (
    <div className='flex'>
      <Sidebar />
      <div className='grow ml-16 md:ml-64 h-full lg:h-screen text-gray-900'>
        <Navbar />
        <div className=''>
            <div className='grow p-8'>
                {/* <h2 className='text-2xl mb-4'>Dashboard</h2> */}
                <div className='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6'>
                    <Card icon={<FaShoppingCart />} title="Users" value="140"/>
                    <Card icon={<FaBox />} title="Balance" value="120"/>
                    {/* <Card icon={<FaUsers />} title="Users" value="30"/> */}
                    {/* <Card icon={<FaCog />} title="Settings" value="11"/> */}
                </div>
            </div>
        </div>
        <div className='border-2 border-black rounded-md m-4 shadow-xl'>
            <div className='border-b-2 border-black pt-2 pl-2'>
                <h2 className='text-lg font-semibold'>Notification</h2>
            </div>
            <div className='max-h-64 overflow-y-auto'>
                {/* <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification> */}
                {
                    
                    notification.map((notice)=>{
                        // console.log(notice.id);
                        return (
                            <div>
                                <Notification title={notice.content}></Notification>
                            </div>
                        )
                    })
                }
            </div>
        </div>
        {/* <div className='border-2 border-black rounded-md m-4 shadow-xl'>
            <div className='border-b-2 border-black pt-2 pl-2'>
                <h2 className='text-lg font-semibold'>Complaints</h2>
            </div>
            <div className='max-h-64 overflow-y-auto'>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
                <Notification title="This is the Notification"></Notification>
            </div>
        </div> */}
      </div>
    </div>
  )
}

export default Dashboard