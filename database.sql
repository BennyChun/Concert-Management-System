CREATE TABLE Customers(
	cust_id	CHAR(6) NOT NULL, 
	cust_name VARCHAR(30) NOT NULL,
	address VARCHAR(40) NULL, 
	birth CHAR(10) NULL, 
	phone CHAR(12) NULL, 
	email VARCHAR(20) NOT NULL UNIQUE,
	PRIMARY KEY (cust_id));

grant select on customers to public;

CREATE TABLE Venue(
	v_name VARCHAR(30) NOT NULL, 
	city VARCHAR(20) NOT NULL, 
	capacity int NOT NULL, 
	street_addr VARCHAR(40) NOT NULL,
	PRIMARY KEY (v_name, city));

grant select on Venue to public;

CREATE TABLE Concert(
	conc_id CHAR(5) NOT NULL, 
	conc_name VARCHAR(30) NOT NULL, 
	duration SMALLINT NULL,  
	startDate CHAR(10) NOT NULL, 
	adults_only NUMBER(1,0) NULL,
	PRIMARY KEY (conc_id));

grant select on Concert to public;

CREATE TABLE Band(
	stage_name VARCHAR(30) NOT NULL, 
	genre VARCHAR(30) NULL,
	form_date CHAR(10) NULL,	
	PRIMARY KEY (stage_name));

grant select on Band to public;

CREATE TABLE artist_partof (
	a_name VARCHAR(30) NOT NULL, 
	country VARCHAR(20) NULL, 
	birth CHAR(10) NULL, 
	stage_name VARCHAR(30) NOT NULL,
	PRIMARY KEY (stage_name, a_name),
	FOREIGN KEY (stage_name) REFERENCES Band
	ON DELETE CASCADE);

grant select on artist_partof to public;

CREATE TABLE performs(
	conc_id	 CHAR(5) NOT NULL, 
	stage_name VARCHAR(30) NOT NULL,
	PRIMARY KEY (conc_id, stage_name),
	FOREIGN KEY (conc_id) REFERENCES Concert
	ON DELETE CASCADE,
	FOREIGN KEY (stage_name) REFERENCES Band
	ON DELETE CASCADE);

grant select on performs to public;

CREATE TABLE holdtickets(
	ticket_id CHAR(10) NOT NULL, 
	seat_num CHAR(3) NOT NULL, 
	start_date CHAR(10) NOT NULL,
	vip NUMBER(1,0) NOT NULL, 
	cost INT NOT NULL,
	city VARCHAR(20) NOT NULL,
	v_name VARCHAR(30) NOT NULL,
	cust_id CHAR(6) NULL,
	available NUMBER(1,0) NOT NULL,
	PRIMARY KEY (ticket_id),
	FOREIGN KEY (v_name, city) REFERENCES Venue
	ON DELETE CASCADE, 
	FOREIGN KEY (cust_id) REFERENCES Customers
	ON DELETE SET NULL);

grant select on holdtickets to public;

CREATE TABLE sells (
	conc_id CHAR(5) NOT NULL,	
	ticket_id CHAR(10) NOT NULL, 
	PRIMARY KEY (conc_id, ticket_id),
	FOREIGN KEY (conc_id) REFERENCES Concert
	ON DELETE CASCADE,
	FOREIGN KEY (ticket_id) REFERENCES holdtickets
	ON DELETE CASCADE);

grant select on sells to public;

CREATE TABLE concert_manager (
	username VARCHAR(20) NOT NULL,
	PRIMARY KEY (username)
)

grant select on concert_manger to public;

Concert Name, ticket id, seat, price, vip , venue, date
