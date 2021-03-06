package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.annotation.ExecutionTimeLogger;
import ru.darvell.gb.spring.annotation.MethodRunLogger;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.nonpersist.FilterProductRequest;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.*;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.exception.ShopException;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.primary.ShopService;
import ru.darvell.gb.spring.service.product_type.ProductTypeService;
import ru.darvell.gb.spring.util.EntityValidator;
import ru.darvell.gb.spring.util.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static ru.darvell.gb.spring.util.ShopConstants.IMAGE_UPLOAD_LINK_PATTERN_V1;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final EntityValidator validator;
    private final ProductTypeService productTypeService;

    @Override
    public ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException {
        Product product = new Product(productDTO);
        product.setProductType(
                productTypeService.getById(productDTO.getTypeId()).orElseThrow(() -> new ShopEntityNotFoundException("Тип продукта не найден"))
        );
        Category category = categoryService.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ShopEntityNotFoundException("Категория не найдена"));

        product.setCategory(category);
        validator.checkShopEntity(product);

        return new ProductDTO(productService.saveOrUpdate(product));
    }


    @Override
    public ProductDTO getProductByIdOrEmpty(Long productId) {
        if (productId != null) {
            return new ProductDTO(productService.findById(productId).orElse(new Product()));
        } else {
            return new ProductDTO(new Product());
        }
    }

    @Override
    @ExecutionTimeLogger
    public ProductRestDTO getProductByIdForRest(Long productId) throws ShopEntityNotFoundException {
        ProductRestDTO productRestDTO = new ProductRestDTO(productService.findById(productId)
                .orElseThrow(() -> new ShopEntityNotFoundException("Продукт не найден")));
        return updateUploadImageLink(productRestDTO);
    }

    @Override
    @ExecutionTimeLogger
    @MethodRunLogger
    public Page<ProductRestDTO> getAllProductsPageable(FilterProductRequest filterProductRequest) {

        if (filterProductRequest.getCategoryTitle() != null && !filterProductRequest.getCategoryTitle().isBlank()) {
            categoryService.findByTitle(filterProductRequest.getCategoryTitle())
                    .ifPresent(c -> filterProductRequest.setCategoryId(c.getId()));
        }

        Pageable pageable = generatePageable(filterProductRequest);

        Page<Product> products = productService.getAllProductsFiltered(filterProductRequest, pageable);
        return new PageImpl<>(products.stream().map(ProductRestDTO::new).map(this::updateUploadImageLink).collect(Collectors.toList()), pageable, products.getTotalElements());
    }

    @Override
    public ProductsCostDTO getProductsCost(List<Long> productIds) {
        ProductsCostDTO result = new ProductsCostDTO();
        productService.getAllByIds(productIds).forEach(p -> result.addProductCost(p.getId(), p.getCost()));
        return result;
    }

    private Pageable generatePageable(FilterProductRequest filterProductRequest) {
        return PageRequest.of(filterProductRequest.getCurrPage(), filterProductRequest.getPageSize()
                , Sort.by(filterProductRequest.getSortDirection(), filterProductRequest.getSortField()));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            productDTO.setImageLink(pathImage.toString());
        }
        return saveOrUpdateProduct(productDTO);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addImageToProduct(Long productId, MultipartFile image) {
        saveWithImage(getProductByIdForRest(productId), image);
    }

    @Override
    public ProductRestDTO saveProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException {
        productRestDTO.setSaveImageLink(null);
        productRestDTO.setId(null);
        productRestDTO = new ProductRestDTO(saveOrUpdateProduct(productRestDTO));
        return updateUploadImageLink(productRestDTO);
    }

    @Override
    public ProductRestDTO updateProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException {
        getProductByIdForRest(productRestDTO.getId());
        productRestDTO = new ProductRestDTO(saveOrUpdateProduct(productRestDTO));
        return updateUploadImageLink(productRestDTO);
    }

    @Override
    public void deleteProductByID(Long productId) {
        log.info("product {}", productService.findById(productId).isPresent());
        productService.findById(productId).ifPresent(productService::deleteProduct);
    }

    @Override
    @ExecutionTimeLogger
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public Category saveOrUpdateCategoryWithoutParent(CategoryDTO categoryDTO) throws ShopException {
        Category category = new Category(categoryDTO);
        validator.checkShopEntity(category);
        return categoryService.saveOrUpdate(new Category(categoryDTO));
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) throws ShopException {
        categoryDTO.setId(null);
        return new CategoryDTO(saveOrUpdateCategory(categoryDTO));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws ShopException {
        if (categoryDTO.getParentCategoryId() != null && categoryDTO.getParentCategoryId().equals(categoryDTO.getId())) {
            categoryDTO.setParentCategoryId(null);
        }
        categoryDTO.setId(getCategoryById(categoryDTO.getId()).getId());
        return new CategoryDTO(saveOrUpdateCategory(categoryDTO));
    }

    private Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException {
        Category parentCategory = null;
        if (categoryDTO.getParentCategoryId() != null) {
            parentCategory = categoryService.findById(categoryDTO.getParentCategoryId())
                    .orElseThrow(() -> new ShopEntityNotFoundException("Не найдена родительская категория с таким ID"));
        }
        Category category = new Category(categoryDTO);
        category.setParentCategory(parentCategory);
        validator.checkShopEntity(category);
        return categoryService.saveOrUpdate(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws ShopEntityNotFoundException {
        Category category = getCategoryById(categoryId);
        categoryService.delete(category);
    }

    @Override
    public CategoryDTO getCategoryDTOById(Long categoryId) throws ShopEntityNotFoundException {
        return new CategoryDTO(getCategoryById(categoryId));
    }

    @Override
    @ExecutionTimeLogger
    public List<CategoryWithChildsDTO> getAllCategoryTrees() {
        return categoryService.getAllWithoutParents().stream().map(this::convertToCategoryWithChildNode).collect(Collectors.toList());
    }

    public CategoryWithChildsDTO convertToCategoryWithChildNode(Category category) {
        CategoryWithChildsDTO categoryDTO = new CategoryWithChildsDTO(category.getId(), category.getTitle());
        if (!category.getSubCategories().isEmpty()) {
            category.getSubCategories().forEach(
                    c -> categoryDTO.getChilds().add(convertToCategoryWithChildNode(c))
            );
        }
        return categoryDTO;
    }

    private Category getCategoryById(Long categoryId) throws ShopEntityNotFoundException {
        return categoryService.findById(categoryId).orElseThrow(() -> new ShopEntityNotFoundException("Не найдена категория с таким ID"));
    }

    private ProductRestDTO updateUploadImageLink(ProductRestDTO productRestDTO) {
        if (productRestDTO.getId() != null) {
            productRestDTO.setSaveImageLink(String.format(IMAGE_UPLOAD_LINK_PATTERN_V1, productRestDTO.getId()));
        }
        return productRestDTO;
    }


}
