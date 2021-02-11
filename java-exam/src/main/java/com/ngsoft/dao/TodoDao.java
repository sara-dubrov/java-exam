package com.ngsoft.dao;

import com.ngsoft.pojos.TodoItem;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public interface TodoDao {

    List<TodoItem> getTodos() throws FileNotFoundException, URISyntaxException;

}
