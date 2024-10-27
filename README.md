# **Angry Birds Game in Java with libGDX**

This project is an **Angry Birds** inspired game developed using **Java** and the **libGDX framework**. The game is structured with a core gameplay loop, multiple levels, various bird and pig types, and dynamic block interactions. Users can sign up, log in, and play through different levels while tracking their high scores. The game follows **Object-Oriented Programming (OOP)** principles, making use of **inheritance**, **polymorphism**, **interfaces**, and **exception handling**. Additionally, **serialization** is used to save and restore game states, and **JUnit** tests are employed for testing.

---
## **Authors**

### 1. **Aaman Sheikh - 2023006**
### 2. **Akshat Singh - 2023064**

---
## **Project Structure**

The project is organized into the following main packages:

### 1. **Core Code**:
- **Exception Handling**:
  - `GameNotFoundException`: Custom exception thrown when a game is not found in the database or state file.
  - `UnableToRestartGameException`: Exception triggered when the game fails to restart due to errors in loading saved state.

### 2. **Model**:
- **Birds**:
  - `RedBird`: The standard bird with average speed and damage.
  - `BlueBird`: A bird that multiplies into three smaller birds after being launched.
  - `BlackBird`: A bird that can explode on impact, dealing more damage.
  - `YellowBird`: A bird that speeds up mid-flight for greater impact.
- **Blocks**:
  - `TNT`: Explosive block that detonates when hit, damaging nearby structures and pigs.
  - `Rock`: A strong block that requires multiple hits to break.
- **Pig**: Represents pigs in the game that can be defeated by birds or falling blocks.
- **Catapult**: Handles the launching of birds and their trajectory.
- **GameSettings**: Stores and manages game settings, including difficulty and sound preferences.
- **LevelIcon**: UI component representing level selection in the game.
- **UserManager**: Manages user data, including high scores, login credentials, and game states.

### 3. **Screen**:
- **HomeScreen**: The main screen presented to the user after login, where they can navigate to other sections.
- **Level1, Level2, Level3**: Classes for the respective levels, each containing unique block and pig setups.
- **Levels**: Manages the selection and loading of available levels.
- **LoadingScreen**: A transitional screen shown when the game is loading resources.
- **Login**: Allows users to log in to their account by validating credentials.
- **SignUpPage**: Enables new users to create accounts.
- **SettingPage**: Allows users to customize game settings like sound and difficulty.
- **StartingPage**: The first screen the player sees when launching the game.
- **LostPage**: Displayed when the player loses a level, offering the option to restart.
- **WonPage**: Shown when the player completes a level successfully.

### 4. **Util**:
- **TouchInputHandler**: Handles user input, such as touch or mouse drag to control the birds and interact with the game.
- **Main**: The entry point of the game. It initializes the game, loads assets, and starts the game loop.

### 5. **Build Configuration**:
- **build.gradle**: This file handles the project dependencies and build configurations, ensuring that libGDX and all necessary libraries are correctly included.

---

## **Core Features**

### **1. Birds and Pigs**
- **Bird Variants**: Red, Blue, Black, and Yellow birds each have unique abilities. Birds are launched from the catapult and follow a trajectory based on angle and speed.
- **Pig Variants**: Pigs come in different sizes and health levels. They can be destroyed by direct bird hits or falling blocks.

### **2. Blocks and Materials**
- **Different Materials**: Blocks can be made from wood, glass, stone, and TNT. These materials have different durability levels, and TNT blocks explode when hit.
- **Block Collisions**: The game features a simple physics system where blocks fall and interact with other elements in the game world when hit.

### **3. Levels**
- **Level1, Level2, Level3**: Each level is uniquely designed with different birds, blocks, and pig configurations. Completing all the levels unlocks new challenges.
- **Serialization**: The game state, including levels, bird usage, and pig status, is saved and restored using Java's serialization mechanism.

### **4. User Authentication**
- **Login/SignUp**: The game includes a user authentication system where players can sign up or log in. User credentials and high scores are managed by the `UserManager` class.
- **High Score Tracking**: The game records high scores for each level, which can be updated as players achieve better results.

### **5. Game Settings**
- **Customizable Settings**: Users can change the difficulty and toggle sound effects in the `SettingPage`. These settings are stored and applied across levels.

### **6. Exception Handling**
- **Custom Exceptions**: The game includes custom exceptions (`GameNotFoundException` and `UnableToRestartGameException`) to handle errors gracefully, such as missing game states or failures in restarting.

---

## **Dependencies**

- **libGDX**: This is the main framework used for game development, providing graphics rendering, input handling, and other game development utilities.
- **JUnit**: Used for unit testing to ensure that game logic, like bird physics and high score updates, works as expected.

### **External Libraries**:
- **libGDX Core**: For cross-platform game development.
- **libGDX Box2D**: For implementing the physics engine.

---

## **How to Run the Project**

### **1. Prerequisites**
- Install **Java Development Kit (JDK)** (version 8 or later).
- Install **Gradle** for managing dependencies.
- Install **libGDX**.

### **2. Setup**
1. Clone the repository.
2. Navigate to the project directory.
3. Run `gradle build` to download all dependencies and build the project.
4. Run `gradle run` to start the game.


---

# **OOPS Concepts Applied**

This project adheres to the core **Object-Oriented Programming (OOP)** principles, including **Encapsulation**, **Polymorphism**, **Inheritance**, and **Abstraction**.

### **1. Encapsulation**
- All the classes have their attributes encapsulated, ensuring data security and controlled access. 
- For example, in all of the bird classes (`YellowBird`, `RedBird`, `BlueBird`, `BlackBird`), each class has its own private attributes like `health` and `stage`. These attributes are accessible only via **getters and setters**, ensuring proper encapsulation.

### **2. Polymorphism**
- Each of the four bird classes (`YellowBird`, `RedBird`, `BlueBird`, `BlackBird`) overrides common methods inherited from the `Bird` superclass, such as `launch()`, `attack()`, and the **getters and setters** for the `health` attribute.
- This demonstrates **polymorphism**, where different bird objects exhibit different behaviors for the same method (e.g., `launch()`), based on their specific type.

### **3. Inheritance**
- The bird classes (`YellowBird`, `RedBird`, `BlueBird`, `BlackBird`) extend the base `Bird` class, inheriting its core functionalities and attributes. 
- This **inheritance** allows each bird class to share common behaviors, such as `launch()` and `attack()`, while also implementing unique behaviors (e.g., `BlueBird` can multiply, `BlackBird` can explode).

### **4. Abstraction**
- The project uses **abstraction** to hide unnecessary details and expose only the relevant functionalities.
- For instance, the `Bird` class is an abstract class that defines common attributes and methods for all birds. However, the specific details of each bird's behavior are abstracted away in the derived classes like `RedBird` or `YellowBird`. This simplifies the interaction with bird objects while allowing for extensibility.

This ensures that the project remains modular, maintainable, and follows industry best practices in software development using OOP principles.

---


## **Future Enhancements**
- **Multiplayer Support**: Add a multiplayer mode where two players can compete against each other.
- **Dynamic Level Generator**: Implement a random level generator for infinite gameplay.
- **Enhanced Graphics**: Upgrade the game's visual elements with animated sprites and better textures.

---

# **Byte Me! - Game Project Contribution Log**

### **Contributors**  
This document provides an overview of the contributions made by each member of the team, detailing their roles, classes implemented, and the specific tasks they performed to complete the project successfully.

---

## **1. Aaman Sheikh**

### Contributions:

- **Model Classes:**
  - Created key game components by developing the classes for:
    - `BlackBird`: Handles the black bird character attributes and behaviors.
    - `Block`: Defines game blocks and their interactions.
    - `BlueBird`: Implements blue bird-specific features and properties.
    - `Catapult`: Manages catapult loading and launching mechanisms.
    - `LevelIcon`: Represents levels' icons on selection screens.
    - `RedBird`: Red bird class to set specific behaviors.
    - `Rock`: Implements rock properties and interactions.
    - `Tnt`: Defines the TNT explosive object, including damage handling.
    - `YellowBird`: Manages the yellow bird character traits.

- **Screen Classes:**
  - Developed classes representing key screens in the game interface:
    - `HomeScreen`: Main screen for accessing various game options.
    - `Level1`, `Level2`, `Level3`: Individual levels with specific configurations.
    - `Levels`: Provides an overview of all available levels.
    - `LoadingScreen`: Displays the loading process for smooth transitions.
    - `Login`: Manages user login functionality.
    - `SignUp`: Enables new users to sign up and register.
    - `StartingPage`: Initial launch page of the game.

- **Main Class:**
  - Developed the primary entry point and main execution logic for initializing and running the game.

- **Workflow Management:**
  - Ensured smooth workflow across the entire game by coordinating interdependencies among classes.
  - Verified the integration between different components.

- **Debugging:**
  - Conducted comprehensive debugging across all classes and screens, resolving logical, runtime, and performance issues.

---

## **2. Akshat Singh**

### Contributions:

- **Exception Classes:**
  - Created specialized exception classes to handle game-specific errors:
    - `GameNotFoundException`: Exception handling for missing game files or resources.
    - `UnableToRestartGameException`: Manages errors occurring during game restart attempts.

- **Utility Class:**
  - Developed `TouchInputHandler`: Handles user interactions, converting touch input into in-game actions to enable smooth, responsive gameplay.

- **Model Classes:**
  - Designed additional core game models:
    - `Pig`: Implements the pig characters, detailing interactions, hit points, and behaviors.
    - `GameSettings`: Manages game configuration settings, including difficulty, sound, and display settings.
    - `UserManager`: Handles user data and account management, including login, registration, and profile management.

- **Screen Classes:**
  - Created specific game screens to manage game results and settings:
    - `SettingPage`: Provides options to modify game settings.
    - `Loss`: Displays when the player loses a level, with options to retry or return to the main menu.
    - `Win`: Celebratory screen shown upon winning a level, displaying the score and options for the next steps.

- **Readme Documentation:**
  - Authored the `README.md` file to document game objectives, usage instructions, and setup details.

- **Debugging Assistance:**
  - Assisted in debugging the program, focusing on UI/UX bugs and optimizing gameplay experience.

---

### **Team Achievements**

- **Code Workflow and Debugging:**
  - The team collaborated to establish a well-defined workflow, enabling efficient debugging and seamless integration among game components.
  - Aaman Sheikh led the primary debugging, while Akshat Singh focused on ensuring a stable user experience by refining exception handling and game settings management.

---

👾💻 Thanks for checking out this project! We hope you enjoy playing **Angry Birds** with a twist!
