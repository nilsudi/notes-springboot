package project.noteswebapp.entities.dto;


import lombok.Data;

import java.util.Date;

@Data
public class NoteDTO {

    public Long id;

    public String title;

    public String content;

    public Date date;

    public boolean isFavorite;

}
