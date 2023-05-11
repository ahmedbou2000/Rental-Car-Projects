import { Grid, TextField } from "@mui/material";
import "../ComponentsStyle/Account.css";
import { Button } from "@nextui-org/react";
import { useState } from "react";

const Account = () => {
  const [isNewPwd, setisNewPwd] = useState();
  const [isNewPwdMatch, setPwdMatch] = useState();
  const [newPWd, setnewPwd] = useState("");
  const [oldPWd, setoldPwd] = useState("");
  return (
    <div className="container w-100 h-100 d-flex flex-column align-items-center py-5">
      <h1 className="Poppins my-5">Information personnelles</h1>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Nom"
            className="w-100 m-2"
            value={"BOUKHRISS"}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Prenom"
            className="w-100 m-2"
            value={"MOHAMED"}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Adresse"
            className="w-100 m-2"
            value={"13 RUE 21 HAY EL WIFAQ SIDI SLIMANE BERKANE"}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Télephone"
            className="w-100 m-2"
            value={"0652085526"}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={12} lg={12}>
          <TextField
            variant="outlined"
            label="Email"
            className="w-100 m-2"
            disabled
            value={"bouukhriss@gmail.com"}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={12} lg={12}>
          <Button
            type="submit"
            className="w-100 h-100 m-2"
            size={"lg"}
            color={"success"}
          >
            mise a jour information
          </Button>
        </Grid>
      </Grid>

      {/********************************Partie de changement de mot de passe****************************************/}

      <h1 className="Poppins my-5">Changement de mot de passe</h1>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center">
          <p>Ancien Mot de passe</p>
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            className="w-100 m-2"
            type="password"
            value={oldPWd}
            onChange={(e) => {
              setoldPwd(e.target.value);
            }}
          />
        </Grid>

        <Grid item xs={12} sm={12} md={6} lg={6} className="center">
          <p>Nouveau Mot de passe</p>
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            className="w-100 m-2"
            type="password"
            error={isNewPwd}
            helperText={isNewPwd ? "entrer un mot de passe différent" : ""}
            value={newPWd}
            onChange={(e) => {
              setnewPwd(e.target.value);
              setisNewPwd(e.target.value === oldPWd);
            }}
          />
        </Grid>

        <Grid item xs={12} sm={12} md={6} lg={6} className="center">
          <p>Confirmer Nouveau Mot de passe</p>
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            className="w-100 m-2"
            type="password"
            error={isNewPwdMatch}
            helperText={
              isNewPwdMatch
                ? "la confirmation de mot de passe ne ressemble pas au nouveau mot de passe"
                : ""
            }
            onChange={(e) => {
              setPwdMatch(e.target.value !== newPWd);
            }}
          />
        </Grid>

        <Grid item xs={12} sm={12} md={12} lg={12}>
          <Button className="w-100 h-100 my-2" size={"lg"} color="success">
            changer mot de passe
          </Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default Account;
