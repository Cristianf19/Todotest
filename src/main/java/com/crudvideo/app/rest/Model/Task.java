package com.crudvideo.app.rest.Model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(schema = "TasksCrud") // Le damos nombre a la base de datos
public class Task { // Nuestra tabla tomara el nombre de la clase mediante la cual es creada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // hacemos que este campo se genere autoincrementandose, iniciando desde el 1
    private long id;

    @Column
    private String title;       // Marcamos este valor como un campo de nuestra tabla

    @Column
    private String description; // Marcamos este valor como un campo de nuestra tabla




    // GETTERS
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }



    // SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
