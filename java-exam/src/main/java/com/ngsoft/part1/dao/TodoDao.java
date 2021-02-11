package com.ngsoft.part1.dao;

import com.ngsoft.part1.pojos.TodoItem;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public interface TodoDao {

    List<TodoItem> getTodos() throws FileNotFoundException, URISyntaxException;

}
