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
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


     post("/home", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/home.vtl");

       String name = request.queryParams("name");
       request.session().attribute("name", name);
       Tamagotchi myTama = new Tamagotchi(name);
       request.session().attribute("myTama", myTama);

       model.put("name", request.session().attribute("name"));
       model.put("myTama", request.session().attribute("myTama"));
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     get("/checker", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/checker.vtl");
       model.put("name", request.session().attribute("name"));
       request.session().attribute("myTama");
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
