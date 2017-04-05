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

@Controller
@RequestMapping("/api/notes")
public class NoteController {

	@Inject
	private NoteDao noteDao;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Note getNote(@PathVariable("id") String id) {
		System.out.println("get: " + id);
		return noteDao.get(id);
	}

	@RequestMapping(value = { "/create", "/update" }, method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public Note saveNote(@RequestBody Note note) {
		System.out.println("save: " + note);
		return noteDao.save(note);
	}

}
