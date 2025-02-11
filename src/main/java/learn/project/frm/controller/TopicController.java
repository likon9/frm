package learn.project.frm.controller;

import learn.project.frm.domain.Topic;
import learn.project.frm.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @GetMapping("/{topic}")
    public String main(
            @PathVariable Topic topic,
            Model model) {
        System.out.println();  System.out.println();  System.out.println();  System.out.println();  System.out.println();  System.out.println();
        System.out.println(topic.toString());
        System.out.println();  System.out.println();  System.out.println();  System.out.println();  System.out.println();  System.out.println();
        model.addAttribute("topic", topic);
        return "addAnswer";
    }
}
