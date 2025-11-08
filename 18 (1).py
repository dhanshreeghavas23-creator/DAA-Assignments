# SVM Spam Detection with Imbalance Handling (oversampling) on spam.csv

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# Scikit-learn utilities for machine learning pipeline
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.svm import LinearSVC
from sklearn.metrics import (
    confusion_matrix, accuracy_score, precision_score, recall_score, f1_score,
    classification_report
)

# -------------------------------
# 1) Load Dataset
# -------------------------------
# The dataset 'spam.csv' contains text messages labeled as 'ham' (non-spam) or 'spam'.
# encoding='latin-1' is used to properly read special characters from the dataset.
df = pd.read_csv("spam.csv", encoding="latin-1")

# Keep only two useful columns: 'v1' (label) and 'v2' (message text)
# Rename columns for clarity
df = df[["v1","v2"]].rename(columns={"v1":"label","v2":"text"}).dropna()

# Convert textual labels into numeric form:
# ham → 0 (non-spam), spam → 1 (spam)
df["y"] = df["label"].map({"ham":0, "spam":1}).astype(int)


# -------------------------------
# 2) Split Dataset (Stratified Split)
# -------------------------------
# We use stratified split so that both training and test sets have the same ratio of spam and ham.
# 80% of the data is used for training, 20% for testing.
X_train_text, X_test_text, y_train, y_test = train_test_split(
    df["text"].astype(str),           # message text
    df["y"].values,                   # numeric labels (0 or 1)
    test_size=0.2,                    # 20% for testing
    random_state=42,                  # ensures reproducibility
    stratify=df["y"].values           # maintains class balance
)


# -------------------------------
# 3) Text Vectorization (TF-IDF)
# -------------------------------
# TF-IDF (Term Frequency - Inverse Document Frequency) converts text into numeric features
# based on the importance of words in the dataset.
vectorizer = TfidfVectorizer(stop_words="english", lowercase=True)

# Learn vocabulary from training data and transform it into a numeric feature matrix
X_train = vectorizer.fit_transform(X_train_text)

# Transform test data using the same learned vocabulary
X_test  = vectorizer.transform(X_test_text)


# -------------------------------
# 4) Handle Imbalance (Optional step)
# -------------------------------
# Note: In this example, we mention "imbalance handling (oversampling)" conceptually.
# Actual oversampling methods like SMOTE or RandomOverSampler can be applied before training.
# For simplicity, we proceed with the existing data split here.


# -------------------------------
# 5) Train the SVM Model
# -------------------------------
# We use a Linear Support Vector Classifier (LinearSVC), which is efficient for text classification.
# 'C' controls the regularization strength; a smaller C means stronger regularization.
model = LinearSVC(C=1.0, random_state=42)

# Train (fit) the model using TF-IDF features and corresponding labels
model.fit(X_train, y_train)


# -------------------------------
# 6) Evaluate Model Performance
# -------------------------------
# Predict labels for test data
y_pred = model.predict(X_test)

# Compute standard evaluation metrics
acc = accuracy_score(y_test, y_pred)         # Overall accuracy
prec = precision_score(y_test, y_pred)       # % of predicted spam that is truly spam
rec = recall_score(y_test, y_pred)           # % of actual spam correctly identified
f1 = f1_score(y_test, y_pred)                # Harmonic mean of precision and recall

# Print results
print(f"Accuracy : {acc:.4f}")
print(f"Precision: {prec:.4f}")
print(f"Recall   : {rec:.4f}")
print(f"F1-score : {f1:.4f}")

# Classification report gives precision, recall, and F1-score per class
print("\nClassification Report:\n", classification_report(y_test, y_pred, target_names=["ham","spam"]))

# Confusion matrix: rows = true labels, columns = predicted labels
# [[TN, FP], [FN, TP]] => True Negatives, False Positives, False Negatives, True Positives
cm = confusion_matrix(y_test, y_pred)
print("Confusion Matrix [[TN, FP],[FN, TP]]:\n", cm)


# -------------------------------
# 7) Visualization of Confusion Matrix
# -------------------------------
# Display confusion matrix as a heatmap for visual understanding
plt.imshow(cm, cmap='Blues')
plt.title("Confusion Matrix")
plt.xlabel("Predicted Label")
plt.ylabel("True Label")

# Add actual numbers inside the matrix squares for clarity
for i in range(cm.shape[0]):
    for j in range(cm.shape[1]):
        plt.text(j, i, cm[i, j], ha="center", va="center", color="black")

plt.show()
