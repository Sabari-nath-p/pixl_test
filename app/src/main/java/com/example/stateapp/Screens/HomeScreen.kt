import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navCtrl : NavController){
    var fruits by remember { mutableStateOf<List<Fruit>>(emptyList()) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        try {
            fruits = ApiClient.apiService.getFruits()

            for(fruit in fruits) Log.e("Fruit-Name","${fruit}")
        } catch (e: Exception) {
            // Handle error here, maybe log or show a message
            Log.e("API-ERROR","${e.message}")
        }
    }


    Column {
        CustomNavigation("Fruit Listing")


        Column (Modifier.background(color = Color.White,).padding(horizontal = 20.dp).verticalScroll(scrollState).fillMaxSize()){
            Spacer(Modifier.height(20.dp))
            for (fruit in fruits) FruitCard(navCtrl,fruit )

        }
    }
}