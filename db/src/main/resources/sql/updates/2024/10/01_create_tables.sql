CREATE SCHEMA finance;
CREATE SCHEMA data;


CREATE TABLE data.user
(
    chat_id    BIGINT PRIMARY KEY,
    username   varchar(255) NOT NULL,
    first_name varchar(255),
    last_name  varchar(255)
);

CREATE TABLE finance.category_type
(
    id       SERIAL PRIMARY KEY,
    name     varchar(255) NOT NULL UNIQUE,
    sys_name varchar(255) NOT NULL UNIQUE
);

INSERT INTO finance.category_type(name, sys_name)
VALUES ('Доход', 'income');
INSERT INTO finance.category_type(name, sys_name)
VALUES ('Расход', 'expense');

CREATE TABLE finance.category
(
    id               SERIAL PRIMARY KEY,
    name             varchar(255) NOT NULL,
    category_type_id BIGINT       NOT NULL,
    FOREIGN KEY (category_type_id) REFERENCES finance.category_type (id)
);

INSERT INTO finance.category(name, category_type_id)
VALUES ('Зарплата', 1);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Бизнес', 1);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Дивиденды', 1);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Подарки', 1);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Дополнительные доходы', 1);

INSERT INTO finance.category(name, category_type_id)
VALUES ('Продукты', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Транспорт', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Уход за собой', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Образование', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Покупка одежды', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Медицина', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Сбережения', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Инвестиции', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Развлечения', 2);
INSERT INTO finance.category(name, category_type_id)
VALUES ('Прочие расходы', 2);

CREATE TABLE finance.incomes
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT         NOT NULL,
    amount      numeric(12, 2) NOT NULL,
    category_id BIGINT         NOT NULL,
    income_date DATE           NOT NULL,
    FOREIGN KEY (category_id) REFERENCES finance.category (id),
    FOREIGN KEY (user_id) REFERENCES data.user (chat_id)
);

CREATE TABLE finance.expense
(
    id           SERIAL PRIMARY KEY,
    user_id      BIGINT         NOT NULL,
    amount       numeric(12, 2) NOT NULL,
    category_id  BIGINT         NOT NULL,
    expense_date DATE           NOT NULL,
    FOREIGN KEY (category_id) REFERENCES finance.category (id),
    FOREIGN KEY (user_id) REFERENCES data.user (chat_id)
);

CREATE TABLE finance.savings
(
    id           SERIAL PRIMARY KEY,
    total_amount numeric(12, 2) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES data.user
);

CREATE TABLE finance.goal
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    target_amount numeric(12, 2) NOT NULL,
    date          DATE,
    savings_id    BIGINT,
    FOREIGN KEY (savings_id) REFERENCES finance.savings (id)
);