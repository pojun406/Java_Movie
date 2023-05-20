-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.11.3-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- cinema 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `cinema`;

-- 테이블 cinema.movie 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie` (
  `Movie_Num` int(11) NOT NULL,
  `Movie_Title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Movie_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.movie:~0 rows (대략적) 내보내기

-- 테이블 cinema.movie_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_detail` (
  `Movie_Detail_ID` varchar(50) DEFAULT NULL,
  `Movie_Num` int(11) DEFAULT NULL,
  `Genre` varchar(50) DEFAULT NULL,
  `Director` varchar(50) DEFAULT NULL,
  `Cast` varchar(50) DEFAULT NULL,
  `Running_Time` varchar(50) DEFAULT NULL,
  `Description` varchar(10000) DEFAULT NULL,
  KEY `MovieDetail_MovieNum` (`Movie_Num`),
  CONSTRAINT `MovieDetail_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.movie_detail:~0 rows (대략적) 내보내기

-- 테이블 cinema.movie_schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_schedule` (
  `Schedule` date DEFAULT NULL,
  `Movie_Num` int(11) DEFAULT NULL,
  `Theater_Num` int(11) DEFAULT NULL,
  KEY `schedule_TheaterNum` (`Theater_Num`),
  KEY `schedule_MovieNum` (`Movie_Num`),
  CONSTRAINT `schedule_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `schedule_TheaterNum` FOREIGN KEY (`Theater_Num`) REFERENCES `theater` (`Theater_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.movie_schedule:~0 rows (대략적) 내보내기

-- 테이블 cinema.reservations 구조 내보내기
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

-- 테이블 데이터 cinema.reservations:~0 rows (대략적) 내보내기

-- 테이블 cinema.reservations_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservations_detail` (
  `Reservation_Num` int(15) NOT NULL,
  `Movie_Num` int(15) NOT NULL,
  `Schedule` date DEFAULT NULL,
  `Theater_Num` int(11) DEFAULT NULL,
  `Seat_Num` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  PRIMARY KEY (`Reservation_Num`),
  KEY `Detail_Movie_Num` (`Movie_Num`),
  CONSTRAINT `Detail_Movie_Num` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Detail_Reservation` FOREIGN KEY (`Reservation_Num`) REFERENCES `reservations` (`Reservation_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.reservations_detail:~0 rows (대략적) 내보내기

-- 테이블 cinema.seat 구조 내보내기
CREATE TABLE IF NOT EXISTS `seat` (
  `SeatNum` int(11) NOT NULL,
  `Theater_Num` int(11) DEFAULT NULL,
  `Assignment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SeatNum`),
  KEY `seat_TheaterNum` (`Theater_Num`),
  CONSTRAINT `seat_TheaterNum` FOREIGN KEY (`Theater_Num`) REFERENCES `theater` (`Theater_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.seat:~0 rows (대략적) 내보내기

-- 테이블 cinema.theater 구조 내보내기
CREATE TABLE IF NOT EXISTS `theater` (
  `Theater_Num` int(11) NOT NULL,
  `Total_Seat` int(11) DEFAULT NULL,
  PRIMARY KEY (`Theater_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.theater:~0 rows (대략적) 내보내기

-- 테이블 cinema.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `UID` int(15) NOT NULL DEFAULT 0,
  `User_ID` varchar(50) NOT NULL,
  `User_PW` varchar(50) NOT NULL,
  `User_Name` varchar(50) NOT NULL,
  `User_CallNum` varchar(50) NOT NULL,
  `User_Pay` int(11) NOT NULL DEFAULT 0,
  `User_Watch_list` varchar(50) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='고객정보';

-- 테이블 데이터 cinema.user:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
