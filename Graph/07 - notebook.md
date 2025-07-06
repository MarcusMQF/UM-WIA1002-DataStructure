# Graph Data Structure - Comprehensive Study Notes

## 1. Graph Fundamentals

### Definition
**Graph**: A mathematical concept and data structure consisting of:
- **Vertices (V)**: Set of nodes/entities
- **Edges (E)**: Set of connections between vertices
- **Notation**: G = (V, E)

### Key Terminology
- **Adjacent vertices**: Two vertices that share the same edge
- **Path**: A sequence of edges connecting one vertex to another
- **Directed vs Undirected**: Edges can have direction or be bidirectional
- **Weighted vs Unweighted**: Edges can carry values (weights) or not

### Real-world Applications
- Flight connections between cities
- Social networks (friends, followers)
- Road networks and GPS navigation
- Computer networks
- COVID-19 contact tracing

## 2. Graph Representation Methods

### 2.1 Adjacency Matrix
**Concept**: 2D array where matrix[i][j] = 1 indicates an edge between vertex i and vertex j

**Advantages**:
- Fast edge lookup: O(1)
- Simple implementation
- Good for dense graphs

**Disadvantages**:
- Space complexity: O(V²)
- Wasteful for sparse graphs
- Adding/removing vertices is expensive

```java
// Adjacency Matrix Example
public class GraphMatrix {
    private int[][] matrix;
    private int numVertices;
    
    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
    }
    
    public void addEdge(int src, int dest) {
        matrix[src][dest] = 1;
        matrix[dest][src] = 1; // For undirected graph
    }
    
    public boolean hasEdge(int src, int dest) {
        return matrix[src][dest] == 1;
    }
    
    // For weighted graphs
    public void addWeightedEdge(int src, int dest, int weight) {
        matrix[src][dest] = weight;
        matrix[dest][src] = weight; // For undirected graph
    }
}
```

### 2.2 Adjacency List
**Concept**: Array of linked lists where each index represents a vertex, and the linked list contains all adjacent vertices

**Advantages**:
- Space efficient for sparse graphs: O(V + E)
- Easy to add/remove vertices
- Memory efficient

**Disadvantages**:
- Slower edge lookup: O(degree of vertex)
- More complex implementation

```java
// Adjacency List using ArrayList
import java.util.*;

public class GraphList {
    private ArrayList<ArrayList<Integer>> adjList;
    private int numVertices;
    
    public GraphList(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // For undirected graph
    }
    
    public boolean hasEdge(int src, int dest) {
        return adjList.get(src).contains(dest);
    }
    
    public List<Integer> getNeighbors(int vertex) {
        return adjList.get(vertex);
    }
}

public class GraphExample {
    public static void main(String[] args) {
        // Create a graph with 4 vertices (0, 1, 2, 3)
        GraphList graph = new GraphList(4);
        
        // Add some edges
        graph.addEdge(0, 1);  // Connect 0 and 1
        graph.addEdge(1, 2);  // Connect 1 and 2
        graph.addEdge(2, 3);  // Connect 2 and 3
        graph.addEdge(0, 3);  // Connect 0 and 3
        
        // Now our graph looks like:
        // 0 -- 1
        // |    |
        // 3 -- 2
        
        // Print the adjacency list representation
        System.out.println("Graph representation:");
        for (int i = 0; i < 4; i++) {
            System.out.println("Vertex " + i + ": " + graph.getNeighbors(i));
        }
        
        // Check some edges
        System.out.println("Edge 0-1 exists: " + graph.hasEdge(0, 1)); // true
        System.out.println("Edge 0-2 exists: " + graph.hasEdge(0, 2)); // false
    }
}
```

## 3. Advanced Graph Implementation

### 3.1 Vertex Class Implementation
```java
public class Vertex<T> {
    private T info;
    private Vertex<T> nextVertex;
    private Edge firstEdge;
    private int inDegree;
    private int outDegree;
    
    public Vertex(T info) {
        this.info = info;
        this.nextVertex = null;
        this.firstEdge = null;
        this.inDegree = 0;
        this.outDegree = 0;
    }
    
    // Getters and setters
    public T getInfo() { return info; }
    public void setInfo(T info) { this.info = info; }
    public Vertex<T> getNextVertex() { return nextVertex; }
    public void setNextVertex(Vertex<T> nextVertex) { this.nextVertex = nextVertex; }
    public Edge getFirstEdge() { return firstEdge; }
    public void setFirstEdge(Edge firstEdge) { this.firstEdge = firstEdge; }
    public int getInDegree() { return inDegree; }
    public int getOutDegree() { return outDegree; }
    public void incrementInDegree() { this.inDegree++; }
    public void incrementOutDegree() { this.outDegree++; }
}
```

### 3.2 Edge Class Implementation
```java
public class Edge {
    private Vertex toVertex;
    private Edge nextEdge;
    private int weight;
    
    public Edge(Vertex toVertex, int weight) {
        this.toVertex = toVertex;
        this.weight = weight;
        this.nextEdge = null;
    }
    
    // Getters and setters
    public Vertex getToVertex() { return toVertex; }
    public void setToVertex(Vertex toVertex) { this.toVertex = toVertex; }
    public Edge getNextEdge() { return nextEdge; }
    public void setNextEdge(Edge nextEdge) { this.nextEdge = nextEdge; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}
```

### 3.3 Complete Graph Implementation
```java
import java.util.*;

public class WeightedGraph<T> {
    private Vertex<T> head;
    private int numVertices;
    
    public WeightedGraph() {
        this.head = null;
        this.numVertices = 0;
    }
    
    // Add vertex
    public void addVertex(T info) {
        if (!hasVertex(info)) {
            Vertex<T> newVertex = new Vertex<>(info);
            if (head == null) {
                head = newVertex;
            } else {
                Vertex<T> current = head;
                while (current.getNextVertex() != null) {
                    current = current.getNextVertex();
                }
                current.setNextVertex(newVertex);
            }
            numVertices++;
        }
    }
    
    // Check if vertex exists
    public boolean hasVertex(T info) {
        return getVertex(info) != null;
    }
    
    // Get vertex by info
    private Vertex<T> getVertex(T info) {
        Vertex<T> current = head;
        while (current != null) {
            if (current.getInfo().equals(info)) {
                return current;
            }
            current = current.getNextVertex();
        }
        return null;
    }
    
    // Add weighted edge
    public void addEdge(T src, T dest, int weight) {
        Vertex<T> srcVertex = getVertex(src);
        Vertex<T> destVertex = getVertex(dest);
        
        if (srcVertex != null && destVertex != null) {
            // Create new edge
            Edge newEdge = new Edge(destVertex, weight);
            newEdge.setNextEdge(srcVertex.getFirstEdge());
            srcVertex.setFirstEdge(newEdge);
            
            // Update degrees
            srcVertex.incrementOutDegree();
            destVertex.incrementInDegree();
        }
    }
    
    // Check if edge exists
    public boolean hasEdge(T src, T dest) {
        Vertex<T> srcVertex = getVertex(src);
        Vertex<T> destVertex = getVertex(dest);
        
        if (srcVertex != null && destVertex != null) {
            Edge current = srcVertex.getFirstEdge();
            while (current != null) {
                if (current.getToVertex().equals(destVertex)) {
                    return true;
                }
                current = current.getNextEdge();
            }
        }
        return false;
    }
    
    // Get edge weight
    public int getWeight(T src, T dest) {
        Vertex<T> srcVertex = getVertex(src);
        Vertex<T> destVertex = getVertex(dest);
        
        if (srcVertex != null && destVertex != null) {
            Edge current = srcVertex.getFirstEdge();
            while (current != null) {
                if (current.getToVertex().equals(destVertex)) {
                    return current.getWeight();
                }
                current = current.getNextEdge();
            }
        }
        return -1; // Edge not found
    }
    
    // Get neighbors of a vertex
    public ArrayList<T> getNeighbors(T info) {
        ArrayList<T> neighbors = new ArrayList<>();
        Vertex<T> vertex = getVertex(info);
        
        if (vertex != null) {
            Edge current = vertex.getFirstEdge();
            while (current != null) {
                neighbors.add(current.getToVertex().getInfo());
                current = current.getNextEdge();
            }
        }
        return neighbors;
    }
    
    // Get all vertices
    public ArrayList<T> getAllVertices() {
        ArrayList<T> vertices = new ArrayList<>();
        Vertex<T> current = head;
        
        while (current != null) {
            vertices.add(current.getInfo());
            current = current.getNextVertex();
        }
        return vertices;
    }
    
    // Print graph
    public void printGraph() {
        Vertex<T> current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " -> ");
            Edge edge = current.getFirstEdge();
            while (edge != null) {
                System.out.print(edge.getToVertex().getInfo() + "(" + edge.getWeight() + ") ");
                edge = edge.getNextEdge();
            }
            System.out.println();
            current = current.getNextVertex();
        }
    }
    
    public int getNumVertices() { return numVertices; }
}
```

## 4. Graph Traversal Algorithms

### 4.1 Depth-First Search (DFS)
**Concept**: Explores as far as possible along each branch before backtracking  
**Data Structure**: Stack (LIFO)

```java
public class GraphTraversal<T> {
    private WeightedGraph<T> graph;
    
    public GraphTraversal(WeightedGraph<T> graph) {
        this.graph = graph;
    }
    
    // DFS Implementation
    public void dfs(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        
        stack.push(startVertex);
        
        while (!stack.isEmpty()) {
            T current = stack.pop();
            
            if (!visited.contains(current)) {
                visited.add(current);
                System.out.print(current + " ");
                
                // Add neighbors to stack
                ArrayList<T> neighbors = graph.getNeighbors(current);
                for (T neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }
    
    // DFS Recursive Implementation
    public void dfsRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        dfsRecursiveHelper(startVertex, visited);
        System.out.println();
    }
    
    private void dfsRecursiveHelper(T vertex, Set<T> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");
        
        ArrayList<T> neighbors = graph.getNeighbors(vertex);
        for (T neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsRecursiveHelper(neighbor, visited);
            }
        }
    }
}
```

**DFS Applications**:
- Detecting graph connectivity
- Finding paths between vertices
- Cycle detection
- Topological sorting
- Maze solving

### 4.2 Breadth-First Search (BFS)
**Concept**: Explores vertices level by level  
**Data Structure**: Queue (FIFO)

```java
public void bfs(T startVertex) {
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new LinkedList<>();
    
    queue.offer(startVertex);
    visited.add(startVertex);
    
    while (!queue.isEmpty()) {
        T current = queue.poll();
        System.out.print(current + " ");
        
        // Add neighbors to queue
        ArrayList<T> neighbors = graph.getNeighbors(current);
        for (T neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
    System.out.println();
}

// BFS to find shortest path (unweighted)
public List<T> bfsShortestPath(T start, T end) {
    if (start.equals(end)) {
        return Arrays.asList(start);
    }
    
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new LinkedList<>();
    Map<T, T> parent = new HashMap<>();
    
    queue.offer(start);
    visited.add(start);
    parent.put(start, null);
    
    while (!queue.isEmpty()) {
        T current = queue.poll();
        
        ArrayList<T> neighbors = graph.getNeighbors(current);
        for (T neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                parent.put(neighbor, current);
                queue.offer(neighbor);
                
                if (neighbor.equals(end)) {
                    return reconstructPath(parent, start, end);
                }
            }
        }
    }
    
    return new ArrayList<>(); // No path found
}

private List<T> reconstructPath(Map<T, T> parent, T start, T end) {
    List<T> path = new ArrayList<>();
    T current = end;
    
    while (current != null) {
        path.add(current);
        current = parent.get(current);
    }
    
    Collections.reverse(path);
    return path;
}
```

**BFS Applications**:
- Finding shortest path (unweighted graphs)
- Level-order traversal
- Bipartite graph checking
- Connected component analysis
- Social network analysis (degrees of separation)

## 5. Graph Algorithms Comparison

| Aspect | DFS | BFS |
|--------|-----|-----|
| Data Structure | Stack | Queue |
| Memory Usage | O(h) - height | O(w) - width |
| Shortest Path | No | Yes (unweighted) |
| Implementation | Recursive/Iterative | Iterative |
| Use Cases | Cycle detection, Topological sort | Shortest path, Level traversal |

## 6. Complete Test Program

```java
public class GraphTest {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        
        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        
        // Add edges
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "E", 4);
        
        // Print graph
        System.out.println("Graph structure:");
        graph.printGraph();
        
        // Test traversals
        GraphTraversal<String> traversal = new GraphTraversal<>(graph);
        
        System.out.println("\nDFS from A:");
        traversal.dfs("A");
        
        System.out.println("\nBFS from A:");
        traversal.bfs("A");
        
        // Test shortest path
        System.out.println("\nShortest path from A to E:");
        List<String> path = traversal.bfsShortestPath("A", "E");
        System.out.println(path);
        
        // Test graph properties
        System.out.println("\nGraph properties:");
        System.out.println("Number of vertices: " + graph.getNumVertices());
        System.out.println("Has edge A->B: " + graph.hasEdge("A", "B"));
        System.out.println("Weight of A->B: " + graph.getWeight("A", "B"));
        System.out.println("Neighbors of A: " + graph.getNeighbors("A"));
    }
}
```

## 7. Key Takeaways

- **Choose representation wisely**: Use adjacency matrix for dense graphs, adjacency list for sparse graphs
- **Understand traversal differences**: DFS for deep exploration, BFS for shortest paths
- **Memory considerations**: DFS uses less memory than BFS in most cases
- **Implementation complexity**: Adjacency list is more complex but more flexible
- **Real-world applications**: Graphs model many real-world problems effectively

## 8. Practice Problems

1. Implement a method to detect cycles in a graph
2. Find all connected components in an undirected graph
3. Implement Dijkstra's algorithm for shortest path in weighted graphs
4. Check if a graph is bipartite using BFS
5. Implement topological sorting using DFS

---

This comprehensive guide covers all the essential concepts from the slides and provides practical Java implementations for mastering graph data structures.
