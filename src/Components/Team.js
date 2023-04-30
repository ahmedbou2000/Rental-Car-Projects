import { Grid } from "@mui/material";
import TeamItem from "./TeamItem";
import theImage from "../assets/images/112.png";
import theSecondImg from "../assets/images/8.jpg"

const Team = () => {
  return (
    <div
      className={`Teams_container container w-100 center-column `}
      style={{
        minHeight: "13rem",
      }}
      id="equipe"
    >
      {/* <h5 className="Poppins b-7 text-center">Révisé par des personnes</h5> */}
      <h1 className="Poppins b-7 text-center">
        Rencontrez notre équipe parfaite
      </h1>
      <Grid container className="w-100 h-100 my-4">
        <Grid item xs={12} sm={12} md={6} lg={6} className="center">
          <TeamItem
            Name="BOUKHRISS MOHAMED"
            Function="Developpeur Full Stack"
            Img={theImage}
            Email="booukhriss@gmail.com"
            userLinkedin={"boukhriss"}
            userFacebook={"booukhriss"}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center">
          <TeamItem
            Name="BOUGUERBA AHMED"
            Function="Developpeur Full Stack"
            Img={theSecondImg}
            Email="bouguerbaahmed06@gmail.com"
            userLinkedin={"ahmed-bouguerba-48107226a"}
            userFacebook={""}
          />
        </Grid>
      </Grid>
    </div>
  );
};

export default Team;
