package com.example.kursovayaclient1.login;

import com.example.kursovayaclient1.HelloApplication;
import com.squareup.okhttp.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML
    TextField loginTf;

    @FXML
    PasswordField passwTf;

    @FXML
    Label errrField;

    public void submit(ActionEvent actionEvent) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8080/login").newBuilder();

        // Add query parameters to the URL
        urlBuilder.addQueryParameter("login", loginTf.getText().trim());
        urlBuilder.addQueryParameter("password", passwTf.getText().trim());

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {

        try {
            Response response = client.newCall(request).execute();
            // Get the response body as a string
            ResponseBody responseBody = response.body();
            String responseBodyString = responseBody.string();

            // Process the response
            if (response.isSuccessful()) {
                // Request was successful
                System.out.println("Response: " + responseBodyString);
                return responseBodyString;
            } else {
                // Request failed
                System.out.println("Request failed with code: " + response.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
        }};

        task.setOnSucceeded(event->{
            errrField.setText(task.getValue());
            try {
                loadPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        task.setOnFailed(event->{
            errrField.setText(task.getValue());
        });

        new Thread(task).start();
    }

    void loadPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent loaded = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(loaded, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
