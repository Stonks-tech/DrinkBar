# DrinkBar - Small android clean architecture demo app

This app was created as a small clean architecture showcase in android application. As the app is
small it does not incorporate feature-modules. In bigger apps feature-modules are a good way to
separate the app into smaller parts, which make maintenance by separate teams easier.

## Migrate XML to Compose Case Study

During development I decided to migrate from XML to Compose. Such migration with Clean Architecture
was pretty straight forward. The only thing I had to do was to create a new module for the compose
ui, and do some small changes in "Dirty Main" (`app` module). As you can see
in [Diff](https://github.com/Stonks-tech/DrinkBar/pull/2/commits/b8247c11081a59a89c14daf9ed4c3934044f0fdb)
none of the unrelated modules (like `presentation`, `xml-ui`, `domain`, `data`, `datasource`) had to
be changed. What is more, with some additional setup (separate build variant with separate
MainActivity for compose and xml), we could still develop xml and compose ui in parallel. That would
be useful if we wanted to migrate on production application.

