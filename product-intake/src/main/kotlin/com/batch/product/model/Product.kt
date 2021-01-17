package com.batch.product.model

import java.math.BigDecimal

data class Product(val id: String, val name: String, val description: String, val price: BigDecimal) {
}