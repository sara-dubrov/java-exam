package com.ngsoft.part1.services;

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


    @Override
    public long countAll() {
        return 0;
    }

    @Override
    public long countDone() {
        return 0;
    }

    @Override
    public long countItemsByUser(int userId) {
        return 0;
    }

    @Override
    public long countFiltered(Integer userId, Boolean isDone, String filter) {
        return 0;
    }
}
