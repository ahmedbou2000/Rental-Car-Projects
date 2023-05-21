import { useState } from "react";
import "../ComponentsStyle/CarItem.css";
import { useEffect } from "react";
const CarItem = (props) => {
  const ItemStyle = {
    width: "350px",
    minHeight: "300px",
    borderRadius: "10px",
    backgroundColor: "white",
  };
  const [carsId, setCarsID]=useState("");
  useEffect(()=>{setCarsID(props.CarId);},[props.CarId])
  return (
    <div
      style={ItemStyle}
      className="shadow d-flex flex-column justify-content-center align-items-center Hand carItem m-4"
      onClick={()=>{
        const car = {
          CARID: carsId,
          MARQUE: `${props.Marque} ${props.Model} ${props.Year} `,
          price: props.Price,
        };
        // console.log("from car item",car)
        props.setSelectedCar(car);
        props.onClick(false);
        // alert(carsId)
      }}
    >
      <img
        src={"http://localhost/restfulApi/images/rental%20cars/" + props.Image}
        alt="Car"
        className="search-item-image"
      />
      <h3 className="Poppins b-6">{props.Marque}</h3>
      <h5 className="Poppins b-6">{props.Model}</h5>
      <span className="w-100 text_right pe-3 f-20 b-7 orangeColor ">
        {props.Price} DH
      </span>
    </div>
  );
};

export default CarItem;
