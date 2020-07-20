package com.sivanagireddy.notebook;

import com.sivanagireddy.notebook.config.NotebookProperties;
import com.sivanagireddy.notebook.domain.Note;
import com.sivanagireddy.notebook.repository.NoteRepository;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class NoteController {
    private NoteRepository noteRepository;
    private Parser parser = Parser.builder().build();
    private Renderer renderer = HtmlRenderer.builder().build();
    @Autowired
    private NotebookProperties properties;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        getAllNotes(model);
        return "index";
    }

    @PostMapping("/note")
    public String saveNote(@RequestParam("image") MultipartFile file,
                           @RequestParam(defaultValue = "") String description,
                           @RequestParam(required = false, defaultValue = "") String publish,
                           @RequestParam(required = false) String upload,
                           Model model) throws Exception {
        if(publish.equals("Publish")) {
            saveNote(description, model);
            getAllNotes(model);
            return "redirect:/";
        }
        if (upload != null && upload.equals("Upload")) {
            if (file != null && file.getOriginalFilename() != null &&
                    !file.getOriginalFilename().isEmpty()) {
                uploadImage(file, description, model);
            }
            getAllNotes(model);
            return "index";
        }
        return "index";
    }

    private void getAllNotes(Model model) {
        List<Note> notes = noteRepository.findAll();
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
    }

    private void saveNote(String description, Model model) {
        if (description != null && !description.trim().isEmpty()) {
            Node document = parser.parse(description.trim());
            String html = renderer.render(document);
            noteRepository.save(new Note(null, html));
            model.addAttribute("description", "");
        }
    }

    private void uploadImage(MultipartFile file, String description, Model model) throws Exception {
        File uploadsDir = new File(properties.getUploadDir());
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
        String fileId = UUID.randomUUID().toString() + "."
                + file.getOriginalFilename().split("\\.")[1];
        file.transferTo(new File(properties.getUploadDir() + fileId));
        model.addAttribute("description", description + " ![](/uploads/" + fileId + ")");
    }

}
