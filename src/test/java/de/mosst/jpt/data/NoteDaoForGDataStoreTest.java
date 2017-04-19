package de.mosst.jpt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class NoteDaoForGDataStoreTest {

	private static final String UUID = "62d85b2d-0557-4a54-b2f7-ebcfc132cb5e";
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		System.setProperty("DATASTORE_PROJECT_ID", "jpt-21");
		// System.setProperty("DATASTORE_HOST", "http://localhost:8081");
		// System.setProperty("DATASTORE_EMULATOR_HOST_PATH", "localhost:8081/datastore");
		// System.setProperty("DATASTORE_EMULATOR_HOST", "localhost:8081");
		// System.setProperty("DATASTORE_DATASET", "jpt-21");
		// System.out.println("n env: " + System.getenv().get("GOOGLE_APPLICATION_CREDENTIALS"));
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testGet() throws NoteNotFoundException {
		NoteDaoForGDataStore dao = new NoteDaoForGDataStore();
		Note note = new Note(UUID, "test-get", "text-get", false);
		dao.save(note);
		Note newNote = dao.get(UUID);
		System.out.println(newNote);
	}

	// @Test
	// public void testSave() {
	// NoteDao dao = new NoteDao();
	// Note note = new Note(UUID, "test-save", "text-save");
	// Note newNote = dao.save(note);
	// System.out.println(newNote);
	// }

}
