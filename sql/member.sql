SELECt * FROM member;

-- 기본 member 테이블
CREATE TABLE member(
    id NUMBER(5),
    name VARCHAR2(10),
    PRIMARY KEY (id)
);

-- identity
CREATE TABLE member(
    id NUMBER(5) GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR2(10),
    PRIMARY KEY (id)
);

COMMIT;