create table users(
    id integer not null,
    username text,
    fio text
);
create table logins(
    id integer not null,
    access_date text,
    user_id integer,
    application text
);