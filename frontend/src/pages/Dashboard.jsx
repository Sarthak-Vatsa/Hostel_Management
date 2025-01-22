import React from 'react'
import Sidebar from '../components/Sidebar'
import Navbar from '../components/Navbar'
import Card from '../components/Card'
import { FaBox, FaCog, FaShoppingCart, FaUsers } from 'react-icons/fa'

const Dashboard = () => {
  return (
    <div className='flex bg-yellow-50'>
      <Sidebar />
      <div className='grow ml-16 md:ml-64 h-full lg:h-screen text-gray-900'>
        {/* <Navbar /> */}
        <div className='border-black border-2 rounded-md m-2 bg-yellow-200'>
            <div className='grow p-8'>
                <h2 className='text-2xl mb-4'>Dashboard</h2>
                <div className='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6'>
                    <Card icon={<FaShoppingCart />} title="Notification" value="140"/>
                    <Card icon={<FaBox />} title="Complaint" value="120"/>
                    {/* <Card icon={<FaUsers />} title="Users" value="30"/> */}
                    {/* <Card icon={<FaCog />} title="Settings" value="11"/> */}
                </div>
            </div>
        </div>
      </div>
    </div>
  )
}

export default Dashboard