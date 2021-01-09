package repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import model.ToDoItem;

public class H2ToDoRepositoryIntegTest {
	private ToDoRepository h2ToDoRepository;

	@Before
	public void setUp() {
		h2ToDoRepository = new H2ToDoRepository();
	}

	@Test
	public void insertToDoItem() {
		ToDoItem newToDoItem = new ToDoItem();
		newToDoItem.setName("Write integration test");
		Long newId = h2ToDoRepository.insert(newToDoItem);
		assertNotNull(newId);
		
		ToDoItem persistedToDoItem = h2ToDoRepository.findById(newId);
		assertNotNull(persistedToDoItem);
		//assertEquals(newToDoItem, persistedToDoItem);
	}

}
