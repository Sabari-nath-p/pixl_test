import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun  DetailedScreen(navController: NavController,) {

    Column(Modifier.padding(horizontal = 30.dp, vertical = 20.dp))
   {
       Text(text = "Second Screen", Modifier.clickable(
           onClick = {
               navController.popBackStack()
           }
       ))
   }

}