package controllers;

import dto.GameDTO;

import dto.TipDTO;
import dto.UserDTO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.IGameService;
import services.ITeamService;
import services.ITipService;
import services.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ITeamService teamService;
    @Autowired
    private IGameService gameService;
    @Autowired
    private ITipService tipService;
    @Autowired
    private IUserService userService;


    @GetMapping("/error")
    public String renderError() {
        return "error";
    }

    @GetMapping("/login")
    public String renderLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String renderRegister() {
        return "register";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletResponse httpResponse, HttpServletRequest httpRequest) throws IOException {
        try {
//          Searching for user by username and then checking his password
            UserDTO user = userService.returnUserByUsername(username);

            if (user == null) {
                throw new Exception("Invalid username!");
            }
            if (!user.getPassword().equals(password)) {
                throw new Exception("Invalid password!");
            }
//            If the user is successfully authenticated, generate active session
            httpRequest.getSession().setAttribute("loggedUser", user);
//          Redirect to home page
            httpResponse.sendRedirect("/games");
        } catch (Exception e) {
            e.printStackTrace();
            httpResponse.sendRedirect("/error");
        }
        return null;
    }

    @RequestMapping("/home")
    public String renderGamesPage(HttpServletRequest request) {

        List<GameDTO> allGames = gameService.returnAllGames();
        List<TipDTO> allTips = tipService.returnAllTips();
        request.setAttribute("games", allGames);
        request.setAttribute("tips", allTips);

        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,@RequestParam String surname,@RequestParam String username, @RequestParam String password, HttpServletResponse httpResponse, HttpServletRequest httpRequest) throws IOException {

        UserEntity userEntity = new UserEntity(name,surname,username,password);

        userService.saveUser(userEntity);

        httpResponse.sendRedirect("/home");

        return "index";

    }
}
