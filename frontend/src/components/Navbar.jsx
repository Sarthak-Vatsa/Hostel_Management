import React, { useContext } from 'react'
import { FaMoon, FaSun } from 'react-icons/fa'
import { ThemeCotext } from '../context/ThemeContextProvider'

const Navbar = () => {
    // const {theme, toggleTheme} = useContext(ThemeCotext)
    return (
        <div className=' text-gray-900 p-4 flex justify-between items-center border-b-2 border-black'>
            <h1 className='font-bold text-3xl'>Dashboard</h1>
            {/* <button className='text-2xl text-dark' onClick={toggleTheme}>
                {theme === "light" ? <FaMoon /> : <FaSun />}
            </button> */}
        </div>
    )
}

export default Navbar