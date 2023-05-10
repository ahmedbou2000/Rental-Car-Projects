import LoadingSearch from "./LoadingSearch";
import CarItem from "./CarItem";
import {useSelector} from "react-redux";
import carImg from "../assets/images/rental cars/c220.png"
import NoCarFound from "./NoCarFound";
const ReservationProc = () => {
  const state = useSelector(state=>state)
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
          <h1
            className="Poppins b-7 text-center w-100 mb-4"
            style={{ height: "10%" }}
          >
            Résultat de recherche
          </h1>
          <div className="car-div-show w-100" style={{ height: "90%" }}>
            <CarItem
              Image={"opel-corsa.png"}
              Marque={"Mercedes-Benz"}
              Model={"Classe C220"}
              Price={400}
            />

            <CarItem
              Image={
                "GOLF7R.png"
              }
              Marque={"Mercedes-Benz"}
              Model={"Classe C220"}
              Price={400}
            />
            {/* <NoCarFound/> */}
          </div>
        </div>
      )}
    </div>
  );
};

export default ReservationProc;
