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
    `username`  varchar(45) not null,
    `authority` varchar(45) not null,
    primary key (`id`)
);


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