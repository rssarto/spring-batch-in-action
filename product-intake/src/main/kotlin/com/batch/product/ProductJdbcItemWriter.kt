package com.batch.product

import com.batch.product.model.Product
import org.springframework.batch.item.ItemWriter
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

class ProductJdbcItemWriter(dataSource: DataSource) : ItemWriter<Product> {
    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    object DbQueries {
        const val INSERT_PRODUCT = "insert into product (id,name,description,price) values(?,?,?,?)"
        const val UPDATE_PRODUCT = "update product set name=?, description=?, price=? where id=?"
    }

    override fun write(products: MutableList<out Product>) {
        for (product in products) {
            val update = jdbcTemplate.update(
                DbQueries.UPDATE_PRODUCT,
                product.name,
                product.description,
                product.price,
                product.id
            )
            if (update == 0) {
                jdbcTemplate.update(
                    DbQueries.INSERT_PRODUCT,
                    product.id,
                    product.name,
                    product.description,
                    product.price
                )
            }
        }
    }
}