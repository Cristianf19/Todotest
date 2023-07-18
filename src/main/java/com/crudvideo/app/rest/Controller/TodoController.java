package com.crudvideo.app.rest.Controller;

import com.crudvideo.app.rest.Model.Task;
import com.crudvideo.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/")
    public String holaMundo(){

        return "Hola Mundo!!!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return "Error en la solicitud: cuerpo de la solicitud no válido no se ha enviado ninguna task.";
    }


    @ExceptionHandler(value = {SQLException.class, SQLNonTransientConnectionException.class})
    public ResponseEntity<String> handleDatabaseConnectionException(Exception ex) {
        // Aquí puedes implementar el manejo de la excepción
        // Puedes devolver una respuesta personalizada, registrar el error, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de conexión a la base de datos");
    }

    // LEER
    @GetMapping(value = "/tasks")// ------------------------ Indica que nuestro método utilizará un método http de tipo GET al cual se podrá acceder mediante la url “/task”
    public List<Task> getTasks(){ // ----------------------- Indica que retornará una lista de tipo Task
        return todoRepository.findAll(); // ---------------- Busca todos los registros en nuestra tabla task, usando un metodo que está heredando del JpaRepository
    }

    @PostMapping(value = "/saveTask") // CREAR
    public ResponseEntity<String> saveTask(@RequestBody Task task){
        todoRepository.save(task);// Guarda en la base de datos nuestra variable task que contiene nuestro titulo y descripcion de la tarea
        return ResponseEntity.status(HttpStatus.OK).body("Save taskkkkkkk");

    }

    // EDITAR
    @PutMapping(value = "/update/{id}")
    public String updateTask(@PathVariable long id, @RequestBody Task task){ // Pide un id, y la nueva tarea que vamos a insertar la cual pide en modo de lista por que debe traer titulo y descripcion
        Task updatedTask = todoRepository.findById(id).get(); // -------------- busca en la base de datos usando el metodo finsById el campo que queremos modificar mediante el id que entregamos y lo asigna a la variable updateTask
        updatedTask.setTitle(task.getTitle()); // ----------------------------- modifica el titulo mediante el metodo que creamos previamente en nuestra clase Task
        updatedTask.setDescription(task.getDescription()); // ----------------- y hace lo mismo con la descripcion, usando un metodo que está heredando del JpaRepository
        todoRepository.save(updatedTask); // ---------------------------------- Ahora, guarda en la base de datos nuestra variable
        return "Updated Task";
    }

    // ELIMINAR
    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable long id){
        Task deletedTask = todoRepository.findById(id).get();// Busca en la base de datos el campo que queremos eliminar mediante el id que entregamos y lo asigna a la variable deletedTask
        todoRepository.delete(deletedTask); // ---------------- Borra el campo que trajo mediante el id, usando un metodo que está heredando del JpaRepository
        return "Deleted task";

    }



}
