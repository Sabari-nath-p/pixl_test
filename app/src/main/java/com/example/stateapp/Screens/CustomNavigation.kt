import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomNavigation(title: String){

    Box(
        Modifier.background(color = Color.Blue)
    ){
        Row (Modifier.padding(top = 40.dp, bottom = 15.dp, start = 10.dp).background(color = Color.Blue.copy(alpha = .1f)).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,){

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "Right arrow",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
            Spacer(Modifier.width(10.dp))
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.W700, color = Color.White )

        }
    }

}