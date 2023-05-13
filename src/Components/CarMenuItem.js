import { useSelector } from "react-redux";
import {useEffect,useState} from "react"
import $ from "jquery";

const CarMenuItem = (props) => {
  const state = useSelector((state) => state);
  const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));
  useEffect(() => {
    $(window).on("resize", () => {
      setIsmobile(localStorage.getItem("isMobile"));
    });
  }, []);
  return ismobile === "true" ? (
    <a
      className={`modelBtn w-100 center Roboto b-7 ${
        props.immatricule === state.selectedCar ? "carActive orangeColor" : ""
      }`}
      onClick={() => {
        props.onClick();
      }}
      href="#showCase"
    >
      <span>{props.label}</span>
    </a>
  ) : (
    <div
      className={`modelBtn w-100 center Roboto b-7 ${
        props.immatricule === state.selectedCar ? "carActive orangeColor" : ""
      }`}
      onClick={() => {
        props.onClick();
      }}
    >
      <span>{props.label}</span>
    </div>
  );
};

export default CarMenuItem;
