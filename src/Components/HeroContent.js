
import { Grid, Button } from "@mui/material";
import "../PagesStyle/HomePage.css";
import rentalVector from "../assets/images/main-car.png";
import { ImKey2 } from "react-icons/im";
import { AiFillCaretRight } from "react-icons/ai";
import ReservationPanel from "./ReservationPanel";
import { useEffect,useState } from "react";
import $ from "jquery";
const HeroContent = () => {
    const [ismobile,setisMobile] = useState(localStorage.getItem("isMobile"));
    useEffect(()=>{
        $(window).on("resize",()=>{setisMobile(localStorage.getItem("isMobile"))})
        console.log(localStorage.getItem("isMobile"));
    },[])
  return (
    <div className="container w-100 h-100" id="accueil">
      <Grid container className="h-100">
        <Grid
          item
          xs={12}
          sm={12}
          md={ismobile === "true" ? 12 : 5}
          lg={ismobile === "true" ? 12 : 5}
          className="h-100 d-flex justify-content-center flex-column"
        >
          <h3
            className={`Poppins b-6 ${
              ismobile === "true" ? "text-center" : ""
            }`}
            style={{ fontSize: ismobile === "true" ? "17px" : "23px" }}
          >
            Planifiez votre voyage maintenant
          </h3>
          <h1
            className={`Poppins b-7 ${
              ismobile === "true" ? "text-center" : ""
            }`}
            style={{ fontSize: ismobile === "true" ? "37px" : "53px" }}
          >
            Économisez{" "}
            <span
              className="Poppins b-9"
              style={{
                fontSize: ismobile === "true" ? "37px" : "53px",
                color: "#ff4d30",
              }}
            >
              Plus
            </span>{" "}
            avec notre location de voiture
          </h1>
          <p
            className="Rubik my-3"
            style={{ fontSize: "16px", color: "#706f7b" }}
          >
            Louez la voiture de vos rêves. Des prix imbattables, des miles
            illimités, des options de prise en charge flexibles et bien plus
            encore.
          </p>
          <div className="mt-3">
            <Button
              size="large"
              variant="contained"
              endIcon={<ImKey2 />}
              className="mx-2"
              style={{
                backgroundColor: "#ff4d30",
                color: "white",
                width: "45%",
                height: "60px",
              }}
              href="#reserver"
            >
              {" "}
              reserver{" "}
            </Button>
            <Button
              size="large"
              variant="contained"
              endIcon={<AiFillCaretRight />}
              className="mx-2"
              style={{
                backgroundColor: "black",
                color: "white",
                width: "45%",
                height: "60px",
              }}
              href="#CarModel"
            >
              savoir plus
            </Button>
          </div>
        </Grid>
        {ismobile === "false" ? (
          <Grid
            item
            xs={12}
            sm={12}
            md={7}
            lg={7}
            className="hero1_2 h-100 d-flex align-items-center"
          >
            <img src={rentalVector} alt={"voiture"} className="w-100 heroImg" />
          </Grid>
        ) : (
          ""
        )}
        <ReservationPanel />
      </Grid>
    </div>
  );
}

export default HeroContent