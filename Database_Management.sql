-- --------------------------------------------------------
-- 호스트:                          localhost
-- 서버 버전:                        10.10.3-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Cinema 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `Cinema`;

-- 테이블 Cinema.movie 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie` (
  `Movie_Num` int(11) NOT NULL,
  `Movie_Title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Movie_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.movie:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;

-- 테이블 Cinema.movie_schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_schedule` (
  `Schedule` int(11) DEFAULT NULL,
  `Movie_Num` int(11) DEFAULT NULL,
  `Theater_Num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.movie_schedule:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `movie_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_schedule` ENABLE KEYS */;

-- 테이블 Cinema.reservations 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservations` (
  `Reservation_Num` int(15) NOT NULL DEFAULT 0,
  `UID` int(15) NOT NULL DEFAULT 0,
  `Payment_Num` int(11) DEFAULT NULL,
  `Payment_Method` varchar(50) DEFAULT NULL,
  `Total_Price` int(15) NOT NULL,
  PRIMARY KEY (`Reservation_Num`),
  KEY `Reservation_UID` (`UID`),
  CONSTRAINT `Reservation_UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.reservations:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;

-- 테이블 Cinema.reservations_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservations_detail` (
  `Reservation_Num` int(15) NOT NULL,
  `Movie_Num` int(15) NOT NULL,
  PRIMARY KEY (`Reservation_Num`),
  KEY `Detail_Movie_Num` (`Movie_Num`),
  CONSTRAINT `Detail_Movie_Num` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Detail_Reservation` FOREIGN KEY (`Reservation_Num`) REFERENCES `reservations` (`Reservation_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.reservations_detail:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reservations_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations_detail` ENABLE KEYS */;

-- 테이블 Cinema.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `UID` int(15) NOT NULL DEFAULT 0,
  `User_ID` varchar(50) NOT NULL,
  `User_PW` varchar(50) NOT NULL,
  `User_Name` varchar(50) NOT NULL,
  `User_CallNum` varchar(50) NOT NULL,
  `User_Pay` varchar(50) NOT NULL,
  `User_Watch_list` varchar(50) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='고객정보';

-- 테이블 데이터 Cinema.user:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
