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
  name         text,
  affiliation  varchar(255),
  organization varchar(255),
  nationality  varchar(255),
  location     varchar(255),
  target_id    double precision not null
    constraint target_pk
    primary key
    constraint target_terevent__fk
    references terevent
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
    primary key
    constraint attacker_terevent__fk
    references terevent,
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

create table countrygdp
(
  country     varchar(255),
  subjectdesc text,
  unit        varchar(255),
  scale       varchar(255),
  notes       text,
  year        integer,
  value       double precision,
  uid         serial not null
    constraint countrygdp_pk
    primary key
);

alter table countrygdp
  owner to josephmessare;

create unique index countrygdp_uid_uindex
  on countrygdp (uid);

create table countrypopulation
(
  country    varchar(255),
  subjectdes text,
  unit       varchar(255),
  scale      varchar(255),
  notes      text,
  year       integer,
  value      double precision,
  uid        serial not null
    constraint countrypopulation_pkey
    primary key
);

alter table countrypopulation
  owner to josephmessare;

create unique index countrypopulation_uid_uindex
  on countrypopulation (uid);

create table countryrevenue
(
  country    varchar(255),
  subjectdes text,
  unit       varchar(255),
  scale      varchar(255),
  notes      text,
  year       integer,
  value      double precision,
  uid        serial not null
    constraint countryrevenue_pkey
    primary key
);

alter table countryrevenue
  owner to josephmessare;

create unique index countryrevenue_uid_uindex
  on countryrevenue (uid);

