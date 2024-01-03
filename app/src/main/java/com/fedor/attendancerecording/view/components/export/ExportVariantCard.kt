package com.fedor.attendancerecording.view.components.export

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedor.attendancerecording.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportVariantCard(
    iconResId: Int,
    exportFormat: String,
    onCardClick: () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick = onCardClick
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(50.dp)
                .fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(iconResId),
                    contentDescription = null)
            }

            Box(
                modifier = Modifier
                    .weight(3f)
                    .padding(2.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.export_to), textAlign = TextAlign.Center)
                    Text(text = exportFormat, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Preview
@Composable
fun ExportVariantCardPreview(){
    ExportVariantCard(
        iconResId = R.drawable.export_excel,
        exportFormat = "xlsx",
        onCardClick = {}
    )
}