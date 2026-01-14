package com.apple.shop;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;


    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);
        var a = new feature();
        a.setName("홍길동");
        a.setAge(20);
        a.나이설정(-10);
        a.한살더하기();
        System.out.println(a.getAge());

        var b= new Item();
        b.setTitle("hello world");
        System.out.println(b.getTitle());
        return "list.html";
    }
}
