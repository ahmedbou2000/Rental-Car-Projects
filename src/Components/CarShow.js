import "../ComponentsStyle/CarShow.css";
import {Grid} from "@mui/material";
import Mercedes from "../assets/images/rental cars/c220.png";
import Audi from "../assets/images/rental cars/Audi_A1.png";
import Corsa from "../assets/images/rental cars/opel-corsa.png";
import Golf from "../assets/images/rental cars/GOLF7R.png";
import Clio from "../assets/images/rental cars/renault clio.png";
import Range from "../assets/images/rental cars/Evoque.png";
import { MdDateRange, MdOutlineAir } from "react-icons/md";
import { GiCarDoor, GiGearStickPattern } from "react-icons/gi";
import { useState } from "react";
import {BsFuelPump} from "react-icons/bs";
import carData from "../Config/CarsModels.json";
import CarMenuItem from "./CarMenuItem";
import { useDispatch } from "react-redux";
const Cars =[Mercedes,Range,Audi,Golf,Corsa,Clio]
const CarShow = () => {
    const dispatch = useDispatch();
    const [selected,setSelected] = useState(0);
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
              <CarMenuItem
                label={"Mercedes Benz C220"}
                idKey="mercedes"
                onClick={() => {
                  setSelected(0);
                  dispatch({ type: "carModel", payload: "mercedes" });
                }}
              />

              <CarMenuItem
                label={"Range Rover Evoque"}
                idKey="range"
                onClick={() => {
                  setSelected(1);
                  dispatch({ type: "carModel", payload: "range" });
                }}
              />

              <CarMenuItem
                label={"Audi A1 S-Line"}
                idKey="audi"
                onClick={() => {
                  setSelected(2);
                  dispatch({ type: "carModel", payload: "audi" });
                }}
              />

              <CarMenuItem
                label={"Volkswagen Golf 7"}
                idKey="golf"
                onClick={() => {
                  setSelected(3);
                  dispatch({ type: "carModel", payload: "golf" });
                }}
              />

              <CarMenuItem
                label={"Opel Corsa"}
                idKey="opel"
                onClick={() => {
                  setSelected(4);
                  dispatch({ type: "carModel", payload: "opel" });
                }}
              />

              <CarMenuItem
                label={"Renault Clio 4"}
                idKey="clio"
                onClick={() => {
                  setSelected(5);
                  dispatch({ type: "carModel", payload: "clio" });
                }}
              />
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
              <img src={Cars[selected]} alt={selected} className=" imgCar" />
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
                  {carData[selected].price} DH{" "}
                </span>{" "}
                /jour
              </span>
              <span className="f-15 b-7 my-1">
                <MdDateRange className="orangeColor f-25" />{" "}
                {carData[selected].year}
              </span>
              <span className="f-15 b-7 my-1">
                <GiCarDoor className="orangeColor f-25" />{" "}
                {carData[selected].door}
              </span>
              <span className="f-15 b-7 my-1">
                <MdOutlineAir className="orangeColor f-25" />{" "}
                {carData[selected].ac}
              </span>
              <span className="f-15 b-7 my-1">
                <GiGearStickPattern className="orangeColor f-25" />{" "}
                {carData[selected].gear}
              </span>
              <span className="f-15 b-7 my-1">
                <BsFuelPump className="orangeColor f-25" />{" "}
                {carData[selected].fuel}
              </span>
            </Grid>
          </Grid>
        </div>
      </div>
    </div>
  );
};

export default CarShow;
