package de.mosst.jpt.data;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

public class NoteDaoForGDataStore implements NoteDao {

	private static final String TEXT = "text";
	private static final String TITLE = "title";
	private static final String ID = "id";

	@Override
	public Note get(String id) throws NoteNotFoundException {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("note");
		Key key = keyFactory.newKey(id);
		Entity e = datastore.get(key);
		if (e == null) {
			throw new NoteNotFoundException(id);
		}

		return convertEntityToNote(e);
	}

	@Override
	public Note save(Note note) {

		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("note");
		Key key = keyFactory.newKey(note.getId());

		FullEntity<Key> noteEntity = Entity.newBuilder(key).set(ID, note.getId()).set(TITLE, note.getTitle()).set(TEXT, note.getText()).build();

		Entity entity = datastore.put(noteEntity);
		return convertEntityToNote(entity);
	}

	private Note convertEntityToNote(Entity e) {
		Note note = new Note(gV(e, ID), gV(e, TITLE), gV(e, TEXT));
		return note;
	}

	private String gV(Entity entity, String field) {
		return entity.getString(field);
	}
}
