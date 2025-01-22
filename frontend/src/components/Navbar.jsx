import React, { useContext } from 'react'
import { FaMoon, FaSun } from 'react-icons/fa'
import { ThemeCotext } from '../context/ThemeContextProvider'

const Navbar = () => {
    // const {theme, toggleTheme} = useContext(ThemeCotext)
    return (
        <div className='bg-yellow-200 text-gray-900 border-2 border-black p-4 flex justify-between items-center'>
            <h1>Dashboard</h1>
            {/* <button className='text-2xl text-dark' onClick={toggleTheme}>
                {theme === "light" ? <FaMoon /> : <FaSun />}
            </button> */}
        </div>
    )
}

export default Navbar