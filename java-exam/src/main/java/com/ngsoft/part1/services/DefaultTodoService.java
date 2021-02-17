package com.ngsoft.part1.services;

import com.ngsoft.part1.dao.TodoDao;
import com.ngsoft.part1.pojos.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * This emulates a service layer that performs data statistics on a data source of {@link com.ngsoft.part1.pojos.TodoItem}s.
 * Whomever is going to consume this service (emulated here by unit test) is not going to know of this specific implementation
 * it will only be aware of the interface {@link TodoService}
 * The data source here is provided by an implementation of TodoDao interface that is already configured by spring.
 * please inject this spring bean.
 * For simplicity the only thing we need to do is to count items.
 * Please:
 * 1. Configure spring xml properly.
 * 2. Fix all the methods to work.
 * If you do both properly the TodoTest unit test will pass.
 *
 * Extra points for code reuse (hint: start with the hardest to implement).
 * Extra points for simple and readable code.
 */
public class DefaultTodoService implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public long countAll() {
        return getTodos().size();
    }

    @Override
    public long countDone() {
        List<TodoItem> todos = getTodos();
        return countByPredicate(todos, getDonePredicate(true));
    }

    @Override
    public long countItemsByUser(int userId) {
        List<TodoItem> todos = getTodos();
        return countByPredicate(todos, getUserIdPredicate(userId));
    }

    @Override
    public long countFiltered(Integer userId, Boolean isDone, String filter) {
        List<Predicate<TodoItem>> predicates = new ArrayList<>();
        if (userId != null) {
            predicates.add(getUserIdPredicate(userId));
        }
        if (isDone != null) {
            predicates.add(getDonePredicate(isDone));
        }
        if (filter != null) {
            predicates.add(getFilterPredicate(filter));
        }

        List<TodoItem> todos = getTodos();
        return countByPredicates(todos, predicates);
    }

    private List<TodoItem> getTodos() {
        try {
            return todoDao.getTodos();
        } catch (FileNotFoundException | URISyntaxException e) {
            throw new RuntimeException("An error occurred while retrieving the todo items");
        }
    }

    private Predicate<TodoItem> getUserIdPredicate(int userId) {
        return todoItem -> todoItem.getUserId() == userId;
    }

    private Predicate<TodoItem> getDonePredicate(boolean isDone) {
        return todoItem -> todoItem.isCompleted().equals(isDone);
    }

    private Predicate<TodoItem> getFilterPredicate(String filter) {
        return todoItem -> todoItem.getTitle().contains(filter);
    }

    private long countByPredicate(List<TodoItem> todoItems, Predicate<TodoItem> predicate) {
        return countByPredicates(todoItems, Collections.singletonList(predicate));
    }

    private long countByPredicates(List<TodoItem> todoItems, List<Predicate<TodoItem>> predicates) {
        return todoItems.stream()
                .filter(predicates.stream().reduce(i -> true, Predicate::and))
                .count();
    }

}
