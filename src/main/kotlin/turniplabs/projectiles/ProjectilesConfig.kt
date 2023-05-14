package turniplabs.projectiles

import io.github.prismwork.prismconfig.api.PrismConfig
import io.github.prismwork.prismconfig.api.config.DefaultDeserializers
import io.github.prismwork.prismconfig.api.config.DefaultSerializers
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


// Copied from one of Flamarine's mods with permission, and rewritten in Kotlin
object ProjectilesConfig {
    fun load(file: File): Config {
        if (!file.name.endsWith(".json5"))
            throw RuntimeException("Failed to read Config")
        var cfg: Config? = null

        // Checks if the file exists, if so then run the try function
        if (file.exists()) {
            try {
                val reader = BufferedReader(FileReader(file))
                val builder: StringBuilder = StringBuilder()
                val ls: String = System.getProperty("line.separator")
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                    builder.append(ls)
                }
                builder.deleteCharAt(builder.length - 1)
                reader.close()

                val content: String = builder.toString()
                cfg = PrismConfig.getInstance().serialize(Config().javaClass, content, DefaultSerializers.getInstance().json5(Config::class.java))
            } catch (e: IOException) {
                throw RuntimeException()
            }
        }
        // If the Config is null, then make a new one with the contents below
        if (cfg == null) cfg = Config()
        save(file, cfg)
        return cfg
    }

    private fun save(file: File?, cfg: Config?) {
        PrismConfig.getInstance().deserializeAndWrite(
            Config::class.java,
            cfg,
            DefaultDeserializers.getInstance().json5(Config::class.java),
            file
        )
    }

    private var itemID = 1099

    fun nextItem(): Int {
        return itemID++
    }
    class Config {

        val eggArrow: Int = nextItem()
        val fireArrow: Int = nextItem()
        val iceArrow: Int = nextItem()
        val explosiveArrow: Int = nextItem()
        val lightningArrow: Int = nextItem()
        val teleportArrow: Int = nextItem()
    }
}