import React from 'react'
import error404 from "../assets/images/404.jpg"
import { Link } from 'react-router-dom'

const NotFound = () => {
  return (
    <div className='w-100 center-column' style={{height:"100vh"}}>
        <img src={error404} alt={"erreu 404"} style={{height:"250px",pointerEvents:"none"}}/>
        <h3 className='Poppins'>la page que vous demander est introuvable</h3>
        <Link to={"/"} className='orangeColor Hand Poppins'>Accueil</Link>
    </div>
  )
}

export default NotFound