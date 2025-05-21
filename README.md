**Quiz App - Android Application **

**1. Introduction**

This document provides detailed documentation for the Quiz App developed using Kotlin and Android Studio. The app is a simple quiz game where users answer music-related questions. It features UI animations, scoring logic, and the ability to restart the quiz.

**2. Features**

- Multiple choice questions with 3-5 answer options
- Visual feedback for correct and incorrect answers
- Card flip animation between questions
- Automatic scoring and quiz result display
- Restart quiz functionality

**3. Project File Overview**

The application is composed of the following key files:

• MainActivity.kt - Handles the quiz logic and UI interaction
• activity_main.xml - Defines the user interface layout
• out_animation.xml / in_animation.xml - Define the flip animations

**4. MainActivity.kt**

This is the core logic file of the application. It handles question display, user input, animations, scoring, and quiz restart functionality.

Key variables and their purpose:

- `questions`: Array of questions presented to the user
- `options`: Nested array of options for each question
- `correctAnswers`: Array holding the correct index per question
- `optionButtons`: List of button views for answer selection
- `restartButton`: Button to restart the quiz
- `questionContainer`: Layout that is animated during card flip

**5. How the App Works**

1. On app launch, the first question is displayed with available options.
2. User selects an answer by tapping a button.
3. The app evaluates the answer, shows color feedback (green for correct, red for incorrect).
4. A flip animation plays and after 2 seconds, the next question loads.
5. When all questions are answered, the final score is displayed.
6. The restart button resets the quiz to the first question.

**6. User Interface Layout**

The app layout is defined in XML with nested LinearLayouts for structure. Buttons are used for answers and a TextView is used for the question. The layout includes padding and styling for user readability.

**7. Flip Animation**

The app uses ObjectAnimators defined in XML (`out_animation.xml` and `in_animation.xml`) to simulate a card flip when transitioning between questions.

**8. Automated Testing**
Espresso UI testing is used to simulate user interaction. The test selects correct answers for all questions and checks that the score is calculated properly. An IdlingResource is used to wait for animations to complete.

**9. Conclusion**
This Quiz App demonstrates fundamental Android development skills including UI design, user input handling, animations, and automated UI testing. The modular structure makes it easy to expand with more questions or new features.

![image](https://github.com/user-attachments/assets/5f4ffdf8-422e-4065-9747-b0bd1c5e49d8)

![image](https://github.com/user-attachments/assets/e6428664-894b-4f6b-900e-33b179614acd)

![image](https://github.com/user-attachments/assets/70725d21-b620-47a4-b5ae-69bd62a56422)

![image](https://github.com/user-attachments/assets/2d12d6d4-5415-478e-8e84-04dbc8fc5689)









