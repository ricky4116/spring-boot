package com.apple.shop.item;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.apple.shop.comment.CommentRepository;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentRepository commentRepository;

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
    String addPost(String title, Integer price,String imageurl) {

        itemService.saveItem(title,price,imageurl);
        return "redirect:/list";
    }

    @PostMapping("/editdata")
    String editPost(Long id, String title, Integer price) {
        itemService.editItem(id, title, price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) throws Exception {

        var comments = commentRepository.findByParentId(id);
        model.addAttribute("comments", comments);

        Optional<Item> result = itemRepository.findById(id);
            if (result.isPresent()) {
                model.addAttribute("data", result.get());
                return "detail.html";
            } else {
                return "redirect:/list";
            }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id,Model model) {

        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }


    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/list/page/{abc}")
    String getListPage(Model model, @PathVariable Integer abc) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(abc - 1, 5));

        model.addAttribute("items", result);
        model.addAttribute("totalPages", result.getTotalPages());

        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL (@RequestParam String filename){
        var result = s3Service.createPresignedUrl("test/"+filename);
        return result;
    }
}
