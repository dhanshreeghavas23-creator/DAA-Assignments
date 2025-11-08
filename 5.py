# 8 PUZZLE GAME SOLVER (A* SEARCH)
# --------------------------------
# Goal: Arrange numbers 1–8 in order with blank (0) at the end.
# Example goal:
# 1 2 3
# 4 5 6
# 7 8

import heapq

# Heuristic function: Manhattan distance (sum of tile distances from goal)
def heuristic(state, goal):
    dist = 0
    for num in range(1, 9):
        i1, j1 = divmod(state.index(num), 3)
        i2, j2 = divmod(goal.index(num), 3)
        dist += abs(i1 - i2) + abs(j1 - j2)
    return dist

# Function to move blank tile (0) up, down, left, right
def get_neighbors(state):
    moves = []
    zero = state.index(0)
    r, c = divmod(zero, 3)
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # up, down, left, right

    for dr, dc in directions:
        nr, nc = r + dr, c + dc
        if 0 <= nr < 3 and 0 <= nc < 3:
            new = list(state)
            new_idx = nr * 3 + nc
            new[zero], new[new_idx] = new[new_idx], new[zero]
            moves.append(tuple(new))
    return moves

# A* Search Algorithm
def astar(start, goal):
    open_list = [(heuristic(start, goal), start)]
    came_from = {}
    g = {start: 0}

    while open_list:
        _, current = heapq.heappop(open_list)

        if current == goal:
            # Reconstruct path
            path = []
            while current in came_from:
                path.append(current)
                current = came_from[current]
            path.append(start)
            return path[::-1]

        for neighbor in get_neighbors(current):
            temp_g = g[current] + 1
            if temp_g < g.get(neighbor, float('inf')):
                came_from[neighbor] = current
                g[neighbor] = temp_g
                f = temp_g + heuristic(neighbor, goal)
                heapq.heappush(open_list, (f, neighbor))
    return None

# Function to print 3x3 puzzle
def print_state(state):
    for i in range(0, 9, 3):
        print(state[i:i+3])
    print()

# ---------------- MAIN ----------------
start = (2, 8, 3,
         1, 6, 4,
         7, 0, 5)

goal = (1, 2, 3,
        4, 5, 6,
        7, 8, 0)

print("Start State:")
print_state(start)
print("Goal State:")
print_state(goal)

path = astar(start, goal)

if path:
    print("Puzzle solved in", len(path)-1, "moves!\n")
    for step, state in enumerate(path):
        print("Step", step)
        print_state(state)
else:
    print("No solution found!")
