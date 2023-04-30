import { useEffect,useState } from "react";
import carIcon from "../assets/images/icon1.png";
import custommerIcon from "../assets/images/icon2.png";
import driveIcon from "../assets/images/icon3.png";
import "../ComponentsStyle/Plans.css";
import $ from "jquery";
const Plans = () => {
    const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));
    useEffect(()=>{
        $(window).on("resize", () => {
        setIsmobile(localStorage.getItem("isMobile"));
      });
    },[])
  return (
    <div
      className="w-100 center-column container"
      style={{
        minHeight: "150px",
        marginTop: ismobile === "true" ? "250px" : "160px",
      }}
      id={"comment"}
    >
      <h5 className="Poppins b-7 text-center">
        Planifiez votre voyage maintenant
      </h5>
      <h1 className="Poppins b-7 text-center">
        Location de voiture simple & rapide
      </h1>
      <div
        className={`w-100 plans-item${ismobile === "true" ? "2" : ""}`}
      >
        <div className="plan-item d-flex flex-column justify-content-begin align-items-center">
          <img src={carIcon} alt="car_select" className="imgPlans" />
          <h5 className="Poppins text-center b-7">Sélectionnez la voiture</h5>
          <p className="f-15 Darik text-center b-5 descText">
            Nous proposons une large gamme de véhicules pour tous vos besoins de
            conduite. Nous avons la voiture parfaite pour répondre à vos besoins
          </p>
        </div>

        <div className="plan-item d-flex flex-column justify-content-begin align-items-center">
          <img src={custommerIcon} alt="car_select" className="imgPlans" />
          <h5 className="Poppins text-center b-7">Contacter l'opérateur</h5>
          <p className="f-15 Darik text-center b-5 descText">
            Nos opérateurs compétents et sympathiques sont toujours prêts à
            répondre à toutes vos questions ou préoccupations
          </p>
        </div>

        <div className="plan-item d-flex flex-column justify-content-begin align-items-center">
          <img src={driveIcon} alt="car_select" className="imgPlans" />
          <h5 className="Poppins text-center b-7">Conduisons</h5>
          <p className="f-15 Darik text-center b-5 descText">
            Que vous preniez la route, nous avons ce qu'il vous faut avec notre
            large gamme de voitures
          </p>
        </div>
      </div>
    </div>
  );
};

export default Plans;
