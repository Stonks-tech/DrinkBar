# DrinkBar - Small android clean architecture demo app

This app was created as a small clean architecture showcase in android application. As the app is
small it does not incorporate feature-modules. In bigger apps feature-modules are a good way to
separate the app into smaller parts, which make maintenance by separate teams easier.

## Documentation

DrinkBar is fully documented with UML. There are diagrams but also class and methods descriptions for more complex
classes or concepts.

### Diagrams

The full-size diagram can be found (UNDER THIS
URL)[https://stonks-tech.github.io/DrinkBar/diagrams/a74bf0e3699da3f8e58beeb19b53e20a.svg].
And here is image preview:
<img src="https://stonks-tech.github.io/DrinkBar/diagrams/a74bf0e3699da3f8e58beeb19b53e20a.svg">

### HTML Documentation

The full documentation with class descriptions and relations can be found (UNDER THIS
URL)[https://stonks-tech.github.io/DrinkBar/] 
</br>
Packages should mirror package structure from the project so classes should be easily found in documentation.

## Screenshots

### Cocktail List (Main Screen)

<img src="https://raw.githubusercontent.com/Stonks-tech/DrinkBar/master/screenshots/drink_list.jpg" alt="Cocktail List Screenshot" width="400"/>

### Cocktail Details

<img src="https://raw.githubusercontent.com/Stonks-tech/DrinkBar/master/screenshots/drink_details.jpg" alt="Cocktail List Screenshot" width="400"/>

## Tech Stack

- UI - Jetpack Compose
- DI - Dagger Hilt
- Architecture - MVVM + Clean Architecture
- Navigation - Navigation Component
- Language - Kotlin

## Migrate XML to Compose Case Study

During development I decided to migrate from XML to Compose. Such migration with Clean Architecture
was pretty straight forward. The only thing I had to do was to create a new module for the compose
ui, and do some small changes in "Dirty Main" (`app` module). As you can see
in [Diff](https://github.com/Stonks-tech/DrinkBar/pull/2/commits/b8247c11081a59a89c14daf9ed4c3934044f0fdb)
none of the unrelated modules (like `presentation`, `xml-ui`, `domain`, `data`, `datasource`) had to
be changed. What is more, with some additional setup (separate build variant with separate
MainActivity for compose and xml), we could still develop xml and compose ui in parallel. That would
be useful if we wanted to migrate on production application.

