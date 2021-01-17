package com.batch.product.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource

@Configuration
@ImportResource(locations = ["classpath*:import-product-job-context.xml", "classpath*:test-context.xml"])
class XmlConfiguration {
}