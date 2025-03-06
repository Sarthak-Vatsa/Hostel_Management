// import React, { useContext } from 'react'
// import { FaMoon, FaSun } from 'react-icons/fa'
// import { ThemeCotext } from '../context/ThemeContextProvider'

// const Navbar = () => {
//     // const {theme, toggleTheme} = useContext(ThemeCotext)
//     return (
//         <div className=' text-gray-900 p-4 flex justify-between items-center border-b-2 border-black'>
//             <h1 className='font-bold text-3xl'>Dashboard</h1>
//             {/* <button className='text-2xl text-dark' onClick={toggleTheme}>
//                 {theme === "light" ? <FaMoon /> : <FaSun />}
//             </button> */}
//         </div>
//     )
// }

// export default Navbar

import {
    Popover,
    PopoverContent,
    PopoverTrigger,
  } from "@/components/ui/popover"
  
import React, { useEffect } from 'react'
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { LogOut, User2 } from "lucide-react"
import { Link, useNavigate } from "react-router-dom"
import { useDispatch, useSelector } from "react-redux"
// import { toast } from "sonner"
import axios from "axios"
import { setUser } from "@/redux/authSlice"

const Navbar = () => {
    const {user} = useSelector(store=>store.auth);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    // useEffect(()=>{
    //     const validateUser = async ()=>{
    //             try{
    //                 const res = await axios.get(`${User_API_End_Point}/validate`,{
    //                     withCredentials:true,
    //                     validateStatus: () => true  // ✅ Treats all status codes as successful
    //                 });
    //                 if(!res.data.sucess){
    //                     dispatch(setUser(null));
    //                     // navigate("/");
    //                     toast.error(res.data.message);
    //                 }
    //             }
    //             catch(error){
    //                 console.log(error);
    //             }
    //     }
    //     validateUser();
    // },[])

    const logoutHandler = async ()=>{
        try {
            const res = await axios.post("http://localhost:8080/students/logout",{
                withCredentials:true
            });

            console.log(res);
            // if(res.data.sucess){   
            //     dispatch(setUser(null));
            //     navigate("/");
            //     // toast.success(res.data.message);
            // }
            dispatch(setUser(null));
        } catch (error) {
            console.log(error);
            // toast.error(error.response.data.message);
        }
    }

  return (
    <div className='bg-white'>
        <div className='flex items-center justify-between mx-auto max-w-7xl h-16'>
            <div>
                <h1 className='text-2xl font-bold'>Hostel<span className='text-[#F83002]'>Management</span></h1>
            </div>
            <div className='flex items-center gap-12'>
                <ul className='flex font-medium items-center gap-5'>
                    {
                        user && user?.role==='ADMIN' ? (
                            <>
                                <li><Link to='/admin/companies'>Companies</Link></li> 
                                <li><Link to='/admin/jobs'>Jobs</Link></li>
                            </>
                        ):(
                            <>
                                <li><Link to='/dashboard'>Home</Link></li>
                                <li><Link to='/'>Notification</Link></li>
                                <li><Link to='/complaint'>Register Complaint</Link></li>
                            </>
                        )
                    }
                </ul> 
                {
                    !user?(
                        <div className="flex items-center gap-2">
                            <Link to="/signin"><Button variant="outline">LogIn</Button></Link>
                            <Link to="/signup"><Button className="bg-[#6A38C2] hover:bg-[#5b30a6]" variant="outline">SignUp</Button></Link>
                        </div>
                    ):(
                        <Popover>
                            <PopoverTrigger asChild>
                                <Avatar className='cursor-pointer'>
                                    <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" />
                                </Avatar>
                            </PopoverTrigger>

                            <PopoverContent className='w-88'>
                                <div className="flex gap-4">
                                    <Avatar className='cursor-pointer'>
                                        <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" />
                                    </Avatar>
                                    <div>
                                        <h4 className="font-medium">{user?.rollNo}</h4>
                                        <p className="text-sm text-muted-foreground"></p>
                                    </div>
                                </div>
                                <div className="flex flex-col my-2 text-gray-600">
                                    {
                                        user && user?.role==='student' && (
                                            <div className="flex w-fit items-center gap-2 cursor-pointer">
                                                <User2></User2>
                                                <Button variant="link"><Link to="/profile">View Profile</Link></Button>
                                            </div>
                                        )
                                    }
                                    <div className="flex w-fit items-center gap-2 cursor-pointer">
                                        <LogOut></LogOut>
                                        <Button onClick={logoutHandler} variant="link">LogOut</Button>
                                    </div>
                                </div>
                            </PopoverContent>
                        </Popover>
                    )
                }      
            </div>
        </div>
    </div>
  )
}

export default Navbar
