package de.mosst.jpt.data;

import org.apache.log4j.Logger;

import com.google.cloud.datastore.Blob;
import com.google.cloud.datastore.BlobValue;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreException;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

public class NoteDaoForGDataStore implements NoteDao {

	Logger LOG = Logger.getLogger(NoteDaoForGDataStore.class);

	private static final String TEXT = "text";
	private static final String TITLE = "title";
	private static final String ID = "id";
	private static final String ENCRYPTED = "encrypted";

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

		Blob blob = Blob.copyFrom(note.getText().getBytes());
		BlobValue text = BlobValue.newBuilder(blob).setExcludeFromIndexes(true).build();

		FullEntity<Key> noteEntity = Entity.newBuilder(key).set(ID, note.getId()).set(TITLE, note.getTitle()).set(TEXT, text).set(ENCRYPTED, note.isEncrypted())
				.build();

		Entity entity = datastore.put(noteEntity);
		return convertEntityToNote(entity);
	}

	private Note convertEntityToNote(Entity e) {
		boolean encrypted = false;
		try {
			encrypted = e.getBoolean(ENCRYPTED);
		} catch (DatastoreException ex) {
			LOG.info("Note hat keine Property 'encryption' ~ v1");
		}
		String text;
		try {
			text = new String(e.getBlob(TEXT).toByteArray());
		} catch (ClassCastException ex) {
			LOG.info("Text is not Blob");
			text = gV(e, TEXT);
		}

		Note note = new Note(gV(e, ID), gV(e, TITLE), text, encrypted);
		return note;
	}

	private String gV(Entity entity, String field) {
		return entity.getString(field);
	}

}
