USE thebanklist;

ALTER TABLE `account`
ADD FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

INSERT INTO user (userid, name, surName) VALUES 
('1', 'Geralt', 'OfRivia'),
('2','Harry', 'Potter'),
('3','Michail', 'Krug'),
('4','Till', 'Lindemann'),
('5','Victor', 'Tsoy'),
('6','Yennefer', 'Vengerberg'),
('7','James', 'Hetfield'),
('8','Vasya','Pupkin'),
('9','Gandalf','Gray'),
('10','Legolas','Greenleaf');

INSERT INTO account (userid, account) VALUES
('1', 1000),
('2', 100000),
('3', 100),
('4', 1666),
('5', 1990),
('6', 12345),
('7', 666),
('8', 5),
('9', 10),
('10', 1070);


