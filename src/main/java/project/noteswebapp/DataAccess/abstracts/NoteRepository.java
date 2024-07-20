package project.noteswebapp.DataAccess.abstracts;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;
import project.noteswebapp.entities.db.Note;
import project.noteswebapp.entities.dto.NoteDTO;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByUuid(String uuid);

}
