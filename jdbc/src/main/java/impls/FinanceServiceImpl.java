package impls;

import finance.Expense;
import finance.Goal;
import finance.Income;
import finance.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.ExpenseRepository;
import repositories.GoalRepository;
import repositories.IncomeRepository;
import repositories.SavingsRepository;
import services.FinanceService;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private SavingsRepository savingsRepository;

    @Override
    public List<Income> getIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public long getCountIncomes() {
        return incomeRepository.count();
    }

    @Override
    public Income getIncomeById(Long id) {
        return incomeRepository.findById(id).orElse(null);
    }

    @Override
    public Income postIncome(Income object) {
        return incomeRepository.save(object);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public long getCountExpenses() {
        return expenseRepository.count();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Override
    public Expense postExpense(Expense object) {
        return expenseRepository.save(object);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Goal> getGoals() {
        return goalRepository.findAll();
    }

    @Override
    public long getCountGoals() {
        return goalRepository.count();
    }

    @Override
    public Goal getGoalById(Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    @Override
    public Goal postGoal(Goal object) {
        return goalRepository.save(object);
    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    @Override
    public Savings getSavingsById(Long id) {
        return savingsRepository.findById(id).orElse(null);
    }

    @Override
    public Savings postSavings(Savings object) {
        return savingsRepository.save(object);
    }

    @Override
    public void deleteSavings(Long id) {
        savingsRepository.deleteById(id);
    }
}
