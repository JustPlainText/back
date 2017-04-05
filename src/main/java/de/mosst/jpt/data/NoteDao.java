package de.mosst.jpt.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class NoteDao {

	private Map<String, Note> notes = new HashMap<>();

	public Note get(String id) {
		Note note = notes.get(id);
		if (note == null) {
			String title = "Created note";
			note = new Note(title, id, "0");
			notes.put(id, note);
		}
		int count = Integer.parseInt(note.getText());
		note.setText("" + ++count);
		return note;
	}

	public Note save(Note note) {
		notes.put(note.getId(), note);
		return get(note.getId());
	}

}
