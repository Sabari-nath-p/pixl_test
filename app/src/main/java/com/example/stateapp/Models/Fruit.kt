data class Fruit(
    val id: Int,
    val name: String,
    val family: String,
    val order: String,
    val genus: String,
    val nutritions: _Nutritions
)

data class _Nutritions(
    val calories: Double,
    val fat: Double,
    val sugar: Double,
    val carbohydrates: Double,
    val protein: Double
)
