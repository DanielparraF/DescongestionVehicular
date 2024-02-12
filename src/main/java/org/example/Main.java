package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear el grafo
        Graph<Vertex, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Mapa para almacenar los vértices
        Map<String, Vertex> vertexMap = new HashMap<>();

        // Pedir al usuario que ingrese los vértices(estaciones) y agregarlos al grafo
        System.out.println("Ingrese los nombres de las estaciones de transporte urbano separadas por espacios: (ejemplo A B C D)");
        String input = scanner.nextLine().trim();
        for (String vertexName : input.split("\\s+")) {
            Vertex vertex = new Vertex(vertexName);
            graph.addVertex(vertex);
            vertexMap.put(vertexName, vertex);
        }

        // Pedir al usuario que ingrese las aristas(que son las distacias) y sus tiempos de duración entre cada estacion
        System.out.println("Ingrese las rutas en el formato: estación_inicio estación_fin tiempo_de_viaje (por ejemplo, A B 10):");
        System.out.println("Escriba 'fin' cuando haya terminado.");
        while (true) {
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("fin")) {
                break;
            }
            String[] parts = input.split("\\s+");
            String sourceName = parts[0];
            String targetName = parts[1];
            double weight = Double.parseDouble(parts[2]);
            Vertex source = vertexMap.get(sourceName);
            Vertex target = vertexMap.get(targetName);
            DefaultWeightedEdge edge = graph.addEdge(source, target);
            graph.setEdgeWeight(edge, weight);
        }

        // Pedir al usuario que ingrese el inicio y el fin de la ruta más corta
        System.out.println("Ingrese el nombre de la estación de inicio:");
        String startName = scanner.nextLine().trim();
        System.out.println("Ingrese el nombre de la estación de destino:");
        String endName = scanner.nextLine().trim();

        // Obtener los vértices(estaciones) de inicio y fin
        Vertex startVertex = vertexMap.get(startName);
        Vertex endVertex = vertexMap.get(endName);

        // Calcular la ruta más corta utilizando el algoritmo de Dijkstra
        DijkstraShortestPath<Vertex, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        List<Vertex> shortestPath = dijkstraAlg.getPath(startVertex, endVertex).getVertexList();
        double shortestDistance = dijkstraAlg.getPathWeight(startVertex, endVertex);

        // Imprimir la ruta más corta y la distancia más corta
        System.out.println("La ruta más corta de " + startName + " a " + endName + " es: " + shortestPath);
        System.out.println("La distancia más corta de " + startName + " a " + endName + " es: " + shortestDistance);
    }
}


