import "../ComponentsStyle/Testimonials.css";
import TestimonialItem from "./TestimonialItem";
import { useEffect, useState } from "react";
import $ from "jquery";
import { GrPrevious, GrNext } from "react-icons/gr";
import { useDispatch } from "react-redux";
const Testimonials = () => {
  const dispatch = useDispatch();
  const [isShown,setShown] = useState(true)
  const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));
  useEffect(() => {
    $(window).on("resize", () => {
      setIsmobile(localStorage.getItem("isMobile"));
    });
  }, []);

  const HandlePrevious = () => {
    // alert("Handle Previous")
    setShown(false);
    dispatch({ type: "previous-testimonial" });
    setShown(true);
  };

  const HandleNext = () => {
    // alert("handle Next 1")
    setShown(false);
    dispatch({ type: "next-testimonial" });
    setShown(true);
  };

  return (
    <div
      className={`testimonials_container container w-100 center-column `}
      style={{
        minHeight: "13rem",
      }}
      id="testimonials"
    >
      <h5 className="Poppins b-7 text-center">Révisé par des personnes</h5>
      <h1 className="Poppins b-7 text-center">Témoignages des clients</h1>
      <p
        className={`Derik my-2 f-20 text-center ${
          ismobile === "true" ? "w-100" : "w-75"
        }`}
        style={{ color: "#72717c" }}
      >
        Découvrez l'impact positif que nous avons eu sur nos clients en lisant
        leurs témoignages. Nos clients ont fait l'expérience de notre service et
        de nos résultats, et ils sont impatients de partager leurs expériences
        positives avec vous.
      </p>
      <div
        className="container testimonials_items_container my-4 d-flex flex-row justify-content-center align-items-center"
        style={{ width: ismobile === "true" ? "100%" : "80%" }}
      >
        
        <div className="previousBtn  center me-3" onClick={HandlePrevious}>
          <GrPrevious className="f-25" />
        </div>
        <TestimonialItem className={`${isShown?"":"d-none"}`}/>
        <div className="nextBtn center ms-3" onClick={HandleNext}>
          <GrNext className="f-25" />
        </div>
        
      </div>
    </div>
  );
};

export default Testimonials;
