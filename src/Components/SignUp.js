import "../ComponentsStyle/SignUp.css";
import {
  Grid,
  TextField,
  FormControlLabel,
  Checkbox,
  Button,
} from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useSelector, useDispatch } from "react-redux";
const SignUp = (props) => {
  const state = useSelector((state) => state);
  const dispatch = useDispatch();
  const [pwd, setPwd] = useState("");
  const [pwd2, setPwd2] = useState("");
  const [nom, setNom] = useState("");
  const [prenom, setPrenom] = useState("");
  const [email, setEmail] = useState("");
  const [tel, setTel] = useState("");
  const [adresse, setAdress] = useState("");
  const [policy, setPolicy] = useState(false);

  const EmptyAll = () => {
    setPwd("");
    setPwd2("");
    setNom("");
    setPrenom("");
    setEmail("");
    setAdress("");
    setTel("");
    setPolicy(false);
  };
  const HandleSignUp = () => {
    dispatch({ type: "loading" });
    if (
      pwd !== "" &&
      pwd2 !== "" &&
      nom !== "" &&
      prenom !== "" &&
      email !== "" &&
      adresse !== "" &&
      tel !== "" &&
      policy
    ) {
      axios
        .post(
          `http://localhost:3001/api/signup?nom=${nom}&prenom=${prenom}&adress=${adresse}&email=${email}&mdp=${pwd}&tel=${tel}`
        )
        .then((resposne) => {
          // console.log("data received", resposne.data);
          if (resposne.data === 1) {
            toast(
              "vous avez déja un compte avec cette adresse mail! essayer de se connecter"
            );
            dispatch({ type: "loading" });
          } else {
            toast(
              "compte crée avec success ! merci de consulter votre boite mail pour confirmer votre compte."
            );
            EmptyAll();
            props.closeModal();
            dispatch({ type: "loading" });
          }
        });
    } else {
      toast("merci de remplir tous les champs!");
      dispatch({ type: "loading" });
    }
  };

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
            value={nom}
            onChange={(e) => setNom(e.target.value)}
          />
          <TextField
            variant="filled"
            label="Prenom"
            className="w-100 my-1"
            size="small"
            value={prenom}
            onChange={(e) => setPrenom(e.target.value)}
          />
          <TextField
            variant="filled"
            label="Adresse"
            className="w-100 my-1"
            size="small"
            value={adresse}
            onChange={(e) => setAdress(e.target.value)}
          />
          <TextField
            variant="filled"
            label="Télephone"
            className="w-100 my-1"
            size="small"
            value={tel}
            onChange={(e) => setTel(e.target.value)}
          />
          <TextField
            variant="filled"
            label="Email"
            className="w-100 my-1"
            type="email"
            size="small"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            variant="filled"
            label="mot de passe"
            className="w-100 my-1"
            type="password"
            size="small"
            value={pwd}
            error={pwd !== pwd2}
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
            error={pwd !== pwd2}
          />
          <div className="w-100">
            <FormControlLabel
              label="J'accepte les conditions d'utilisation."
              control={
                <Checkbox
                  value={policy}
                  onChange={(e) => {
                    setPolicy(e.target.checked);
                  }}
                />
              }
            />
          </div>
          <Button
            variant="contained"
            className="Poppins w-100 bg-success my-4"
            size="large"
            onClick={HandleSignUp}
            disabled={state.isLoading}
          >
            s'enregistrer
          </Button>
        </Grid>
      </Grid>
      <ToastContainer position="bottom-center" />
    </div>
  );
};

export default SignUp;
