    package com.turkcell.product_service.application.usecases;

    import com.turkcell.product_service.application.dtos.request.CreateProductRequest;
    import com.turkcell.product_service.application.dtos.response.ProductListResponse;
    import com.turkcell.product_service.application.dtos.response.ProductResponse;
    import com.turkcell.product_service.application.dtos.request.UpdateProductRequest;
    import com.turkcell.product_service.application.mapper.ProductMapper;
    import com.turkcell.product_service.application.ports.ProductServicePort;
    import com.turkcell.product_service.domain.entities.Product;
    import com.turkcell.product_service.domain.repositories.ProductRepository;
    import com.turkcell.product_service.domain.valueobjects.Currency;
    import com.turkcell.product_service.domain.valueobjects.Price;
    import com.turkcell.product_service.domain.valueobjects.Stock;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.NoSuchElementException;
    import java.util.UUID;

    @Service
    public class ProductServiceImpl implements ProductServicePort {

        private final ProductRepository productRepository;
        private final ProductMapper productMapper;

        public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
            this.productRepository = productRepository;
            this.productMapper = productMapper;
        }


        @Override
        public ProductResponse createProduct(CreateProductRequest request) {
            Product product = productMapper.toDomain(request);
            Product savedProduct = productRepository.save(product);
            return productMapper.toResponse(savedProduct);
        }

        //TODO:: public static ProductId fromUUID(UUID id) {
        //    return new ProductId(id);} DOMAİNE EKLENEBİLİR
        @Override
        public ProductResponse getProductById(UUID id) {
            Product product = getProductEntityById(id);
            return productMapper.toResponse(product);
        }

        @Override
        public List<ProductListResponse> getAllProducts() {
            return productRepository.findAll().stream().map(productMapper::toListResponse).toList();
        }

        @Override
        public ProductResponse updateProduct(UUID id, UpdateProductRequest request) {
            Product product = getProductEntityById(id);

            // Ürün bilgilerini güncelle
            product.updateProduct(request.name(), request.description());

            // Fiyat ve currency dönüşümü
            product.updatePrice(new Price(request.price(), Currency.fromCode(request.currency())));

            // Stok güncelle
            product.updateStock(new Stock(request.stockQuantity()));

            Product updatedProduct = productRepository.save(product);

            return productMapper.toResponse(updatedProduct);
        }


        @Override
        public void deleteProduct(UUID id) {
            Product product = getProductEntityById(id);
            productRepository.deleteById(product.getId());
        }

        private Product getProductEntityById(UUID id) {
            Product.ProductId productId = Product.ProductId.fromString(id.toString());
            return productRepository.findById(productId)
                    .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        }

    }
