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
CREATE DATABASE IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `Cinema`;

-- 테이블 Cinema.movie 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie` (
  `Movie_Num` varchar(50) NOT NULL DEFAULT '',
  `Movie_Title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Movie_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 Cinema.movie:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` (`Movie_Num`, `Movie_Title`) VALUES
	('20198482', '드림'),
	('20226270', '스즈메의 문단속'),
	('20226411', '범죄도시3'),
	('20227890', '슈퍼 마리오 브라더스'),
	('20228555', '더 퍼스트 슬램덩크'),
	('20231029', '슬픔의 삼각형'),
	('20231089', '존 윅 4'),
	('20231348', '극장판 짱구는 못말려: 동물소환 닌자 배꼽수비대'),
	('20231496', '가디언즈 오브 갤럭시: Volume 3'),
	('20231592', '분노의 질주: 라이드 오어 다이');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;

-- 테이블 Cinema.movie_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_detail` (
  `Movie_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Movie_Title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Genre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Director` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Actor` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Running_Time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Description` varchar(15000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  KEY `Detail_MovieNum` (`Movie_Num`),
  CONSTRAINT `Detail_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.movie_detail:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `movie_detail` DISABLE KEYS */;
INSERT INTO `movie_detail` (`Movie_Num`, `Movie_Title`, `Genre`, `Director`, `Actor`, `Running_Time`, `Description`) VALUES
	('20198482', '드림', '코미디,드라마,', '이병헌', '박서준,이지은,김종수,고창석,정승길,이현우,양현민,홍완표,허준석,이하늬,박재철,', '125', ''),
	('20226270', '스즈메의 문단속', '애니메이션,', '신카이 마코토', '', '121', ''),
	('20226411', '범죄도시3', '범죄,액션,', '이상용', '마동석,이준혁,아오키 무네타카,이범수,김민재,이지훈,전석호,고규필,', '105', ''),
	('20227890', '슈퍼 마리오 브라더스', '애니메이션,어드벤처,코미디,', 'empty', '크리스 프랫,안야 테일러 조이,잭 블랙,찰리 데이,', '92', ''),
	('20228555', '더 퍼스트 슬램덩크', '애니메이션,', '이노우에 다케히코', '', '124', ''),
	('20231029', '슬픔의 삼각형', '코미디,드라마,', '루벤 외스틀룬드', '우디 해럴슨,해리스 딕킨슨,', '147', ''),
	('20231089', '존 윅 4', '액션,', '채드 스타헬스키', '키아누 리브스,로렌스 피쉬번,이안 맥쉐인,빌 스카스가드,견자단,', '169', ''),
	('20231348', '극장판 짱구는 못말려: 동물소환 닌자 배꼽수비대', '애니메이션,', '하시모토 마사카즈', '', '99', ''),
	('20231496', '가디언즈 오브 갤럭시: Volume 3', '액션,', '제임스 건', '크리스 프랫,조 샐다나,데이브 바티스타,카렌 길런,폼 클레멘티에프,빈 디젤,브래들리 쿠퍼,윌 폴터,', '149', ''),
	('20231592', '분노의 질주: 라이드 오어 다이', '액션,', '루이스 리테리어', '빈 디젤,제이슨 모모아,제이슨 스타뎀,샤를리즈 테론,브리 라슨,미셸 로드리게즈,성 강,', '140', '');
/*!40000 ALTER TABLE `movie_detail` ENABLE KEYS */;

-- 테이블 Cinema.movie_schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_schedule` (
  `Schedule` date DEFAULT NULL,
  `Movie_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Theater_Num` int(11) DEFAULT NULL,
  KEY `schedule_TheaterNum` (`Theater_Num`),
  KEY `schedule_MovieNum` (`Movie_Num`),
  CONSTRAINT `schedule_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `schedule_TheaterNum` FOREIGN KEY (`Theater_Num`) REFERENCES `theater` (`Theater_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `Movie_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `Schedule` date DEFAULT NULL,
  `Theater_Num` int(11) DEFAULT NULL,
  `Seat_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  PRIMARY KEY (`Reservation_Num`),
  KEY `Detail_Movie_Num` (`Movie_Num`),
  KEY `Reservation_Seat` (`Seat_Num`),
  CONSTRAINT `Detail_Reservation` FOREIGN KEY (`Reservation_Num`) REFERENCES `reservations` (`Reservation_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Reservation_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Reservation_Seat` FOREIGN KEY (`Seat_Num`) REFERENCES `seat` (`Seat_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.reservations_detail:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reservations_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations_detail` ENABLE KEYS */;

-- 테이블 Cinema.seat 구조 내보내기
CREATE TABLE IF NOT EXISTS `seat` (
  `Seat_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Theater_Num` int(11) DEFAULT NULL,
  `Assignment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Seat_Num`) USING BTREE,
  KEY `seat_TheaterNum` (`Theater_Num`),
  CONSTRAINT `seat_TheaterNum` FOREIGN KEY (`Theater_Num`) REFERENCES `theater` (`Theater_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.seat:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;

-- 테이블 Cinema.theater 구조 내보내기
CREATE TABLE IF NOT EXISTS `theater` (
  `Theater_Num` int(11) NOT NULL,
  `Total_Seat` int(11) DEFAULT NULL,
  PRIMARY KEY (`Theater_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 Cinema.theater:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;

-- 테이블 Cinema.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `UID` int(15) NOT NULL DEFAULT 0,
  `User_ID` varchar(50) NOT NULL,
  `User_PW` varchar(50) NOT NULL,
  `User_Name` varchar(50) NOT NULL,
  `User_CallNum` varchar(50) NOT NULL,
  `User_Pay` int(11) DEFAULT NULL,
  `User_Watch_list` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci COMMENT='고객정보';

-- 테이블 데이터 Cinema.user:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`UID`, `User_ID`, `User_PW`, `User_Name`, `User_CallNum`, `User_Pay`, `User_Watch_list`) VALUES
	(0, 'admin', '1234', 'admin', '00000000000', 0, NULL),
	(1, 'test1', '1234', 'testman', '01015812348', 0, NULL),
	(2, 'test2', '1111', '테스트', '12342365', 0, NULL),
	(3, 'test4', '123', '자바발표중', '1231231234', 0, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
