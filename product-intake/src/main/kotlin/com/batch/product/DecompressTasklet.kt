package com.batch.product

import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.core.io.Resource
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.IllegalStateException
import java.util.*
import java.util.zip.ZipInputStream

class DecompressTasklet(
    private val inputResource: Resource,
    private val targetDirectory: String,
    private val targetFile: String
) : Tasklet {
    override fun execute(p0: StepContribution, p1: ChunkContext): RepeatStatus? {
        val zis = ZipInputStream(BufferedInputStream(inputResource.inputStream))
        val targetDirectoryAsFile = File(targetDirectory)

        if (!targetDirectoryAsFile.exists()) {
            FileUtils.forceMkdir(targetDirectoryAsFile)
        }

        val target = File(targetDirectory, targetFile)
        var dest: BufferedOutputStream
        while (Objects.nonNull(zis.nextEntry)) {
            if (!target.exists()) {
                target.createNewFile()
            }

            val fos = FileOutputStream(target)
            val dest = BufferedOutputStream(fos)
            IOUtils.copy(zis, dest)
            dest.flush()
            dest.close()
        }
        zis.close()
        if (!target.exists()) {
            throw IllegalStateException("Could not decompress anything from thr archive")
        }
        return RepeatStatus.FINISHED
    }
}