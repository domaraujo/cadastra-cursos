CREATE SEQUENCE MYSEQUENCE AS INTEGER START WITH 1;

INSERT INTO CATEGORIA VALUES (NEXTVAL('mySequence'),'Programação');
INSERT INTO CATEGORIA VALUES (NEXTVAL('mySequence'),'QA');
INSERT INTO CATEGORIA VALUES (NEXTVAL('mySequence'),'Metodologia Ágil');
