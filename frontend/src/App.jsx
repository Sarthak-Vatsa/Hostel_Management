
import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Button } from "./components/ui/button"
import Home from "./pages/Home"
import Signin from "./pages/Signin"
import Signup from "./pages/Signup"
import Dashboard from "./pages/Dashboard"
import RegisterComplaint from "./components/RegisterComplaint"
import ComplaintSetup from "./components/ComplaintSetup"
import Profile from "./components/Profile"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home></Home>}></Route>
        <Route path="/signin" element={<Signin></Signin>}></Route>
        <Route path="/signup" element={<Signup></Signup>}></Route>
        <Route path="/dashboard" element={<Dashboard></Dashboard>}></Route>
        <Route path="/complaint" element={<RegisterComplaint></RegisterComplaint>}></Route>
        <Route path="/register/complaint" element={<ComplaintSetup></ComplaintSetup>}></Route>
        <Route path="/profile" element={<Profile></Profile>}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
