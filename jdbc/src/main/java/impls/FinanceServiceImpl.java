package impls;

import finance.Expense;
import finance.Goal;
import finance.Income;
import finance.Savings;
import finance.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.*;
import services.FinanceService;
import user.User;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private UserRepository userRepository;

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
    public User postIncome(Income object) {
        incomeRepository.save(object);
        return userRepository.getUserByChatId(object.getUser().getChatId());
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
    public User postExpense(Expense object) {
        expenseRepository.save(object);
        return userRepository.findById(object.getUser().getChatId()).orElse(null);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Category> getCategoriesByCategoryTypeSysName(String sysName) {
        return categoryRepository.findByCategoryTypeSysName(sysName);
    }

    @Override
    public Category getCategoryByName(String name){
        return categoryRepository.findCategoriesByName(name);
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
