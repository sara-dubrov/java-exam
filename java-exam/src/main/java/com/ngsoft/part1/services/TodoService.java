package com.ngsoft.part1.services;

public interface TodoService {
    /**
     * This method should count all the items in the list
     * @return number of elements
     */
    long countAll();

    /**
     * This method should count all the completed todos in a given list
     * @return the number of completed todos
     */
    long countDone();

    /**
     * This method should count all tods for a given user
     * @param userId
     * @return the number of todos for a specific user
     */
    long countItemsByUser(int userId);

    /*
            filter the list passed by the other arguments. If no value is passed for any argument
            then it should no be taken into account
        */

    /**
     *
     * @param userId the id of the user - use null to not filter by user
     * @param isDone the value of completed boolean - use null to not filter
     * @param filter a substring to look within the todo item title
     * @return
     */
    long countFiltered(Integer userId, Boolean isDone, String filter);
}
