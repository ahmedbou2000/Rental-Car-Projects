import $ from "jquery";

const checkMobile = () => {
  localStorage.setItem("isMobile", $(window).width() < 1000);
};
$(window).on("resize", checkMobile);

const initialState = {
  dark: false,
  color: "#000",
  bgcolor: "#efefef",
  selectedtestimonial:0,
  selectedCar:"mercedes"
};

checkMobile();
const Reducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case "dark":
      return { ...state, dark: true, color: "#fff", bgcolor: "#000" };
    case "next-testimonial":
      // console.log("handle Next Dispatch");
      return {
        ...state,
        selectedtestimonial:
          state.selectedtestimonial +1>1?1:state.selectedtestimonial +1,
      };
    case "previous-testimonial":
      // console.log("handle Previous Dispatch");
      return {
        ...state,
        selectedtestimonial:
          state.selectedtestimonial - 1 < 0 ? 0 : state.selectedtestimonial - 1,
      };
      case "carModel":
        return { ...state, selectedCar :payload};
    default:
      return { ...state };
  }
};

export default Reducer;
