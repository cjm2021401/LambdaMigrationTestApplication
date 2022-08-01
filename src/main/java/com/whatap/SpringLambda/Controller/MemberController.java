package com.whatap.SpringLambda.Controller;

import com.whatap.SpringLambda.Domain.Member;
import com.whatap.SpringLambda.Service.MemberService;
import com.whatap.SpringLambda.Util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    private final MemberService memberService;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/")
    public String Home(){ return "home";}

    @PostMapping("login")
    public String login(Member member, Model model, HttpSession session){
        if(memberService.login(member)){
            logger.info("Success to login and Make session for "+ member.getEmail());
            SessionUtil.setUser(session, member.getEmail());
            return "mail";
        }
        else{
            logger.warn("Fail to login");
            model.addAttribute("message", "fail");
            return "home";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }


}
