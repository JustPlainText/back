package de.mosst.jpt.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mosst.jpt.data.Note;
import de.mosst.jpt.data.NoteDao;
import de.mosst.jpt.data.NoteNotFoundException;

@Controller
@RequestMapping("/api/notes")
public class NoteController {

	@Inject
	private NoteDao noteDao;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Note getNote(@PathVariable("id") String id) throws NoteNotFoundException {
		System.out.println("get: " + id);
		return noteDao.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Note saveNote(@RequestBody Note note) {
		System.out.println("save: " + note);
		return noteDao.save(note);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Note updateNote(@PathVariable("id") String id, @RequestBody Note note) {
		System.out.println("update: " + note);
		if (note.getId() == null) {
			note.setId(id);
			System.out.println("update: " + note);
		}
		return noteDao.save(note);
	}

}
