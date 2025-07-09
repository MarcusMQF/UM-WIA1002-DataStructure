# Data Structure Exam Question: Flight Routing System

## Question (40 marks)

You are tasked with developing a **Flight Routing System** for an airline company using a **weighted directed graph** data structure. The system should help passengers find flight routes between cities.

### System Requirements:

1. **Graph Representation** (10 marks)
   - Each city is represented as a vertex
   - Each flight route is represented as a weighted directed edge
   - Edge weights represent flight costs in dollars
   - Use adjacency list representation for memory efficiency

2. **Core Operations** (15 marks)
   Implement the following methods:
   - `addCity(String cityName)` - Add a new city to the system
   - `addFlight(String from, String to, int cost)` - Add a flight route with cost
   - `hasDirectFlight(String from, String to)` - Check if direct flight exists
   - `getFlightCost(String from, String to)` - Get cost of direct flight
   - `getAllDestinations(String from)` - Get all cities reachable from a city

3. **Path Finding Algorithms** (15 marks)
   - **Any Route (DFS)**: Find any possible route between two cities
   - **Cheapest Route (Modified BFS)**: Find the cheapest route between two cities
   - **All Possible Routes**: Find all possible routes from one city to another

### Sample Test Case:
```
Cities: KL, Singapore, Bangkok, Tokyo, Sydney
Flights:
- KL → Singapore (150)
- KL → Bangkok (200)
- Singapore → Tokyo (500)
- Bangkok → Tokyo (450)
- Tokyo → Sydney (600)
- Singapore → Sydney (800)
```

### Expected Output:
```
Any route KL → Sydney: [KL, Singapore, Tokyo, Sydney]
Cheapest route KL → Sydney: [KL, Bangkok, Tokyo, Sydney] (Cost: 1250)
All routes KL → Sydney: 
1. [KL, Singapore, Tokyo, Sydney] (Cost: 1250)
2. [KL, Singapore, Sydney] (Cost: 950)
3. [KL, Bangkok, Tokyo, Sydney] (Cost: 1250)
```

### Implementation Notes:
- Use proper object-oriented design with separate classes
- Handle edge cases (city not found, no route exists)
- Provide clear comments explaining your algorithm choices
- **DFS finds ANY path (not necessarily optimal)**
- **BFS/Dijkstra finds SHORTEST/CHEAPEST path**

---

## Solution Approach:

### Key Concepts:
1. **DFS (Depth-First Search)**: 
   - Explores deep into the graph before backtracking
   - Finds ANY path between vertices
   - Uses stack (recursive or iterative)
   - Good for: Finding if path exists, exploring all possibilities

2. **BFS (Breadth-First Search)**:
   - Explores level by level
   - Finds SHORTEST path in unweighted graphs
   - Uses queue
   - For weighted graphs, use Dijkstra's algorithm for optimal path

3. **Modified BFS/Dijkstra**:
   - For cheapest route in weighted graphs
   - Uses priority queue to always process lowest cost first
   - Guarantees optimal solution

### File Structure:
- `Vertex.java` - Represents cities
- `Edge.java` - Represents flight routes  
- `FlightGraph.java` - Main graph implementation
- `FlightRoutingSystem.java` - Business logic and algorithms
- `FlightTest.java` - Test program demonstrating functionality 