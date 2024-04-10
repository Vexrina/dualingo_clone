package com.example.dualingo_clone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dualingo_clone.R
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.ui.theme.fredokaFamily


@Composable
fun ImageComponent(imageResId: Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ThreeDotsComponent(
    dotSize: Dp = 8.dp,
    dotSpacing: Dp = 8.dp,
    modifier: Modifier,

    dotIndexInColorA: Int
) {
    val activeColor = AppTheme.colors.activeDot
    val nonActiveColor = AppTheme.colors.userListItem
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(if (dotIndexInColorA == 3) activeColor else nonActiveColor, CircleShape)
        )
        Box(modifier = Modifier.width(dotSpacing))
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(if (dotIndexInColorA == 2) activeColor else nonActiveColor, CircleShape)
        )
        Box(modifier = Modifier.width(dotSpacing))
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(if (dotIndexInColorA == 1) activeColor else nonActiveColor, CircleShape)
        )
    }
}

@Composable
fun BoldText(
    text: String,
    modifier: Modifier,
    textColor:Color = AppTheme.colors.boldText,
    fontSize: Int = 22,
    textAlign: TextAlign = TextAlign.Center
    ) {
    BasicText(
        text = text,
        style = TextStyle(
            fontSize = fontSize.sp,
            color = textColor,
            fontFamily = fredokaFamily,
            textAlign = textAlign,
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = modifier,
    )
}

@Composable
fun TextComponent(text: String, modifier: Modifier) {
    BasicText(
        text = text,
        style = TextStyle(
            fontSize = 15.sp,
            color = AppTheme.colors.text,
            fontFamily = fredokaFamily,
            textAlign = TextAlign.Center,
        ),
        modifier = modifier,
    )
}

@Composable
fun LabelWithLinkComponent(
    labelText: String,
    linkText: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(modifier = modifier) {
        Text(
            text = labelText,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.text,
                fontFamily = fredokaFamily,
            ),
        )
        Text(
            text = linkText,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.button,
                fontFamily = fredokaFamily,
            ),
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

@Composable
fun Label3WithLinkComponent(
    label1Text: String,
    label2Text: String,
    linkText: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(modifier = modifier) {
        Text(
            text = label1Text,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.text,
                fontFamily = fredokaFamily,
            ),
        )
        Text(
            text = linkText,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.button,
                fontFamily = fredokaFamily,
            ),
            modifier = Modifier.clickable(onClick = onClick)
        )
        Text(
            text = label2Text,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.text,
                fontFamily = fredokaFamily,
            ),
        )
    }
}

@Composable
fun ButtonComponent(
    text: String,
    modifier: Modifier,
    buttonColors: Color = AppTheme.colors.button,
    textAlign: TextAlign = TextAlign.Center,
    textColor: Color = Color.White,
    radius: Int = 12,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(radius),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColors),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = fredokaFamily,
                color = textColor,
                textAlign = textAlign,
                fontSize = 20.sp,
            )
        )
    }
}

@Composable
fun Header(
    text: String,
    modifier: Modifier,
    backgroundColor: Color = AppTheme.colors.splash,
    backIcon: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        if (backIcon) {
            Icon(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(start = 24.dp, bottom = 21.dp, top = 21.dp)
                    .height(27.dp)
                    .width(17.dp)
            )
        }
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                fontFamily = fredokaFamily
            ),
            modifier = modifier
                .align(Alignment.CenterVertically)

        )
    }
}


@Composable
fun HeaderMainScreen(
    imageResId: Int = R.drawable.avatar_placeholder,
    profileImage: ImageBitmap? = null,
    userName: String,
    onClick: () -> Unit,
){
    Column(
        modifier = Modifier
            .background(AppTheme.colors.splash)
            .fillMaxWidth()
            .height(135.dp)
    ) {
        if (profileImage==null){
            Image(
                painterResource(id =imageResId),
                contentDescription = "Avatar",
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 24.dp, top = 10.dp)
                    .size(54.dp)
                    .clip(CircleShape)
                    .clickable(onClick=onClick)
            )
        } else {
            Image(
                bitmap = profileImage,
                contentDescription = "Avatar",
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 24.dp, top = 10.dp)
                    .size(54.dp)
                    .clip(CircleShape)
                    .clickable(onClick=onClick)
            )
        }
        BoldText(
            text = "Hello, $userName",
            modifier = Modifier
                .padding(top=8.dp, start=24.dp),
            textColor = Color.White
        )
        TextComponent(
            text = "Are you ready for learning today?",
            modifier = Modifier
                .padding(top=5.dp, bottom = 11.dp, start=24.dp)
        )
    }
}

@Composable
fun HeaderProfile(
    imageResId: Int = R.drawable.avatar_placeholder,
    profileImage: ImageBitmap? = null,
    userName: String,
){
    Column(
        modifier = Modifier
            .background(AppTheme.colors.splash)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        if (profileImage==null){
            Image(
                painterResource(id =imageResId),
                contentDescription = "Avatar",
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 24.dp, top = 10.dp)
                    .size(134.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                bitmap = profileImage,
                contentDescription = "Avatar",
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 24.dp, top = 10.dp)
                    .size(134.dp)
                    .clip(CircleShape)
            )
        }
        BoldText(
            text = "Your profile, $userName",
            modifier = Modifier
                .padding(top=8.dp, start=24.dp),
            textColor = Color.White
        )
    }
}

enum class TextVisuals {
    Text, Password,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    enabled: Boolean = true,
    textVisuals: TextVisuals = TextVisuals.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: (@Composable () -> Unit)? = {},
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontFamily = fredokaFamily,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

            )
        },
        shape = RoundedCornerShape(16.dp),
        visualTransformation = when (textVisuals) {
            TextVisuals.Text -> VisualTransformation.None
            TextVisuals.Password -> PasswordVisualTransformation()
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = AppTheme.colors.field,
            unfocusedContainerColor = AppTheme.colors.field,
            disabledContainerColor = AppTheme.colors.field,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
        ),
        trailingIcon = trailingIcon,
    )
}

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    header: String,
    textFieldValue: String,
    enabled: Boolean = true,
    onTextFieldChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textVisuals: TextVisuals = TextVisuals.Text,
    trailingIcon: (@Composable () -> Unit)? = {},
) {
    Column(modifier = modifier)
    {
        Text(
            text = header,
            fontFamily = fredokaFamily,
            fontWeight = FontWeight.Medium
        )
        DTextField(
            enabled = enabled,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .height(56.dp),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            value = textFieldValue,
            onValueChange = onTextFieldChange,
            placeholder = header,
            textVisuals = textVisuals,
            trailingIcon = trailingIcon,
        )
    }
}

@Composable
fun TopUserItem(
    imageBitmap: ImageBitmap,
    userName: String,
    points: Int,
){
    Row(
        horizontalArrangement=Arrangement.Center,
        verticalAlignment=Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 24.dp, top = 10.dp, end = 24.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(AppTheme.colors.userListItem)
            .fillMaxWidth()
            .height(64.dp)
            .wrapContentHeight(),
    ){
        Image(
            bitmap = imageBitmap,
            contentDescription = "Avatar $userName",
            contentScale= ContentScale.Crop,
            modifier = Modifier
                .padding(start = 16.dp,)
                .height(24.dp)
                .width(36.dp)
                .clip(CircleShape)
        )
        Text(
            text = userName,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = fredokaFamily,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.boldText,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier
                .padding(start = 24.dp)
                .width(170.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        Text(
            text = "$points points",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = fredokaFamily,
                textAlign = TextAlign.End,
                color = AppTheme.colors.boldText,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier
                .padding(start = 1.dp, end = 13.dp)
                .width(70.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun ExcersiseCard(
    smile:String,
    text:String,
    backgroundColor: Color,
    index: Int,
    onClick: () -> Unit,
){
    Column(
        modifier=Modifier
            .padding(
                start=if(index%2==0) 24.dp else 20.dp,
                top=16.dp,
                end=if(index%2==1) 24.dp else 0.dp
            )
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .width(150.dp)
            .height(120.dp)
            .clickable(onClick=onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        BasicText(
            text = smile,
            modifier = Modifier,
            style = TextStyle(
                fontSize = 72.sp
            )
            )
        BasicText(
            text = text,
            modifier = Modifier
                .padding(top=0.dp),
            style = TextStyle(
                fontFamily = fredokaFamily,
                color = Color.White,
                fontSize = 18.sp
            )
        )
    }
}