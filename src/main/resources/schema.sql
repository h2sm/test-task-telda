CREATE TABLE regions (
    region_id         int AUTO_INCREMENT NOT NULL,
    region_name       text UNIQUE NOT NULL,
    short_region_name text NOT NULL,
    PRIMARY KEY (region_id)
);