package org.example.videojuegos_psp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoVideojuegos extends JpaRepository<Videojuego, Long> {
    List<Videojuego> findByCompañia(String compañia);
}



