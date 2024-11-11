package com.snippet.snippet.controller;


import com.snippet.snippet.model.Snippet;
import com.snippet.snippet.repository.SnippetRepository;
import com.snippet.snippet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snippet")
public class SnippetController {

    @Autowired
    private SnippetRepository snippetRepository;

    @Autowired
    private UserRepository userRepository;

    // POST /snippet - Create a new snippet
    @PostMapping
    public ResponseEntity<Snippet> createSnippet(@RequestParam Long userId, @RequestBody Snippet snippet) {
        return userRepository.findById(userId).map(user -> {
            snippet.setUser(user);
            Snippet savedSnippet = snippetRepository.save(snippet);
            return ResponseEntity.ok(savedSnippet);
        }).orElse(ResponseEntity.notFound().build());
    }

    // GET /snippet - Get all snippets
    @GetMapping
    public List<Snippet> getAllSnippets(@RequestParam(value = "lang", required = false) String language) {
        if (language != null) {
            return snippetRepository.findByLanguage(language);
        }
        return snippetRepository.findAll();
    }

    // GET /snippet/{id} - Get a snippet by ID
    @GetMapping("/{id}")
    public ResponseEntity<Snippet> getSnippetById(@PathVariable Long id) {
        Optional<Snippet> snippet = snippetRepository.findById(id);
        return snippet.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

