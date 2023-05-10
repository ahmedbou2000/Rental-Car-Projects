import React from "react";

import "../ComponentsStyle/Navbar.css";
import { Button, Divider, Grid, Modal } from "@mui/material";
import Logo from "../assets/images/logo.png";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import $ from "jquery";
import links from "../Config/NavLinks.json";
import { Sling as Hamburger } from "hamburger-react";
import Login from "./Login";
import SignUp from "./SignUp";
const Navbar = () => {
  const [showNavbg, setShowedNav] = useState($(window).scrollTop() > 50);
  const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));
  const [NavOpen, setNavOpen] = useState(false);
  const [LoginOpen, setLoginOpen] = useState(false);
  const [isLogin, setIsLogin] = useState(true);
  const state = useSelector((state) => state);
  const linkStyle = {
    color: state.color,
    backgroundColor:"transparent",
    border:"none"
  };
  useEffect(() => {
    $(window)
      .on("scroll", () => {
        setShowedNav($(window).scrollTop());
      })
      .on("storage", () => {
        setIsmobile(localStorage.getItem("isMobile"));
      })
      .on("resize", () => {
        setIsmobile(localStorage.getItem("isMobile"));
      });
  }, []);

  return (
    <div
      style={{ backgroundColor: showNavbg ? "white" : "transparent" }}
      className={` ${ismobile === "true" ? "" : "container"}
      ${ismobile === "false" && showNavbg ? "shadow" : ""}
       h-100 rounded w-100`}
    >
      <Grid container className="h-100 rounded">
        <Grid
          item
          xs={6}
          sm={6}
          md={ismobile === "true" ? 6 : 2}
          lg={ismobile === "true" ? 6 : 3}
          className="h-100 "
        >
          <img src={Logo} alt={"logo de rental car"} className="imgLogo" />
        </Grid>
        {ismobile === "false" ? (
          <>
            <Grid item xs={12} sm={12} md={6} lg={6} className="h-100 center">
              {links.map((link, index) => (
                <a
                  key={index}
                  href={link.href}
                  style={linkStyle}
                  className="mx-2 Rubik text-capitalize link"
                >
                  {link.label}
                </a>
              ))}
            </Grid>
            <Grid
              item
              xs={12}
              sm={12}
              md={4}
              lg={3}
              className="h-100 end-column"
            >
              <Button
                variant="text"
                style={{ color: "black", height: "50px" }}
                onClick={() => {
                  setLoginOpen(true);
                  setIsLogin(true);
                }}
              >
                se connecter
              </Button>
              <Button
                variant="contained"
                className="shadow ms-3"
                style={{ backgroundColor: "#ff4d30", height: "50px" }}
                onClick={() => {
                  setLoginOpen(true);
                  setIsLogin(false);
                }}
              >
                s'enregistrer
              </Button>
            </Grid>
          </>
        ) : (
          <Grid
            item
            xs={6}
            sm={6}
            md={6}
            lg={6}
            className="d-flex justify-content-end align-items-center"
          >
            <Hamburger
              onToggle={() => {
                setNavOpen(!NavOpen);
              }}
              toggled={NavOpen}
            />
          </Grid>
        )}
      </Grid>
      {/************************ Modal Hamburger *****************/}
      <Modal open={NavOpen} className="center">
        <div className={"ModalBoxMobile"}>
          <span className="w-100 my-1  d-flex justify-content-end">
            <Hamburger
              onToggle={() => {
                setNavOpen(!NavOpen);
              }}
              toggled={true}
            />
          </span>
          {links.map((link, index) => (
            // <span className="w-100 my-1 text-center navItemMobile">
            <React.StrictMode key={index}>
              <a
                href={link.href}
                style={linkStyle}
                className="w-100  my-1 Rubik text-capitalize link text-center navItemMobile"
                onClick={() => {
                  setNavOpen(!NavOpen);
                }}
              >
                {link.label}
              </a>
              <Divider  className="w-100" />
            </React.StrictMode>
            // </span>
          ))}
          <button
            style={linkStyle}
            className="w-100  my-1 Rubik text-capitalize link text-center navItemMobile Hand"
            onClick={() => {
              setNavOpen(!NavOpen);
              setLoginOpen(true);
              setIsLogin(true);
            }}
          >
            se connecter
          </button>
          <Divider className="w-100" />
          <button
            style={linkStyle}
            className="w-100  my-1 Rubik text-capitalize link text-center navItemMobile Hand"
            onClick={() => {
              setNavOpen(!NavOpen);
              setLoginOpen(true);
              setIsLogin(false);
            }}
          >
            s'enregistrer
          </button>
        </div>
      </Modal>
      {/************************* Login & SignUp Modal *********************/}
      <Modal open={LoginOpen} className="center">
        <div
          className={`loginModel ${
            ismobile === "true" ? "w-100" : "w-25"
          } bg-white shadow rounded`}
          style={{ minHeight: ismobile === "true" ? "100vh" : "30rem" }}
        >
          <div
            className="w-100 d-flex justify-content-end align-items-center "
            style={{ height: "10%" }}
          >
            <Hamburger
              onToggle={() => {
                setLoginOpen(!LoginOpen);
              }}
              toggled={LoginOpen}
            />
          </div>
          <div style={{ height: "90%" }} className="w-100 ">
            {isLogin ? <Login /> : <SignUp />}
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default Navbar;
