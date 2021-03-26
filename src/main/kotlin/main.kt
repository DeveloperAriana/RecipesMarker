
val ingredients = mapOf<Int,String>(Pair(1,"Agua"), Pair(2,"Leche"),Pair(3,"Carne"),Pair(4,"Verduras"),Pair(5,"Frutas"),Pair(6,"Cereal"),Pair(7,"Huevos"),Pair(8,"Aceite"))

fun makeRecipe(recipe: MutableMap<String, MutableMap<Int, String>>) {

    val ingredientsSelected = mutableMapOf<Int, String>()
    val nameRecipe = nameOfRecipe()

    showIngredients()
    val recipe = saveRecipe(recipe,nameRecipe,selectIngredients(ingredientsSelected))
    val listOfIngredients = recipe.get(nameRecipe)

    println("""
===================================
        |Nueva receta añadida:
===================================
        |Nombre: ${nameRecipe}
        |Ingredientes:
    """.trimMargin())
    listOfIngredients?.values?.forEach { println("""* $it""") }
    println("===================================")

}

fun nameOfRecipe(): String? {
    println("Introduce el nombre de la receta:")
    return readLine()
}

fun showIngredients(){

    println("Elige los ingredientes:")
    for ((index,ingredient) in ingredients){
       println("${index} | $ingredient")
    }

}

fun saveRecipe(recipe: MutableMap<String, MutableMap<Int, String>>,nameRecipe: String?, listfIngredients: MutableMap<Int, String>): MutableMap<String, MutableMap<Int, String>> {

    recipe[nameRecipe.toString()] = listfIngredients
    return recipe

}

fun selectIngredients(ingredientsSelected: MutableMap<Int, String>): MutableMap<Int, String> {

    do{
        print("Opcion:")
        var option = readLine()

        if(option!!.toInt() !in 1..8)  {
            println("Esta opcion no esta disponible")
            break
        }

        val ingredient = ingredients[option?.toInt()]

        ingredientsSelected[ingredientsSelected.size + 1] = ingredient.toString()
        println("Añadido a la receta:  " + ingredientsSelected.getValue(ingredientsSelected.size))

    }while (option != "0")

    return ingredientsSelected
}

fun showRecipes(recipe: MutableMap<String, MutableMap<Int, String>>) {
    if(recipe.isNotEmpty()) {
        println("MIS RECETAS:")
        recipe.forEach {
            println(
                """
    ===================================
              Receta ${it.key}
    ===================================
    """.trimIndent()
            )
            println("Ingredientes:")
            it.value.forEach({ println("""* $it""") })
            println("===================================")
        }
    }else{
        println("No hay recetas en la lista, pulsa 1 para añadir una receta")
    }
}

fun main(args: Array<String>) {

    val recipe = mutableMapOf<String,MutableMap<Int,String>>()
    println(":: Bienvenido a Recipe Maker ::")

    do{

        println("""
            |Selecciona la opción deseada:
            | 1. Hacer una receta
            | 2. Ver mis recetas
            | 3. Salir""".trimMargin())

        println("-Opcion:")
        var option:String? = readLine()
        when(option){
             "1" -> makeRecipe(recipe)
             "2" -> showRecipes(recipe)
             "3" -> println("--:Ha finalizado el programa:--")
        }

    }while (option != "3")
}

