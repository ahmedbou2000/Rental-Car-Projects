import { Button, Grid } from "@mui/material";
import "../ComponentsStyle/ReservationPanel.css";
import book_bg from "../assets/images/book-bg.png";
import { DatePicker } from "antd";
import { useEffect, useState } from "react";
import $ from "jquery";
const ReservationPanel = () => {
  const { RangePicker } = DatePicker;
  const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));
  useEffect(() => {
    $(window).on("resize", () => {
      setIsmobile(localStorage.getItem("isMobile"));
    });
  }, []);
  const panelStyle = {
    backgroundImage: `url(${book_bg})`,
  };
  return (
    <div
      style={panelStyle}
      className="reservationPanel container shadow w-100 p-3"
      id="reserver"
    >
      <h1 className="f-25 Poppins b-8 py-2">Réserver un voiture</h1>
      <Grid container>
        <Grid
          item
          xs={12}
          sm={12}
          md={12}
          lg={12}
          //   style={{ height: "60px" }}
          className="center mb-3"
        >
          <Grid container>
            <Grid
              item
              xs={12}
              sm={12}
              md={9}
              lg={9}
              className="h-100 center-column"
            >
              {ismobile === "true" ? (
                <div className="w-100">
                  <DatePicker
                    placeholder="date départ"
                    className="w-100 my-1"
                    size="large"
                  />
                  <DatePicker
                    placeholder="date arrivée"
                    className="w-100 my-1"
                    size="large"
                  />
                </div>
              ) : (
                <RangePicker
                  className="theDatePicker w-100"
                  placeholder={["date départ", "date arrivée"]}
                />
              )}
            </Grid>
            <Grid item xs={12} sm={12} md={3} lg={3} className="h-100  center">
              <Button
                variant="contained"
                className={`btn-reserver shadow w-100 ${
                  ismobile === "true" ? "my-2" : ""
                }`}
              >
                Réserver
              </Button>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </div>
  );
};

export default ReservationPanel;
