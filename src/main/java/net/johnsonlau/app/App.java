package net.johnsonlau.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class App 
{
    public static void main(String[] args)
    {
        System.out.println("start...");

        get("/", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("fruits", Arrays.asList("apple", "banana", "orange", "peach"));
            return new ModelAndView(attributes, "fruitPicker.ftl");
        }, new FreeMarkerEngine());

        post("/favorite_fruit", (req, res) -> {
            final String fruit = req.queryParams("fruit");
            if(fruit == null)
                return "Why don't you pick one?";
            else
                return "Your favorite fruit is " + fruit;
        });

        get("/echo/:thing", (req, res) -> "echo: " + req.params(":thing"));

        get("/hello", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Have a nice day, Johnson!");

            // The hello.ftl file is located in directory:
            // src/main/resources/spark/template/freemarker
            return new ModelAndView(attributes, "hello.ftl");
        }, new FreeMarkerEngine());
    }
}
