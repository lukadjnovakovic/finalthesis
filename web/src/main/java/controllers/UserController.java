package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import services.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    IUserService userService;
    private HttpServletRequest httpRequest;

    @GetMapping(value = "/users/{id}")
    public UserDTO getUserById(@PathVariable Integer id) throws Exception {
        return userService.returnUserById(id);
    }


    @PostMapping(value = "/login")
    public String returnUser(HttpEntity<String> httpRequest){
        UserDTO user = null;
        System.out.println(httpRequest.getBody());



        Gson gson = new Gson(); // Creates new instance of Gson
        JsonElement element = gson.fromJson (httpRequest.getBody(), JsonElement.class); //Converts the json string to JsonElement without POJO
        JsonObject jsonObj = element.getAsJsonObject(); //Converting JsonElement to JsonObject

        String username = jsonObj.get("username").getAsString(); //To fetch the values from json object
        String password = jsonObj.get("password").getAsString();

        System.out.println(username + password);


        return "evo ti gsooooon"+username;

//        try {
//            System.out.println(username+"dsadasdasd");
////          Searching for user by username and then checking his password
//            user = userService.returnUserByUsername(username);
//            if(!user.getPassword().equals(password)){
//                throw new Exception("Invalid password!");
//            }
//            httpRequest.getSession().setAttribute("loggedUser", user);
//            httpResponse.sendRedirect("/home/");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
