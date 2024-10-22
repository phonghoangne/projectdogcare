package com.app.specification;

import com.app.model.Product;
import com.app.payload.request.ProductRequest;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // specifcatin -> where co dieu dynamic
    // pageable
    public static Specification<Product> likeName(String name) {
        // select id  ,productName from product where productName like '% %'
        return ((root, query, criteriaBuilder) -> name == null ? criteriaBuilder.conjunction() : // where 1 =1
                criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<Product> equaProductCategory(Integer id) {
        // select id  ,productName from product where productName like '% %'
        return ((root, query, criteriaBuilder) -> id == null ? criteriaBuilder.conjunction() : // where 1 =1
                criteriaBuilder.equal(root.get("categoryId"), id));
    }

    public static Specification<Product> getSpect(ProductRequest request) {
        Specification<Product> specification = Specification.where(
                likeName(request.getName())
        );
        specification = specification.and(equaProductCategory(request.getCategoryId()));
        return specification;
    }


}
