package com.batch.product

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ImportProductsIntegrationTest {

    @Autowired
    lateinit var jobLauncher: JobLauncher

    @Autowired
    lateinit var job: Job

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun importProducts() {
        val initial = jdbcTemplate.queryForObject("select count(1) from product", Int::class.java)
        jobLauncher.run(
            job,
            JobParametersBuilder()
                .addString("inputResource", "classpath:/data/input.zip")
                .addString("targetDirectory", "./src/main/resources/output/")
                .addString("targetFile", "products.txt")
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters()
        )
        val nbOfNewProducts = 5
        if (initial != null) {
            Assertions.assertThat(initial.plus(nbOfNewProducts)).isEqualTo(jdbcTemplate.queryForObject("select count(1) from product", Int::class.java))
        }
    }
}