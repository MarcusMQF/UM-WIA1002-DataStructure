package code;

/**
 * Edge class representing a flight route in the flight routing system.
 * Each edge contains destination city, flight cost, and reference to next edge.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class Edge {
    private Vertex destinationCity;  // Destination city (vertex)
    private int flightCost;         // Cost of the flight
    private Edge nextEdge;          // Reference to next edge from the same source city
    
    /**
     * Constructor to create a new edge (flight route)
     * @param destinationCity The destination city
     * @param flightCost The cost of the flight
     */
    public Edge(Vertex destinationCity, int flightCost) {
        this.destinationCity = destinationCity;
        this.flightCost = flightCost;
        this.nextEdge = null;
    }
    
    // Getter methods
    public Vertex getDestinationCity() {
        return destinationCity;
    }
    
    public int getFlightCost() {
        return flightCost;
    }
    
    public Edge getNextEdge() {
        return nextEdge;
    }
    
    // Setter methods
    public void setDestinationCity(Vertex destinationCity) {
        this.destinationCity = destinationCity;
    }
    
    public void setFlightCost(int flightCost) {
        if (flightCost >= 0) {
            this.flightCost = flightCost;
        } else {
            throw new IllegalArgumentException("Flight cost cannot be negative");
        }
    }
    
    public void setNextEdge(Edge nextEdge) {
        this.nextEdge = nextEdge;
    }
    
    /**
     * Get the name of the destination city
     * @return String name of the destination city
     */
    public String getDestinationName() {
        return destinationCity != null ? destinationCity.getCityName() : "Unknown";
    }
    
    /**
     * Check if this edge has a valid destination
     * @return true if destination is not null, false otherwise
     */
    public boolean hasValidDestination() {
        return destinationCity != null;
    }
    
    /**
     * Override toString method for easy printing
     * @return String representation of the edge
     */
    @Override
    public String toString() {
        return "Flight to " + getDestinationName() + " (Cost: $" + flightCost + ")";
    }
    
    /**
     * Override equals method for proper comparison
     * @param obj Object to compare with
     * @return true if edges have same destination and cost, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return flightCost == edge.flightCost && 
               destinationCity != null ? destinationCity.equals(edge.destinationCity) : 
               edge.destinationCity == null;
    }
    
    /**
     * Override hashCode method for proper hashing
     * @return hash code based on destination and cost
     */
    @Override
    public int hashCode() {
        int result = destinationCity != null ? destinationCity.hashCode() : 0;
        result = 31 * result + flightCost;
        return result;
    }
} 