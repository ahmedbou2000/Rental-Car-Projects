import {
  Button,
  Checkbox,
  FormControlLabel,
  Grid,
  TextField,
} from "@mui/material";
import "../ComponentsStyle/Login.css";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

const Login = (props) => {
  const state = useSelector((state) => state);
  const dispatch = useDispatch();
  const [userEmail, setEmail] = useState("");
  const [userPwd, setPwd] = useState("");
  const HandleLogin = () => {
    dispatch({ type: "loading" });
    axios
      .get("http://localhost:3001/api/auth", {
        params: { email: userEmail, mdp: userPwd },
      })
      .then((response) => {
        if (response.data.length === 1) {
          if (response.data[0].status === "confirmé") {
            dispatch({ type: "login", payload: true });
            dispatch({ type: "user", payload: response.data[0] });
            setEmail("");
            setPwd("");
            props.closeModal();
            dispatch({ type: "loading" });
            toast("Bienvenue de retour! Vous êtes connecté avec succès.");
          } else {
            toast("vous devez confirmer votre compte !");
            dispatch({ type: "loading" });
            props.closeModal();
          }
        } else {
          toast("email ou mot de passe incorrect ! Merci de réssayer !");
          dispatch({ type: "loading" });
        }
      })
      .catch((error) => console.log("error occured !"));
  };

  const handleEnterPress = (event) => {
    if (event.keyCode === 13) {
      HandleLogin();
    }
  };

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
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
          <TextField
            variant="filled"
            label="mot de passe"
            className="w-100 my-2"
            type="password"
            size="medium"
            onChange={(e) => {
              setPwd(e.target.value);
            }}
            onKeyDown={handleEnterPress}
          />
          <div className="w-100">
            <FormControlLabel
              label="se souvenir de moi"
              control={<Checkbox />}
            />
          </div>
          <Button
            variant="contained"
            className="Poppins w-100 bg-success my-4"
            size="large"
            onClick={HandleLogin}
            disabled={state.isLoading}
          >
            se connecter
          </Button>
          <ToastContainer position="bottom-center" />
        </Grid>
      </Grid>
    </div>
  );
};

export default Login;
