package org.example;
// Clase principal del programa

import java.util.Objects;

// Importaciones necesarias de Java

public class Vertex {
    private final String name;

    // Constructor de la clase Vertex
    public Vertex(String name) {
        this.name = name;
    }

    // Sobrescribe el método toString para obtener una representación legible del vértice
    @Override
    public String toString() {
        return name;
    }

    // Sobrescribe el método equals para comparar dos vértices por su nombre
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return name.equals(vertex.name);
    }

    // Sobrescribe el método hashCode para generar un código hash basado en el nombre del vértice
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

