import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import Confirmation from "../assets/images/confirmation.jpg";
import Expiry from "../assets/images/ExpiryMail.jpg";
import { Link } from "react-router-dom";
import axios from "axios";
import { Loading } from "@nextui-org/react";
import { useDispatch, useSelector } from "react-redux";

function isWithinOneDay(date1, date2) {
  const oneDayMilliseconds = 24 * 60 * 60 * 1000; // Number of milliseconds in a day
  const difference = Math.abs(date1.getTime() - date2.getTime());

  return difference <= oneDayMilliseconds;
}
const ConfirmEmail = () => {
  const dispatch = useDispatch();
  const state = useSelector(state=>state);
  const navigate = useNavigate();
  let { Email, Year, Month, Day } = useParams();
  const [isValid, setIsValid] = useState(false);

  useEffect(() => {
    dispatch({ type: "loading" });
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, "0");
    const day = String(currentDate.getDate()).padStart(2, "0");
    const formattedDate = `${year}-${month}-${day}`;
    const data = `${Year / 1648}-${Month / 1648}-${Day / 1648}`;
    if (isWithinOneDay(new Date(formattedDate), new Date(data))) {
      axios
        .get(`http://localhost:3001/api/checkforemail?email=${Email}`)
        .then((response) => {
          if (response.data === 1) {
            dispatch({ type: "loading" });
            navigate("/", { replace: true });
          } else {
            axios
              .post(`http://localhost:3001/api/confirm?email=${Email}`)
              .then((response) => {
                if (response.data === 0) {
                  setIsValid(true);
                  dispatch({ type: "loading" });
                }
              });
          }
        });
    }
  }, [Year, Month, Day, Email, navigate, dispatch]);
  return (
    <>
      {state.isLoading
        ?<div className="w-100 center" style={{height:"100vh"}}>
          <Loading/>
        </div>:<>
        {isValid ? (
          <>
            <div className="w-100 center-column" style={{ height: "100vh" }}>
              <img
                src={Confirmation}
                alt={"erreur 404"}
                style={{ height: "250px", pointerEvents: "none" }}
              />
              <h3 className="Poppins">
                Votre compte a été confirmé avec success !
              </h3>
              <Link to={"/"} className="orangeColor Hand Poppins">
                Accueil
              </Link>
            </div>
          </>
        ) : (
          <>
            <div className="w-100 center-column" style={{ height: "100vh" }}>
              <img
                src={Expiry}
                alt={"erreu 404"}
                style={{ height: "250px", pointerEvents: "none" }}
              />
              <h3 className="Poppins text-center">
                Confirmation expiré !<br />
                <span className="Hand orangeColor">
                  renvoyer un email de confirmation
                </span>
              </h3>
              <Link to={"/"} className="orangeColor Hand Poppins my-5">
                Accueil
              </Link>
            </div>
          </>
        )}
      </>
      }
    </>
  );
};

export default ConfirmEmail;
