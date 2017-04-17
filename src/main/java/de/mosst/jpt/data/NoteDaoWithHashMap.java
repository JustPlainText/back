package de.mosst.jpt.data;

import java.util.HashMap;
import java.util.Map;

public class NoteDaoWithHashMap implements NoteDao {

	Map<String, Note> notes = new HashMap<>();

	@Override
	public Note get(String id) throws NoteNotFoundException {
		return notes.get(id);
	}

	@Override
	public Note save(Note note) {
		notes.put(note.getId(), note);
		return notes.get(note.getId());
	}

}
