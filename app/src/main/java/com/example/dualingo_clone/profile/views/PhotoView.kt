package com.example.dualingo_clone.profile.views

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.canhub.cropper.CropImage.CancelledResult.uriContent
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.dualingo_clone.R
import com.example.dualingo_clone.profile.models.ProfileViewState
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.Header

@Composable
fun PhotoView(
    viewState: ProfileViewState,
    onAcceptImage: ()->Unit,
    onBack: ()->Unit,
    onUpdateImage: (Bitmap?)->Unit,
) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            imageUri = result.uriContent
        } else {
            val exception = result.error
        }
    }

    LaunchedEffect(imageUri) {
        if (imageUri != null) {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
            bitmap = ImageDecoder.decodeBitmap(source)
            onUpdateImage.invoke(bitmap)
        }
    }

    val buttonModifier = Modifier
        .padding(bottom = 28.dp)
        .width(327.dp)
        .height(56.dp)

    Scaffold(
        topBar = {
            Column {
                Header(
                    text = "Your photo is gorgeous!",
                    modifier = Modifier
                        .padding(start = 24.dp),
                    backIcon = true,
                    onClick = onBack
                )
                BoldText(
                    text = "Choose your beautiful avatar!",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp)
                        .width(222.dp)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ButtonComponent(
                    text = "Load your image",
                    modifier = buttonModifier
                ) {
                    val cropOption = CropImageContractOptions(uriContent, CropImageOptions())
                    imageCropLauncher.launch(cropOption)
                }
                ButtonComponent(
                    text = "Accept your image",
                    modifier = buttonModifier,
                    onClick = onAcceptImage
                )
            }
        },
        content = { padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
                    .padding(top = 40.dp)
            ) {
                if (viewState.image != null) {
                    Image(
                        bitmap = viewState.image.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(300.dp)
                            .clip(CircleShape)
                            .background(Color.Blue)
                            .border(
                                width = 1.dp,
                                color = Color.Blue,
                                shape = CircleShape
                            )

                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Blue)
                            .size(300.dp)
                    )
                }
            }
        }
    )
}