-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 07 juil. 2023 à 14:37
-- Version du serveur : 10.10.2-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rental_car_v2`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `IDAGENCE` int(11) NOT NULL AUTO_INCREMENT,
  `NOMAGENCE` text DEFAULT NULL,
  `ADRESSEAGENCE` text DEFAULT NULL,
  `EMAILAGENCE` text DEFAULT NULL,
  `MDP` text DEFAULT NULL,
  PRIMARY KEY (`IDAGENCE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`IDAGENCE`, `NOMAGENCE`, `ADRESSEAGENCE`, `EMAILAGENCE`, `MDP`) VALUES
(1, 'RENTALOO', 'Adresse agence', 'email@email.com', '010203'),
(2, 'RENTALOO', 'Adresse agence', 'email@email.com', '010203');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `IDCLIENT` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` text DEFAULT NULL,
  `PRENOM` text DEFAULT NULL,
  `ADRESSE` text DEFAULT NULL,
  `EMAIL` text DEFAULT NULL,
  `TEL` text DEFAULT NULL,
  `mdp` text NOT NULL,
  `status` text NOT NULL,
  PRIMARY KEY (`IDCLIENT`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IDCLIENT`, `NOM`, `PRENOM`, `ADRESSE`, `EMAIL`, `TEL`, `mdp`, `status`) VALUES
(1, 'BOUKHRISS', 'MOHAMED', '13 RUE 21', 'BOOUKHRISS@GMAIL.COM', '0652085526', 'admin67', 'confirmé'),
(11, 'BOUKHRISS', 'MOUAD', '13 RUE 21 HAY EL WIFAK SIDI SLIMANE CHERAA', 'BOUUKHRISS@GMAIL.COM', '0652085526', '147852369.Mohamedoush', 'confirmé'),
(12, 'Bouguerba', 'ahmed', 'adresse berkane maroc', 'Bouguerba@gmail.com', '0626556562', 'ahmed', 'comfirmé');

-- --------------------------------------------------------

--
-- Structure de la table `detail`
--

DROP TABLE IF EXISTS `detail`;
CREATE TABLE IF NOT EXISTS `detail` (
  `IDDETAIL` int(11) NOT NULL AUTO_INCREMENT,
  `IDRESERVATION` int(11) NOT NULL,
  `PRIXLOCATION` float DEFAULT NULL,
  `STATUT` text DEFAULT NULL,
  PRIMARY KEY (`IDDETAIL`),
  KEY `FK_AVOIR` (`IDRESERVATION`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `detail`
--

INSERT INTO `detail` (`IDDETAIL`, `IDRESERVATION`, `PRIXLOCATION`, `STATUT`) VALUES
(28, 28, 300, 'accepté'),
(30, 30, 200, 'accepté'),
(31, 31, 300, 'accepté');

-- --------------------------------------------------------

--
-- Structure de la table `detailvoiture`
--

DROP TABLE IF EXISTS `detailvoiture`;
CREATE TABLE IF NOT EXISTS `detailvoiture` (
  `idDetail` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  `doors` int(11) NOT NULL,
  `ac` varchar(1) NOT NULL,
  `price` float NOT NULL,
  `gear` text NOT NULL,
  `fuel` text NOT NULL,
  `idVoiture` int(11) NOT NULL,
  PRIMARY KEY (`idDetail`),
  KEY `fk_Vehicule_Detail` (`idVoiture`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `detailvoiture`
--

INSERT INTO `detailvoiture` (`idDetail`, `year`, `doors`, `ac`, `price`, `gear`, `fuel`, `idVoiture`) VALUES
(1, 2021, 5, 'T', 250, 'Automatique', 'Diesel', 1),
(2, 2021, 5, 'T', 350, 'Automatique', 'Diesel', 2),
(3, 2022, 5, 'T', 400, 'Automatique', 'Diesel', 3),
(4, 2022, 5, 'T', 300, 'Automatique', 'Diesel', 4),
(5, 2022, 5, 'T', 300, 'Automatique', 'Diesel', 5);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `IDRESERVATION` int(11) NOT NULL AUTO_INCREMENT,
  `IDCLIENT` int(11) NOT NULL,
  `IDVOITURE` int(11) NOT NULL,
  `ID2EMECONDUCTEUR` int(11) DEFAULT NULL,
  `DATERESERVATION` date DEFAULT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  PRIMARY KEY (`IDRESERVATION`),
  KEY `FK_METTRE` (`IDCLIENT`),
  KEY `FK_PARTICIPE` (`ID2EMECONDUCTEUR`),
  KEY `FK_PARTICIPER` (`IDVOITURE`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`IDRESERVATION`, `IDCLIENT`, `IDVOITURE`, `ID2EMECONDUCTEUR`, `DATERESERVATION`, `DATEDEBUT`, `DATEFIN`) VALUES
(10, 1, 2, NULL, '2023-06-03', '2023-06-25', '2023-06-29'),
(22, 1, 1, 1, '2023-07-03', '2023-07-03', '2023-07-04'),
(26, 11, 5, 12, '2023-07-03', '2023-07-03', '2023-07-05'),
(27, 12, 5, 11, '2023-07-03', '2023-07-03', '2023-07-05'),
(28, 12, 2, 11, '2023-07-04', '2023-07-04', '2023-07-07'),
(30, 11, 5, 11, '2023-07-04', '2023-06-04', '2023-06-09'),
(31, 12, 4, 1, '2023-07-04', '2023-07-04', '2023-07-08');

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

DROP TABLE IF EXISTS `voiture`;
CREATE TABLE IF NOT EXISTS `voiture` (
  `IDVOITURE` int(11) NOT NULL AUTO_INCREMENT,
  `IDAGENCE` int(11) NOT NULL,
  `MARQUE` text DEFAULT NULL,
  `MODELE` text DEFAULT NULL,
  `IMMATRICULE` text DEFAULT NULL,
  `ANNEE` int(11) DEFAULT NULL,
  `CARBURANT` text DEFAULT NULL,
  `img` text NOT NULL,
  PRIMARY KEY (`IDVOITURE`),
  KEY `FK_CONTIENT` (`IDAGENCE`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`IDVOITURE`, `IDAGENCE`, `MARQUE`, `MODELE`, `IMMATRICULE`, `ANNEE`, `CARBURANT`, `img`) VALUES
(1, 1, 'Audi', 'A1', '43256-A-49', 2021, 'Diesel', 'A1.png'),
(2, 1, 'Opel', 'Corsa', '42218-A-49', 2022, 'Electrique', 'corsa.png'),
(3, 1, 'Audi ', 'A1', '13322-A-49', 2022, 'essence', 'A1.png'),
(4, 1, 'Volkswagen', 'Golf 7', '39421-A-49', 2020, 'Diesel', 'GOLF7R.png'),
(5, 1, 'Renault', 'Clio 4', '44037-A-50', 2022, 'Hybrid', 'clio.png'),
(34, 1, 'BMW', '2000', 'A - 49 - 1234', 2020, 'Diesel', '');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `detail`
--
ALTER TABLE `detail`
  ADD CONSTRAINT `FK_AVOIR` FOREIGN KEY (`IDRESERVATION`) REFERENCES `reservation` (`IDRESERVATION`);

--
-- Contraintes pour la table `detailvoiture`
--
ALTER TABLE `detailvoiture`
  ADD CONSTRAINT `fk_Vehicule_Detail` FOREIGN KEY (`idVoiture`) REFERENCES `voiture` (`IDVOITURE`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_METTRE` FOREIGN KEY (`IDCLIENT`) REFERENCES `client` (`IDCLIENT`),
  ADD CONSTRAINT `FK_PARTICIPE` FOREIGN KEY (`ID2EMECONDUCTEUR`) REFERENCES `client` (`IDCLIENT`),
  ADD CONSTRAINT `FK_PARTICIPER` FOREIGN KEY (`IDVOITURE`) REFERENCES `voiture` (`IDVOITURE`);

--
-- Contraintes pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `FK_CONTIENT` FOREIGN KEY (`IDAGENCE`) REFERENCES `agence` (`IDAGENCE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
