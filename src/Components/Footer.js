

const Footer = () => {
  return (
    <div className="w-100 bg- pt-4 bg-light" style={{ minHeight: "10rem" }}>
      <div className="w-50 h-100 container d-flex flex-column justify-content-center align-items-center">
        <span className="w-100 text-center f-30">
          <span className="Poppins b-8 orangeColor">Rental</span>Car
        </span>
        <span className=" w-75 Poppins text-center f-17 GreyColor">
          Nous proposons une large gamme de véhicules pour tous vos besoins de
          conduite. Nous avons la voiture parfaite pour répondre à vos besoins.
        </span>
      </div>
      <div
        style={{ height: "2.4rem" }}
        className="w-100 orangeBg mt-3 d-flex justify-content-around align-items-center"
      >
        <span className="text-center f-16 text-white">
          <span className="Poppins b-8">Rental</span>Car
        </span>

        <span className="text-center f-16 text-white">
          <span className="Poppins b-8">2020 &copy;</span>
        </span>
      </div>
    </div>
  );
}

export default Footer