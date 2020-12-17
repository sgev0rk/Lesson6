import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

data class Person(
    val name: String,
    val age: Int,
    val numberOfRelatives: Int,
    val mother: Person? = null,
    val father: Person? = null,
    val majority: Boolean = age >= 18
)

fun main() {
    val myMother = Person("Tanya", 59, 4)
    val myFather = Person("Aram", 62, 5)
    val me = Person("Gevork", 35, 2, myMother, myFather)

    val filePathJson = "TestTree.json"
    val gson1 = GsonBuilder().setPrettyPrinting().create()
    val meToJson = gson1.toJson(me)

    File(filePathJson).writeText(meToJson)
    println("$meToJson\n")

    /*If file not found, or this file is empty*/
    try {
        val gson2 = Gson()
        if (File(filePathJson).readText() != "") {
            println(gson2.fromJson<Person>(File(filePathJson).readText(), Person::class.java))
        } else println("File is empty")
    } catch (e: java.io.FileNotFoundException) {
        println("File not found")
    }
}