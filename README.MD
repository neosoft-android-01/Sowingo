# Sowingo App
Sowingo App list all Product with basic function like favorite particular product, serach and filtering based on Favourite items

----
## Android Setup ##

1. Install [Android Studio 4.0](https://developer.android.com/studio/archive)
   (click 'I agree' btn in order to see the archived versions)
2. Import Sowingo from Android Studio->File->New->Import Project

----

### App scaffolding

Package [`com.neosofttech.sowingo.ui`][1]

[1]: app/src/main/java/com/neosofttech/sowingo/ui provides theming related stuff
[2]: app/src/main/java/com/neosofttech/sowingo/ui/AppContainer  - App Container with TOpBar

### Products

Package [`com.neosofttech.sowingo.product.view`][3]
This package contains different custom Composable functions and combine them in a list
that scrolls vertically and horizontally.

See how to:

* Use `Row`s and `Column`s to arrange the contents of the UI
* Add an `AppBar`
* Use `Shape` to round the corners of the images
* Use elevation 

Package [`com.neosofttech.sowingo.product.data`][4]
This package contains retrofit config and expose methods to communicate with different service


Package [`com.neosofttech.sowingo.product.domain`][5]
This package contains repository and usecase to expose processed data to view layer

Package [`com.neosofttech.sowingo.di`][1]
This package contains dependecy generation and configuration required through out application 
