package cn.edu.ncut.controller;

import cn.edu.ncut.dao.UserDao;
import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public  String showAllUsers(Model model)
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
}
