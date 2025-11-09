import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import mean_squared_error, r2_score

# --- Load dataset ---
df = pd.read_csv("Housing.csv")   # any dataset name
print("Columns in dataset:", list(df.columns))

# --- Automatically pick first two numeric columns ---
num_cols = df.select_dtypes(include=["number"]).columns
if len(num_cols) < 2:
    raise ValueError("Need at least two numeric columns!")
x_col, y_col = num_cols[:2]

# --- Prepare data ---
x = df[x_col].dropna()
y = df[y_col].dropna()

# --- Simple Linear Regression (from scratch) ---
x_mean, y_mean = x.mean(), y.mean()
b1 = np.sum((x - x_mean) * (y - y_mean)) / np.sum((x - x_mean)**2)
b0 = y_mean - b1 * x_mean
y_pred = b0 + b1 * x

# --- Evaluate ---
mse = mean_squared_error(y, y_pred)
r2 = r2_score(y, y_pred)

# --- Results ---
print("\n=== Simple Linear Regression (Scratch) ===")
print(f"X (feature): {x_col}")
print(f"Y (target) : {y_col}")
print(f"Intercept (b0): {b0:.4f}")
print(f"Slope (b1): {b1:.4f}")
print(f"MSE: {mse:.4f}")
print(f"R²: {r2:.4f}")

# --- Plot ---
plt.scatter(x, y, color='blue', label='Data')
plt.plot(x, y_pred, color='red', label='Best Fit Line')
plt.xlabel(x_col)
plt.ylabel(y_col)
plt.title('Simple Linear Regression (from scratch)')
plt.legend()
plt.show()
