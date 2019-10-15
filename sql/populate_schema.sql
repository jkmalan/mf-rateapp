USE `mf_ratetool`;

INSERT INTO `ratesheet_defaults`
    (`code`, `value`)
    VALUES
           ('base_price', '102.0000'),
           ('base_rate', '0.05950'),
           ('base_scalar', '0.0000'),
           ('base_margin', '0.0000');


# Practice values for base rate testing
INSERT INTO `bkt_ltv`
    (`ltv_min`)
VALUES
    (70),
    (65),
    (60);

INSERT INTO `bkt_fico`
    (`fico_min`)
VALUES
    (740),
    (700),
    (660);

INSERT INTO `adj_ltv_vs_fico`
    (`bkt_ltv`, `bkt_fico`, `rate_adj`)
    VALUES
        (1, 1, 5.00),
        (1, 2, 5.25),
        (1, 3, 5.50),
        (2, 1, 5.25),
        (2, 2, 5.50),
        (2, 3, 5.75),
        (3, 1, 5.50),
        (3, 2, 5.75),
        (3, 3, 6.00);
