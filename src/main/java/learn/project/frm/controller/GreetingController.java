package learn.project.frm.controller;

import jakarta.validation.Valid;
import learn.project.frm.domain.Role;
import learn.project.frm.domain.Topic;
import learn.project.frm.domain.User;
import learn.project.frm.repos.TopicRepository;
import learn.project.frm.repos.UserRepository;
import learn.project.frm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Controller
public class GreetingController {

    @Autowired
    private TopicRepository topicRepository;

    private final UserService userService;

    public GreetingController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String greeting(
            Model model
            //, @RequestParam(name = "name", required = false, defaultValue = "123") String name
    ) {

      //  model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            Model model) {
        System.out.println(user);
        Iterable<Topic> topics = topicRepository.findAll();
        model.addAttribute("topics", topics);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Topic topic,
            Model model) {


        topic.setSender(user);
        topicRepository.save(topic);
        Iterable<Topic> topics = topicRepository.findAll();
        model.addAttribute("topics", topics);

        return "main";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    private String addNewUser(@Valid User user,
                              Model model
                              ) {
        System.out.println(user.toString());
        if(userService.addUser(user)) {
            model.addAttribute("message", "User successfully registered!");
            return "login";
        }
        model.addAttribute("message", "Username already in use!");
        return "registration";
    }
}
