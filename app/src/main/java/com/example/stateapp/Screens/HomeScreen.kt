import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navCtrl: NavController) {
    val fruitList = remember { mutableStateListOf<Fruit>() }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val fruits = ApiClient.apiService.getFruits().mapIndexed { i, fruit -> fruit.copy(id = "${fruit.name}_$i")}
            fruitList.clear()
            fruitList.addAll(fruits)
            Log.e("Fruit-List", fruits.toString())
            isLoading = false
        } catch (e: Exception) {
            Log.e("API-ERROR", "${e.message}")
        }
    }

    Column(Modifier.fillMaxSize()) {
        CustomNavigation("Fruit Listing")

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Blue)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .background(Color.White)
            ) {
                itemsIndexed(fruitList, key = { _, item -> item.name }) { index, fruit ->
                    var offsetY by remember { mutableStateOf(0f) }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragEnd = { offsetY = 0f },
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        offsetY += dragAmount.y

                                        val targetIndex = (index + offsetY / 100).roundToInt()
                                            .coerceIn(0, fruitList.lastIndex)

                                        if (targetIndex != index) {
                                            fruitList.removeAt(index)
                                            fruitList.add(targetIndex, fruit)
                                            offsetY = 0f
                                        }
                                    }
                                )
                            }
                    ) {
                        FruitCard(navCtrl, fruit)
                    }
                }
            }
        }
    }
}
