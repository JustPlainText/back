package de.mosst.jpt.data;

public interface NoteDao {

	Note get(String id) throws NoteNotFoundException;

	Note save(Note note);

}
