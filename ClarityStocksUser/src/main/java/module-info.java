module ClarityStocksUser {
    requires com.google.gson;
    exports controller;
    exports userModel;
    opens userModel to com.google.gson;
}