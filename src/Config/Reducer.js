import $ from "jquery";

const checkMobile = () => {
  localStorage.setItem("isMobile", $(window).width() < 1000);
};
$(window).on("resize", checkMobile);

const initialState = {
  dark: false,
  color: "#000",
  bgcolor: "#efefef",
  selectedtestimonial: 0,
  selectedCar: "mercedes",
  isLoading: false,
  isLogged: false,
  user: {
    ADRESSE: "",
    EMAIL: "",
    Nom: "",
    PRENOM: "",
    TEL: "",
    idClient: 1,
  },
  CarDetail: {
    idDetail: 0,
    year: 0,
    doors: 0,
    ac: "T",
    price: 0,
    gear: "Automatique",
    fuel: "Diesel",
    idVoiture: 0,
  },
};

checkMobile();
const Reducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case "dark":
      return { ...state, dark: true, color: "#fff", bgcolor: "#000" };
    case "next-testimonial":
      return {
        ...state,
        selectedtestimonial:
          state.selectedtestimonial + 1 > 1 ? 1 : state.selectedtestimonial + 1,
      };
    case "previous-testimonial":
      return {
        ...state,
        selectedtestimonial:
          state.selectedtestimonial - 1 < 0 ? 0 : state.selectedtestimonial - 1,
      };
    case "carModel":
      return { ...state, selectedCar: payload };
    case "loading":
      return { ...state, isLoading: !state.isLoading };
    case "login":
      return { ...state, isLogged: payload };
    case "user":
      return { ...state, user: payload };

    case "carDetail":
      return { ...state, CarDetail: payload };
    default:
      return { ...state };
  }
};

export default Reducer;
