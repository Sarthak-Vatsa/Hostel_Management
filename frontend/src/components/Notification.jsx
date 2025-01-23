import React from 'react'

function Notification({title}) {
  return (
    <div className='p-4 mt-2 mx-8 rounded-md border-2 border-black bg-white'>
        <p className='font-semibold text-lg'>{title}</p>
    </div>
  )
}

export default Notification
