package services;

import finance.Expense;
import finance.Goal;
import finance.Income;
import finance.Savings;
import finance.category.Category;
import finance.category.CategoryType;
import org.springframework.stereotype.Service;
import user.User;

import java.util.List;

@Service
public interface FinanceService {

    /*
        Finance
     */

    /*
        Incomes
     */

    List<Income> getIncomes(Long chatId);

    long getCountIncomes();

    Income getIncomeById(Long id);

    User postIncome(Income object);

    void deleteIncome(Long id);

    /*
        Expense
     */

    List<Expense> getExpenses(Long chatId);

    long getCountExpenses();

    Expense getExpenseById(Long id);

    User postExpense(Expense object);

    void deleteExpense(Long id);

    /*
        Category
     */

    List<Category> getCategoriesByCategoryTypeSysName(String sysName);
    Category getCategoryByName(String name);

    /*
        Goals
     */

    List<Goal> getGoals();

    long getCountGoals();

    Goal getGoalById(Long id);

    Goal postGoal(Goal object);

    void deleteGoal(Long id);


    /*
        Savings
     */

    Savings getSavingsById(Long id);

    Savings postSavings(Savings object);

    void deleteSavings(Long id);
}
