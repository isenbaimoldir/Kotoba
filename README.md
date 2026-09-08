# Kotoba

Kotoba is an Android vocabulary app which helps any level of Japanese learners to search, save, organize, and learn collected vocabulary in one app.

## Features 

- Search vocabulary
- View information about a vocabulary
- Save vocabulary entries to personal study decks
- Create, rename, and delete vocabulary decks
- View and manage the vocabulary deck
- Remove vocabulary from a deck
- Mark vocabulary as learned or unlearned
- Study vocabulary from a selected deck
- View quiz results and learning progress
- Store vocabulary, decks, and learning progress locally for offline use

## Project Structure
```
Kotoba/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   ├── kotlin+java/
│   │   └── com.example.kotoba/
│   │       └── MainActivity.kt
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   ├── values/
│   │   │   ├── strings.xml
│   │   │   ├── colors.xml
│   │   │   └── themes.xml
│   │   ├── drawable/
│   │   └── mipmap/
│   └── build.gradle.kts
├── gradle/
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── .gitignore
└── README.md
```

## Build and Run Instructions
1. Clone the Repository
   'git clone https://github.com/isenbaimoldir/Kotoba'
2. Open the project folder in Android Studio
3. Let Gradle sync finish downloading dependencies
4. Click Run to build and launch the app

## Status
Currently this is only the idea of the app and it will partake future changes during the semester
