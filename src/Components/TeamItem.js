import "../ComponentsStyle/TeamItem.css";
import { BsLinkedin, BsFacebook } from "react-icons/bs";
import { MdEmail } from "react-icons/md";
const TeamItem = (props) => {
  return (
    <div
      className="center-column Poppins teamItemContainer"
    >
      <div
        className="TeamImgCircle my-2"
        style={{ backgroundImage: `url(${props.Img})` }}
      ></div>
      <h3 className="b-6 mt-3 text-uppercase">{props.Name}</h3>
      <span>{props.Function}</span>
      <span className="my-3">
        <a
          href={`https://www.linkedin.com/in/${props.userLinkedin}`}
          target="_blank"
          rel="noreferrer"
        >
          <BsLinkedin className="orangeColor f-25 Hand mx-2" />
        </a>
        <a
          href={`https://www.facebook.com/${props.userFacebook}`}
          target="_blank"
          rel="noreferrer"
        >
          <BsFacebook className="orangeColor f-25 Hand mx-2" />
        </a>
        <a href={`mailto: ${props.Email}`} target="_blank" rel="noreferrer">
          <MdEmail className="orangeColor f-25 Hand mx-2" />
        </a>
      </span>
    </div>
  );
};

export default TeamItem;
