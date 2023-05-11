import { User, Dropdown } from "@nextui-org/react";
import { useDispatch } from "react-redux";
import "../ComponentsStyle/LoggedProfile.css";
import { Modal } from "@mui/material";
import { useState } from "react";
import { Sling as Hamburger } from "hamburger-react";
import Demands from "./Demands";
import Account from "./Account";
import { CgProfile } from "react-icons/cg";
import { MdOutlineDocumentScanner } from "react-icons/md";
import { SlLogout } from "react-icons/sl";

const LoggedProfile = (props) => {
  const [OpenModal, setModalOpen] = useState(false);
  const [isDemand, setDemand] = useState(false);
  const dispatch = useDispatch();

  const Close = () => {
    setModalOpen(false);
  };
  const HandleDropSelect = (key) => {
    switch (key) {
      case "Demands":
        setDemand(true);
        setModalOpen(true);
        break;
      case "Profile":
        setDemand(false);
        setModalOpen(true);
        break;
      case "Disconnect":
        dispatch({ type: "login", payload: false });
        break;
      default:
        break;
    }
  };
  return (
    <>
      <Dropdown>
        <Dropdown.Trigger>
          {props.mobile ? (
            <button className="w-100  my-1 Rubik text-capitalize link text-center navItemMobile Hand bg-none border-none">
              options
            </button>
          ) : (
            <User size="lg" text="BM" className="userProfile" />
          )}
        </Dropdown.Trigger>
        <Dropdown.Menu onAction={HandleDropSelect} color="warning">
          <Dropdown.Item key={"account"}>BOUKHRISS MOHAMED</Dropdown.Item>
          <Dropdown.Item key={"Profile"} withDivider icon={<CgProfile />}>
            mon compte
          </Dropdown.Item>
          <Dropdown.Item key={"Demands"} icon={<MdOutlineDocumentScanner />}>
            mes demandes
          </Dropdown.Item>
          <Dropdown.Item
            key={"Disconnect"}
            withDivider
            color="error"
            icon={<SlLogout />}
          >
            se déconnecter
          </Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>

      <Modal open={OpenModal} className="center">
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
                setModalOpen(!OpenModal);
                setDemand(false);
              }}
              toggled={OpenModal}
            />
          </div>
          <div
            style={{ height: "90%" }}
            className="container w-100 overflow-auto"
          >
            {isDemand ? <Demands Close={Close} /> : <Account />}
          </div>
        </div>
      </Modal>
    </>
  );
};

export default LoggedProfile;
