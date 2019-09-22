import com.google.common.hash.Hashing
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.charset.StandardCharsets.UTF_8

class ChecksumTest {

    val gradleRunner = GradleRunner.create().withPluginClasspath()

    @BeforeEach
    fun setUp(@TempDir projectDir: File) {
        File(projectDir, "build.gradle").writeText("""
            buildscript {
                dependencies {
                    classpath(files(${gradleRunner.pluginClasspath
                            .map { "'${it.absolutePath}'" }
                            .joinToString(", ")
                    }))
                }
            }
        """)
    }

    @Test
    fun `computes checksum`(@TempDir projectDir: File) {
        File(projectDir, "input.txt").writeText("Hello World!")
        File(projectDir, "build.gradle").appendText("""
            task myChecksum(type: Checksum) {
                inputFile = file('input.txt')
            }
        """)

        gradleRunner
                .withProjectDir(projectDir)
                .withArguments("myChecksum")
                .build()

        val checksumFile = File(projectDir, "input.txt.sha512")
        assertTrue(checksumFile.exists(), "checksum file was written")
        val computedChecksum = checksumFile.readText()
        val expectedChecksum = Hashing.sha512().hashString("Hello World!", UTF_8).toString()
        assertEquals(expectedChecksum, computedChecksum)
    }
}
