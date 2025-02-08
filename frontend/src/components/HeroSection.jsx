import React from 'react'
import { Button } from './ui/button'
import { Search } from 'lucide-react'

const HeroSection = () => {
  return (
    <div className='text-center'>
        <div className='flex flex-col gap-4 my-10'>
            <span className='mx-auto  px-4 py-2 rounded-full bg-gray-100 text-[#F83002] font-medium'>No.1 Hostel Mangement website</span>
            <h1 className='text-5xl font-bold'>Search, Register & <br></br> Get All <span className='text-[#6A38C2]'>Yours Compalints</span></h1>
            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Voluptatibus quo rem officia labore at!</p>
            <div className='flex w-[40%] shadow-lg border border-gray-200 pl-3 rounded-full items-center gap-4 mx-auto'>
                <input 
                type="text"
                placeholder='Find Your Dream Job'
                className='outline-none border-none w-full'
                />
                <Button className='rounded-r-full bg-[#6A38C2]'>
                    <Search className='h-5 w-5'></Search>
                </Button>
            </div>
        </div>
    </div>
  )
}

export default HeroSection