import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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

    var draggingItem: LazyListItemInfo? by remember { mutableStateOf(null) }
    var draggingItemIndex by remember { mutableStateOf<Int?>(null) }
    var delta by remember { mutableStateOf(0f) }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        try {
            val fruits = ApiClient.apiService.getFruits().mapIndexed { i, fruit -> fruit.copy(id = "${fruit.name}_$i") }
            fruitList.clear()
            fruitList.addAll(fruits)
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
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .background(Color.White)
                    .pointerInput(fruitList) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = { offset ->
                                listState.layoutInfo.visibleItemsInfo
                                    .firstOrNull { item ->
                                        offset.y.toInt() in item.offset..(item.offset + item.size)
                                    }?.also {
                                        draggingItem = it
                                        draggingItemIndex = it.index
                                    }
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                delta += dragAmount.y

                                val currentDraggingIndex = draggingItemIndex ?: return@detectDragGesturesAfterLongPress
                                val currentDraggingItem = draggingItem ?: return@detectDragGesturesAfterLongPress

                                val startOffset = currentDraggingItem.offset + delta
                                val endOffset = startOffset + currentDraggingItem.size
                                val middleOffset = (startOffset + endOffset) / 2

                                val targetItem = listState.layoutInfo.visibleItemsInfo.find { item ->
                                    middleOffset.toInt() in item.offset..(item.offset + item.size) &&
                                            item.index != currentDraggingIndex
                                }

                                if (targetItem != null) {
                                    val from = currentDraggingIndex
                                    val to = targetItem.index

                                    fruitList.apply {
                                        add(to, removeAt(from))
                                    }

                                    draggingItemIndex = to
                                    draggingItem = targetItem
                                    delta += currentDraggingItem.offset - targetItem.offset
                                }
                            },
                            onDragEnd = {
                                draggingItem = null
                                draggingItemIndex = null
                                delta = 0f
                            },
                            onDragCancel = {
                                draggingItem = null
                                draggingItemIndex = null
                                delta = 0f
                            }
                        )
                    }
            ) {
                itemsIndexed(fruitList, key = { _, item -> item.id }) { index, fruit ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        FruitCard(navCtrl, fruit)
                    }
                }
            }
        }
    }
}
