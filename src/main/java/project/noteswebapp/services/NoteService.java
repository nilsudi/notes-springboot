package project.noteswebapp.services;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import project.noteswebapp.DataAccess.abstracts.NoteRepository;
import project.noteswebapp.entities.db.Note;
import project.noteswebapp.entities.dto.NoteDTO;
import project.noteswebapp.utils.modelmapper.ModelMapperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final ModelMapperManager modelMapper;

    public NoteService(NoteRepository noteRepository, ModelMapperManager modelMapper) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }

    public List<Note> getAll(){
        return this.noteRepository.findAll();
    }

    public NoteDTO getNoteByUuid(String uuid){
        Note note = this.noteRepository.findByUuid(uuid);
        return this.modelMapper.forResponse().map(note,NoteDTO.class);

    }

    public NoteDTO getNoteById(Long id){
        Optional<Note> note = this.noteRepository.findById(id);
        return this.modelMapper.forResponse().map(note,NoteDTO.class);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> getFavorites() {
        List<Note> favoriteNotes = new ArrayList<>();
        for (Note note : noteRepository.findAll()) {
            if (note.isFavorite) {
                favoriteNotes.add(note);
            }
        }
        return favoriteNotes;
    }

    public void addToFavorites(Long id){
        Note note = noteRepository.findById(id).orElse(null);
        if (note != null){
            note.isFavorite = true;
            noteRepository.save(note);
        }else {
            throw new RuntimeException("cannot find note with id : "+id);
        }
    }

    public void removeFromFavorites(Long id){
        Note note = noteRepository.findById(id).orElse(null);
        if (note != null){
            note.isFavorite = false;
            noteRepository.save(note);
        }else {
            throw new RuntimeException("cannot find note with id : "+id);
        }
    }
}
