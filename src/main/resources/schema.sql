create table countryfinance
(
  country     varchar(255),
  subject     varchar(255),
  subjectdes  text,
  unit        varchar(50),
  description text,
  scale       varchar(255),
  year        integer,
  amount      double precision
);

alter table countryfinance
  owner to josephmessare;

create table terevent
(
  event_id double precision not null
    constraint terevent_pk
    primary key,
  country  varchar(255),
  city     varchar(255),
  method   varchar(255),
  month    integer,
  day      integer,
  year     integer
);

alter table terevent
  owner to josephmessare;

create unique index terevent_id_uindex
  on terevent (event_id);

create table target
(
  name         varchar(255),
  affiliation  varchar(255),
  organization varchar(255),
  nationality  varchar(255),
  location     varchar(255),
  target_id    double precision
);

alter table target
  owner to josephmessare;

create unique index target_target_id_uindex
  on target (target_id);

create table attacker
(
  name       varchar(255),
  id         double precision not null
    constraint attacker_pkey
    primary key,
  weapontype varchar(255),
  weapon     varchar(255),
  numwounded integer,
  damagecost integer,
  motive     text
);

alter table attacker
  owner to josephmessare;

create unique index attacker_id_uindex
  on attacker (id);

