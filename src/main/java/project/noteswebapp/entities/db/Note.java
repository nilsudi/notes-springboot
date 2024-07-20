package project.noteswebapp.entities.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.yaml.snakeyaml.events.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Table(name = "notes")
@Data
@AllArgsConstructor
@Entity
public class Note {

    @Column(name = "uuid")
    private String uuid;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "creation_date")
    private String date;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_favorite")
    public boolean isFavorite;

    public Note(){
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    @PrePersist
    void date() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date = formatter.format(new Date());
    }
}
