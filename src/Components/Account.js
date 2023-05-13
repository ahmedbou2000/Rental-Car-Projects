import { Grid, TextField } from "@mui/material";
import "../ComponentsStyle/Account.css";
import { Button } from "@nextui-org/react";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
const Account = () => {
  const dispatch = useDispatch();
  const state = useSelector((state) => state);
  const [isNewPwd, setisNewPwd] = useState();
  const [isNewPwdMatch, setPwdMatch] = useState();
  const [newPWd, setnewPwd] = useState("");
  const [oldPWd, setoldPwd] = useState("");
  const [confirmationPwd, setConfirmationPwd] = useState("");
  const [nom, setNom] = useState(state.user.Nom);
  const [prenom, setPrenom] = useState(state.user.PRENOM);
  const [adresse, setAddresse] = useState(state.user.ADRESSE);
  const [tel, setTel] = useState(state.user.TEL);

  const HandleUpdateUser = () => {
    axios
      .post(
        `http://localhost:3001/api/client/update?nom=${nom}&prenom=${prenom}&adresse=${adresse}&tel=${tel}&idClient=${state.user.idClient}`
      )
      .then((response) => {
        if (response.data === 0) {
          const myuser = {
            ADRESSE: adresse,
            EMAIL: state.user.EMAIL,
            Nom: nom,
            PRENOM: prenom,
            TEL: tel,
            idClient: state.user.idClient,
          };
          dispatch({ type: "user", payload: myuser });
          toast("informations changée avec success !");
        }
      })
      .catch((error) => console.log("error occured", error));
  };

  const HandlePasswordChange = () => {
    if (
      newPWd !== "" &&
      newPWd !== null &&
      oldPWd !== "" &&
      oldPWd !== null &&
      confirmationPwd !== "" &&
      confirmationPwd !== null
    ) {
      if (oldPWd !== newPWd) {
        if (newPWd === confirmationPwd) {
          dispatch({ type: "loading" });
          axios
            .get("http://localhost:3001/api/auth", {
              params: { email: state.user.EMAIL, mdp: oldPWd },
            })
            .then((response) => {
              if (response.data.length === 1) {
                axios
                  .post(
                    `http://localhost:3001/api/client/change/password?email=${state.user.EMAIL}&oldPWD=${oldPWd}&newPWD=${newPWd}`
                  )
                  .then((result) => {
                    console.log(result.data);
                    if (result.data === 0) {
                      setoldPwd("");
                      setnewPwd("");
                      setConfirmationPwd("");
                      toast("mot de passe changée avec success");
                      dispatch({ type: "loading" });
                    } else {
                      toast("une erreur s'est produite merci de réssayer !");
                      dispatch({ type: "loading" });
                    }
                  });
              } else {
                toast("ancien mot de passe incorrect !");
                dispatch({ type: "loading" });
              }
            })
            .catch((error) => console.log("error occured !"));
        } else {
          toast(
            "le nouveau mot de passe doit être différent de mot de passe actuel !"
          );
        }
      } else {
        toast(
          "le nouveau mot de passe et sa confirmation ne sont pas identique"
        );
      }
    } else {
      toast("merci de remplir tous les champs nécessaire");
    }
  };

  return (
    <div className="container w-100 h-100 d-flex flex-column align-items-center py-5">
      <h1 className="Poppins my-5">Information personnelles</h1>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Nom"
            className="w-100 m-2"
            value={nom}
            onChange={(e) => setNom(e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Prenom"
            className="w-100 m-2"
            value={prenom}
            onChange={(e) => setPrenom(e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Adresse"
            className="w-100 m-2"
            value={adresse}
            onChange={(e) => setAddresse(e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <TextField
            variant="outlined"
            label="Télephone"
            className="w-100 m-2"
            value={tel}
            onChange={(e) => setTel(e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={12} lg={12}>
          <TextField
            variant="outlined"
            label="Email"
            className="w-100 m-2 disabledInpt"
            disabled
            value={state.user.EMAIL}
            style={{ cursor: "not-allowed !important" }}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={12} lg={12}>
          <Button
            type="submit"
            className="w-100 h-100 m-2"
            size={"lg"}
            color={"success"}
            onPress={HandleUpdateUser}
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
            value={confirmationPwd}
            helperText={
              isNewPwdMatch
                ? "la confirmation de mot de passe ne ressemble pas au nouveau mot de passe"
                : ""
            }
            onChange={(e) => {
              setConfirmationPwd(e.target.value);
              setPwdMatch(e.target.value !== newPWd);
            }}
          />
        </Grid>

        <Grid item xs={12} sm={12} md={12} lg={12}>
          <Button
            className="w-100 h-100 my-2"
            size={"lg"}
            color="success"
            onPress={HandlePasswordChange}
          >
            changer mot de passe
          </Button>
        </Grid>
      </Grid>
      <ToastContainer />
    </div>
  );
};

export default Account;
