package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Cart;
import com.skillnest.everythingsouvneirs.data.model.Item;
import com.skillnest.everythingsouvneirs.data.model.Product;
import com.skillnest.everythingsouvneirs.data.model.Quote;
import com.skillnest.everythingsouvneirs.data.repository.CartRepository;
import com.skillnest.everythingsouvneirs.data.repository.ItemRepository;
import com.skillnest.everythingsouvneirs.data.repository.ProductRepository;
import com.skillnest.everythingsouvneirs.data.repository.QuoteRepository;
import com.skillnest.everythingsouvneirs.dtos.request.CreateItemRequest;
import com.skillnest.everythingsouvneirs.dtos.response.ItemResponse;
import com.skillnest.everythingsouvneirs.exception.CartNotFoundException;
import com.skillnest.everythingsouvneirs.exception.ItemNotFoundException;
import com.skillnest.everythingsouvneirs.exception.ProductNotFoundException;
import com.skillnest.everythingsouvneirs.exception.QuoteNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final QuoteRepository quoteRepository;

    @Override
    public ItemResponse createItem(CreateItemRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        if (request.getCartId() != null) {
            Cart cart = cartRepository.findById(request.getCartId())
                    .orElseThrow(() -> new CartNotFoundException("Cart not found"));
            item.setCart(cart);
        }

        if (request.getQuoteId() != null) {
            Quote quote = quoteRepository.findById(request.getQuoteId())
                    .orElseThrow(() -> new QuoteNotFoundException("Quote not found"));
            item.setQuote(quote);
        }

        Item savedItem = itemRepository.save(item);
        return ItemMapper.mapToItemResponse(savedItem);
    }

    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemMapper::mapToItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponse getItemById(String id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        return ItemMapper.mapToItemResponse(item);
    }

    @Override
    public void deleteItem(String id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item not found");
        }
        itemRepository.deleteById(id);
    }
}
