"""
BREADTH-FIRST SEARCH (BFS) FOR SHORTEST PATH IN A MAZE
-------------------------------------------------------
Concept:
- BFS explores level by level (1 step away, then 2 steps away…)
- Because of this, the FIRST time BFS reaches the goal = shortest path.
- Maze is a grid where:
      0 = free cell
      1 = wall (cannot move)
- Movements allowed: Up, Down, Left, Right
"""

from collections import deque

def bfs_shortest_path(maze, start, goal):
    """
    Function to perform BFS and return the shortest path from start to goal.

    Parameters:
    maze  : 2D list of 0s and 1s
    start : (row, col)
    goal  : (row, col)
    """

    # Directions: Down, Up, Right, Left
    directions = [(1,0), (-1,0), (0,1), (0,-1)]

    # Queue for BFS (stores cells to explore)
    queue = deque([start])

    # Set to mark visited cells
    visited = set()
    visited.add(start)

    # Dictionary to store the parent of each cell
    # This helps reconstruct the shortest path later
    parent = {start: None}

    while queue:
        current = queue.popleft()

        # If we reach the goal, we stop BFS because it's the shortest path
        if current == goal:
            break

        r, c = current

        # Explore all 4 possible moves
        for dr, dc in directions:
            nr, nc = r + dr, c + dc

            # Check: inside maze + not a wall + not visited
            if (0 <= nr < len(maze) and
                0 <= nc < len(maze[0]) and
                maze[nr][nc] == 0 and
                (nr, nc) not in visited):

                visited.add((nr, nc))
                parent[(nr, nc)] = current
                queue.append((nr, nc))

    # Reconstruct shortest path from goal back to start
    path = []
    node = goal

    if node not in parent:
        return None  # No path exists

    while node is not None:
        path.append(node)
        node = parent[node]

    return path[::-1]  # reverse the list to get start → goal


# -------------------------------------------------------------
# ✅ EXAMPLE MAZE
# 0 = free path
# 1 = obstacle
# -------------------------------------------------------------

maze = [
    [0, 1, 0, 0],
    [0, 0, 0, 1],
    [1, 0, 0, 0],
    [0, 0, 1, 0]
]

start = (0, 0)
goal  = (3, 3)

# ✅ Run BFS
shortest_path = bfs_shortest_path(maze, start, goal)

print("Shortest Path:", shortest_path)
