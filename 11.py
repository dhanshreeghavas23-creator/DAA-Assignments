# linear_regression_predict_price.py

# -----------------------------------------
# Import necessary Python libraries
# -----------------------------------------
import pandas as pd                # For handling data (loading CSV, creating DataFrames)
import numpy as np                 # For numerical operations
import matplotlib.pyplot as plt    # For visualizing data and regression results
from sklearn.linear_model import LinearRegression  # For performing linear regression
from sklearn.model_selection import KFold          # For applying K-Fold cross-validation
from sklearn.metrics import mean_absolute_error, mean_squared_error  # For evaluating model performance


# -----------------------------------------
# 1. Load and Prepare Dataset
# -----------------------------------------
# Load dataset named 'Housing.csv'. Make sure this file is in the same folder.
df = pd.read_csv("Housing.csv")

# Keep only the relevant columns: price (target), area and bedrooms (features)
df = df[['price', 'area', 'bedrooms']].dropna()  # Drop any rows with missing values

# Define input features (X) and target variable (y)
X = df[['area', 'bedrooms']]
y = df['price']


# -----------------------------------------
# 2. Model Validation using K-Fold Cross Validation
# -----------------------------------------
# KFold splits the dataset into 'k' parts — here, k=3 means 3 folds.
# It helps ensure our model is tested on different subsets to avoid overfitting.
kf = KFold(n_splits=3, shuffle=True, random_state=1)

mae_list = []   # To store Mean Absolute Error for each fold
rmse_list = []  # To store Root Mean Squared Error for each fold

# Run the training and testing process for each fold
for train_index, test_index in kf.split(X):
    # Split the data into training and testing sets based on fold indices
    X_train, X_test = X.iloc[train_index], X.iloc[test_index]
    y_train, y_test = y.iloc[train_index], y.iloc[test_index]

    # Create Linear Regression model
    model = LinearRegression()

    # Train model using training data
    model.fit(X_train, y_train)

    # Predict prices for the test data
    y_pred = model.predict(X_test)

    # Calculate errors for this fold
    mae = mean_absolute_error(y_test, y_pred)               # Average of absolute prediction errors
    rmse = np.sqrt(mean_squared_error(y_test, y_pred))      # Root mean squared error (penalizes large errors)

    # Store errors
    mae_list.append(mae)
    rmse_list.append(rmse)

# Print average performance across all folds
print(f"Average MAE : {np.mean(mae_list):.2f}")   # Lower MAE = better performance
print(f"Average RMSE: {np.mean(rmse_list):.2f}")  # Lower RMSE = better performance


# -----------------------------------------
# 3. Train Final Model on the Entire Dataset
# -----------------------------------------
# After validating, train again on the full dataset for final predictions
model = LinearRegression()
model.fit(X, y)


# -----------------------------------------
# 4. Visualization of Regression Results
# -----------------------------------------
# To visualize, fix the number of bedrooms to the average in dataset
avg_bedrooms = df['bedrooms'].mean()

# Create a range of 'area' values for plotting the line
area_range = np.linspace(df['area'].min(), df['area'].max(), 100)

# Predict prices for these areas using the trained model and average bedrooms
predicted_prices = model.predict(
    np.column_stack((area_range, np.full_like(area_range, avg_bedrooms)))
)

# Plot actual data points and regression line
plt.figure(figsize=(8, 5))
plt.scatter(df['area'], df['price'], alpha=0.6, label="Actual Data Points")  # Blue dots = real data
plt.plot(area_range, predicted_prices, color='red', linewidth=2,
         label=f"Regression Line (bedrooms={avg_bedrooms:.1f})")  # Red line = predicted relationship

plt.xlabel("Area (sq ft)")
plt.ylabel("Price")
plt.title("Linear Regression: Area vs Price (with average bedrooms)")
plt.legend()
plt.show()


# -----------------------------------------
# 5. Prediction Function
# -----------------------------------------
def predict_price(area, bedrooms):
    """
    Predict house price based on input:
    - area (in square feet)
    - number of bedrooms
    The function uses the trained linear regression model.
    """
    price = model.predict([[area, bedrooms]])[0]   # Predict and extract price value
    print(f"Predicted Price for {bedrooms} BHK with {area} sq.ft area: ₹{price:,.2f}")
    return price


# -----------------------------------------
# 6. Example Predictions
# -----------------------------------------
# Try predicting price for a 3BHK house with 2500 sq.ft area
predict_price(2500, 3)

# You can uncomment below examples to test more predictions:
# predict_price(1800, 2)
# predict_price(3500, 4)


    

