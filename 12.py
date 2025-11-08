import math
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.metrics import mean_squared_error , r2_score

# -------- Model: fit + predict (manual closed-form) --------
def fit_simple_linear_regression(x, y):
    """
    Fits y = b0 + b1*x using closed-form least squares
    b1 = sum((x - x̄)(y - ȳ)) / sum((x - x̄)^2)
    b0 = ȳ - b1 * x̄
    """
    x = np.asarray(x, dtype=float)
    y = np.asarray(y, dtype=float)

    x_mean = np.mean(x)
    y_mean = np.mean(y)

    num = np.sum((x - x_mean) * (y - y_mean))
    den = np.sum((x - x_mean) ** 2)

    if den == 0:
        raise ValueError("Cannot fit a line: all X values are identical (variance is zero).")

    b1 = num / den
    b0 = y_mean - b1 * x_mean
    return b0, b1

def predict_simple_linear_regression(x, b0, b1):
    x = np.asarray(x, dtype=float)
    return b0 + b1 * x

df = pd.read_csv("Student_performance_data _.csv")

df = df[["StudyTimeWeekly" , 'GPA']].dropna()
x = df["StudyTimeWeekly"].values
y = df['GPA'].values

# Fit
b0, b1 = fit_simple_linear_regression(x, y)

# Predict
y_pred = predict_simple_linear_regression(x, b0, b1)

# Evaluate
mse = mean_squared_error(y, y_pred)
r2  = r2_score(y, y_pred)

print("=== Linear Regression (from scratch) ===")
print(f"Feature (X): {"StudyTimeWeekly"}")
print(f"Target  (y): {'GPA'}")
print(f"Intercept (b0): {b0:.6f}")
print(f"Slope     (b1): {b1:.6f}")
print(f"MSE: {mse:.6f}")
print(f"R^2: {r2:.6f}")

# -------- Plot --------
plt.figure(figsize=(7,5))
plt.scatter(x, y, alpha=0.7, label="Data")
# Sort for a clean line
order = np.argsort(x)
plt.plot(x[order], y_pred[order], linewidth=2, label="Best-fit line",color='orange')
plt.xlabel("StudyTimeWeekly")
plt.ylabel("GPA")
plt.title("Linear Regression (from scratch)")
plt.legend()
plt.tight_layout()
plt.show()


