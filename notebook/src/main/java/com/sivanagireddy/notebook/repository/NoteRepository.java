package com.sivanagireddy.notebook.repository;

import com.sivanagireddy.notebook.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
