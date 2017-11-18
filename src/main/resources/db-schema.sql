CREATE TABLE words (
    id INTEGER NOT NULL,
    wordinpolish VARCHAR(50) NOT NULL,
    wordinenglish VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE stats (
  id INTEGER NOT NULL,
  num_correct INTEGER NOT NULL,
  num_total INTEGER NOT NULL,
  FOREIGN KEY (id) REFERENCES words(id)
)