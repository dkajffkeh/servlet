/*
-- ����� ��û�� ���� ������ sql ��.
--*ȸ�����
--1. �α��� ��û�� ������ sql��

*/
SELECT *
FROM MEMBER
WHERE USER_ID='admin'
AND USER_PWD='1234'
AND STATUS='y';

--* �������� ����
--1. �������� ����Ʈ ��ȸ.
-- ����Ʈ ������ ��ȸ�� sql ��.

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
      
      
      
--3.�Խ��� �� ��ȸ�� ����� SQL��
--��ȸ�� 1 ������Ű�� �� ���뵵 SELECT �ؿ;���.

UPDATE
      NOTICE
  SET COUNT = COUNT + 1
WHERE NOTICE_NO = ?   
AND   STATUS ='Y' ;

-- 3-2 �ش� �������� ��ȸ�ϴ� SQL��

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
  
--�Խñ� ����ȸ ��û�� ������ sql�� 3_1. �ش� �Խñ� ��ȸ�� ������Ű�� SQL��
UPDATE 
      BOARD
   SET
      COUNT = COUNT + 1
WHERE BOARD_NO = 3
AND   STATUS = 'Y' ;

--3-2 �ش� �Խñ� ���� ��ȸ�� SQL��

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

--3-3 �ش� �Խñۿ� �����ִ� ÷�����Ͽ� ���� ���� ��ȸ�� SQL
SELECT 
      FILE_NO
     ,ORIGIN_NAME
     ,CHANGE_NAME
     ,FILE_PATH
FROM ATTACHMENT  
WHERE REF_BNO = ?;

--�Ϲ� �Խ��� �����ϱ� ��û�� ������ sql��
UPDATE
      BOARD
   SET
      CATEGORY_NO=?
     ,BOARD_TITLE=?
     ,BOARD_CONTENT=?
WHERE BOARD_NO = ?
AND STATUS ='Y';

--���ο� ÷�������� �������.
--> ������ ÷�������� �־��� ���(Attach �� ����� ���� => update)
UPDATE
      ATTACHMENT
   SET 
      ORIGIN_NAME=?
     ,CHANGE_NAME=?
     ,FILE_PATH=?
WHERE FILE_NO=? ;     
      


--> ÷������ �� �������. (Attach ����� ���� => insert)
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
--���� �Խ��� �ۼ��ϱ� ��û�� ������ SQL��
--1. �����Խ��� �ۼ��ϱ� ��û�� ������ SQL��
--1_1 BOARD TABLE ���� 1�ٸ� INSERT��.
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
--÷������ ATTACHMENT
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
 
--2��° �����Խ��� ����Ʈ ��ȸ�� SQL��
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


       


