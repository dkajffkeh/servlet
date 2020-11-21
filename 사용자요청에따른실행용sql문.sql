/*
-- 사용자 요청에 따른 실행할 sql 문.
--*회원사비스
--1. 로그인 요청시 실행할 sql문

*/
SELECT *
FROM MEMBER
WHERE USER_ID='admin'
AND USER_PWD='1234'
AND STATUS='y';

--* 공지사항 서비스
--1. 공지사항 리스트 조회.
-- 리스트 페이지 조회할 sql 문.

SELECT 
       NOTICE_NO
      ,NOTICE_TITLE
      ,USER_ID
      ,COUNT
      ,CREATE_DATE

FROM   NOTICE
JOIN   MEMBER ON(NOTICE_WRITER=USER_NO)
WHERE STATUS='Y'
ORDER BY DESC;
      
      
      
--3.게시판 상세 조회시 실행될 SQL문
--조회수 1 증가시키고 그 내용도 SELECT 해와야함.

UPDATE
      NOTICE
  SET COUNT = COUNT + 1
WHERE NOTICE_NO = ?   
AND   STATUS ='Y' ;

-- 3-2 해당 공지사항 조회하는 SQL문

SELECT 
       NOTICE_NO
     , NOTICE_TITLE
     , NOTICE_CONTENT
     , USER_ID
     , CREATE_DATE
     , COUNT
  FROM NOTICE N
  JOIN MEMBER ON (NOTICE_WRITER=USER_NO)
  WHERE NOTICE_NO = ?
    AND N.STATUS='Y';
      
UPDATE 
      NOTICE
   SET
      NOTICE_TITLE = ?
    , NOTICE_CONENT = ?
WHERE NOTICE_NO = ?
AND   STATUS = 'Y' ;
      
UPDATE 
      NOTICE
   SET
      STATUS = 'N'
WHERE NOTICE_NO=? ;      

--
INSERT
      INTO
          BOARD
          (
           BOARD_NO
          ,BOARD_TYPE
          ,CATEGORY_NO
          ,BOARD_TITLE
          ,BOARD_CONTENT
          ,BOARD_WRITER
          ,CREATE_DATE
          )
          VALUES
          (
          SEQ_BNO.NEXTVAL
         ,1
         ,?
         ,?
         ,?
         ,?
         ,SYSDATE
          ) ;
--

INSERT
      INTO ATTACHMENT
      (
       FILE_NO
      ,REF_BNO
      ,ORIGIN_NAME
      ,CHANGE_NAME
      ,FILE_PATH
      )
      VALUES
      (
      SEQ_FNO.NEXTVAL
     ,SEQ_BNO.CURRVAL
     ,?
     ,?
     ,?
      )  ;
  
--게시글 상세조회 요청시 실행할 sql문 3_1. 해당 게시글 조회수 증가시키는 SQL문
UPDATE 
      BOARD
   SET
      COUNT = COUNT + 1
WHERE BOARD_NO = 3
AND   STATUS = 'Y' ;

--3-2 해당 게시글 정보 조회용 SQL문

SELECT
      BOARD_NO
     ,CATEGORY_NAME
     ,BOARD_TITLE
     ,BOARD_CONTENT
     ,USER_ID
     ,CREATE_DATE
FROM BOARD B
LEFT JOIN CATEGORY C USING(CATEGORY_NO)
JOIN MEMBER M ON(BOARD_WRITER=USER_NO)
WHERE BOARD_NO = 119
AND   B.STATUS='Y'
AND   BOARD_TYPE=2;

--3-3 해당 게시글에 딸려있는 첨부파일에 대한 정보 조회용 SQL
SELECT 
      FILE_NO
     ,ORIGIN_NAME
     ,CHANGE_NAME
     ,FILE_PATH
FROM ATTACHMENT  
WHERE REF_BNO = ?;

--일반 게시판 수정하기 요청시 실행할 sql문
UPDATE
      BOARD
   SET
      CATEGORY_NO=?
     ,BOARD_TITLE=?
     ,BOARD_CONTENT=?
WHERE BOARD_NO = ?
AND STATUS ='Y';

--새로운 첨부파일이 있을경우.
--> 기존에 첨부파일이 있었을 경우(Attach 에 기록이 있음 => update)
UPDATE
      ATTACHMENT
   SET 
      ORIGIN_NAME=?
     ,CHANGE_NAME=?
     ,FILE_PATH=?
WHERE FILE_NO=? ;     
      


--> 첨부파일 이 없을경우. (Attach 기록이 없음 => insert)
INSERT INTO
           ATTACHMENT
           (
           FILE_NO
          ,REF_BNO
          ,ORIGIN_NAME
          ,CHANGE_NAME
          ,FILE_PATH
           )
     VALUES
           (
           SEQ_FNO.NEXTVAL
          ,?
          ,?
          ,?
          ,?
           ) ;
--사진 게시판 작성하기 요청시 실행할 SQL문
--1. 사진게시판 작성하기 요청시 실행할 SQL문
--1_1 BOARD TABLE 에는 1줄만 INSERT됨.
INSERT
      INTO BOARD
      (
      BOARD_NO
     ,BOARD_TYPE
     ,BOARD_TITLE
     ,BOARD_CONTENT
     ,BOARD_WRITER
     ,CREATE_DATE
      )
      VALUES
      (
      SEQ_BNO.NEXTVAL
     ,2
     ,?
     ,?
     ,?
     ,SYSDATE
      )
;
--첨부파일 ATTACHMENT
INSERT
      INTO ATTACHMENT
      (
      FILE_NO
     ,REF_BNO
     ,ORIGIN_NAME
     ,CHANGE_NAME
     ,FILE_PATH
     ,FILE_LEVEL
      )
      VALUES
      (
      SEQ_FNO.NEXTVAL
     ,SEQ_BNO.CURRVAL
     ,?
     ,?
     ,?
     ,?  
      )
      ;
 
--2번째 사진게시판 리스트 조회용 SQL문
SELECT
      BOARD_NO
     ,BOARD_TITLE
     ,COUNT
     ,FILE_PATH || CHANGE_NAME "TITLEIMG"
FROM BOARD B
JOIN ATTACHMENT ON(BOARD_NO=REF_BNO)
WHERE BOARD_TYPE=2
AND B.STATUS = 'Y'
AND FILE_LEVEL =1
ORDER BY BOARD_NO DESC;


       


