import doneImg from "../assets/svgs/done.svg";
const DoneStep = () => {
  return (
    <div className="done-div-show w-100 h-100 center-column">
      <img
        src={doneImg}
        alt={"Done"}
        style={{ pointerEvents: "none", height: "200px" }}
      />
      <h1 className="Poppins b-7 f-25 mt-4 mb-1">demande envoyé avec succes</h1>
      {/* <p className="Poppins text-center">
        vous pouvez consulté vos demandes de location sur{" "}
        <span className="orangeColor Hand Poppins">mes demandes</span>
      </p> */}
    </div>
  );
}

export default DoneStep