package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.CreateItemRequest;
import com.skillnest.everythingsouvneirs.dtos.response.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse createItem(CreateItemRequest request);

    List<ItemResponse> getAllItems();

    ItemResponse getItemById(String id);

    void deleteItem(String id);
}
