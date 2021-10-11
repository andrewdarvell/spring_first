package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.domain.dto.ProductRestDTO;
import ru.darvell.gb.spring.service.ShopService;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@RequestMapping(REST_URL_V1 + ADMIN_URL + PRODUCT_URL)
@RequiredArgsConstructor
@RestController
public class ProductAdminController {

    private final ShopService shopService;

    @PostMapping()
    public ResponseEntity<ProductRestDTO> addProduct(@RequestBody ProductRestDTO productRestDTO) {
        return ResponseEntity.ok(shopService.saveProduct(productRestDTO));

    }

    @PutMapping()
    public ProductRestDTO updateProduct(@RequestBody ProductRestDTO productRestDTO) {
        return shopService.updateProduct(productRestDTO);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable(name = "productId") Long productId) {
        shopService.deleteProductByID(productId);
    }

    @PostMapping("/{productId}/image")
    public ResponseEntity<Void> uploadProductImage(@PathVariable(name = "productId") Long productId,
                                                   @RequestParam(value = "image", required = false) MultipartFile image) {
        shopService.addImageToProduct(productId, image);
        return ResponseEntity.ok().build();
    }
}
