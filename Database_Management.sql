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
CREATE DATABASE IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `cinema`;

-- 테이블 cinema.movie 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie` (
  `Movie_Num` varchar(50) NOT NULL DEFAULT '',
  `Movie_Title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Movie_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 cinema.movie:~10 rows (대략적) 내보내기
DELETE FROM `movie`;
INSERT INTO `movie` (`Movie_Num`, `Movie_Title`) VALUES
	('19960041', '아기공룡 둘리-얼음별 대모험'),
	('20226270', '스즈메의 문단속'),
	('20226411', '범죄도시3'),
	('20227890', '슈퍼 마리오 브라더스'),
	('20231029', '슬픔의 삼각형'),
	('20231348', '극장판 짱구는 못말려: 동물소환 닌자 배꼽수비대'),
	('20231496', '가디언즈 오브 갤럭시: Volume 3'),
	('20231592', '분노의 질주: 라이드 오어 다이'),
	('20231677', '남은 인생 10년'),
	('20231839', '인어공주');

-- 테이블 cinema.movie_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_detail` (
  `Movie_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Movie_Title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Genre` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Director` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Actor` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Running_Time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Description` varchar(15000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Poster_URL` varchar(1000) DEFAULT NULL,
  KEY `Detail_MovieNum` (`Movie_Num`),
  CONSTRAINT `Detail_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.movie_detail:~10 rows (대략적) 내보내기
DELETE FROM `movie_detail`;
INSERT INTO `movie_detail` (`Movie_Num`, `Movie_Title`, `Genre`, `Director`, `Actor`, `Running_Time`, `Description`, `Poster_URL`) VALUES
	('19960041', '아기공룡 둘리-얼음별 대모험', '애니메이션,어드벤처,코미디,가족,', '김수정', '', '84', '남극의 거대한 빙산 조각에 억만년 전에 실종된 아기공룡 둘리가 갇혀 있다가 어느날 한강으로 흘러 들어오게 된다. 가사 상태에서 깨어난 둘리는 소시민 고길동씨 댁에 머물게 되는데, 호기심 많고 말썽꾸러기인 둘리로 인해 고길동씨 집은 하루도 맘 편할 날이 없다. 거기에 희동이, 외계인 도우너, 또치, 마이콜등이 가세하고 이들은 타임 코스모스를 타고 빨리 어른이 되기 위해 미래 여행을 떠난다. 그러나 타임 코스모스의 작동실수로 이들은 우주의 미로 속에 던져지게 된다.', 'http://file.koreafilm.or.kr/thm/02/99/18/04/tn_DPK020534.jpg'),
	('20226270', '스즈메의 문단속', '애니메이션,', '신카이 마코토', '', '121', '큐슈의 작은 마을에서 살고 있는 소녀 ‘스즈메’는어느 날, 여행을 하며 ‘문’을 찾고 있는 한 청년을 만난다.그의 뒤를 쫓아간 소녀는 산속 폐허에서 덩그러니 남겨진 낡은 문을 발견한다.무언가에 이끌린 듯 ‘스즈메’는 문으로 손을 뻗는데…2023년, ‘문단속을 위한 여행’이 시작된다!', 'http://file.koreafilm.or.kr/thm/02/99/17/94/tn_DPF026742.jpg'),
	('20226411', '범죄도시3', '범죄,액션,', '이상용', '마동석,이준혁,아오키 무네타카,이범수,김민재,이지훈,전석호,고규필,', '105', '대체불가 괴물형사 마석도, 서울 광수대로 발탁!베트남 납치 살해범 검거 후 7년 뒤, ‘마석도’(마동석)는 새로운 팀원들과 함께 살인사건을 조사한다.사건 조사 중, ‘마석도’는 신종 마약 사건이 연루되었음을 알게 되고 수사를 확대한다.한편, 마약 사건의 배후인 \'주성철\'(이준혁)은 계속해서 판을 키워가고 약을 유통하던 일본 조직과 \'리키\'(아오키 무네타카)까지 한국에 들어오며 사건의 규모는 점점 더 커져가는데...나쁜 놈들 잡는 데 이유 없고 제한 없다커진 판도 시원하게 싹 쓸어버린다!', 'http://file.koreafilm.or.kr/thm/02/99/18/05/tn_DPK020541.jpg'),
	('20227890', '슈퍼 마리오 브라더스', '애니메이션,어드벤처,코미디,', 'empty', '크리스 프랫,안야 테일러 조이,잭 블랙,찰리 데이,', '92', '따단-딴-따단-딴 ♫전 세계를 열광시킬 올 타임 슈퍼 어드벤처의 등장!뉴욕의 평범한 배관공 형제 \'마리오\'와 ‘루이지’는 배수관 고장으로 위기에 빠진 도시를 구하려다 미스터리한 초록색 파이프 안으로 빨려 들어가게 된다.파이프를 통해 새로운 세상으로 차원 이동하게 된 형제.형 \'마리오\'는 뛰어난 리더십을 지닌 \'피치\'가 통치하는 버섯왕국에 도착하지만 동생 \'루이지\'는 빌런 \'쿠파\'가 있는 다크랜드로 떨어지며 납치를 당하고 ‘마리오’는 동생을 구하기 위해 ‘피치’와 ‘키노피오’의 도움을 받아 \'쿠파\'에 맞서기로 결심한다.그러나 슈퍼스타로 세상을 지배하려는 그의 강력한 힘 앞에 이들은 예기치 못한 위험에 빠지게 되는데...!동생을 구하기 위해! 세상을 지키기 위해!\'슈퍼 마리오\'로 레벨업하기 위한 \'마리오\'의 스펙터클한 스테이지가 시작된다!', 'http://file.koreafilm.or.kr/thm/02/99/17/99/tn_DPF027009.jpg'),
	('20231029', '슬픔의 삼각형', '코미디,드라마,', '루벤 외스틀룬드', '우디 해럴슨,해리스 딕킨슨,', '147', '호화 크루즈에 #협찬 으로 승선한 인플루언서 모델 커플.각양각색의 부자들과 휴가를 즐기던 사이, 뜻밖의 사건으로 배가 전복되고 8명만이 간신히 무인도에 도착한다.할 줄 아는 거라곤 구조 대기뿐인 사람들… 이때 존재감을 드러내는 건,“여기선 내가 캡틴입니다. 자, 내가 누구라고요?”', 'http://file.koreafilm.or.kr/thm/02/99/18/05/tn_DPF027332.jpg'),
	('20231348', '극장판 짱구는 못말려: 동물소환 닌자 배꼽수비대', '애니메이션,', '하시모토 마사카즈', '', '99', '사실... 짱구는 닌자 가문의 후계자였다?!어느 날 ‘짱구‘와 동갑내기인 5살 ‘진구’를 데리고, 짱구 가족을 찾아온 수상한 여성.서로의 아이가 바뀌었다는 충격적인 소식을 전한다.하루아침에 닌자 가문의 후계자 ‘진구’로 불리게 된 짱구는 ‘부리부리 엉덩이 분신술’로 닌자 유치원을 초토화시킨다.한편, 떡잎마을에 남겨진 진구와 짱구 가족은 짱구를 찾으러 닌자 마을로 향하고, 세상의 중심인 ‘지구의 배꼽’이 흔들리기 시작하며, 지구가 붕괴될 위기에 처하는데…과연 짱구는 세상을 지켜낼 수 있을까?!', 'http://file.koreafilm.or.kr/thm/02/99/18/01/tn_DPF027154.jpg'),
	('20231496', '가디언즈 오브 갤럭시: Volume 3', '액션,', '제임스 건', '크리스 프랫,조 샐다나,데이브 바티스타,카렌 길런,폼 클레멘티에프,빈 디젤,브래들리 쿠퍼,윌 폴터,', '149', '‘가모라’를 잃고 슬픔에 빠져 있던 ‘피터 퀼’이 위기에 처한 은하계와 동료를 지키기 위해 다시 한번 가디언즈 팀과 힘을 모으고, 성공하지 못할 경우 그들의 마지막이 될지도 모르는 미션에 나서는 이야기', 'http://file.koreafilm.or.kr/thm/02/99/17/97/tn_DPF026925.jpg'),
	('20231592', '분노의 질주: 라이드 오어 다이', '액션,', '루이스 리테리어', '빈 디젤,제이슨 모모아,제이슨 스타뎀,샤를리즈 테론,브리 라슨,미셸 로드리게즈,성 강,', '140', '아무리 빨리 달려도 과거를 앞지를 순 없다돔(빈 디젤)과 그의 패밀리 앞에 나타난 운명의 적 단테(제이슨 모모아). 과거의 그림자는 돔의 모든 것을 파괴하기 위해 달려온다. 단테에 의해 산산히 흩어진 패밀리들은 모두 목숨을 걸고 맞서야 하는 함정에 빠지고 마는데..달리거나 죽거나, 그들의 마지막 질주가 시작된다.', 'http://file.koreafilm.or.kr/thm/02/99/18/02/tn_DPF027183.jpg'),
	('20231677', '남은 인생 10년', '멜로/로맨스,', '후지이 미치히토', '고마츠 나나,사카구치 켄타로,', '124', '스무 살이 되던 해, 수 만명 중 1명이 걸리는 난치병으로 10년의 삶을 선고받은 ‘마츠리’는 삶의 의지를 잃은 ‘카즈토’를 만나 사랑에 빠진다. 처음 만난 봄, 즐거운 여름, 아름답던 가을, 깊어진 겨울까지 하루하루 애틋하게 사랑한 두 사람 하지만 쌓이는 추억만큼 줄어드는 시간 앞에 결국 ‘마츠리’는 ‘카즈토’를 떠나기로 결심하는데…', 'http://file.koreafilm.or.kr/thm/02/99/18/04/tn_DPF027265.jpg'),
	('20231839', '인어공주', '뮤지컬,가족,판타지,멜로/로맨스,', '롭 마샬', '멜리사 맥카시,하비에르 바르뎀,아콰피나,', '135', '“내 안의 목소리를 따라자유롭게 꿈꾸고 사랑할 거야”아틀란티카 바다의 왕 ‘트라이튼’의 사랑스러운 막내딸인 인어 ‘에리얼’은 늘 인간들이 사는 바다 너머 세상으로의 모험을 꿈꾼다.어느 날, 우연히 바다 위로 올라갔다가 폭풍우 속 가라앉는 배에 탄 인간 ‘에릭 왕자’의 목숨을 구해준다.갈망하던 꿈과 운명적인 사랑을 이루기 위해 용기를 낸 ‘에리얼’은 사악한 바다 마녀 ‘울슐라’와의 위험한 거래를 통해 다리를 얻게 된다.드디어 바다를 벗어나 그토록 원하던 인간 세상으로 가게 되지만, 그 선택으로 ‘에리얼’과 아틀란티카 왕국 모두 위험에 처하게 되는데…바닷속, 그리고 그 너머아름다운 꿈과 사랑의 멜로디가 펼쳐진다!', 'http://file.koreafilm.or.kr/thm/02/99/18/05/tn_DPF027383.jpg');

-- 테이블 cinema.movie_schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `movie_schedule` (
  `Schedule` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Movie_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Theater_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  KEY `schedule_TheaterNum` (`Theater_Num`),
  KEY `schedule_MovieNum` (`Movie_Num`),
  CONSTRAINT `schedule_MovieNum` FOREIGN KEY (`Movie_Num`) REFERENCES `movie` (`Movie_Num`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `schedult_TheaterNum` FOREIGN KEY (`Theater_Num`) REFERENCES `theater` (`Theater_Num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.movie_schedule:~2 rows (대략적) 내보내기
DELETE FROM `movie_schedule`;
INSERT INTO `movie_schedule` (`Schedule`, `Movie_Num`, `Theater_Num`) VALUES
	('09:00,14:00', '19960041', '1관'),
	('09:00,14:00', '20226411', '2관');

-- 테이블 cinema.reservations 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservations` (
  `Reservation_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `UID` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Movie_Title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Schedule` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Theater_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Seat_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Payment_Method` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  KEY `Reservation_UID` (`UID`),
  CONSTRAINT `Reservation_UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.reservations:~9 rows (대략적) 내보내기
DELETE FROM `reservations`;
INSERT INTO `reservations` (`Reservation_Num`, `UID`, `Movie_Title`, `Schedule`, `Theater_Num`, `Seat_Num`, `Price`, `Payment_Method`) VALUES
	('230529601', '6', '범죄도시3', '09:00', '1관', 'S-44', 20000, 'cash'),
	('230529601', '6', '범죄도시3', '09:00', '1관', 'S-45', 20000, 'cash'),
	('230529602', '6', '범죄도시3', '09:00', '1관', 'S-44', 40000, 'Card'),
	('230529602', '6', '범죄도시3', '09:00', '1관', 'S-45', 40000, 'Card'),
	('230529602', '6', '범죄도시3', '09:00', '1관', 'S-52', 40000, 'Card'),
	('230529602', '6', '범죄도시3', '09:00', '1관', 'S-53', 40000, 'Card'),
	('230529603', '6', '범죄도시3', '09:00', '1관', 'S-20', 20000, 'cash'),
	('230529603', '6', '범죄도시3', '09:00', '1관', 'S-21', 20000, 'cash'),
	('230529604', '6', '범죄도시3', '09:00', '2관', 'S-37', 10000, 'cash');

-- 테이블 cinema.theater 구조 내보내기
CREATE TABLE IF NOT EXISTS `theater` (
  `Theater_Num` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Theater_Size` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Theater_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- 테이블 데이터 cinema.theater:~8 rows (대략적) 내보내기
DELETE FROM `theater`;
INSERT INTO `theater` (`Theater_Num`, `Theater_Size`) VALUES
	('1관', 'big'),
	('2관', 'big'),
	('3관', 'big'),
	('4관', 'small'),
	('5관', 'small'),
	('6관', 'small'),
	('7관', 'small'),
	('8관', 'small');

-- 테이블 cinema.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `UID` varchar(50) NOT NULL,
  `User_ID` varchar(50) NOT NULL,
  `User_PW` varchar(50) NOT NULL,
  `User_Name` varchar(50) NOT NULL,
  `User_CallNum` varchar(50) NOT NULL,
  `User_Pay` int(11) DEFAULT 0,
  `User_Watch_list` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 cinema.user:~9 rows (대략적) 내보내기
DELETE FROM `user`;
INSERT INTO `user` (`UID`, `User_ID`, `User_PW`, `User_Name`, `User_CallNum`, `User_Pay`, `User_Watch_list`) VALUES
	('0', 'admin', '1234', 'admin', '00000000000', 0, NULL),
	('1', 'test1', '1234', 'testman', '01015812348', 0, NULL),
	('2', 'test2', '1111', '테스트', '12342365', 0, NULL),
	('3', 'test4', '123', '자바발표중', '1231231234', 0, NULL),
	('4', 'dkdkd', '11', '테섯터', '0002020202', 0, NULL),
	('5', 't', '1', 'y', '11', 0, NULL),
	('6', '1', '123', '1', '1', 0, NULL),
	('7', '11', '112', '11', '122', 0, NULL),
	('8', '555', '5555', 'test555', '555', 0, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
