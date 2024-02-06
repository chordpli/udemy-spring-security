create table `users`
(
    `id`       INT         not null auto_increment,
    `username` varchar(45) not null,
    `password` varchar(45) not null,
    `enabled`  int         not null,
    primary key (`id`)
);

create table `authorities`
(
    `id`        int         not null auto_increment,
    `customer_id` int not null,
    `name`  varchar(45) not null,
    primary key (`id`),
    key `customer_id`(customer_id),
    constraint `authorities_ibfk_1` foreign key (`customer_id`) references `customer` (`customer_id`)
);

insert into `authorities` (`customer_id`, `name`)
values (1, `VIEWACCOUNT`);

insert into `authorities` (`customer_id`, `name`)
values (1, `VIEWCARDS`);

insert into `authorities` (`customer_id`, `name`)
values (1, `VIEWLOANS`);

insert into `authorities` (`customer_id`, `name`)
values (1, `VIEWBALANCE`);

insert into `authorities` (`customer_id`, `name`)
values (1, `ROLE_USER`);

insert into `authorities` (`customer_id`, `name`)
values (1, `ROLE_ADMIN`);

create table `customer`
(
    `id`            INT          not null auto_increment,
    `name`          varchar(100) not null,
    `email`         varchar(45)  not null,
    `mobile_number` varchar(20)  not null,
    `password`      varchar(200) not null,
    `role`          varchar(45)  not null,
    `created_at`    date DEFAULT null,
    primary key (`id`)
);

insert into `customer` (`name`, `email`, `mobile_number`, `password`, `role`, `created_at`)
values ('pli', 'chordplaylist@gmail.com', '000-0000', '1234', 'admin', CURDATE());

create table `accounts`
(
    `customer_id`    int          not null,
    `account_number` int          not null,
    `account_type`   varchar(100) not null,
    `branch_address` varchar(200) not null,
    `created_at`     date DEFAULT null,
    key              `sutomer_id` (`customer_id`),
    CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) on delete cascade
);


insert into `aacounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `created_at`)
values (1, 123456, 'saving', '1234', CURDATE());

create table `account_transactions`
(
    `transaction_id`      varchar(200) not null,
    `account_number`      int          not null,
    `customer_id`         int          not null,
    `transaction_dt`      date DEFAULT null,
    `transaction_summary` varchar(200) not null,
    `transaction_type`    varchar(100) not null,
    `transaction_type`    varchar(100) not null,
    `transaction_amt`     int          not null,
    `closing_balance`     int          not null,
    `created_at`          date DEFAULT null,
    primary key (`transaction_id`),
    like `customer_id` (`customer_id`),
    key                   `account_number` (`account_number`),
    CONSTRAINT `account_transactions_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) on delete cascade,
    CONSTRAINT `account_transactions_ibfk_2` FOREIGN KEY (`account_number`) REFERENCES `accounts` (`account_number`) on delete cascade
);

insert into `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`,
                                    `transaction_summary`, `transaction_type`, `transaction_amt`, `closing_balance`,
                                    `created_at`)
values ('1234', 123456, 1, CURDATE(), 'deposit', 'credit', 1000, 1000, CURDATE());

insert into `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`,
                                    `transaction_summary`, `transaction_type`, `transaction_amt`, `closing_balance`,
                                    `created_at`)
values ('1235', 123456, 1, CURDATE(), 'withdraw', 'debit', 1000, 0, CURDATE());

CREATE TABLE `loans`
(
    `loan_number`        int          NOT NULL AUTO_INCREMENT,
    `customer_id`        int          NOT NULL,
    `start_dt`           date         NOT NULL,
    `loan_type`          varchar(100) NOT NULL,
    `total_loan`         int          NOT NULL,
    `amount_paid`        int          NOT NULL,
    `outstanding_amount` int          NOT NULL,
    `create_dt`          date DEFAULT NULL,
    PRIMARY KEY (`loan_number`),
    KEY                  `customer_id` (`customer_id`),
    CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO `loans` (`customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_dt`)
VALUES (1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO `loans` (`customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_dt`)
VALUES (1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO `loans` (`customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_dt`)
VALUES (1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO `loans` (`customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`,
                     `create_dt`)
VALUES (1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

CREATE TABLE `cards`
(
    `card_id`          int          NOT NULL AUTO_INCREMENT,
    `card_number`      varchar(100) NOT NULL,
    `customer_id`      int          NOT NULL,
    `card_type`        varchar(100) NOT NULL,
    `total_limit`      int          NOT NULL,
    `amount_used`      int          NOT NULL,
    `available_amount` int          NOT NULL,
    `create_dt`        date DEFAULT NULL,
    PRIMARY KEY (`card_id`),
    KEY                `customer_id` (`customer_id`),
    CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`,
                     `create_dt`)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`,
                     `create_dt`)
VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`,
                     `create_dt`)
VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

CREATE TABLE `notice_details`
(
    `notice_id`      int          NOT NULL AUTO_INCREMENT,
    `notice_summary` varchar(200) NOT NULL,
    `notice_details` varchar(500) NOT NULL,
    `notic_beg_dt`   date         NOT NULL,
    `notic_end_dt`   date DEFAULT NULL,
    `create_dt`      date DEFAULT NULL,
    `update_dt`      date DEFAULT NULL,
    PRIMARY KEY (`notice_id`)
);

INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`,
                              `update_dt`)
VALUES ('Home Loan Interest rates reduced',
        'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`,
                              `update_dt`)
VALUES ('Net Banking Offers',
        'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`,
                              `update_dt`)
VALUES ('Mobile App Downtime',
        'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`,
                              `update_dt`)
VALUES ('E Auction notice',
        'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`,
                              `update_dt`)
VALUES ('Launch of Millennia Cards',
        'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` (`notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`,
                              `update_dt`)
VALUES ('COVID-19 Insurance',
        'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

CREATE TABLE `contact_messages`
(
    `contact_id`    varchar(50)   NOT NULL,
    `contact_name`  varchar(50)   NOT NULL,
    `contact_email` varchar(100)  NOT NULL,
    `subject`       varchar(500)  NOT NULL,
    `message`       varchar(2000) NOT NULL,
    `create_dt`     date DEFAULT NULL,
    PRIMARY KEY (`contact_id`)
);