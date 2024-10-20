package services;

import finance.Expense;
import finance.Income;

import java.util.List;

public interface FinanceService {

    /*
        Finance
     */

    List<Income> getIncomes();

    long getCountIncomes();

    Income getIncomeById(Long id);

    Income postIncome(Income object);

    void deleteIncome(Long id);

    List<Expense> getExpenses();

    long getCountExpenses();

    Expense getExpenseById(Long id);

    Expense postExpense(Expense object);

    void deleteExpense(Long id);
}
