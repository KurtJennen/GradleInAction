package repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import model.ToDoItem;

public class InMemoryToDoRepositoryTest {
	private ToDoRepository inMemoryToDoRepository;

	@Before
	public void setUp() {
		inMemoryToDoRepository = new InMemoryToDoRepository();
	}

	@Test
	public void insertToDoItem() {
		ToDoItem newToDoItem = new ToDoItem();
		newToDoItem.setName("Write unit test");
		Long newId = inMemoryToDoRepository.insert(newToDoItem);
		assertNotNull(newId);
		
		ToDoItem persistedToDoItem = inMemoryToDoRepository.findById(newId);
		assertNotNull(persistedToDoItem);
		assertEquals(newToDoItem, persistedToDoItem);
	}

}
