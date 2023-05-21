import { useState } from "react";
import CarItem from "./CarItem";
import NoCarFound from "./NoCarFound";
import { useEffect } from "react";

const FirstStep = (props) => {
    const [Cars,setCars] = useState([]);
    useEffect(()=>{
        setCars(props.Data)
    },[props.Data])
  return (
    <div className="car-div-show w-100" style={{ height: "90vh" }}>
      {Cars.length === 0 ? (
        <NoCarFound />
      ) : (
        Cars.map((element, index) => (
          <CarItem
            key={index}
            CarId={element.IDVOITURE}
            Image={element.img}
            Marque={element.MARQUE}
            Model={element.MODELE}
            Year ={element.year}
            Price={element.price}
            onClick={props.onClick}
            setSelectedCar={props.setSelectedCar}
          />
        ))
      )}
    </div>
  );
}

export default FirstStep