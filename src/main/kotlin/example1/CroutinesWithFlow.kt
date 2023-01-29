package example1

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.Dispatchers
import java.io.File

data class LineData(val line: String)

/**
 * Request:
 * example code on Kotlin coroutines with flow for reading lines from some file and save data of lines to data base with some capacity of flow
 *
 * Source code chatGPT with errors:
 *
 * import kotlinx.coroutines.flow.*
 * import kotlinx.coroutines.Dispatchers
 * import java.io.File
 *
 * class LineData(val line: String)
 *
 * fun main() {
 *     val file = File("path/to/file.txt")
 *     val flow = file.bufferedReader().lineFlow()
 *
 *     flow
 *         .map { LineData(it) }
 *         .flowOn(Dispatchers.IO) // Move the flow to background thread
 *         .buffer(10) // Limit the capacity of the flow to 10 items
 *         .collect { lineData ->
 *             // Save the line data to the database
 *             saveToDatabase(lineData)
 *         }
 * }
 *
 * suspend fun saveToDatabase(lineData: LineData) {
 *     // Insert the line data to the database
 *     // ...
 * }
 *
 * RESULT working version:
 */
suspend fun main() {
    val flow: Flow<String> = File("src/main/resources/file.txt")
        .bufferedReader().lineSequence().asFlow()
        .flowOn(Dispatchers.IO).buffer(1)
    flow
        .map { LineData(it) }
        .collect { lineData ->
            // Save the line data to the database
            saveToDatabase(lineData)
        }
}

fun saveToDatabase(lineData: LineData) {
    println(lineData)
}
