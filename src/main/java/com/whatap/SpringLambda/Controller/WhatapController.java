package com.whatap.SpringLambda.Controller;

import com.whatap.SpringLambda.Domain.Email;
import com.whatap.SpringLambda.Domain.Member;
import com.whatap.SpringLambda.Service.EmailService;
import com.whatap.SpringLambda.Service.MemberService;
import com.whatap.SpringLambda.Util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WhatapController {

    private final MemberService memberService;
    private final EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public WhatapController(EmailService emailService, MemberService memberService){
        this.emailService=emailService;
        this.memberService=memberService;
    }

    @PostMapping("send")
    public String sendmail(Email email, Model model, HttpSession session,HttpServletRequest request){
        String emailurl=SessionUtil.getUser(session);
        Member member =new Member();
        System.out.println(emailurl);
        member.setEmail(emailurl);
        try {
            emailService.SendEmail(email, member);
            memberService.addCount(emailurl);
            model.addAttribute("mail", "success");
        }catch (Exception e){
            logger.warn("Fail to send email");
            model.addAttribute("mail", "fail");
        }
        HttpSession now = request.getSession();
        now.invalidate();
        return "home";
    }

}
