package code;

/**
 * Vertex class representing a city in the flight routing system.
 * Each vertex contains city information and a reference to the first edge (flight route).
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class Vertex {
    private String cityName;        // Name of the city
    private Vertex nextVertex;      // Reference to next vertex in the graph
    private Edge firstEdge;         // Reference to first edge (flight route) from this city
    private int inDegree;          // Number of incoming flights
    private int outDegree;         // Number of outgoing flights
    
    /**
     * Constructor to create a new vertex (city)
     * @param cityName The name of the city
     */
    public Vertex(String cityName) {
        this.cityName = cityName;
        this.nextVertex = null;
        this.firstEdge = null;
        this.inDegree = 0;
        this.outDegree = 0;
    }
    
    // Getter methods
    public String getCityName() {
        return cityName;
    }
    
    public Vertex getNextVertex() {
        return nextVertex;
    }
    
    public Edge getFirstEdge() {
        return firstEdge;
    }
    
    public int getInDegree() {
        return inDegree;
    }
    
    public int getOutDegree() {
        return outDegree;
    }
    
    // Setter methods
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public void setNextVertex(Vertex nextVertex) {
        this.nextVertex = nextVertex;
    }
    
    public void setFirstEdge(Edge firstEdge) {
        this.firstEdge = firstEdge;
    }
    
    // Utility methods for degree management
    public void incrementInDegree() {
        this.inDegree++;
    }
    
    public void incrementOutDegree() {
        this.outDegree++;
    }
    
    public void decrementInDegree() {
        if (this.inDegree > 0) {
            this.inDegree--;
        }
    }
    
    public void decrementOutDegree() {
        if (this.outDegree > 0) {
            this.outDegree--;
        }
    }
    
    /**
     * Check if this vertex has any outgoing flights
     * @return true if there are outgoing flights, false otherwise
     */
    public boolean hasOutgoingFlights() {
        return firstEdge != null;
    }
    
    /**
     * Override toString method for easy printing
     * @return String representation of the vertex
     */
    @Override
    public String toString() {
        return "City: " + cityName + " (In: " + inDegree + ", Out: " + outDegree + ")";
    }
    
    /**
     * Override equals method for proper comparison
     * @param obj Object to compare with
     * @return true if the cities have the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return cityName != null ? cityName.equals(vertex.cityName) : vertex.cityName == null;
    }
    
    /**
     * Override hashCode method for proper hashing
     * @return hash code based on city name
     */
    @Override
    public int hashCode() {
        return cityName != null ? cityName.hashCode() : 0;
    }
} 