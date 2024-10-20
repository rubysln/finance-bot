-- income_v
CREATE OR REPLACE VIEW finance_work.income_v
AS
SELECT i.id,
       i.category_id,
       i.amount,
       i.income_date,
       c.name AS category_name
FROM finance.incomes i
         LEFT JOIN finance.category c on i.category_id = c.id;

--expense_v
CREATE OR REPLACE VIEW finance_work.expense_v
AS
SELECT e.id,
       e.category_id,
       e.amount,
       e.expense_date,
       c.name AS category_name
FROM finance.expense e
         LEFT JOIN finance.category c on e.category_id = c.id;