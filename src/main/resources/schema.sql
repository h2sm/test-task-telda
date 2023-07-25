CREATE TABLE regions (
    region_id         serial NOT NULL,
    region_name       text   NOT NULL,
    short_region_name text   NOT NULL,
    PRIMARY KEY (region_id)
);