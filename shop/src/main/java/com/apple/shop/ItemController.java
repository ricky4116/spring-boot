package com.apple.shop;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

//    @Autowired
//    public ItemController(ItemRepository itemRepository,ItemService itemService){
//        this.itemRepository=itemRepository;
//        this.itemService=itemService;
//    }

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {

        itemService.saveItem(title,price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) throws Exception {

        Optional<Item> result = itemRepository.findById(id);
            if (result.isPresent()) {
                model.addAttribute("data", result.get());
                return "detail.html";
            } else {
                return "redirect:/list";
            }
    }


}
