import "../ComponentsStyle/TestimonialItem.css";
import { Grid } from "@mui/material";
import { FaQuoteLeft, FaQuoteRight } from "react-icons/fa";
import ReactStars from "react-rating-stars-component";
import testimonial1 from "../assets/images/1.jpg";
import testimonial2 from "../assets/images/2.jpg";
import testimonial3 from "../assets/images/3.jpg";
import testimonial4 from "../assets/images/4.jpg";
import testimonialsList from "../Config/TestimonialsList.json";
import { useSelector } from "react-redux";

const Imgs = [testimonial1, testimonial2, testimonial3, testimonial4];
const TestimonialItem = (props) => {
  const state = useSelector((state) => state);
  return (
    <div className={`${props.className} testimonial_Item w-100 shadow h-100`}>
      <Grid container className={"w-100 h-100"} style={{ minHeight: "15rem" }}>
        <Grid
          item
          xs={12}
          sm={12}
          md={4}
          lg={4}
          className="h-100 d-flex flex-column justify-content-center align-items-center"
          style={{ minHeight: "15rem" }}
        >
          <div
            style={{
              backgroundImage: `url(${
                Imgs[testimonialsList[state.selectedtestimonial].img]
              })`,
            }}
            className="imgCirle"
          ></div>
          <ReactStars
            count={5}
            size={24}
            activeColor={"#ff4d30"}
            value={testimonialsList[state.selectedtestimonial].rate}
            isHalf
            edit={false}
          />
        </Grid>
        <Grid
          item
          xs={12}
          sm={12}
          md={8}
          lg={8}
          className="h-100 d-flex flex-column align-items-center justify-content-center w-100"
          style={{ minHeight: "15rem" }}
        >
          <div className="w-100" style={{ height: "15%" }}>
            <FaQuoteLeft className=" ps-2 f-35 orangeColor" />
          </div>
          <div className="w-100 center-column" style={{ height: "70%" }}>
            <h3 className="authorstate text-uppercase b-7 Roboto my-3">
              {testimonialsList[state.selectedtestimonial].Author}
            </h3>
            <p className="text-center f-16 Derik b-6 px-2">
              {testimonialsList[state.selectedtestimonial].content}
            </p>
          </div>
          <div
            className="w-100 d-flex justify-content-end"
            style={{ height: "15%" }}
          >
            <FaQuoteRight className="pe-2 f-35 orangeColor" />
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default TestimonialItem;
