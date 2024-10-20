package impls;

import finance.Expense;
import finance.Income;
import repositories.ExpenseRepository;
import repositories.IncomeRepository;
import services.FinanceService;

import java.util.List;

public class FinanceServiceImpl implements FinanceService {

    private IncomeRepository incomeRepository;
    private ExpenseRepository expenseRepository;
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
}
