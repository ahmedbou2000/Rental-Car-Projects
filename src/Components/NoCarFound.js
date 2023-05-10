import EmptyResult from "../assets/svgs/Empty.svg";
const NoCarFound = () => {
  return (
    <div className="w-100 h-100 center-column">
      <img src={EmptyResult} alt="this vectore  for empty seach" style={{height:"250px"}}/>
      <p className="text-center f-20 w-75 b-7 Montserrat">
        Désolé, aucune voiture de location n'a été trouvée pour les critères de
        recherche spécifiés. Veuillez modifier vos critères de recherche et
        réessayer. Si vous avez des questions ou des préoccupations, n'hésitez
        pas à nous contacter pour obtenir de l'aide
      </p>
    </div>
  );
};

export default NoCarFound;
