import LoadingSearch from "./LoadingSearch";
import { useDispatch, useSelector } from "react-redux";
import FirstStep from "./FirstStep";
import SecondStep from "./SecondStep";
import { useEffect, useState } from "react";
import DoneStep from "./DoneStep";
import axios from "axios";
const ReservationProc = (props) => {
  const dispatch = useDispatch();
  const state = useSelector((state) => state);
  const [is1Step, set1step] = useState(true);
  const [isDone, setDone] = useState(false);
  const [CarsData, setCarsData] = useState([]);
  const [selectedCar,setSelectedCar] = useState()
  const clickSet = (value) => {
    set1step(value);
  };
  const Done = (value) => {
    setDone(value);
  };

  useEffect(() => {
    dispatch({ type: "loading" });
    axios
      .get(
        `http://localhost:3001/api/checking?depart=${props.Depart}&retour=${props.Retour}`
      )
      .then((response) => {
        console.log(response.data);
        setCarsData(response.data);
        dispatch({ type: "loading" });
        console.log("from reservation proc component", response.data);
      })
      .catch((error) => {
        console.log("an error occured while fetching car reservation", error);
        dispatch({ type: "loading" });
      });
  }, [dispatch, props.Depart, props.Retour]);

const HandleCarSelect =(car)=>{
    setSelectedCar(car);
    // console.log("from reservation proc",car)
}

  return (
    <div
      className={`w-100 h-100 d-flex flex-column justify-content-center align-items-center`}
    >
      {state.isLoading ? (
        <>
          <LoadingSearch />
          <h2 className="poppins flash">recherche en cours</h2>
        </>
      ) : (
        <div className="Montserrat w-100 d-flex flex-column justify-content-center align-items-center h-100 reservationProcContent">
          {isDone ? (
            ""
          ) : (
            <h1
              className="Poppins b-7 text-center w-100 my-4"
              style={{ height: "10vh" }}
            >
              {is1Step ? "Résultat de recherche" : "Information de Location"}
            </h1>
          )}

          {is1Step ? (
            <FirstStep onClick={clickSet} Data={CarsData} setSelectedCar={HandleCarSelect}/>
          ) : !isDone ? (
            <SecondStep
              Car={selectedCar}
              Depart={props.Depart}
              Retour={props.Retour}
              NomLocataire={state.user.Nom}
              PrenomLocataire={state.user.PRENOM}
              Email={state.user.EMAIL}
              Telephone={state.user.TEL}
              onClick={Done}
            />
          ) : (
            <DoneStep />
          )}
        </div>
      )}
    </div>
  );
};

export default ReservationProc;
