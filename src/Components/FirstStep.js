import { useState } from "react";
import CarItem from "./CarItem";
import NoCarFound from "./NoCarFound";
import { useEffect } from "react";

const Car ={
    Image:"c220.png",
    Marque:"Mercedes-Benz",
    Model:"Classe C220",
    Price:400
}
const FirstStep = (props) => {
    const [Cars,setCars] = useState([]);
    useEffect(()=>{
        setCars([Car])
    },[])
  return (
    <div className="car-div-show w-100" style={{ height: "90vh" }}>
      {Cars.length === 0 ? (
        <NoCarFound />
      ) : (
        Cars.map((element, index) => (
          <CarItem
            key={index}
            Image={element.Image}
            Marque={element.Marque}
            Model={element.Model}
            Price={element.Price}
            onClick={props.onClick}
          />
        ))
      )}
    </div>
  );
}

export default FirstStep