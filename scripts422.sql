CREATE TABLE users ( 
    id INTEGER PRIMARY KEY,
    name TEXT, 
	age INTEGER,
	driving_licence BOOLEAN,
    car_id INTEGER REFERENCES cars (id) 
)

CREATE TABLE cars ( 
    id INTEGER PRIMARY KEY,
    manufacturer TEXT, 
	model TEXT,
	price INTEGER 
)
