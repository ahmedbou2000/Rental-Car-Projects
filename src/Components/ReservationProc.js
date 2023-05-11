import LoadingSearch from "./LoadingSearch";
import { useSelector } from "react-redux";
import FirstStep from "./FirstStep";
import SecondStep from "./SecondStep";
import { useState } from "react";
import DoneStep from "./DoneStep";
const ReservationProc = (props) => {
  const state = useSelector((state) => state);
  const [is1Step, set1step] = useState(true);
  const [isDone, setDone] = useState(false);
  const clickSet = (value) => {
    set1step(value);
  };
  const Done = (value) => {
    setDone(value);
  };
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
            <FirstStep onClick={clickSet} />
          ) : !isDone ? (
            <SecondStep
              Car={"Mercedes-Benz c220"}
              Depart={props.Depart}
              Retour={props.Retour}
              NomLocataire={"BOUKHRISS"}
              PrenomLocataire={"MOHAMED"}
              Email={"bouukhriss@gmail.com"}
              Telephone={"0652085526"}
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
