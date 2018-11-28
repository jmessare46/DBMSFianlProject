CREATE TABLE terevent(
  id INTEGER,
  year INTEGER,
  month VARCHAR(10),
  day INTEGER,
  country VARCHAR(20),
  city VARCHAR(25),
  method VARCHAR(255),
  target VARCHAR(255)
);

CREATE TABLE countryfinance(
  country VARCHAR(255),
  subject VARCHAR(255),
  subjectdes VARCHAR(255),
  year INTEGER,
  unit VARCHAR(10),
  description VARCHAR(255),
  scale VARCHAR(255)
);