import { Grid } from "@mui/material";
import "../ComponentsStyle/Demands.css";
import { AiOutlineFileAdd } from "react-icons/ai";
import DemandItem from "./DemandItem";
const Demands = (props) => {
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
          <DemandItem
            car="Mercedes-Benz Class C220"
            depart="2023-05-12"
            retour="2023-05-14"
            etat="en attente"
          />
          <DemandItem
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
          />
        </Grid>
      </Grid>
    </div>
  );
};

export default Demands;
