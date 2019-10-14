###
### REFERENCE TABLES
###

# Contains default values for base prices, rates, and margins
CREATE TABLE IF NOT EXISTS `ratesheet_defaults` (
    `code` VARCHAR(255),
    `value` VARCHAR(255),
    PRIMARY KEY (`code`)
);

# Contains bucket values for fico scores
CREATE TABLE IF NOT EXISTS `bkt_fico` (
    `id` INT AUTO_INCREMENT,
    `fico_min` NUMERIC,
    PRIMARY KEY (`id`)
);

# Contains bucket values for dscrs
CREATE TABLE IF NOT EXISTS `bkt_dscr` (
    `id` INT AUTO_INCREMENT,
    `dscr_min` NUMERIC,
    PRIMARY KEY (`id`)
);

# Contains bucket values for reserves
CREATE TABLE IF NOT EXISTS `bkt_reserve` (
    `id` INT AUTO_INCREMENT,
    `reserve_min` NUMERIC,
    PRIMARY KEY (`id`)
);

# Contains bucket values for loan balances
CREATE TABLE IF NOT EXISTS `bkt_balance` (
    `id` INT AUTO_INCREMENT,
    `balance_min` NUMERIC,
    PRIMARY KEY (`id`)
);

# Contains bucket values for loan purposes
CREATE TABLE IF NOT EXISTS `bkt_purpose` (
    `id` INT AUTO_INCREMENT,
    `purpose` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `bkt_state` (
    `id` INT AUTO_INCREMENT,
    `state` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `bkt_property` (
    `id` INT AUTO_INCREMENT,
    `property` VARCHAR(255),
    PRIMARY KEY (`id`)
);

###
### ADJUSTMENT TABLES
###

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_fico` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_fico` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_dscr` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_dscr` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_reserve` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_reserve` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_balance` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_balance` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_purpose` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_purpose` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_state` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_state` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `adj_ltv_vs_property` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_property` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`)
);

# Amortization, Prepayment Penalty Term, Lock Period, Prepayment Penalty Term vs Loan Amount vs Max Pricing
