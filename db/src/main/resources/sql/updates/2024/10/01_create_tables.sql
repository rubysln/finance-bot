CREATE SCHEMA finance;
CREATE SCHEMA finance_work;

CREATE TABLE finance.category_type
(
    id   SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE finance.category
(
    id               SERIAL PRIMARY KEY,
    name             varchar(255) not null,
    category_type_id BIGINT       NOT NULL,
    FOREIGN KEY (category_type_id) REFERENCES finance.category_type (id)
);

CREATE TABLE finance.incomes
(
    id          SERIAL PRIMARY KEY,
    amount      numeric(12, 2) NOT NULL,
    category_id BIGINT         NOT NULL,
    income_date DATE           NOT NULL,
    FOREIGN KEY (category_id) REFERENCES finance.category (id)
);

CREATE TABLE finance.expense
(
    id           SERIAL PRIMARY KEY,
    amount       numeric(12, 2) NOT NULL,
    category_id  BIGINT         NOT NULL,
    expense_date DATE           NOT NULL,
    FOREIGN KEY (category_id) REFERENCES finance.category (id)
);