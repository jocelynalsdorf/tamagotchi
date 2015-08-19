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
     
      request.session().attribute("myTama");
      model.put("template", "templates/home.vtl");

      //without this line, tama is not persisting when returning from var routes
      model.put("myTama", request.session().attribute("myTama"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


     post("/home", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/home.vtl");
      //gather input to create tama
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

       request.session().attribute("userInput");
       //store in model so when return from another page its there still
       model.put("userInput", request.session().attribute("userInput"));

       

       request.session().attribute("myTama");
       model.put("myTama", request.session().attribute("myTama"));
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     post("/checker", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/checker.vtl");

       String userInput = request.queryParams("userInput");
       request.session().attribute("userInput", userInput);
       model.put("userInput", request.session().attribute("userInput"));

       Tamagotchi myTama = request.session().attribute("myTama");

       if (userInput.equals("feed")) {
         myTama.setFoodLevel(4);
       } else if (userInput.equals("sleep")) {
         myTama.setSleepLevel(6);
       }else if (userInput.equals("activity")) {
         myTama.setActivityLevel(3);
       }

       request.session().attribute("name");

       model.put("name", request.session().attribute("name"));
       model.put("myTama", request.session().attribute("myTama"));
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());


//timer starts here
       Tamagotchi myTama = new Tamagotchi("Alice");
       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
          public void run() {
            myTama.decrementLevels();
            System.out.println(myTama.getFoodLevel());
          }
        },1000,20000);

 }
}
