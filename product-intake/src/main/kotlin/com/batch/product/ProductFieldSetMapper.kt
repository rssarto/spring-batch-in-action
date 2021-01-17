package com.batch.product

import com.batch.product.model.Product
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class ProductFieldSetMapper : FieldSetMapper<Product> {
    override fun mapFieldSet(fieldSet: FieldSet): Product {
        return Product(
            id = fieldSet.readString("PRODUCT_ID"),
            name = fieldSet.readString("NAME"),
            description = fieldSet.readString("DESCRIPTION"),
            price = fieldSet.readBigDecimal("PRICE")
        );
    }
}