# TOWER OF HANOI (EASY PYTHON IMPLEMENTATION)
# --------------------------------------------
# Goal: Move all disks from source rod (A) to destination rod (C)
# using auxiliary rod (B), following these rules:
# 1. Only one disk can be moved at a time.
# 2. A larger disk cannot be placed on top of a smaller disk.
# 3. All disks must be moved from A → C.

def tower_of_hanoi(n, source, auxiliary, destination):
    """
    Recursive function to solve Tower of Hanoi.
    n = number of disks
    source = starting rod
    auxiliary = helper rod
    destination = target rod
    """
    if n == 1:
        print(f"Move disk 1 from {source} --> {destination}")
        return

    # Step 1: Move (n-1) disks from source to auxiliary
    tower_of_hanoi(n-1, source, destination, auxiliary)

    # Step 2: Move the largest disk (nth) from source to destination
    print(f"Move disk {n} from {source} --> {destination}")

    # Step 3: Move the (n-1) disks from auxiliary to destination
    tower_of_hanoi(n-1, auxiliary, source, destination)


# ---------------- MAIN PROGRAM ----------------
print("TOWER OF HANOI GAME")
n = int(input("Enter number of disks: "))

print("\nThe sequence of moves is:\n")
tower_of_hanoi(n, "A", "B", "C")
