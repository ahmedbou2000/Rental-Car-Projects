import "../ComponentsStyle/CarShow.css";
import { Grid } from "@mui/material";
import { MdDateRange, MdOutlineAir } from "react-icons/md";
import { GiCarDoor, GiGearStickPattern } from "react-icons/gi";
import { useEffect, useState } from "react";
import { BsFuelPump } from "react-icons/bs";
import CarMenuItem from "./CarMenuItem";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";

const CarShow = () => {
  const dispatch = useDispatch();
  const [myCars, setMyCars] = useState([]);
  const [carDetails, setCarDetails] = useState([]);
  const state = useSelector((state) => state);


  const SelectDetail = (idV) => {
    const det = carDetails.filter((det) => det.idVoiture === idV);
    dispatch({ type: "carDetail", payload: det[0] });
  };

  const fetchCars = () => {
    axios
      .get("http://localhost:3001/api/voiture/all", {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        setMyCars(response.data);
      })
      .catch((error) => {
        console.log("error occured");
      });
  };

  const fetchDetails = () => {
    axios
      .get("http://localhost:3001/api/voiture/detail/all", {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        // console.log(response.data);
        setCarDetails(response.data);
      })
      .catch((error) => {
        console.log("error occured");
      });
  };
  useEffect(() => {
    fetchCars();
    fetchDetails();
  }, []);

  const [selected, setSelected] = useState(
    myCars.length > 0
      ? myCars.img
      : "cliquer pour afficher les informations de chaque voiture"
  );

  return (
    <div className="w-100 carShowcontainer " id={"CarModel"}>
      <div className="container w-100 h-100 py-3">
        <h5 className="Poppins b-7 text-center">Modèles des véhicules</h5>
        <h1 className="Poppins b-7 text-center">Notre flotte de location</h1>
        <p
          className={`Derik my-2 f-20 text-center `}
          style={{ color: "#72717c" }}
        >
          Choisissez parmi une variété de nos incroyables véhicules à louer pour
          votre prochaine aventure ou voyage d'affaires
        </p>
        <div className="carShow w-100 h-100">
          <Grid container className="w-100 h-100 center">
            <Grid
              item
              xs={12}
              sm={12}
              md={2}
              lg={2}
              className="d-flex flex-column justify-content-center align-items-center    "
            >
              {myCars.map((element, index) => (
                <CarMenuItem
                  key={index}
                  label={element.MARQUE + " " + element.MODELE}
                  immatricule={element.IMMATRICULE}
                  idKey={element.MARQUE + " " + element.MODELE}
                  onClick={() => {
                    setSelected(element.img);
                    SelectDetail(element.IDVOITURE);
                    dispatch({
                      type: "carModel",
                      payload: element.IMMATRICULE,
                    });
                  }}
                />
              ))}
            </Grid>
            <Grid
              item
              xs={12}
              sm={12}
              md={8}
              lg={8}
              className="imgContainer center"
              id="showCase"
            >
              <img
                src={
                  selected ===
                  "cliquer pour afficher les informations de chaque voiture"
                    ? ""
                    : `http://localhost/restfulAPI/images./rental%20cars/${selected}`
                }
                alt={selected}
                className=" imgCar"
              />
            </Grid>
            <Grid
              item
              xs={12}
              sm={12}
              md={2}
              lg={2}
              className="DetailsContainer h-100 w-100 d-flex flex-column justify-content-center px-2 "
            >
              <span>
                <span className="f-25 orangeColor b-7">
                  {state.CarDetail.price} DH{" "}
                </span>{" "}
                /jour
              </span>
              <span className="f-15 b-7 my-1">
                <MdDateRange className="orangeColor f-25" />{" "}
                {state.CarDetail.year}
              </span>
              <span className="f-15 b-7 my-1">
                <GiCarDoor className="orangeColor f-25" />{" "}
                {state.CarDetail.doors}
              </span>
              <span className="f-15 b-7 my-1">
                <MdOutlineAir className="orangeColor f-25" />{" "}
                {state.CarDetail.ac === "T" ? "Oui" : "Non"}
              </span>
              <span className="f-15 b-7 my-1">
                <GiGearStickPattern className="orangeColor f-25" />{" "}
                {state.CarDetail.gear}
              </span>
              <span className="f-15 b-7 my-1">
                <BsFuelPump className="orangeColor f-25" />{" "}
                {state.CarDetail.fuel}
              </span>
            </Grid>
          </Grid>
        </div>
      </div>
    </div>
  );
};

export default CarShow;
