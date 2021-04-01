package springusermanager.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springusermanager.demo.entity.User;
import springusermanager.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    //DI call model step 2
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model){
        List<User> users = (List<User>) userRepository.findAll();
        //request.setAttributes("users",users);
        model.addAttribute("users", users);
        return "index";
    }
    @RequestMapping(value = "add")
    public String addUser(Model model){
        //request.setAttributes("users",users);
        model.addAttribute("users", new User());
        return "addUser";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user){
        userRepository.save(user);
        return "redirect:/";
    }
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model){
        Optional<User> userEdit = userRepository.findById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam("id") Long userId, Model model){
        userRepository.deleteById(userId);
        return "redirect:/";
    }
}
