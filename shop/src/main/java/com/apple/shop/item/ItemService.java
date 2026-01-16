package com.apple.shop.item;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title,Integer price,String imageurl){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setImageurl(imageurl);
        itemRepository.save(item);
    }

    public void editItem(Long id, String title, Integer price) {
        Item item = new Item();
        if(title.length()>100) {
            throw new IllegalArgumentException("제목이 너무김");
        }
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);

        itemRepository.save(item);
    }

}
