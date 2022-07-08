package ru.kata.spring.boot_security.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/admin")
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }
    @GetMapping("/admin/create")
    public String createUserForm(User user, Model model){
        model.addAttribute("roleAdmin",roleService.getAdminRole());
        model.addAttribute("roleUser",roleService.getUserRole());
        return "create";
    }
    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.findById(id).getRoles().clear();
        userService.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/edit/{id}")
    public String editUserForm(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }
    @PostMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute("user") User user,@PathVariable("id") Long id,Model model) {
        User user1 =userService.findById(id);
        user.setRoles(user1.getRoles());
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
