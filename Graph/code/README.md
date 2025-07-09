# Flight Routing System - Graph Data Structure Implementation

## Overview
This project implements a comprehensive **Flight Routing System** using a weighted directed graph data structure. It demonstrates various graph algorithms including DFS, BFS, and Dijkstra's algorithm for finding different types of routes between cities.

## Problem Statement
**Exam Question**: Design a flight routing system that helps passengers find flight routes between cities using graph data structures and implement path-finding algorithms.

## Key Learning Objectives
- Understanding graph representation using adjacency lists
- Implementing DFS for finding ANY path
- Implementing BFS for finding SHORTEST path (by hops)
- Implementing Dijkstra's algorithm for finding CHEAPEST path
- Comparing algorithm performance and characteristics

## File Structure

```
Graph/code/
├── Vertex.java              # Represents cities in the flight network
├── Edge.java                # Represents flight routes with costs
├── FlightGraph.java         # Main graph implementation using adjacency list
├── FlightRoutingSystem.java # Path-finding algorithms (DFS, BFS, Dijkstra)
├── FlightTest.java          # Comprehensive test program
└── README.md               # This file
```

## Core Components

### 1. Vertex.java
- Represents cities in the flight network
- Contains city name, references to next vertex and first edge
- Tracks in-degree and out-degree for analysis

### 2. Edge.java  
- Represents flight routes between cities
- Contains destination city, flight cost, and reference to next edge
- Implements weighted connections in the graph

### 3. FlightGraph.java
- Main graph implementation using adjacency list representation
- Provides methods for adding cities, adding flights, checking connections
- Memory-efficient representation suitable for sparse graphs

### 4. FlightRoutingSystem.java
- Implements three key algorithms:
  - **DFS (Depth-First Search)**: Finds ANY route between cities
  - **BFS (Breadth-First Search)**: Finds SHORTEST route by number of hops
  - **Dijkstra's Algorithm**: Finds CHEAPEST route by total cost

## Algorithm Comparison

| Algorithm | Purpose | Data Structure | Time Complexity | Space Complexity | Optimal For |
|-----------|---------|----------------|-----------------|------------------|-------------|
| **DFS** | Find ANY path | Stack (recursive) | O(V + E) | O(V) | Path existence checking |
| **BFS** | Find SHORTEST path | Queue | O(V + E) | O(V) | Minimum hops |
| **Dijkstra** | Find CHEAPEST path | Priority Queue | O((V + E) log V) | O(V) | Minimum cost |

## Key Concepts Demonstrated

### DFS (Depth-First Search)
- **Purpose**: Find ANY route between two cities
- **Characteristic**: Explores deep into the graph before backtracking
- **Use Case**: When you just need to know if a path exists
- **Result**: May not be optimal in terms of cost or hops

### BFS (Breadth-First Search)  
- **Purpose**: Find SHORTEST route by number of flight connections
- **Characteristic**: Explores level by level
- **Use Case**: When you want minimum number of flight changes
- **Result**: Optimal for unweighted graphs (minimum hops)

### Dijkstra's Algorithm
- **Purpose**: Find CHEAPEST route by total flight cost
- **Characteristic**: Always processes lowest-cost paths first
- **Use Case**: When cost optimization is the priority
- **Result**: Guaranteed optimal solution for weighted graphs

## How to Run

1. **Compile all Java files:**
   ```bash
   javac *.java
   ```

2. **Run the test program:**
   ```bash
   java FlightTest
   ```

## Sample Test Case

The test program creates a flight network with:
- **Cities**: KL, Singapore, Bangkok, Tokyo, Sydney
- **Flight Routes**:
  - KL → Singapore ($150)
  - KL → Bangkok ($200)  
  - Singapore → Tokyo ($500)
  - Bangkok → Tokyo ($450)
  - Tokyo → Sydney ($600)
  - Singapore → Sydney ($800)

## Expected Output Analysis

For route **KL → Sydney**:

1. **DFS (Any Route)**: 
   - Finds any valid path (may vary between runs)
   - Example: KL → Singapore → Tokyo → Sydney

2. **BFS (Shortest by hops)**:
   - Finds path with minimum flight connections
   - Result: KL → Singapore → Sydney (2 hops)

3. **Dijkstra (Cheapest)**:
   - Finds path with minimum total cost
   - Result: KL → Bangkok → Tokyo → Sydney ($1250)

## Testing Features

The test program demonstrates:
- ✅ Building flight network and adding routes
- ✅ Basic graph operations (city existence, direct flights, costs)
- ✅ All three path-finding algorithms
- ✅ Edge case handling (same city, non-existent cities, isolated nodes)
- ✅ Algorithm comparison and performance analysis
- ✅ Finding all possible routes between cities

## Important Notes

### When to Use Each Algorithm:

1. **Use DFS when:**
   - You need to check if ANY path exists
   - You're doing graph traversal for connectivity
   - Memory usage needs to be minimized
   - You're implementing cycle detection

2. **Use BFS when:**
   - You need the SHORTEST path in unweighted graphs
   - You want minimum number of connections/hops
   - You're doing level-order exploration
   - You need to find nearest neighbors

3. **Use Dijkstra when:**
   - You need the CHEAPEST/OPTIMAL path in weighted graphs
   - Cost optimization is the primary concern
   - You're working with positive edge weights
   - You need guaranteed optimal solutions

## Graph Representation Choice

**Adjacency List vs Adjacency Matrix:**

This implementation uses **Adjacency List** because:
- ✅ Memory efficient for sparse graphs: O(V + E) vs O(V²)
- ✅ Faster for adding/removing vertices
- ✅ Better for real-world flight networks (cities don't connect to all other cities)
- ❌ Slower edge lookup: O(degree) vs O(1)

## Extensions and Improvements

Possible enhancements:
1. **Bidirectional flights** (undirected edges)
2. **Flight scheduling** with time constraints
3. **Multi-criteria optimization** (cost + time + comfort)
4. **Dynamic flight updates** (delays, cancellations)
5. **Capacity constraints** on flights

## Exam Preparation Tips

1. **Understand the differences** between DFS, BFS, and Dijkstra
2. **Practice implementing** each algorithm from scratch
3. **Know time/space complexities** and when to use each algorithm
4. **Understand graph representations** and their trade-offs
5. **Practice edge case handling** (empty graphs, unreachable nodes)

---

**Author**: Data Structure Course  
**Version**: 1.0  
**Purpose**: Educational demonstration of graph algorithms and data structures 