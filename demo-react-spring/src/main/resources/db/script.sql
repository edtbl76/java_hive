create table EMPLOYEE
(
    ID          BIGINT not null,
    FIRST_NAME  VARCHAR(255),
    LAST_NAME   VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    VERSION     BIGINT,
    constraint PK_EMPLOYEE
        primary key (ID)
);

create table MANAGER
(
    ID       BIGINT not null,
    NAME     VARCHAR(255),
    PASSWORD VARCHAR(255),
    constraint PK_MANAGER
        primary key (ID)
);


