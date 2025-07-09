package code;

import java.util.*;

/**
 * FlightGraph class implementing a weighted directed graph using adjacency list representation.
 * This represents the flight network where cities are vertices and flight routes are edges.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class FlightGraph {
    private Vertex head;              // Head of the vertex linked list
    private int numberOfCities;       // Total number of cities in the graph
    
    /**
     * Constructor to create an empty flight graph
     */
    public FlightGraph() {
        this.head = null;
        this.numberOfCities = 0;
    }
    
    /**
     * Add a new city to the flight network
     * @param cityName Name of the city to add
     * @return true if city was added successfully, false if city already exists
     */
    public boolean addCity(String cityName) {
        // Check if city already exists
        if (hasCity(cityName)) {
            return false;
        }
        
        // Create new vertex for the city
        Vertex newCity = new Vertex(cityName);
        
        // Add to the beginning of the vertex list
        if (head == null) {
            head = newCity;
        } else {
            Vertex current = head;
            while (current.getNextVertex() != null) {
                current = current.getNextVertex();
            }
            current.setNextVertex(newCity);
        }
        
        numberOfCities++;
        return true;
    }
    
    /**
     * Add a flight route between two cities
     * @param fromCity Source city
     * @param toCity Destination city
     * @param cost Cost of the flight
     * @return true if flight was added successfully, false otherwise
     */
    public boolean addFlight(String fromCity, String toCity, int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Flight cost cannot be negative");
        }
        
        Vertex sourceVertex = getVertex(fromCity);
        Vertex destinationVertex = getVertex(toCity);
        
        // Both cities must exist
        if (sourceVertex == null || destinationVertex == null) {
            return false;
        }
        
        // Check if flight already exists (avoid duplicates)
        if (hasDirectFlight(fromCity, toCity)) {
            return false;
        }
        
        // Create new edge and add to the source vertex's adjacency list
        Edge newFlight = new Edge(destinationVertex, cost);
        newFlight.setNextEdge(sourceVertex.getFirstEdge());
        sourceVertex.setFirstEdge(newFlight);
        
        // Update degrees
        sourceVertex.incrementOutDegree();
        destinationVertex.incrementInDegree();
        
        return true;
    }
    
    /**
     * Check if a city exists in the flight network
     * @param cityName Name of the city to check
     * @return true if city exists, false otherwise
     */
    public boolean hasCity(String cityName) {
        return getVertex(cityName) != null;
    }
    
    /**
     * Check if there's a direct flight between two cities
     * @param fromCity Source city
     * @param toCity Destination city
     * @return true if direct flight exists, false otherwise
     */
    public boolean hasDirectFlight(String fromCity, String toCity) {
        Vertex sourceVertex = getVertex(fromCity);
        if (sourceVertex == null) {
            return false;
        }
        
        Edge currentEdge = sourceVertex.getFirstEdge();
        while (currentEdge != null) {
            if (currentEdge.getDestinationCity().getCityName().equals(toCity)) {
                return true;
            }
            currentEdge = currentEdge.getNextEdge();
        }
        
        return false;
    }
    
    /**
     * Get the cost of a direct flight between two cities
     * @param fromCity Source city
     * @param toCity Destination city
     * @return Flight cost, or -1 if no direct flight exists
     */
    public int getFlightCost(String fromCity, String toCity) {
        Vertex sourceVertex = getVertex(fromCity);
        if (sourceVertex == null) {
            return -1;
        }
        
        Edge currentEdge = sourceVertex.getFirstEdge();
        while (currentEdge != null) {
            if (currentEdge.getDestinationCity().getCityName().equals(toCity)) {
                return currentEdge.getFlightCost();
            }
            currentEdge = currentEdge.getNextEdge();
        }
        
        return -1; // No direct flight found
    }
    
    /**
     * Get all cities that can be reached directly from a given city
     * @param fromCity Source city
     * @return List of destination cities
     */
    public List<String> getAllDestinations(String fromCity) {
        List<String> destinations = new ArrayList<>();
        Vertex sourceVertex = getVertex(fromCity);
        
        if (sourceVertex == null) {
            return destinations; // Return empty list if city doesn't exist
        }
        
        Edge currentEdge = sourceVertex.getFirstEdge();
        while (currentEdge != null) {
            destinations.add(currentEdge.getDestinationCity().getCityName());
            currentEdge = currentEdge.getNextEdge();
        }
        
        return destinations;
    }
    
    /**
     * Get all cities in the flight network
     * @return List of all city names
     */
    public List<String> getAllCities() {
        List<String> cities = new ArrayList<>();
        Vertex current = head;
        
        while (current != null) {
            cities.add(current.getCityName());
            current = current.getNextVertex();
        }
        
        return cities;
    }
    
    /**
     * Get a vertex by city name
     * @param cityName Name of the city
     * @return Vertex object if found, null otherwise
     */
    protected Vertex getVertex(String cityName) {
        Vertex current = head;
        while (current != null) {
            if (current.getCityName().equals(cityName)) {
                return current;
            }
            current = current.getNextVertex();
        }
        return null;
    }
    
    /**
     * Get the number of cities in the network
     * @return Number of cities
     */
    public int getNumberOfCities() {
        return numberOfCities;
    }
    
    /**
     * Print the entire flight network
     */
    public void printFlightNetwork() {
        System.out.println("=== Flight Network ===");
        Vertex current = head;
        
        while (current != null) {
            System.out.print(current.getCityName() + " → ");
            
            Edge edge = current.getFirstEdge();
            if (edge == null) {
                System.out.println("No outgoing flights");
            } else {
                boolean first = true;
                while (edge != null) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(edge.getDestinationName() + "($" + edge.getFlightCost() + ")");
                    edge = edge.getNextEdge();
                    first = false;
                }
                System.out.println();
            }
            
            current = current.getNextVertex();
        }
        System.out.println("Total cities: " + numberOfCities);
    }
    
    /**
     * Check if the graph is empty
     * @return true if no cities exist, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Clear all cities and flights from the network
     */
    public void clear() {
        head = null;
        numberOfCities = 0;
    }
} 