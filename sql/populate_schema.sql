USE `mf_ratetool`;

INSERT INTO `ratesheet_defaults`
    (`key`, `value`)
    VALUES
           ('base_price', '102.0000'),
           ('base_rate', '0.05950'),
           ('base_scalar', '0.0000'),
           ('base_margin', '0.0000');

INSERT INTO `bkt_ltv`
    (`ltv_min`, `ltv_max`)
VALUES
    (95, NULL),
    (90, 95),
    (85, 90),
    (80, 85),
    (75, 80),
    (70, 75),
    (65, 70),
    (60, 65),
    (55, 60),
    (50, 55),
    (0, 50);

INSERT INTO `bkt_fico`
    (`fico_min`, `fico_max`)
VALUES
    (780, NULL),
    (760, 780),
    (740, 760),
    (720, 740),
    (700, 720),
    (680, 700),
    (660, 680),
    (640, 660),
    (620, 640),
    (600, 620),
    (580, 600),
    (560, 580),
    (540, 560),
    (520, 540),
    (500, 520),
    (NULL, 500);

INSERT INTO `bkt_dscr`
    (`dscr_min`, `dscr_max`)
VALUES
    (1.50, NULL),
    (1.25, 1.50),
    (1.00, 1.25),
    (NULL, 1.00);

INSERT INTO `bkt_reserve`
    (`reserve_min`, `reserve_max`)
VALUES
    (12.0, NULL),
    (6.0, 12.0),
    (4.0, 6.0),
    (2.0, 4.0),
    (NULL, 2.0);

INSERT INTO `bkt_balance`
    (`balance_min`, `balance_max`)
VALUES
    (5000000.00, NULL),
    (4000000.00, 5000000.00),
    (3000000.00, 4000000.00),
    (2500000.00, 3000000.00),
    (2000000.00, 2500000.00),
    (1500000.00, 2000000.00),
    (1000000.00, 1500000.00),
    (750000.00, 1000000.00),
    (500000.00, 750000.00),
    (250000.00, 500000.00),
    (150000.00, 250000.00),
    (100000.00, 150000.00),
    (75000.00, 100000.00),
    (NULL, 75000.00);

INSERT INTO `bkt_purpose`
    (`purpose`)
VALUES
    ('Purchase'),
    ('Rate Refinance'),
    ('Cash Out Refinance');

INSERT INTO `bkt_state`
    (`state`, `tier`)
VALUES
    ('AK', 1),
    ('AL', 1),
    ('AR', 1),
    ('AZ', 2),
    ('CA', 2),
    ('CO', 2),
    ('CT', 3),
    ('DC', 3),
    ('DE', 2),
    ('FL', 2),
    ('GA', 1),
    ('HI', 2),
    ('IA', 1),
    ('ID', 2),
    ('IL', 2),
    ('IN', 1),
    ('KS', 1),
    ('KY', 1),
    ('LA', 2),
    ('MA', 2),
    ('MD', 3),
    ('ME', 2),
    ('MI', 2),
    ('MN', 2),
    ('MO', 1),
    ('MS', 1),
    ('MT', 3),
    ('NC', 1),
    ('ND', 2),
    ('NE', 1),
    ('NH', 1),
    ('NJ', 3),
    ('NM', 2),
    ('NV', 2),
    ('NY', 3),
    ('OH', 2),
    ('OK', 2),
    ('OR', 2),
    ('PA', 2),
    ('RI', 2),
    ('SC', 1),
    ('SD', 3),
    ('TN', 1),
    ('TX', 2),
    ('UT', 1),
    ('VA', 2),
    ('VT', 3),
    ('WA', 1),
    ('WI', 2),
    ('WV', 2),
    ('WY', 2);

INSERT INTO `bkt_property`
    (`property`)
VALUES
    ('Planned Unit Development'),
    ('Single Family Residence'),
    ('Multi Family Residence'),
    ('Townhouse'),
    ('Condominium');

INSERT INTO `bkt_amortization`
    (`amortization`)
VALUES
    ('Interest Only');

INSERT INTO `bkt_prepayment`
    (`prepayment`)
VALUES
    ('60 Months'),
    ('48 Months'),
    ('36 Months'),
    ('24 Months'),
    ('12 Months'),
    ('No Penalty');

INSERT INTO `bkt_term`
    (`term`)
VALUES
    ('1 YR BALLOON'),
    ('3/1 ARM'),
    ('5/1 ARM'),
    ('7/1 ARM'),
    ('10/1 ARM'),
    ('10 YR FIX'),
    ('15 YR FIX'),
    ('30 YR FIX'),
    ('40 YR FIX');

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
