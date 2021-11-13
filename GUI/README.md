GUI parts

To run in eclipse the package has to be named "application", it does not work under a (default package) package.

When running for the first time, go to run configurations, arguments. and add the following VM arguments:
--module-path "Path to where you have javafx installed" --add-modules javafx.controls,javafx.fxml

Also make sure you have javaFX in your build path libraries (modulepath).
