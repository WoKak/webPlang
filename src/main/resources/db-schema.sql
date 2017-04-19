DROP TABLE words
IF EXISTS;

CREATE TABLE words (
    id integer NOT NULL,
    wordinpolish character(50),
    wordinenglish character(50)
);