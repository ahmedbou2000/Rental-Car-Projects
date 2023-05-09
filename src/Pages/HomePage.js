import Navbar from "../Components/Navbar";
import "../PagesStyle/HomePage.css";
import { Layout } from "antd";
import herobg from "../assets/images/hero-bg.png";
import HeroContent from "../Components/HeroContent";
import { useEffect, useState } from "react";
import $ from "jquery";
import Plans from "../Components/Plans";
import Testimonials from "../Components/Testimonials";
import CarShow from "../Components/CarShow";
import Team from "../Components/Team";
import Contact from "../Components/Contact";
import Footer from "../Components/Footer";
const HomePage = () => {
  const { Header, Content } = Layout;
  const [showNavbg, setShowedNav] = useState($(window).scrollTop() > 50);
  const [ismobile, setIsmobile] = useState(localStorage.getItem("isMobile"));

  useEffect(() => {
    $(window)
      .on("scroll", () => {
        setShowedNav($(window).scrollTop());
      })
      .on("resize", () => {
        setIsmobile(localStorage.getItem("isMobile"));
      });
  }, []);
  return (
    <Layout>
      <Content
        className=""
        style={{ minHeight: "140vh", backgroundColor: "white" }}
      >
        <Header
          style={{
            backgroundColor:
              ismobile === "true" && showNavbg ? "white" : "transparent",
          }}
          className={`header w-100 ${
            ismobile === "true" && showNavbg ? "shadow" : ""
          }`}
        >
          <Navbar />
        </Header>
        <div
          className="container-header"
          style={{ backgroundImage: `url(${herobg})` }}
        >
          <HeroContent />
        </div>
        <Plans />
        <CarShow/>
        <Team/>
        <Testimonials/>
        <Contact/>
        <Footer/>
      </Content>
    </Layout>
  );
};

export default HomePage;
