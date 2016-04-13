package cn.edu.ncut.controller;

import cn.edu.ncut.dao.UserDao;
import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lixiwei-mac
 * @create 22:54
 */
@Controller
@RequestMapping("/userController")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserDao userDao;

    @RequestMapping("/showAllUsers")
    public  String showAllUsers(@CookieValue(value="sessionId",required = false) String sessionId, Model model)
    {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "showUser";
    }
    @RequestMapping("addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse response,Model model){
        User user = new User();
        user.setName(request.getParameter("name").toString());
        user.setAge(Integer.parseInt(request.getParameter("age").toString()));
        userService.addUser(user);
        return "redirect:showAllUsers";
    }

    /**
     * MongoDB
     * @param model
     * @return
     */
    @RequestMapping("showAllMongoUser")
    public String showAllMongoUser(Model model)
    {
        String collectionName = "users";
        List<User> mongoUsers = userDao.findAll(collectionName);
        for (User user : mongoUsers)
        {
            System.out.println(user.toString());
        }
        model.addAttribute("mongoUsers",mongoUsers);
        return "showMongoUsers";
    }
}
