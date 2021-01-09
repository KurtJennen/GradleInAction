package chapter3.repository;

import chapter3.model.ToDoItem;

trait ToDoRepository {
    def findAll(): java.util.List[ToDoItem]
    def findById(id: java.lang.Long): ToDoItem
    def insert(toDoItem: ToDoItem): java.lang.Long
    def update(toDoItem: ToDoItem): Unit
    def delete(toDoItem: ToDoItem): Unit
}
