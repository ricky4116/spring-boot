package com.apple.shop.notice;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeRepository noticeRepository;


    @GetMapping("/notice")
    String list(Model model){
        List<Notice> result = noticeRepository.findAll();
        model.addAttribute("notices",result);
        var a = new Notice();
        System.out.println(a.toString());
        return "notice.html";
    }
}
