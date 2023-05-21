import { Button, Grid, TextField } from "@mui/material";
import axios from "axios";
import { useSelector } from "react-redux";
import { ToastContainer, toast } from "react-toastify";
const SecondStep = (props) => {
  const state = useSelector((state) => state);

  const HandleAddReservation = () => {
    const reservation = {
      idClient: state.user.idClient,
      idCar: props.Car.CARID,
      date: `${new Date().getFullYear()}-${
        new Date().getMonth() + 1
      }-${new Date().getDate()}`,
      debut: props.Depart,
      retour: props.Retour,
      price: props.Car.price,
    };
    axios
      .post(
        `http://localhost:3001/api/reservation/add?client=${reservation.idClient}&car=${reservation.idCar}&date=${reservation.date}&depart=${reservation.debut}&retour=${reservation.retour}&price=${reservation.price}`
      )
      .then((response) => {
        if (response.data === 0) props.onClick(true);
        else {
          toast("une erreur s'est produite merci de réssayer !");
        }
      })
      .catch((error) => console.log("an error occured check details", error));
  };
  return (
    <div
      className="information-div-show-1 w-100"
      style={{ height: "100%", backgroundColor: "whitesmoke" }}
    >
      <Grid container className="w-100 ">
        <Grid item xs={12} sm={12} md={12} lg={12} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"Voiture"}
            style={{ textAlign: "center" }}
            value={props.Car.MARQUE}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"Date départ"}
            value={props.Depart}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"Date retour"}
            value={props.Retour}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"Nom locataire"}
            value={props.NomLocataire}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"Prenom locataire"}
            value={props.PrenomLocataire}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"Email"}
            value={props.Email}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled disabledInpt"
            label={"N°télephone"}
            value={props.Telephone}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={12} lg={12} className="center  p-3">
          <Button
            variant="contained bg-success text-white p-3 w-100 my-5"
            size="large"
            onClick={HandleAddReservation}
          >
            confirmer et envoyer
          </Button>
        </Grid>
      </Grid>
      <ToastContainer position="bottom-center" />
    </div>
  );
};

export default SecondStep;
