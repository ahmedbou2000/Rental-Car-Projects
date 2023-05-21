import { Badge } from "@nextui-org/react";
import { Grid } from "@mui/material";
import { MdOutlineCode } from "react-icons/md";
const DemandItem = (props) => {
  return (
    <div
      className="opacityanimation w-100 h-100 shadow rounded my-2"
      style={{ backgroundColor: "whitesmoke"}}
    >
      <Grid container style={{minHeight:"60px"}} className="py-3">
        <Grid
          item
          xs={12}
          sm={12}
          md={4}
          lg={4}
          className="center Poppins b-7 f-20"
        >
          {props.car}
        </Grid>
        <Grid
          item
          xs={12}
          sm={12}
          md={4}
          lg={4}
        //   style={{ minHeight: "80px" }}
          className="center Montserrat b-5 f-18"
        >
          {props.depart} <MdOutlineCode className="mx-2" /> {props.retour}
        </Grid>
        <Grid
          item
          xs={12}
          sm={12}
          md={4}
          lg={4}
        //   style={{ minHeight: "80px" }}
          className="center d-flex"
        >
          <Badge
            color={
              props.etat === "en attente"
                ? "warning"
                : props.etat === "accepté"
                ? "success"
                : props.etat === "refusé"
                ? "error"
                : ""
            }
            className="badge_container"
          >
            {props.etat}
          </Badge>
        </Grid>
      </Grid>
    </div>
  );
};

export default DemandItem;
