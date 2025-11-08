# -----------------------------------------
# Depth-First Search (DFS) Implementation
# For Traversing a Game Map (Graph)
# -----------------------------------------

def dfs(graph, start, target, visited=None, path=None):
    # Initialize visited and path lists
    if visited is None:
        visited = set()
    if path is None:
        path = []

    # Mark the current node as visited
    visited.add(start)
    path.append(start)
    print(f"Visiting: {start}")

    # Check if target is found
    if start == target:
        print(f"\n🎯 Target '{target}' found!")
        print(f"Path: {' -> '.join(path)}")
        return True

    # Explore all neighbors
    for neighbor in graph.get(start, []):
        if neighbor not in visited:
            found = dfs(graph, neighbor, target, visited, path)
            if found:
                return True  # Stop once target is found

    # If target not found, backtrack
    return False


# -----------------------------------------
# Example: Game Map represented as a Graph
# -----------------------------------------

game_map = {
    'A': ['B', 'C'],
    'B': ['D', 'E'],
    'C': ['F'],
    'D': [],
    'E': ['F'],
    'F': []
}

# Starting point and target
start_node = 'A'
target_node = 'F'

# -----------------------------------------
# Run DFS
# -----------------------------------------
print("🧭 DFS Traversal Order:\n")
dfs(game_map, start_node, target_node)
