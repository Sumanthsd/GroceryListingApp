**Household Grocery Listing App**
A simple Android application built with Jetpack Compose that helps you manage your household grocery items across multiple stores (Flipkart, Big Basket, Swiggy, DMart, Amazon, Jio Mart).
You can add grocery items, mark them as completed, and maintain per-store pricing in editable columns.

**✨ Features**
📌 Add grocery items with names and prices.
🛍️ Manage multiple store price comparisons: Flipkart, Big Basket, Swiggy, DMart, Amazon, Jio Mart.
✅ Mark items as completed once purchased.
📊 Editable price fields for each store.
🖋️ Clean Material 3 UI using Jetpack Compose.

**🛠️ Tech Stack**
Kotlin
Jetpack Compose for UI
Material 3 Components
ViewModel + State management

**🚀 Getting Started**
Prerequisites
Android Studio
Kotlin 1.9+
Gradle 8+

**Setup**
Clone the repository:
git clone https://github.com/<your-username>/GroceryListingApp.git
Open the project in Android Studio.
Sync Gradle and build the project.
Run the app on an emulator or physical device.

**📂 Project Structure**
GroceryListingApp/
 ├── app/                  # Main app module
 │   ├── ui/               # Composables & UI screens
 │   ├── viewmodel/        # GroceryViewModel
 │   └── model/            # GroceryItem data class
 ├── build.gradle
 ├── settings.gradle
 └── README.md

**📝 Roadmap**
Add database (Room) for persistence
Cloud sync with Firebase
Dark mode support
Export grocery list as CSV/PDF

**🤝 Contributing**
Contributions are welcome!
Fork the repo

Create a new branch (feature/my-feature)

Commit your changes

Open a Pull Request
