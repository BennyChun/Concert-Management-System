INSERT INTO CUSTOMERS VALUES
('100000', 'Johnny Li', '111 No 3 Road, Richmond BC', '1996/01/21', '604-543-1924', 'johnny@gmail.com');

INSERT INTO CUSTOMERS VALUES
('100001', 'Miki Macapagal', '222 Main Road, Vancouver BC', '1996/06/01', '604-232-2912', 'miki@gmail.com');

INSERT INTO CUSTOMERS VALUES
('100002', 'Chris Tsang', '333 Odlin Road, Richmond BC', '1956/11/05', '778-143-1234', 'ct5@gmail.com');

INSERT INTO CUSTOMERS VALUES
('100003', 'Japhet Jemina', '444 Burrard St, Vancouver BC', '1985/12/03', '778-992-9954', 'japhet@ubc.ca');

INSERT INTO CUSTOMERS VALUES
('100004', 'Emma Watson', '555 Wesbrook St, Vancouver BC', '1992/08/15', '778-523-2956', 'watson@hotmail.com');

INSERT INTO VENUE VALUES
('Rogers Arena', 'Vancouver', 20000, '995 Griffiths Ave');

INSERT INTO VENUE VALUES
('Pacific Coliseum', 'Vancouver', 15000, '900 W Hastings St');

INSERT INTO VENUE VALUES
('Madison Square Garden', 'New York City', 23123, '888 Apple St');

INSERT INTO VENUE VALUES
('T Mobile Arena', 'Las Vegas', 18000, '123 Dice Road');

INSERT INTO VENUE VALUES
('TD Garden', 'Boston', 17049, '4444 Celtic Ave');

INSERT INTO CONCERT VALUES
('00000', 'Summer Sixteen Tour', 3, '2016/06/01', 0);

INSERT INTO CONCERT VALUES
('00001', 'Starboy: Legend of the Fall', 2, '2018/01/01', 1);

INSERT INTO CONCERT VALUES
('00002', 'A Head Full of Dreams Tour', 4, '2015/09/21', 0);

INSERT INTO CONCERT VALUES
('00003', 'Dangerous Woman Tour', 3, '2016/02/14', 0);

INSERT INTO CONCERT VALUES
('00004', 'The Damn Tour', 3, '2017/08/18', 0);

INSERT INTO BAND VALUES
('Drake', 'Rap', '2005/01/01');

INSERT INTO BAND VALUES
('The Weeknd', 'RnB', '2007/03/01');

INSERT INTO BAND VALUES
('Coldplay', 'Rock', '2000/01/01');

INSERT INTO BAND VALUES
('Ariana Grande', 'Pop', '2014/01/01');

INSERT INTO BAND VALUES
('Kendrick Lamar', 'Rap', '2007/09/01');

INSERT INTO ARTIST_PARTOF VALUES 
('Aubrey Drake Graham', 'Canada', '1986/10/24', 'Drake');

INSERT INTO ARTIST_PARTOF VALUES 
('Abel Makkonen Tesfaye', 'Canada', '1990/02/16', 'The Weeknd');

INSERT INTO ARTIST_PARTOF VALUES 
('Chris Martin', 'England', '1977/03/02', 'Coldplay');

INSERT INTO ARTIST_PARTOF VALUES 
('Ariana Grande', 'United States', '1993/06/26', 'Ariana Grande');

INSERT INTO ARTIST_PARTOF VALUES 
('Kendrick Lamar', 'United States', '1987/06/17', 'Kendrick Lamar');

INSERT INTO PERFORMS VALUES
('00000', 'Drake');

INSERT INTO PERFORMS VALUES
('00001', 'The Weeknd');

INSERT INTO PERFORMS VALUES
('00002', 'Coldplay');

INSERT INTO PERFORMS VALUES
('00003', 'Ariana Grande');

INSERT INTO PERFORMS VALUES
('00004', 'Kendrick Lamar');

INSERT INTO HOLDTICKETS VALUES
('0123456789', '101', '2016/06/01', 0, 150, 'Vancouver', 'Rogers Arena', '100000', 0);

INSERT INTO HOLDTICKETS VALUES
('1591231431', '100', '2016/06/01', 1, 150, 'New York City', 'Madison Square Garden', '100000', 0);

INSERT INTO HOLDTICKETS VALUES
('5554443332', '301', '2015/09/21', 0, 100, 'New York City', 'Madison Square Garden', '100004', 0);

INSERT INTO HOLDTICKETS VALUES
('7778889992', 'Z11', '2017/08/18', 1, 500, 'Las Vegas', 'T Mobile Arena', '100001', 0);

INSERT INTO HOLDTICKETS VALUES
('5911239992', 'A25', '2016/02/14', 0, 111, 'Boston', 'TD Garden', '100002', 0);

INSERT INTO SELLS VALUES
('00000', '0123456789');

INSERT INTO SELLS VALUES
('00001', '1591231431');

INSERT INTO SELLS VALUES
('00002', '5554443332');

INSERT INTO SELLS VALUES
('00003', '5911239992');

INSERT INTO SELLS VALUES
('00004', '7778889992');

INSERT INTO CONCERT_MANAGER VALUES
('admin');

INSERT INTO CONCERT_MANAGER VALUES
('manager');




