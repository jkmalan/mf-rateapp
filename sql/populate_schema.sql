USE `mf_ratetool`;

INSERT INTO `ratesheet_defaults`
    (`code`, `value`)
    VALUES
           ('base_price', '102.0000'),
           ('base_rate', '0.05950'),
           ('base_scalar', '0.0000'),
           ('base_margin', '0.0000');

INSERT INTO `adj_ltv_vs_fico`
    (`bucket_ltv`, `bucket_fico`, `rate_adj`)
    VALUES
           (1, 1, 0.000);
