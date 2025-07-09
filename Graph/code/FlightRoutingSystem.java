package code;

import java.util.*;

/**
 * FlightRoutingSystem class implementing various path-finding algorithms
 * for the flight routing system. Contains DFS for any path, BFS for shortest path
 * (in terms of hops), and Dijkstra for cheapest path.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class FlightRoutingSystem {
    private FlightGraph flightGraph;
    
    /**
     * Constructor
     * @param flightGraph The flight network graph
     */
    public FlightRoutingSystem(FlightGraph flightGraph) {
        this.flightGraph = flightGraph;
    }
    
    /**
     * Find ANY route between two cities using DFS (Depth-First Search)
     * DFS explores as far as possible along each branch before backtracking
     * @param fromCity Source city
     * @param toCity Destination city
     * @return List representing the route, empty if no route exists
     */
    public List<String> findAnyRoute(String fromCity, String toCity) {
        // Validate input cities
        if (!flightGraph.hasCity(fromCity) || !flightGraph.hasCity(toCity)) {
            return new ArrayList<>();
        }
        
        // If source and destination are the same
        if (fromCity.equals(toCity)) {
            return Arrays.asList(fromCity);
        }
        
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        
        // Start DFS
        if (dfsHelper(fromCity, toCity, visited, path)) {
            return new ArrayList<>(path);
        }
        
        return new ArrayList<>(); // No route found
    }
    
    /**
     * Helper method for DFS recursive implementation
     * @param currentCity Current city being visited
     * @param targetCity Destination city
     * @param visited Set of visited cities
     * @param path Current path being built
     * @return true if route found, false otherwise
     */
    private boolean dfsHelper(String currentCity, String targetCity, 
                             Set<String> visited, List<String> path) {
        visited.add(currentCity);
        path.add(currentCity);
        
        // Check if we reached the target
        if (currentCity.equals(targetCity)) {
            return true;
        }
        
        // Explore all neighbors
        List<String> neighbors = flightGraph.getAllDestinations(currentCity);
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (dfsHelper(neighbor, targetCity, visited, path)) {
                    return true;
                }
            }
        }
        
        // Backtrack if no route found through this path
        path.remove(path.size() - 1);
        return false;
    }
    
    /**
     * Find shortest route (minimum hops) using BFS (Breadth-First Search)
     * BFS explores level by level, guaranteeing shortest path in unweighted graphs
     * @param fromCity Source city
     * @param toCity Destination city
     * @return List representing the shortest route, empty if no route exists
     */
    public List<String> findShortestRoute(String fromCity, String toCity) {
        // Validate input cities
        if (!flightGraph.hasCity(fromCity) || !flightGraph.hasCity(toCity)) {
            return new ArrayList<>();
        }
        
        // If source and destination are the same
        if (fromCity.equals(toCity)) {
            return Arrays.asList(fromCity);
        }
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        
        // Initialize BFS
        queue.offer(fromCity);
        visited.add(fromCity);
        parent.put(fromCity, null);
        
        // BFS traversal
        while (!queue.isEmpty()) {
            String currentCity = queue.poll();
            
            // Get all destinations from current city
            List<String> neighbors = flightGraph.getAllDestinations(currentCity);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, currentCity);
                    queue.offer(neighbor);
                    
                    // Check if we reached the target
                    if (neighbor.equals(toCity)) {
                        return reconstructPath(parent, fromCity, toCity);
                    }
                }
            }
        }
        
        return new ArrayList<>(); // No route found
    }
    
    /**
     * Find cheapest route using modified Dijkstra's algorithm
     * Uses priority queue to always process the lowest cost path first
     * @param fromCity Source city
     * @param toCity Destination city
     * @return RouteInfo containing the cheapest route and total cost
     */
    public RouteInfo findCheapestRoute(String fromCity, String toCity) {
        // Validate input cities
        if (!flightGraph.hasCity(fromCity) || !flightGraph.hasCity(toCity)) {
            return new RouteInfo(new ArrayList<>(), -1);
        }
        
        // If source and destination are the same
        if (fromCity.equals(toCity)) {
            return new RouteInfo(Arrays.asList(fromCity), 0);
        }
        
        // Dijkstra's algorithm implementation
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        PriorityQueue<CityDistance> pq = new PriorityQueue<>(
            Comparator.comparingInt(cd -> cd.distance)
        );
        
        // Initialize distances
        for (String city : flightGraph.getAllCities()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(fromCity, 0);
        
        pq.offer(new CityDistance(fromCity, 0));
        parent.put(fromCity, null);
        
        while (!pq.isEmpty()) {
            CityDistance current = pq.poll();
            String currentCity = current.city;
            int currentDistance = current.distance;
            
            // If we've already found a better path, skip
            if (currentDistance > distances.get(currentCity)) {
                continue;
            }
            
            // Check if we reached the target
            if (currentCity.equals(toCity)) {
                List<String> path = reconstructPath(parent, fromCity, toCity);
                return new RouteInfo(path, currentDistance);
            }
            
            // Explore neighbors
            List<String> neighbors = flightGraph.getAllDestinations(currentCity);
            for (String neighbor : neighbors) {
                int flightCost = flightGraph.getFlightCost(currentCity, neighbor);
                int newDistance = currentDistance + flightCost;
                
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    parent.put(neighbor, currentCity);
                    pq.offer(new CityDistance(neighbor, newDistance));
                }
            }
        }
        
        return new RouteInfo(new ArrayList<>(), -1); // No route found
    }
    
    /**
     * Find all possible routes between two cities using DFS with path tracking
     * @param fromCity Source city
     * @param toCity Destination city
     * @return List of all possible routes with their costs
     */
    public List<RouteInfo> findAllRoutes(String fromCity, String toCity) {
        // Validate input cities
        if (!flightGraph.hasCity(fromCity) || !flightGraph.hasCity(toCity)) {
            return new ArrayList<>();
        }
        
        List<RouteInfo> allRoutes = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> currentPath = new ArrayList<>();
        
        findAllRoutesHelper(fromCity, toCity, visited, currentPath, 0, allRoutes);
        
        // Sort routes by cost
        allRoutes.sort(Comparator.comparingInt(route -> route.totalCost));
        
        return allRoutes;
    }
    
    /**
     * Helper method for finding all routes using DFS
     * @param currentCity Current city being visited
     * @param targetCity Destination city
     * @param visited Set of visited cities in current path
     * @param currentPath Current path being built
     * @param currentCost Current total cost
     * @param allRoutes List to store all found routes
     */
    private void findAllRoutesHelper(String currentCity, String targetCity,
                                   Set<String> visited, List<String> currentPath,
                                   int currentCost, List<RouteInfo> allRoutes) {
        visited.add(currentCity);
        currentPath.add(currentCity);
        
        // If we reached the target, save this route
        if (currentCity.equals(targetCity)) {
            allRoutes.add(new RouteInfo(new ArrayList<>(currentPath), currentCost));
        } else {
            // Continue exploring neighbors
            List<String> neighbors = flightGraph.getAllDestinations(currentCity);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    int flightCost = flightGraph.getFlightCost(currentCity, neighbor);
                    findAllRoutesHelper(neighbor, targetCity, visited, currentPath,
                                      currentCost + flightCost, allRoutes);
                }
            }
        }
        
        // Backtrack
        visited.remove(currentCity);
        currentPath.remove(currentPath.size() - 1);
    }
    
    /**
     * Helper method to reconstruct path from parent mapping
     * @param parent Parent mapping from BFS/Dijkstra
     * @param start Starting city
     * @param end Ending city
     * @return List representing the path
     */
    private List<String> reconstructPath(Map<String, String> parent, String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;
        
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }
        
        Collections.reverse(path);
        return path;
    }
    
    /**
     * Calculate total cost of a given route
     * @param route List of cities in the route
     * @return Total cost, or -1 if route is invalid
     */
    public int calculateRouteCost(List<String> route) {
        if (route == null || route.size() < 2) {
            return 0;
        }
        
        int totalCost = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            int segmentCost = flightGraph.getFlightCost(route.get(i), route.get(i + 1));
            if (segmentCost == -1) {
                return -1; // Invalid route
            }
            totalCost += segmentCost;
        }
        
        return totalCost;
    }
    
    /**
     * Inner class to represent city with distance for Dijkstra's algorithm
     */
    private static class CityDistance {
        String city;
        int distance;
        
        CityDistance(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }
    
    /**
     * Inner class to represent route information with path and cost
     */
    public static class RouteInfo {
        private List<String> route;
        private int totalCost;
        
        public RouteInfo(List<String> route, int totalCost) {
            this.route = route;
            this.totalCost = totalCost;
        }
        
        public List<String> getRoute() {
            return route;
        }
        
        public int getTotalCost() {
            return totalCost;
        }
        
        public boolean isValid() {
            return !route.isEmpty() && totalCost >= 0;
        }
        
        @Override
        public String toString() {
            if (!isValid()) {
                return "No route available";
            }
            return String.join(" → ", route) + " (Cost: $" + totalCost + ")";
        }
    }
} 