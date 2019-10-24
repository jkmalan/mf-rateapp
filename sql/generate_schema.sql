###
### REFERENCE TABLES
### Contains lookup tables, lookup buckets, and defaults
###

# Contains default values for base prices, rates, and margins
CREATE TABLE IF NOT EXISTS `ratesheet_defaults` (
    `key` VARCHAR(255),
    `value` VARCHAR(255),
    PRIMARY KEY (`key`)
);

# Contains bucket values for ltvs
CREATE TABLE IF NOT EXISTS `bkt_ltv` (
    `id` INT AUTO_INCREMENT,
    `ltv_min` NUMERIC(9,2),
    `ltv_max` NUMERIC(9,2),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for fico scores
CREATE TABLE IF NOT EXISTS `bkt_fico` (
    `id` INT AUTO_INCREMENT,
    `fico_min` NUMERIC(9,2),
    `fico_max` NUMERIC(9,2),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for dscrs
CREATE TABLE IF NOT EXISTS `bkt_dscr` (
    `id` INT AUTO_INCREMENT,
    `dscr_min` NUMERIC(9,2),
    `dscr_max` NUMERIC(9,2),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for monthly reserves
CREATE TABLE IF NOT EXISTS `bkt_reserve` (
    `id` INT AUTO_INCREMENT,
    `reserve_min` NUMERIC(9,2),
    `reserve_max` NUMERIC(9,2),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for loan balances
CREATE TABLE IF NOT EXISTS `bkt_balance` (
    `id` INT AUTO_INCREMENT,
    `balance_min` NUMERIC(9,2),
    `balance_max` NUMERIC(9,2),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for loan purposes
CREATE TABLE IF NOT EXISTS `bkt_purpose` (
    `id` INT AUTO_INCREMENT,
    `purpose` VARCHAR(255),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for states
CREATE TABLE IF NOT EXISTS `bkt_state` (
    `id` INT AUTO_INCREMENT,
    `state` VARCHAR(255),
    `tier` INT,
    `include` BOOLEAN,
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for property types
CREATE TABLE IF NOT EXISTS `bkt_property` (
    `id` INT AUTO_INCREMENT,
    `property` VARCHAR(255),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for amortizations
CREATE TABLE IF NOT EXISTS `bkt_amortization` (
    `id` INT AUTO_INCREMENT,
    `amortization` VARCHAR(255),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for prepayment penalty terms
CREATE TABLE IF NOT EXISTS `bkt_prepayment` (
    `id` INT AUTO_INCREMENT,
    `prepayment` NUMERIC(9,2),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains bucket values for loan terms
CREATE TABLE IF NOT EXISTS `bkt_term` (
    `id` INT AUTO_INCREMENT,
    `term` VARCHAR(255),
    `display` VARCHAR(255),
    PRIMARY KEY (`id`)
);

# Contains map values for product table fico scores
CREATE TABLE IF NOT EXISTS `prod_fico` (
    `id` INT AUTO_INCREMENT,
    `fico_min` NUMERIC(9,2),
    `fico_max` NUMERIC(9,2),
    `fico_map` NUMERIC(9,2),
    PRIMARY KEY (`id`)
);

# Contains map values for product table loan balances
CREATE TABLE IF NOT EXISTS `prod_balance` (
    `id` INT AUTO_INCREMENT,
    `balance_min` NUMERIC(9,2),
    `balance_max` NUMERIC(9,2),
    `balance_map` NUMERIC(9,2),
    PRIMARY KEY (`id`)
);

###
### ADJUSTMENT TABLES
### Contains rate and adjustment tables
###

# Lookup base rate by ltv and fico score
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_fico` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_fico` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_fico`) REFERENCES `bkt_fico`(`id`)
);

# Lookup a rate adjustment based on ltv and dscr
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_dscr` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_dscr` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_dscr`) REFERENCES `bkt_dscr`(`id`)
);

# Lookup a rate adjustment based on ltv and monthly reserves
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_reserve` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_reserve` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_reserve`) REFERENCES `bkt_reserve`(`id`)
);

# Lookup a rate adjustment based on ltv and loan balance
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_balance` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_balance` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_balance`) REFERENCES `bkt_balance`(`id`)
);

# Lookup a rate adjustment based on ltv and loan purpose
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_purpose` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_purpose` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_purpose`) REFERENCES `bkt_purpose`(`id`)
);

# Lookup a rate adjustment based on ltv and state
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_state` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_state` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_state`) REFERENCES `bkt_state`(`id`)
);

# Lookup a rate adjustment based on ltv and property type
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_property` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_property` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_property`) REFERENCES `bkt_property`(`id`)
);

# Lookup a rate adjustment based on ltv and amortization
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_amortization` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_amortization` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_amortization`) REFERENCES `bkt_amortization`(`id`)
);

# Lookup a rate adjustment based on ltv and prepayment penalty term
CREATE TABLE IF NOT EXISTS `adj_ltv_vs_prepayment` (
    `id` INT AUTO_INCREMENT,
    `bkt_ltv` INT,
    `bkt_prepayment` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_ltv`) REFERENCES `bkt_ltv`(`id`),
    FOREIGN KEY (`bkt_prepayment`) REFERENCES `bkt_prepayment`(`id`)
);

# Lookup a rate adjustment based on loan term
CREATE TABLE IF NOT EXISTS `adj_term` (
    `id` INT AUTO_INCREMENT,
    `bkt_term` INT,
    `rate_adj` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_term`) REFERENCES `bkt_term`(`id`)
);

###
### PRODUCT TABLES
### Contains max LTV product tables
###

# Lookup a max ltv based on dscr, loan purpose, fico score, and loan balance
CREATE TABLE IF NOT EXISTS `max_ltv` (
    `id` INT AUTO_INCREMENT,
    `bkt_dscr` INT,
    `bkt_purpose` INT,
    `prod_fico` INT,
    `prod_balance` INT,
    `max_ltv` DECIMAL(9,2),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`bkt_dscr`) REFERENCES `bkt_dscr`(`id`),
    FOREIGN KEY (`bkt_purpose`) REFERENCES `bkt_purpose`(`id`),
    FOREIGN KEY (`prod_fico`) REFERENCES `prod_fico`(`id`),
    FOREIGN KEY (`prod_balance`) REFERENCES `prod_balance`(`id`)
);
