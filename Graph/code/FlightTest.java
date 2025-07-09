package code;

import java.util.*;

/**
 * Test program for the Flight Routing System
 * Demonstrates all functionality including graph operations and pathfinding algorithms
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class FlightTest {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("        FLIGHT ROUTING SYSTEM TEST PROGRAM");
        System.out.println("=".repeat(60));
        
        // Create flight network
        FlightGraph flightNetwork = new FlightGraph();
        FlightRoutingSystem routingSystem = new FlightRoutingSystem(flightNetwork);
        
        // Test 1: Build the flight network
        System.out.println("\n1. BUILDING FLIGHT NETWORK");
        System.out.println("-".repeat(40));
        
        // Add cities
        String[] cities = {"KL", "Singapore", "Bangkok", "Tokyo", "Sydney"};
        for (String city : cities) {
            boolean added = flightNetwork.addCity(city);
            System.out.println("Adding city " + city + ": " + (added ? "SUCCESS" : "FAILED"));
        }
        
        // Test adding duplicate city
        boolean duplicate = flightNetwork.addCity("KL");
        System.out.println("Adding duplicate city KL: " + (duplicate ? "SUCCESS" : "FAILED (Expected)"));
        
        // Add flight routes
        System.out.println("\nAdding flight routes:");
        addFlight(flightNetwork, "KL", "Singapore", 150);
        addFlight(flightNetwork, "KL", "Bangkok", 200);
        addFlight(flightNetwork, "Singapore", "Tokyo", 500);
        addFlight(flightNetwork, "Bangkok", "Tokyo", 450);
        addFlight(flightNetwork, "Tokyo", "Sydney", 600);
        addFlight(flightNetwork, "Singapore", "Sydney", 800);
        
        // Test 2: Display flight network
        System.out.println("\n2. FLIGHT NETWORK STRUCTURE");
        System.out.println("-".repeat(40));
        flightNetwork.printFlightNetwork();
        
        // Test 3: Basic graph operations
        System.out.println("\n3. BASIC GRAPH OPERATIONS");
        System.out.println("-".repeat(40));
        
        // Test city existence
        testCityExistence(flightNetwork, "KL");
        testCityExistence(flightNetwork, "London");
        
        // Test direct flights
        testDirectFlight(flightNetwork, "KL", "Singapore");
        testDirectFlight(flightNetwork, "KL", "Tokyo");
        
        // Test flight costs
        testFlightCost(flightNetwork, "KL", "Singapore");
        testFlightCost(flightNetwork, "KL", "Tokyo");
        
        // Test destinations
        testDestinations(flightNetwork, "KL");
        testDestinations(flightNetwork, "Sydney");
        
        // Test 4: Path finding algorithms
        System.out.println("\n4. PATH FINDING ALGORITHMS");
        System.out.println("-".repeat(40));
        
        // Test DFS (Any Route)
        System.out.println("DFS - Finding ANY route from KL to Sydney:");
        List<String> anyRoute = routingSystem.findAnyRoute("KL", "Sydney");
        displayRoute("Any Route (DFS)", anyRoute, routingSystem);
        
        // Test BFS (Shortest Route by hops)
        System.out.println("\nBFS - Finding SHORTEST route (by hops) from KL to Sydney:");
        List<String> shortestRoute = routingSystem.findShortestRoute("KL", "Sydney");
        displayRoute("Shortest Route (BFS)", shortestRoute, routingSystem);
        
        // Test Dijkstra (Cheapest Route)
        System.out.println("\nDijkstra - Finding CHEAPEST route from KL to Sydney:");
        FlightRoutingSystem.RouteInfo cheapestRoute = routingSystem.findCheapestRoute("KL", "Sydney");
        displayRouteInfo("Cheapest Route (Dijkstra)", cheapestRoute);
        
        // Test 5: All possible routes
        System.out.println("\n5. ALL POSSIBLE ROUTES");
        System.out.println("-".repeat(40));
        List<FlightRoutingSystem.RouteInfo> allRoutes = routingSystem.findAllRoutes("KL", "Sydney");
        System.out.println("All routes from KL to Sydney:");
        for (int i = 0; i < allRoutes.size(); i++) {
            System.out.println((i + 1) + ". " + allRoutes.get(i));
        }
        
        // Test 6: Edge cases
        System.out.println("\n6. EDGE CASE TESTING");
        System.out.println("-".repeat(40));
        
        // Same city route
        List<String> sameCity = routingSystem.findAnyRoute("KL", "KL");
        displayRoute("Same city route (KL to KL)", sameCity, routingSystem);
        
        // Non-existent city
        List<String> invalidRoute = routingSystem.findAnyRoute("KL", "London");
        displayRoute("Invalid route (KL to London)", invalidRoute, routingSystem);
        
        // No route exists (add isolated city)
        flightNetwork.addCity("Isolated");
        List<String> noRoute = routingSystem.findAnyRoute("KL", "Isolated");
        displayRoute("No route exists (KL to Isolated)", noRoute, routingSystem);
        
        // Test 7: Algorithm comparison
        System.out.println("\n7. ALGORITHM COMPARISON");
        System.out.println("-".repeat(40));
        compareAlgorithms(routingSystem, "KL", "Sydney");
        compareAlgorithms(routingSystem, "Singapore", "Bangkok");
        
        // Test 8: Performance analysis
        System.out.println("\n8. PERFORMANCE ANALYSIS");
        System.out.println("-".repeat(40));
        performanceAnalysis(routingSystem, "KL", "Sydney");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        TEST PROGRAM COMPLETED SUCCESSFULLY");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Helper method to add a flight and display result
     */
    private static void addFlight(FlightGraph graph, String from, String to, int cost) {
        boolean added = graph.addFlight(from, to, cost);
        System.out.println("  " + from + " → " + to + " ($" + cost + "): " + 
                          (added ? "SUCCESS" : "FAILED"));
    }
    
    /**
     * Test city existence
     */
    private static void testCityExistence(FlightGraph graph, String city) {
        boolean exists = graph.hasCity(city);
        System.out.println("City '" + city + "' exists: " + exists);
    }
    
    /**
     * Test direct flight existence
     */
    private static void testDirectFlight(FlightGraph graph, String from, String to) {
        boolean exists = graph.hasDirectFlight(from, to);
        System.out.println("Direct flight " + from + " → " + to + ": " + exists);
    }
    
    /**
     * Test flight cost
     */
    private static void testFlightCost(FlightGraph graph, String from, String to) {
        int cost = graph.getFlightCost(from, to);
        System.out.println("Flight cost " + from + " → " + to + ": " + 
                          (cost == -1 ? "No direct flight" : "$" + cost));
    }
    
    /**
     * Test destinations from a city
     */
    private static void testDestinations(FlightGraph graph, String from) {
        List<String> destinations = graph.getAllDestinations(from);
        System.out.println("Destinations from " + from + ": " + destinations);
    }
    
    /**
     * Display route information
     */
    private static void displayRoute(String title, List<String> route, 
                                   FlightRoutingSystem system) {
        System.out.println(title + ":");
        if (route.isEmpty()) {
            System.out.println("  No route found");
        } else {
            int cost = system.calculateRouteCost(route);
            System.out.println("  Route: " + String.join(" → ", route));
            System.out.println("  Cost: " + (cost == -1 ? "Invalid" : "$" + cost));
            System.out.println("  Hops: " + (route.size() - 1));
        }
    }
    
    /**
     * Display route info object
     */
    private static void displayRouteInfo(String title, FlightRoutingSystem.RouteInfo routeInfo) {
        System.out.println(title + ":");
        if (!routeInfo.isValid()) {
            System.out.println("  No route found");
        } else {
            System.out.println("  " + routeInfo);
            System.out.println("  Hops: " + (routeInfo.getRoute().size() - 1));
        }
    }
    
    /**
     * Compare different algorithms
     */
    private static void compareAlgorithms(FlightRoutingSystem system, String from, String to) {
        System.out.println("Comparing algorithms for route " + from + " → " + to + ":");
        
        // DFS (Any route)
        List<String> dfsRoute = system.findAnyRoute(from, to);
        int dfsCost = system.calculateRouteCost(dfsRoute);
        
        // BFS (Shortest by hops)
        List<String> bfsRoute = system.findShortestRoute(from, to);
        int bfsCost = system.calculateRouteCost(bfsRoute);
        
        // Dijkstra (Cheapest)
        FlightRoutingSystem.RouteInfo dijkstraRoute = system.findCheapestRoute(from, to);
        
        System.out.println("  DFS (Any):      " + String.join(" → ", dfsRoute) + 
                          " (Cost: $" + dfsCost + ", Hops: " + (dfsRoute.size() - 1) + ")");
        System.out.println("  BFS (Shortest): " + String.join(" → ", bfsRoute) + 
                          " (Cost: $" + bfsCost + ", Hops: " + (bfsRoute.size() - 1) + ")");
        System.out.println("  Dijkstra (Cheapest): " + dijkstraRoute);
        System.out.println();
    }
    
    /**
     * Performance analysis
     */
    private static void performanceAnalysis(FlightRoutingSystem system, String from, String to) {
        System.out.println("Performance analysis for " + from + " → " + to + ":");
        
        // Measure DFS time
        long startTime = System.nanoTime();
        @SuppressWarnings("unused")
        List<String> dfsResult = system.findAnyRoute(from, to);
        long dfsTime = System.nanoTime() - startTime;
        
        // Measure BFS time
        startTime = System.nanoTime();
        @SuppressWarnings("unused")
        List<String> bfsResult = system.findShortestRoute(from, to);
        long bfsTime = System.nanoTime() - startTime;
        
        // Measure Dijkstra time
        startTime = System.nanoTime();
        @SuppressWarnings("unused")
        FlightRoutingSystem.RouteInfo dijkstraResult = system.findCheapestRoute(from, to);
        long dijkstraTime = System.nanoTime() - startTime;
        
        // Measure All Routes time
        startTime = System.nanoTime();
        List<FlightRoutingSystem.RouteInfo> allRoutes = system.findAllRoutes(from, to);
        long allRoutesTime = System.nanoTime() - startTime;
        
        System.out.println("  DFS time:        " + dfsTime / 1000 + " microseconds");
        System.out.println("  BFS time:        " + bfsTime / 1000 + " microseconds");
        System.out.println("  Dijkstra time:   " + dijkstraTime / 1000 + " microseconds");
        System.out.println("  All routes time: " + allRoutesTime / 1000 + " microseconds");
        System.out.println("  Total routes found: " + allRoutes.size());
    }
} 