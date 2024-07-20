package project.noteswebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import project.noteswebapp.DataAccess.abstracts.NoteRepository;
import project.noteswebapp.entities.db.Note;
import project.noteswebapp.entities.dto.NoteDTO;
import project.noteswebapp.services.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/all")
    public List<Note> getAllNotes(){
        return this.noteService.getAll();
    }

    @PostMapping("/all")
    public Note createNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @GetMapping("/favorites")
    public List<Note> getFavorites(){
        return noteService.getFavorites();
    }

    @GetMapping("/uuid/{uuid}")
    public NoteDTO getNoteByUuid(@PathVariable String uuid){
        return this.noteService.getNoteByUuid(uuid);
    }

    @DeleteMapping("/id/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public NoteDTO getNoteById(@PathVariable Long id){
        return this.noteService.getNoteById(id);
    }

    @PostMapping("/id/{id}")
    public void addToFavorites(@PathVariable Long id){
        noteService.addToFavorites(id);
    }

    @PostMapping("/favorites/id/{id}")
    public void removeFromFavorites(@PathVariable Long id){
        noteService.removeFromFavorites(id);
    }

   

}
