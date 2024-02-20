package org.example.videojuegos_psp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videojuegos")
public class ControladorVideojuegos {
    @Autowired
    private RepoVideojuegos repoVideojuegos;

    @PostMapping
    public ResponseEntity<String> crearVideojuego(@RequestBody Videojuego videojuego) {
        repoVideojuegos.save(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body("Videojuego creado exitosamente");
    }

    public ResponseEntity<String> borrarVideojuego(@PathVariable Long id) {
        repoVideojuegos.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Videojuego eliminado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarVideojuego(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        videojuego.setId(id);
        repoVideojuegos.save(videojuego);
        return ResponseEntity.status(HttpStatus.OK).body("Videojuego modificado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerVideojuego(@PathVariable Long id) {
        Optional<Videojuego> videojuego = repoVideojuegos.findById(id);
        return videojuego.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> listarVideojuegos() {
         List<Videojuego> videojuegos = repoVideojuegos.findAll();
        return ResponseEntity.ok(videojuegos);
    }

    @GetMapping("/porCompania/{compania}")
    public ResponseEntity<Object> listarVideojuegosPorCompania(@PathVariable String compania) {
        List<Videojuego> videojuegos = repoVideojuegos.findByCompañia(compania);
        return ResponseEntity.ok(videojuegos);
    }
}

