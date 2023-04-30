import { Grid } from "@mui/material";
import "../ComponentsStyle/Contact.css";
import {BsFillTelephoneFill} from "react-icons/bs";
import {GrMail} from "react-icons/gr";
import { IoLocationSharp, IoSend } from "react-icons/io5";
import {TextField,Button} from "@mui/material"

const Contact = () => {
  return (
    <Grid container className="container w-100 " style={{ minHeight: "20rem" }} id='contact'>
      <Grid
        item
        xs={12}
        sm={12}
        md={6}
        lg={6}
        className="w-100 d-flex flex-column justify-content-center align-items-center p-3"
      >
        <h1 className="Poppins b-7 f-35 text-center  my-4">
          Besoin d'informations supplémentaires ?
        </h1>
        <p className="f-20">
          Un professionnel aux multiples facettes compétent dans de multiples
          domaines de la recherche, du développement ainsi qu'un spécialiste de
          l'apprentissage. Plus de 5 ans d'expérience.
        </p>
        <span className="w-100 f-20 b-6 ">
          <span className="Hand  HoverOrangeColor">
            <BsFillTelephoneFill className="mx-2" />
            (212)6 06 06 06 06{" "}
          </span>
        </span>
        <span className="w-100 f-20 b-6">
          <span className="Hand HoverOrangeColor">
            <GrMail className="mx-2" />
            rentalcar@rentalo.ma
          </span>
        </span>

        <span className="w-100 f-20 b-6">
          <span className="Hand HoverOrangeColor">
            <IoLocationSharp className="mx-2" />
            Oujda, Maroc
          </span>
        </span>
      </Grid>
      <Grid
        item
        xs={12}
        sm={12}
        md={6}
        lg={6}
        className="w-100 d-flex flex-column justify-content-center align-items-center p-3"
      >
        <TextField
          className="w-100 my-3"
          label="Nom Complet"
          variant="standard"
        />
        <TextField
          className="w-100 my-3"
          label="Email"
          variant="standard"
          type="email"
        />
        <TextField
          className="w-100 my-3"
          label="Message"
          variant="standard"
          multiline
        />
        <Button variant="contained" startIcon={<IoSend/>} className="w-100 my-5" size="large" style={{backgroundColor:"#ff4d30"}}>
            Envoyer
        </Button>
      </Grid>
    </Grid>
  );
};

export default Contact;
