import React from 'react'
import nitkkrlogo from "../assets/nitkkr_logo.jpg"
import { Button } from '../components/ui/button'
import { Link } from 'react-router-dom'

function Home() {
  return (
    <div className='bg-yellow-200 flex h-screen justify-center'>
        <div className='h-full flex flex-col justify-center max-w-3xl w-full items-center'>
            <div className='border-black border-2 h-min flex flex-col items-center p-4 space-y-8 max-w-xl w-full bg-white shadow-2xl rounded-lg'>
                <div className='w-32'>
                    <img src={ nitkkrlogo } alt="" />
                </div>
                <div>
                    <p className='text-2xl font-semibold'>Hostel Managment Website</p>
                </div>
                <div className='flex w-1/2 justify-between'>
                    <div>
                       <Link to="/signup">
                            <Button>Sign Up</Button>
                       </Link>
                    </div>
                    <div>
                        <Link to="/signin">
                            <Button>Sign In</Button>
                       </Link>
                    </div>
                </div>
            </div>
        </div>
      
    </div>
  )
}

export default Home
