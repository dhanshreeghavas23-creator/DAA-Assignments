import heapq   # heapq will help us use a priority queue (min-heap) for A* open list
import math    # math library is used here only for distance formula (heuristic)

# A* Search Algorithm
def astar(graph, coords, start, goal):
    open_list = [(0, start)]   # priority queue storing (f-cost, node). Start has f=0
    came_from = {}             # to remember path: child -> parent
    g = {start: 0}             # dictionary to store g-cost (distance from start)

    # Heuristic function (h). Here we use Euclidean distance between current node and goal
    def h(n):
        x1, y1 = coords[n]         # coordinates of current node
        x2, y2 = coords[goal]      # coordinates of goal node
        return math.hypot(x1 - x2, y1 - y2)  # sqrt((x1-x2)^2 + (y1-y2)^2)

    # Main loop, runs while nodes remain in open list
    while open_list:
        _, current = heapq.heappop(open_list)  # take node with smallest f-cost

        # If we reached goal, reconstruct final path by backtracking using came_from
        if current == goal:
            path = []
            while current in came_from:   # walk backwards from goal to start
                path.append(current)
                current = came_from[current]
            return [start] + path[::-1]   # reverse path and add start node

        # Check all neighbors of current node
        for neigh, cost in graph[current].items():
            temp_g = g[current] + cost    # new g-cost = current g + cost to neighbor

            # If this new path is better, update it
            if temp_g < g.get(neigh, float('inf')):
                g[neigh] = temp_g                 # update g-cost
                came_from[neigh] = current        # store path direction (parent)
                f = temp_g + h(neigh)             # f-cost = g-cost + heuristic
                heapq.heappush(open_list, (f, neigh))  # push neighbor in queue

    return None  # return None if goal cannot be reached


# -------- Real-Life Coordinates Example (Cities as points) --------
coords = {
    "A": (0, 0),     # imagine "A" is a starting city at coordinates (0,0)
    "B": (2, 3),     # distance used to guide heuristic
    "C": (5, 4),
    "D": (7, 1)      # goal city
}

# Graph representing roads and travel cost (distance or time)
graph = {
    "A": {"B": 4, "C": 6},     # from city A: B costs 4, C costs 6
    "B": {"C": 2, "D": 7},     # from B: C costs 2, D costs 7
    "C": {"D": 3},             # from C: D costs 3
    "D": {}                    # from D: no outgoing roads
}

# Run A* from city A to D
path = astar(graph, coords, "A", "D")
print("Shortest informed path:", path)
