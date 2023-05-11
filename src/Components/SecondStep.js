import { Button, Grid, TextField } from "@mui/material";

const SecondStep = (props) => {
  return (
    <div
      className="information-div-show-1 w-100"
      style={{ height: "100%", backgroundColor: "whitesmoke" }}
    >
      <Grid container className="w-100 ">
        <Grid item xs={12} sm={12} md={12} lg={12} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"Voiture"}
            style={{ textAlign: "center" }}
            value={props.Car}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"Date départ"}
            value={props.Depart}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"Date retour"}
            value={props.Retour}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"Nom locataire"}
            value={props.NomLocataire}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"Prenom locataire"}
            value={props.PrenomLocataire}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"Email"}
            value={props.Email}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={6} lg={6} className="center  p-3">
          <TextField
            className="w-100 txt-disabled"
            label={"N°télephone"}
            value={props.Telephone}
          />
        </Grid>
        <Grid item xs={12} sm={12} md={12} lg={12} className="center  p-3">
          <Button
            variant="contained bg-success text-white p-3 w-100 my-5"
            size="large"
            onClick={()=>{
              props.onClick(true)
            }}
          >
            confirmer et envoyer
          </Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default SecondStep;
