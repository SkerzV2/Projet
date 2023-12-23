-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 23 déc. 2023 à 01:39
-- Version du serveur : 8.0.31
-- Version de PHP : 8.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddprojet`
--

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

DROP TABLE IF EXISTS `competence`;
CREATE TABLE IF NOT EXISTS `competence` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_matiere` int NOT NULL,
  `id_user` int NOT NULL,
  `sous_matiere` longtext NOT NULL,
  `statut` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user_competence` (`id_user`),
  KEY `id_matiere_competence` (`id_matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`id`, `id_matiere`, `id_user`, `sous_matiere`, `statut`) VALUES
(1, 1, 3, '#verbesirréguliers#date#nombres', 1),
(2, 1, 1, '#gérondif#présent#date#nombres', 2),
(3, 1, 5, '#nombres', 3),
(4, 2, 1, '#contrats#régulation#organisation', 1),
(5, 2, 5, '#structurejuridique#droit', 1),
(6, 2, 2, '#intégration#rôleétat#environnement#facteurséconomiques', 2),
(7, 2, 7, '#facteurséconomiques#structurejuridique#droit', 3),
(8, 2, 6, '#contrats#régulation#organisation#intégration#rôleétat#environnement#facteurséconomiques#structurejuridique#droit', 1),
(9, 2, 3, '#organisation#intégration', 3),
(10, 2, 4, '#droit', 1),
(11, 3, 7, '#orthographe#conjugaison', 1),
(12, 3, 4, '#présent#futursimple#pronompersonnel#conjonctioncoordination#auxiliareavoir#indicatif', 3),
(13, 3, 6, '#participepassé#présent#futursimple#pronompersonnel', 1),
(14, 3, 3, '#auxiliareavoir#indicatif', 1),
(15, 4, 1, '#loidepoisson#probabilités#statistiques', 1),
(16, 4, 5, '#équations#factorisation#nombresrelatifs#intégrale#dérivée', 3),
(17, 4, 7, '#tableaudevariation#matrice', 1),
(18, 4, 3, '#équations#factorisation#nombresrelatifs#intégrale#dérivée#tableaudevariation#matrice#développement#loidepoisson#probabilités#statistiques', 3),
(19, 4, 4, '#nombresrelatifs#intégrale#dérivée#tableaudevariation', 1),
(20, 4, 2, '#factorisation', 1),
(21, 5, 1, '#java#sql#python#php#javascript#modelosi#tcpip', 1),
(22, 5, 5, '#windows#linux#dhcp', 1),
(23, 5, 2, '#poo#boucles#conditions#json#api', 1),
(24, 5, 7, '#java#sql', 3),
(25, 5, 6, '#linux#dhcp#dns#voip#cisco', 2),
(26, 5, 3, '#linux#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api', 3),
(27, 5, 4, '#javascript#modelosi#tcpip#windows#linux#dhcp#dns#voip#cisco', 1),
(28, 6, 7, '#puissanceetenjeuxmondiaux', 1),
(29, 6, 6, '#crise1929#rÃ©gimestotalitaires#secondeguerremondiale#tiersmonde#france', 1),
(30, 6, 3, '#secondeguerremondiale#tiersmonde#france#constructioneuropÃ©enne', 1),
(31, 6, 4, '#tiersmonde#france', 3),
(32, 3, 1, '#orthographe#conjugaison', 1),
(36, 3, 9, '#orthographe#pronompersonnel,#auxiliareavoir', 1),
(37, 5, 9, '#tcpip#linux#dhcp#cisco#api', 1),
(39, 6, 1, '#crise1929#régimestotalitaires#tiersmonde#constructioneuropéenne', 1);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_updated` date NOT NULL,
  `date_fin_demande` date NOT NULL,
  `sous_matiere` longtext NOT NULL,
  `id_user` int NOT NULL,
  `id_matiere` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_matiere` (`id_matiere`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `date_updated`, `date_fin_demande`, `sous_matiere`, `id_user`, `id_matiere`, `status`) VALUES
(1, '2023-09-25', '2023-12-28', '#tableaudevariation#matrice#développement', 3, 4, 0),
(2, '2023-09-25', '2024-01-04', '#nombresrelatifs#intégrale#loidepoisson#probabilités#statistiques', 5, 4, 0),
(3, '2023-10-06', '2023-10-11', '#java#sql#python#php', 6, 5, 0),
(4, '2023-10-08', '2023-10-13', '#conjonctioncoordination#auxiliareavoir', 6, 3, 0),
(5, '2023-10-08', '2023-10-13', '#nombres', 6, 1, 0),
(6, '2023-10-08', '2023-10-13', '#intégration#rôleétat#environnement', 6, 2, 0),
(7, '2023-10-22', '2023-10-27', '#date#nombres', 1, 1, 0),
(8, '2023-10-22', '2023-10-24', '#conjugaison#participepassé#présent', 1, 3, 0),
(9, '2023-10-15', '2023-10-20', '#windows#linux', 7, 5, 0),
(10, '2023-10-29', '2023-11-03', '#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api', 7, 5, 0),
(11, '2023-10-22', '2023-10-31', '#boucles#conditions', 9, 5, 0),
(12, '2023-11-12', '2023-12-26', '#json#api#sql', 8, 5, 1),
(13, '2023-11-19', '2023-11-22', '#dhcp#dns#voip', 5, 5, 0),
(14, '2023-10-17', '2023-12-20', '#sql#python', 5, 5, 0),
(15, '2023-10-29', '2023-10-31', '#probabilités#statistiques', 7, 4, 0),
(16, '2023-11-08', '2023-11-16', '#contrats#régulation#organisation', 1, 2, 0),
(17, '2023-12-16', '2023-12-22', '#verbesirréguliers#gérondif#date', 1, 1, 0),
(18, '2023-12-22', '2023-12-23', '#nombres', 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `code` int NOT NULL,
  `sous_matiere` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id`, `designation`, `code`, `sous_matiere`) VALUES
(1, 'Anglais', 1, '#verbesirréguliers#gérondif#présent#date#nombres'),
(2, 'CEJM', 2, '#contrats#régulation#organisation#intégration#rôleétat#environnement#facteurséconomiques#structurejuridique#droit'),
(3, 'Français', 3, '#orthographe#conjugaison#participepassé#présent#futursimple,#pronompersonnel,#conjonctioncoordination#auxiliareavoir#indicatif'),
(4, 'Mathématiques', 2, '#équations#factorisation#nombresrelatifs#intégrale#dérivée#tableaudevariation#matrice#développement#loidepoisson#probabilités#statistiques'),
(5, 'Informatique', 5, '#java#sql#python#php#javascript#modelosi#tcpip#windows#linux#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api'),
(6, 'Histoire', 6, '#crise1929#régimestotalitaires#secondeguerremondiale#tiersmonde#france#constructioneuropéenne#puissanceetenjeuxmondiaux');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_salle` varchar(10) NOT NULL,
  `etage` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `code_salle`, `etage`) VALUES
(101, 'Salle 101', 1),
(102, 'Salle 102', 1),
(201, 'Salle 201', 2),
(202, 'Salle 202', 2),
(301, 'Salle 301', 3),
(302, 'Salle 302', 3),
(303, 'Salle 303', 3),
(304, 'Salle 304', 3);

-- --------------------------------------------------------

--
-- Structure de la table `soutien`
--

DROP TABLE IF EXISTS `soutien`;
CREATE TABLE IF NOT EXISTS `soutien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_demande` int NOT NULL,
  `id_competence` int NOT NULL,
  `id_salle` int DEFAULT NULL,
  `date_du_soutien` date NOT NULL,
  `date_updated` date NOT NULL,
  `description` longtext NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_competence` (`id_competence`),
  KEY `id_demande` (`id_demande`),
  KEY `id_salle_soutien` (`id_salle`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `soutien`
--

INSERT INTO `soutien` (`id`, `id_demande`, `id_competence`, `id_salle`, `date_du_soutien`, `date_updated`, `description`, `status`) VALUES
(1, 1, 15, NULL, '2023-12-17', '2023-12-16', 'adad', 2),
(2, 12, 21, NULL, '2023-12-17', '2023-12-16', 'adadadaz', 2),
(3, 2, 15, NULL, '2023-12-17', '2023-12-16', 'adadadaz', 2),
(4, 14, 21, NULL, '2023-12-17', '2023-12-16', 'azdsxad', 1),
(5, 12, 21, NULL, '2023-12-17', '2023-12-16', 'adsxcc', 1);

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`id`, `libelle`) VALUES
(0, 'Expirée'),
(1, 'En cours'),
(2, 'Terminée');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `niveau` varchar(50) NOT NULL,
  `sexe` int NOT NULL,
  `telephone` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `role`, `niveau`, `sexe`, `telephone`) VALUES
(1, 'Castaing', 'Sabine', 'sabine.castaing@lerebours.org', 'sabcas', 'Etudiant', '5', 2, '0661010101'),
(2, 'Thouri', 'Erwan', 'erwan.thouri@lerebours.org', 'erwtho', 'Etdudiant', '2', 1, '0661020202'),
(3, 'Bloch', 'Nicolas', 'nicolas.bloch@lerebours.org', 'nicblo', 'Etudiant', '3', 1, '0661030303'),
(4, 'Chittarath', 'Christophe', 'chittarath.christophe@lerebours.org', 'chrchi', 'Etdudiant', '3', 1, '0661040404'),
(5, 'Buffeteau', 'Jacques', 'jacques.buffeteau@lerebours.org', 'jacbuf', 'Etudiant', '0', 1, '0661050505'),
(6, 'Pamart', 'Marie-Sophie', 'pamart.marie.sophie@lerebours.org', 'pammar', 'Etudiant', '4', 2, '0661060606'),
(7, 'Sordet', 'Evelyne', 'evelyne.sordet@lerebours.org', 'evesor', 'Etudiant', '5', 2, '0661070707'),
(8, 'Cornia', 'Alberto', 'alberto.cornia@lerebours.org', 'albcor', 'Etudiant', '3', 1, '0661080808'),
(9, 'Ioualitene', 'Fatah', 'loualitene.fatah@lerebours.org', 'fatlou', 'Etdudiant', '2', 1, '0661090909');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `competence`
--
ALTER TABLE `competence`
  ADD CONSTRAINT `id_matiere_competence` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `id_user_competence` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `demande_ibfk_1` FOREIGN KEY (`status`) REFERENCES `status` (`id`),
  ADD CONSTRAINT `id_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `soutien`
--
ALTER TABLE `soutien`
  ADD CONSTRAINT `id_competence` FOREIGN KEY (`id_competence`) REFERENCES `competence` (`id`),
  ADD CONSTRAINT `id_demande` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id`),
  ADD CONSTRAINT `id_salle_soutien` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
