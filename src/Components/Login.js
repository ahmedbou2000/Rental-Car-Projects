import { Button, Checkbox, FormControlLabel, Grid, TextField } from "@mui/material";
import "../ComponentsStyle/Login.css";
// import { useEffect, useState } from "react";
// import $ from "jquery";
const Login = () => {
//   const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));

//   useEffect(() => {
//     $(window).on("resize", () => {
//       setIsmobile(localStorage.getItem("isMobile"));
//     });
//   }, []);
  return (
    <div className="w-100 h-100  d-flex flex-column justify-content-center align-items-center">
      <h1 className="Poppins w-100  text-center f-20 mt-3">
        Connectez-vous à votre compte
      </h1>
      <p className="Derrik f-15 text-center">
        et consulter votre historique de location, vos préférences et vos
        réservations en cours.
      </p>
      <Grid container className="w-100 h-100 center">
        {/* {ismobile === "true" ? (
          ""
        ) : (
          <Grid item xs={0} sm={0} md={6} lg={6} className="w-100 h-100 center">
            <img src={loginVector} alt="login vector" className="w-100 " />
          </Grid>
        )} */}
        <Grid
          item
          xs={12}
          sm={12}
          md={12}
          lg={12}
          className="w-100 h-100 d-flex flex-column justify-content-center align-items-center p-3"
        >
          <TextField
            variant="filled"
            label="Email"
            className="w-100 my-2"
            type="email"
            size="medium"
          />
          <TextField
            variant="filled"
            label="mot de passe"
            className="w-100 my-2"
            type="password"
            size="medium"
          />
          <div className="w-100">
            <FormControlLabel
              label="se souvenir de moi"
              control={<Checkbox />}
            />
          </div>
          <Button variant="contained" className="Poppins w-100 bg-success my-4" size='large'>se connecter</Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default Login;
