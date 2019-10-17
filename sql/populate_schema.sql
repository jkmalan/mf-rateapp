USE `mf_ratetool`;

INSERT INTO `ratesheet_defaults`
    (`key`, `value`)
VALUES
   ('base_price', '102.0000'),
   ('base_rate', '0.05950'),
   ('base_scalar', '0.0000'),
   ('base_margin', '0.0000');

INSERT INTO `bkt_ltv`
    (`ltv_min`, `ltv_max`, `display`)
VALUES
    (95, NULL, '95 <'),
    (90, 95, '90-95'),
    (85, 90, '85-90'),
    (80, 85, '80-85'),
    (75, 80, '75-80'),
    (70, 75, '70-75'),
    (65, 70, '65-70'),
    (60, 65, '60-65'),
    (55, 60, '55-60'),
    (50, 55, '50-55'),
    (NULL, 50, '<= 50');

INSERT INTO `bkt_fico`
    (`fico_min`, `fico_max`, `display`)
VALUES
    (780, NULL, '780 <='),
    (760, 779, '760-779'),
    (740, 759, '740-759'),
    (720, 739, '720-739'),
    (700, 719, '700-719'),
    (680, 699, '680-699'),
    (660, 679, '660-679'),
    (640, 659, '640-659'),
    (620, 639, '620-639'),
    (600, 619, '600-619'),
    (580, 599, '580-599'),
    (560, 579, '560-579'),
    (540, 559, '540-559'),
    (520, 539, '520-539'),
    (500, 519, '500-519'),
    (NULL, 500, '< 500');

INSERT INTO `bkt_dscr`
    (`dscr_min`, `dscr_max`, `display`)
VALUES
    (1.50, NULL, '1.50 <='),
    (1.25, 1.49, '1.25-1.49'),
    (1.00, 1.24, '1.00-1.24'),
    (NULL, 1.00, '< 1.00');

INSERT INTO `bkt_reserve`
    (`reserve_min`, `reserve_max`, `display`)
VALUES
    (12, NULL, '12 Months <='),
    (6, 12, '6-12 Months'),
    (4, 6, '4-6 Months'),
    (2, 4, '2-4 Months'),
    (NULL, 2, '< 2 Months');

INSERT INTO `bkt_balance`
    (`balance_min`, `balance_max`, `display`)
VALUES
    (5000000.00, NULL, '$5,000,000.01<'),
    (4000000.00, 5000000.00, '$4,000,000.01-$5,000,000.00'),
    (3000000.00, 4000000.00, '$3,000,000.01-$4,000,000.00'),
    (2500000.00, 3000000.00, '$2,500,000.01-$3,000,000.00'),
    (2000000.00, 2500000.00, '$2,000,000.01-$2,500,000.00'),
    (1500000.00, 2000000.00, '$1,500,000.01-$2,000,000.00'),
    (1000000.00, 1500000.00, '$1,000,000.01-$1,500,000.00'),
    (750000.00, 1000000.00, '$750,000.01-$1,000,000.00'),
    (500000.00, 750000.00, '$500,000.01-$750,000.00'),
    (250000.00, 500000.00, '$250,000.01-$500,000.00'),
    (150000.00, 250000.00, '$150,000.01-$250,000.00'),
    (100000.00, 150000.00, '$100,000.01-$150,000.00'),
    (75000.00, 100000.00, '$75,001.00-$100,000.00'),
    (NULL, 75000.00, '<$75,000.00');

INSERT INTO `bkt_purpose`
    (`purpose`)
VALUES
    ('Purchase'),
    ('Rate Refinance'),
    ('Cash Out Refinance');

INSERT INTO `bkt_state`
    (`state`, `tier`, `include`, `display`)
VALUES
    ('AK', 1, TRUE, 'Alaska'),
    ('AL', 1, TRUE, 'Alabama'),
    ('AR', 1, TRUE, 'Arkansas'),
    ('AZ', 2, FALSE, 'Arizona'),
    ('CA', 2, FALSE, 'California'),
    ('CO', 2, TRUE, 'Colorado'),
    ('CT', 3, TRUE, 'Connecticut'),
    ('DC', 3, TRUE, 'Washington DC'),
    ('DE', 2, TRUE, 'Delaware'),
    ('FL', 2, TRUE, 'Florida'),
    ('GA', 1, TRUE, 'Georgia'),
    ('HI', 2, TRUE, 'Hawaii'),
    ('IA', 1, TRUE, 'Iowa'),
    ('ID', 2, FALSE, 'Idaho'),
    ('IL', 2, TRUE, 'Illinois'),
    ('IN', 1, TRUE, 'Indiana'),
    ('KS', 1, TRUE, 'Kansas'),
    ('KY', 1, TRUE, 'Kentucky'),
    ('LA', 2, TRUE, 'Louisiana'),
    ('MA', 2, TRUE, 'Massachusetts'),
    ('MD', 3, TRUE, 'Maryland'),
    ('ME', 2, TRUE, 'Maine'),
    ('MI', 2, TRUE, 'Michigan'),
    ('MN', 2, FALSE, 'Minnesota'),
    ('MO', 1, TRUE, 'Missouri'),
    ('MS', 1, TRUE, 'Mississippi'),
    ('MT', 3, TRUE, 'Montana'),
    ('NC', 1, TRUE, 'North Carolina'),
    ('ND', 2, FALSE, 'North Dakota'),
    ('NE', 1, TRUE, 'Nebraska'),
    ('NH', 1, TRUE, 'Hew Hampshire'),
    ('NJ', 3, TRUE, 'New Jersey'),
    ('NM', 2, TRUE, 'New Mexico'),
    ('NV', 2, FALSE, 'Nevada'),
    ('NY', 3, TRUE, 'New York'),
    ('OH', 2, TRUE, 'Ohio'),
    ('OK', 2, TRUE, 'Oklahoma'),
    ('OR', 2, FALSE, 'Oregon'),
    ('PA', 2, TRUE, 'Pennsylvania'),
    ('RI', 2, TRUE, 'Rhode Island'),
    ('SC', 1, TRUE, 'South Carolina'),
    ('SD', 3, TRUE, 'South Dakota'),
    ('TN', 1, TRUE, 'Tennessee'),
    ('TX', 2, TRUE, 'Texas'),
    ('UT', 1, FALSE, 'Utah'),
    ('VA', 2, TRUE, 'Virginia'),
    ('VT', 3, FALSE, 'Vermont'),
    ('WA', 1, TRUE, 'Washington'),
    ('WI', 2, TRUE, 'Wisconsin'),
    ('WV', 2, TRUE, 'West Virginia'),
    ('WY', 2, TRUE, 'Wyoming');

INSERT INTO `bkt_property`
    (`property`, `display`)
VALUES
    ('PUD', 'Planned Unit Development'),
    ('SFR', 'Single Family Residence'),
    ('MFR', 'Multi Family Residence'),
    ('TOH', 'Townhouse'),
    ('NWC', 'Non-Warrantable Condo'),
    ('CND', 'Condo');

INSERT INTO `bkt_amortization`
    (`amortization`)
VALUES
    ('Interest Only');

INSERT INTO `bkt_prepayment`
    (`prepayment`, `display`)
VALUES
    (60, '60 Months'),
    (48, '48 Months'),
    (36, '36 Months'),
    (24, '24 Months'),
    (12, '12 Months'),
    (0, 'No Penalty');

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
