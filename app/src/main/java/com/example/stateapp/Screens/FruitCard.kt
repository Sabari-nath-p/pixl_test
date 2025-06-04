import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FruitCard(navCtrl : NavController, model:Fruit){


    Row (Modifier.padding(vertical = 10.dp,).background(color = Color.White)){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp).clip(shape = CircleShape).background(color = Color.Blue.copy(alpha = .1f)) // Size of the circle
              // Background color
        ){
            Text(
                text = model.name.firstOrNull()?.toString() ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.width(10.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterVertically)
        ){

        Text(
                text = model.genus,
                color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
            Text(
                text = model.genus,
                color = Color.Black,
                fontSize = 12.sp,

                textAlign = TextAlign.Center
            )


        }
        Spacer(Modifier.weight(weight = 2f))
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = "Right arrow",
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )

    }
}