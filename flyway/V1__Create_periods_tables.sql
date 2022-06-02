CREATE TABLE one_minute_period (
  time_stamp timestamp,
  currency CHAR(3),
  cost NUMERIC(20, 4),
  PRIMARY KEY (time_stamp, currency)
);

CREATE TABLE five_minutes_period (
  time_stamp timestamp,
  currency CHAR(3),
  cost NUMERIC(20, 4),
  PRIMARY KEY (time_stamp, currency)
);

CREATE TABLE ten_minutes_period (
  time_stamp timestamp,
  currency CHAR(3),
  cost NUMERIC(20, 4),
  PRIMARY KEY (time_stamp, currency)
);

CREATE TABLE one_hour_period (
  time_stamp timestamp,
  currency CHAR(3),
  cost NUMERIC(20, 4),
  PRIMARY KEY (time_stamp, currency)
);

CREATE TABLE one_day_period (
  time_stamp timestamp,
  currency CHAR(3),
  cost NUMERIC(20, 4),
  PRIMARY KEY (time_stamp, currency)
);
