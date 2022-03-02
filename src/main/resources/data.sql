
--Franchise
INSERT INTO franchise ( description, name ) VALUES ( 'Bruce Banner, a scientist on the run from the U.S. Government, must find a cure for the monster he turns into whenever he loses his temper.', 'Marvel' );
INSERT INTO franchise ( description, name ) VALUES ( 'When a killer shark unleashes chaos on a beach community off Long Island, it is up to a local sheriff, a marine biologist, and an old seafarer to hunt the beast down.', 'The Jaws Franchise' );
INSERT INTO franchise ( description, name ) VALUES ( 'When bitten by a genetically modified spider, a nerdy, shy, and awkward high school student gains spider-like abilities that he eventually must use to fight evil as a superhero after tragedy befalls his family.', 'Marvel' );


--Characters

INSERT INTO character ( alias, full_name, gender, url) VALUES ( 'Spidey', 'Spider Man', 'male', 'https://www.imdb.com/title/tt0145487/mediaviewer/rm3632146944/?ref_=tt_ov_i');
INSERT INTO character ( alias, full_name, gender, url) VALUES ( 'Captain', 'Captain Quint', 'male', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.facebook.com%2FCAPTAIN.QUINT.JAWS%2F&psig=AOvVaw0JljCbS4OQJZtxFObrLX-5&ust=1646217400354000&source=images&cd=vfe&ved=2ahUKEwjNzZ7s26T2AhVYHMAKHZ37Bi4QjRx6BAgAEAk');
INSERT INTO character ( alias, full_name, gender, url) VALUES ( 'Bruce', 'Bruce Banner', 'male', 'https://m.media-amazon.com/images/M/MV5BMTYwNjQ5MTI1NF5BMl5BanBnXkFtZTcwMzU5MTI2Mw@@._V1_UY317_CR16,0,214,317_AL_.jpg');


--Movies
INSERT INTO movie (director, genre, release_year, title, trailer, picture) VALUES
    ('Louis Leterrier', 'Action',
     2008, 'The Incredible Hulk', 'https://www.imdb.com/video/vi1726152985?playlistId=tt0800080&ref_=tt_ov_vi', 'https://www.imdb.com/title/tt0800080/mediaviewer/rm2081134080/?ref_=tt_ov_i');

INSERT INTO movie (director, genre, release_year, title, trailer, picture) VALUES
                                                      ('Steven Spielberg', 'Thriller',
                                                       1975, 'Jaws', 'https://www.youtube.com/watch?v=U1fu_sA7XhE',
                                                       'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSvjq3-vt5bD0zu0uiek5P9wVywgas7CIi8v77BIUTobwAx0KvP');
INSERT INTO movie (director, genre, release_year, title, trailer, picture) VALUES
    ('Sam Raimi', 'Action',
     2002, 'Spider-Man', 'https://www.imdb.com/video/vi1497605145?playlistId=tt0145487&ref_=tt_ov_vi', 'https://www.imdb.com/title/tt0145487/mediaviewer/rm3632146944/?ref_=tt_ov_i');