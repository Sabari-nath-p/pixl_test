import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomNavigation(title: String){

    Row (Modifier.padding(top = 30.dp, bottom = 15.dp, start = 10.dp)){

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowLeft,
            contentDescription = "Right arrow",
            modifier = Modifier.size(30.dp),
            tint = Color.Gray
        )
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.W700, )

    }

}