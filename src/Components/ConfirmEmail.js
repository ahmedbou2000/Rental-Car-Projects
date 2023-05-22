import { useParams } from "react-router-dom";
import CryptoJS from "crypto-js";
import { useEffect, useState } from "react";
import Confirmation from "../assets/images/confirmation.jpg";
import Expiry from "../assets/images/ExpiryMail.jpg";
import { Link } from "react-router-dom";

function isWithinOneDay(date1, date2) {
  const oneDayMilliseconds = 24 * 60 * 60 * 1000; // Number of milliseconds in a day
  const difference = Math.abs(date1.getTime() - date2.getTime());

  return difference <= oneDayMilliseconds;
}
const ConfirmEmail = () => {
  let { Email, Year, Month, Day } = useParams();
  const [isValid, setIsValid] = useState(false);
  useEffect(() => {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, "0");
    const day = String(currentDate.getDate()).padStart(2, "0");
    const formattedDate = `${year}-${month}-${day}`;
    const data = `${Year / 1648}-${Month / 1648}-${Day / 1648}`;
    if (isWithinOneDay(new Date(formattedDate), new Date(data))) {
      setIsValid(true);
    }
  }, []);
  return (
    <>
      {isValid ? (
        <>
          <div className="w-100 center-column" style={{ height: "100vh" }}>
            <img
              src={Confirmation}
              alt={"erreu 404"}
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
              Confirmation expiré !<br/>
              <a className="Hand orangeColor">
                renvoyer un email de confirmation
              </a>
            </h3>
            <Link to={"/"} className="orangeColor Hand Poppins my-5">
              Accueil
            </Link>
          </div>
        </>
      )}
    </>
  );
};

export default ConfirmEmail;
