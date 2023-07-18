package com.crudvideo.app.rest.Repository;

import com.crudvideo.app.rest.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task, Long> {
}
