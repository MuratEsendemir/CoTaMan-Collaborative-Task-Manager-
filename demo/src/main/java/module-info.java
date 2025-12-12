module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.net.http;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    opens com.example to javafx.fxml;
    exports com.example;

    exports com.example.database;
    opens com.example.database to org.mongodb.bson;
}
