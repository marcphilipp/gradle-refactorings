import com.google.common.hash.Hashing
import com.google.common.io.Files
import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

open class Checksum @Inject constructor(objects: ObjectFactory) : DefaultTask() {

    @InputFile
    @PathSensitive(PathSensitivity.NONE)
    val inputFile = objects.fileProperty()

    @OutputFile
    val checksumFile = objects.fileProperty().convention {
        File("${inputFile.get().asFile}.sha512")
    }

    @TaskAction
    fun generateChecksum() {
        val hashCode = Files.asByteSource(inputFile.get().asFile).hash(Hashing.sha512())
        checksumFile.get().asFile.writeText(hashCode.toString())
    }
}
