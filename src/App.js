import "./App.css";
import HomePage from "./Pages/HomePage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useEffect } from "react";
import $ from "jquery";
import NotFound from "./Components/NotFound";
import ConfirmEmail from "./Components/ConfirmEmail";
function App() {
  useEffect(() => {
    $(window).on("hashchange", function () {
      const url = window.location.href;
      window.history.pushState(null, null, url.substring(0, url.indexOf("#")));
    });
  }, []);
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/confirm/:email/:date*" element={<ConfirmEmail/>}/>
        <Route path="*" element={<NotFound/>}/>
      </Routes>
    </Router>
  );
}

export default App;
