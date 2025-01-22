import React from 'react'
import { Label } from './ui/label'
import { Input } from './ui/input'

function Inputbox({label,placeholder,type}) {
  return (
    <div>
        <div className="grid w-full items-center gap-1.5">
            <Label>{label}</Label>
            <Input type={type} placeholder={placeholder}></Input>
        </div>
    </div>
  )
}

export default Inputbox
