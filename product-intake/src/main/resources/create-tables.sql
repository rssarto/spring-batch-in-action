CREATE TABLE IF NOT EXISTS product (
    id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    price decimal(20,2) NOT NULL
);
ALTER TABLE product ADD PRIMARY KEY (id);