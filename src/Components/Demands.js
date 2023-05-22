import { Grid } from "@mui/material";
import "../ComponentsStyle/Demands.css";
import { AiOutlineFileAdd } from "react-icons/ai";
import DemandItem from "./DemandItem";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import axios from "axios";
const Demands = (props) => {
  const state = useSelector((state) => state);
  const [demands, setDemands] = useState([]);
  useEffect(() => {
    axios
      .get(`http://localhost:3001/api/demands?client=${state.user.idClient}`)
      .then((response) => {
        console.log(response.data);
        setDemands(response.data);
      })
      .catch((error) => {
        console.log("an error occured", error);
      });
  }, [state.user.idClient]);
  return (
    <div className="w-100 h-100 d-flex flex-column align-items-center">
      <h1 className="Poppins f-25 b-7 w-100">Mes demandes de location</h1>
      <span className="w-100 text_right">
        <a href={"#accueil"} style={{ textDecoration: "none" }}>
          <AiOutlineFileAdd
            className="f-30 Hand HoverOrangeColor"
            onClick={() => {
              props.Close();
            }}
          />
        </a>
      </span>
      <Grid container className="my-4" spacing={2} rowSpacing={2}>
        <Grid
          item
          xs={12}
          sm={12}
          md={12}
          lg={12}
          style={{ minHeight: "80px" }}
          className="d-flex flex-column justify-content-center py-2"
        >
          {demands.map((element, index) => (
            <DemandItem
              key={index}
              car={`${element.MARQUE} ${element.MODELE} ${element.ANNEE}`}
              depart={element.DATEDEBUT}
              retour={element.DATEFIN}
              etat={element.STATUT}
            />
          ))}

          {/* <DemandItem
            car="Opel Corsa 2022"
            depart="2023-04-11"
            retour="2023-04-14"
            etat="accepté"
          />
          <DemandItem
            car="Audi A1 2022"
            depart="2023-04-21"
            retour="2023-04-23"
            etat="refusé"
          /> */}
        </Grid>
      </Grid>
    </div>
  );
};

export default Demands;
