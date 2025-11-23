package main.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import main.User.service.UserService;
import main.web.dto.LoginRequest;
import main.web.dto.registerRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }
    @GetMapping("/login")
    public ModelAndView getLoginPage(@RequestParam(name = "loginAttemptMessage", required = false) String message, @RequestParam(name = "error", required = false) String errorMessage, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest", new LoginRequest());
        modelAndView.addObject("loginAttemptMessage", message);
        String inactiveUserMessage = (String) session.getAttribute("inactiveUserMessage");
        if (inactiveUserMessage != null) {
            modelAndView.addObject("inactiveAccountMessage", inactiveUserMessage);
        } else if (errorMessage != null) {
            modelAndView.addObject("errorMessage", "Invalid username or password");
        }

        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", new registerRequest());
        return modelAndView;

    }
    @PostMapping("/register")
    public ModelAndView register(@Valid registerRequest registerRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        userService.register(registerRequest);
        redirectAttributes.addFlashAttribute("successfulRegistration", "You have registered successfully");

        return new ModelAndView("redirect:/login");
    }
}
