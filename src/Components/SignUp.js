import "../ComponentsStyle/SignUp.css";
import {Grid,TextField,FormControlLabel,Checkbox,Button} from "@mui/material"
import {useState} from "react";

const SignUp = () => {
    const [pwd, setPwd] = useState("")
    const [pwd2,setPwd2]  = useState("")
  return (
    <div className="w-100 h-100   d-flex flex-column justify-content-center align-items-center">
      <h1 className="Poppins w-100  text-center f-20 mt-3">inscrivez-vous</h1>
      <p className="Derrik f-15 text-center">
        Pour que nous puissions vous fournir la meilleure expérience de location
        possible, nous avons besoin de quelques informations de base. Merci de
        remplir le formulaire ci-dessous avec vos détails personnels
      </p>
      <Grid container className="w-100 h-100 center">
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
            label="Nom"
            className="w-100 my-1"
            size="small"
          />
          <TextField
            variant="filled"
            label="Prenom"
            className="w-100 my-1"
            size="small"
          />
          <TextField
            variant="filled"
            label="Email"
            className="w-100 my-1"
            type="email"
            size="small"
          />
          <TextField
            variant="filled"
            label="mot de passe"
            className="w-100 my-1"
            type="password"
            size="small"
            value={pwd}
            error={pwd != pwd2} 
            onChange={(e) => setPwd(e.target.value)}
          />
          <TextField
            variant="filled"
            label="confirmer mot de passe"
            className="w-100 my-1"
            type="password"
            size="small"
            value={pwd2}
            onChange={(e) => setPwd2(e.target.value)}
            error={pwd != pwd2}
          />
          <div className="w-100">
            <FormControlLabel
              label="J'accepte les conditions d'utilisation."
              control={<Checkbox />}
            />
          </div>
          <Button
            variant="contained"
            className="Poppins w-100 bg-success my-4"
            size="large"
          >
            s'enregistrer
          </Button>
        </Grid>
      </Grid>
    </div>
  );
}

export default SignUp