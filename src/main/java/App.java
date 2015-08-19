import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.out;
import java.lang.*;
import java.util.Timer;
import java.util.Map;
import java.util.HashMap;
import java.util.TimerTask;

import spark.ModelAndView;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";
    staticFileLocation("/public");



    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      request.session().attribute("myTama");

      //without this line, tama is not persisting when returning from var routes
      model.put("myTama", request.session().attribute("myTama"));

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


     post("/home", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/home.vtl");

      //name is to gather input to create tama instance
       String name = request.queryParams("name");

       //Create tama and story model in session
       Tamagotchi myTama = new Tamagotchi(name);
       request.session().attribute("myTama", myTama);
       model.put("myTama", request.session().attribute("myTama"));
  
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     get("/checker", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/checker.vtl");

//store in userInput in model so when return from another page its there still
       request.session().attribute("userInput");
       model.put("userInput", request.session().attribute("userInput"));

//creates an instance of the requestSession myTama object so can call timer on it
       Tamagotchi myTama = request.session().attribute("myTama");
       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
          public void run() {
            myTama.decrementLevels();     
          }
        },1000, 10000);
       

//makes the mytama created in home route available in this view
       request.session().attribute("myTama");
       model.put("myTama", request.session().attribute("myTama"));

       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     post("/checker", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/checker.vtl");
//gets userInput fromm radio buttons from form on checker view
       String userInput = request.queryParams("userInput");
       request.session().attribute("userInput", userInput);
       model.put("userInput", request.session().attribute("userInput"));

//creates an instance of the requestSession myTama object so can call methods on it
       Tamagotchi myTama = request.session().attribute("myTama");
       if (userInput.equals("feed")) {
         myTama.setFoodLevel(4);
       } else if (userInput.equals("sleep")) {
         myTama.setSleepLevel(6);
       }else if (userInput.equals("activity")) {
         myTama.setActivityLevel(3);
       }
      
       model.put("myTama", request.session().attribute("myTama"));
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());


    

 }
}
