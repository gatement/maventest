package net.johnsonlau.app;

import static spark.Spark.*;

public class App 
{
    public static void main(String[] args)
    {
        System.out.println("Good night, Johnson!");
        get("/", (req, res) -> "123");
        get("/hello", (req, res) -> "Hello World");
    }
}
