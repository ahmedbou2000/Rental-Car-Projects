import { Button, Grid } from "@mui/material";
import "../ComponentsStyle/ReservationPanel.css";
import book_bg from "../assets/images/book-bg.png";
import { DatePicker } from "antd";
import { useEffect, useState } from "react";
import { Modal } from "@mui/material";
import { Sling as Hamburger } from "hamburger-react";
import $ from "jquery";
import ReservationProc from "./ReservationProc";
import moment from "moment/moment";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ReservationPanel = () => {
  function disabledDate(current) {
    // Disable dates before today
    return current && current < moment().startOf("day");
  }
  const notify = () => {
    toast("Veuillez sélectionner une date départ et date retour !");
  };
  const { RangePicker } = DatePicker;
  const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));
  const [isReservationOpen, setReservationOpen] = useState(false);
  const [DepartDate, setDepart] = useState("");
  const [RetourDate, setRetour] = useState("");
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
                    disabledDate={disabledDate}
                    size="large"
                    onChange={(date, datestring) => {
                      if (date !== null) {
                        setDepart(
                          `${date.$y}-${date.$M + 1}-${date.$D}`
                        );
                      } else {
                        setDepart(null);
                      }
                    }}
                  />
                  <DatePicker
                    placeholder="date arrivée"
                    className="w-100 my-1"
                    disabledDate={disabledDate}
                    size="large"
                    onChange={(date, datestring) => {
                      if (date !== null) {
                        setRetour(
                          `${date.$y}-${date.$M + 1}-${date.$D}`
                        );
                      } else {
                        setRetour(null);
                      }
                    }}
                  />
                </div>
              ) : (
                <RangePicker
                  className="theDatePicker w-100"
                  placeholder={["date départ", "date arrivée"]}
                  disabledDate={disabledDate}
                  onChange={(date, datestring) => {
                    if (date !== null) {
                      setDepart(
                        `${date[0].$y}-${date[0].$M + 1}-${date[0].$D}`
                      );
                      setRetour(
                        `${date[1].$y}-${date[1].$M + 1}-${date[1].$D}`
                      );
                    } else {
                      setDepart(null);
                      setRetour(null);
                    }
                  }}
                />
              )}
            </Grid>
            <Grid item xs={12} sm={12} md={3} lg={3} className="h-100  center">
              <Button
                variant="contained"
                className={`btn-reserver shadow w-100 ${
                  ismobile === "true" ? "my-2" : ""
                }`}
                onClick={() => {
                  if (
                    RetourDate === "" ||
                    DepartDate === "" ||
                    RetourDate === null ||
                    DepartDate === null
                  ) {
                    notify();
                  } else {
                    setReservationOpen(!isReservationOpen);
                  }
                }}
              >
                Réserver
              </Button>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
      {/*******************************    Reservation modal   *****************************/}
      <Modal open={isReservationOpen} className="center">
        <div
          className={`loginModel w-100 h-100 shadow rounded`}
          style={{ backgroundColor: "whitesmoke" }}
        >
          <div
            className="w-100 d-flex justify-content-end align-items-center "
            style={{ height: "10%" }}
          >
            <Hamburger
              onToggle={() => {
                setReservationOpen(!isReservationOpen);
              }}
              toggled={isReservationOpen}
            />
          </div>
          <div style={{ height: "90%" }} className="container w-100 ">
            <ReservationProc Depart={DepartDate} Retour={RetourDate} />
          </div>
        </div>
      </Modal>
      <ToastContainer position="bottom-center" />
    </div>
  );
};

export default ReservationPanel;
