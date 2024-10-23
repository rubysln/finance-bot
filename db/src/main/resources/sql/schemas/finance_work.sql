-- income_v
CREATE OR REPLACE VIEW finance_work.income_v
AS
SELECT i.id,
       u.username,
       c.name AS category_name,
       i.amount,
       i.income_date
FROM finance.incomes i
         LEFT JOIN finance.category c on i.category_id = c.id
         LEFT JOIN data.user u on u.chat_id = i.user_id;

--expense_v
CREATE OR REPLACE VIEW finance_work.expense_v
AS
SELECT e.id,
       u.username,
       e.amount,
       e.expense_date,
       c.name AS category_name
FROM finance.expense e
         LEFT JOIN finance.category c on c.id = e.category_id
         LEFT JOIN data.user u on u.chat_id = e.user_id;
