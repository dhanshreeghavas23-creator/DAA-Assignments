# TIC-TAC-TOE GAME (EASY PYTHON VERSION)
# ---------------------------------------
# Two players: X and O take turns.
# The first player to make 3 in a row (row, column, or diagonal) wins!

# Function to print the Tic-Tac-Toe board
def print_board(board):
    print("\n")
    for row in board:
        print(" | ".join(row))
        print("-" * 5)
    print("\n")

# Function to check if a player has won
def check_win(board, player):
    # Check rows, columns, and diagonals
    for i in range(3):
        if all([cell == player for cell in board[i]]):  # row check
            return True
        if all([board[j][i] == player for j in range(3)]):  # column check
            return True
    # diagonals
    if all([board[i][i] == player for i in range(3)]):
        return True
    if all([board[i][2 - i] == player for i in range(3)]):
        return True
    return False

# Function to check if the board is full (draw)
def is_draw(board):
    for row in board:
        if " " in row:
            return False
    return True

# MAIN GAME FUNCTION
def play_game():
    # Empty board (3x3)
    board = [[" " for _ in range(3)] for _ in range(3)]
    current_player = "X"  # X starts first

    print("Welcome to Tic-Tac-Toe!")
    print_board(board)

    while True:
        print(f"Player {current_player}'s turn.")

        # Get user input (row and column from 1–3)
        row = int(input("Enter row (1-3): ")) - 1
        col = int(input("Enter column (1-3): ")) - 1

        # Check for valid move
        if board[row][col] != " ":
            print("That spot is already taken! Try again.")
            continue

        # Place player's symbol
        board[row][col] = current_player
        print_board(board)

        # Check for a win
        if check_win(board, current_player):
            print(f"🎉 Player {current_player} wins! 🎉")
            break

        # Check for draw
        if is_draw(board):
            print("It's a draw!")
            break

        # Switch player
        current_player = "O" if current_player == "X" else "X"

# Run the game
play_game()
