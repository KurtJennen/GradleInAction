package repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import model.ToDoItem;

public class InMemoryToDoRepositoryNGTest {
    private static ToDoRepository inMemoryToDoRepository;

    @BeforeClass
    public static void setUp() {
        inMemoryToDoRepository = new InMemoryToDoRepository();
    }

    @Test
    public void testToDoItem() {
        ToDoItem newToDoItem = new ToDoItem();
        newToDoItem.setName("Write unit tests");
        Long newId = inMemoryToDoRepository.insert(newToDoItem);
        assertNotNull(newId);

        ToDoItem persistedToDoItem = inMemoryToDoRepository.findById(newId);
        assertNotNull(persistedToDoItem);
        assertEquals(newToDoItem, persistedToDoItem);
    }
}