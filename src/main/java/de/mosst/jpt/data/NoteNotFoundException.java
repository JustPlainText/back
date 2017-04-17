package de.mosst.jpt.data;

public class NoteNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String noteId;

	public NoteNotFoundException(String noteId) {
		this.noteId = noteId;
	}

	@Override
	public String getMessage() {
		return "Note cannot be found id: " + noteId;
	}

}
