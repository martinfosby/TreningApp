package com.example.treningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.treningapp.data.EnduranceExercises
import com.example.treningapp.data.Exercise
import com.example.treningapp.data.ExerciseType
import com.example.treningapp.data.StrengthExercises
import com.example.treningapp.data.enduranceExercises
import com.example.treningapp.data.strengthExercises
import com.example.treningapp.ui.theme.TreningAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreningAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TreningApp()
                }
            }
        }
    }
}

@Composable
fun TreningApp(modifier: Modifier = Modifier) {
    var program by remember {
        mutableStateOf(ExerciseType.STRENGTH)
    }
    Scaffold(
        topBar = {
            TreningTopAppBar(modifier = Modifier.fillMaxWidth())
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            StrengthEnduranceButtons(
                strengthOnClick = { program = ExerciseType.STRENGTH },
                enduranceOnClick = { program = ExerciseType.ENDURANCE },
                enduranceEnabled = false
            )
            AmountText(program = program)
            ExerciseList(program = program, paddingValues = paddingValues)
        }
    }
}

@Composable
fun StrengthEnduranceButtons(
    strengthOnClick: () -> Unit,
    enduranceOnClick: () -> Unit,
    strengthEnabled: Boolean = true,
    enduranceEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = strengthOnClick, enabled = strengthEnabled) {
            Text(text = stringResource(id = R.string.strength), style = MaterialTheme.typography.labelLarge)
        }
        Button(onClick = enduranceOnClick, enabled = enduranceEnabled) {
            Text(text = stringResource(id = R.string.endurance), style = MaterialTheme.typography.labelLarge)
        }

    }
}

@Composable
fun AmountText(program: ExerciseType, modifier: Modifier = Modifier) {
    when (program) {
        ExerciseType.STRENGTH -> {
            val strengthExercises = strengthExercises
            val strengthText = stringResource(R.string.exercises_sets, strengthExercises.list.size, strengthExercises.totalSets)
            val completedText = stringResource(R.string.exercises_completed, strengthExercises.exercisesCompleted)
            val setsCompletedText = stringResource(R.string.sets_completed, strengthExercises.setsCompleted)
            Text(text = strengthText, style = MaterialTheme.typography.displayMedium)
            Text(text = completedText, style = MaterialTheme.typography.displayMedium)
            Text(text = setsCompletedText, style = MaterialTheme.typography.displayMedium)
        }
        ExerciseType.ENDURANCE -> {
            val enduranceExercises = enduranceExercises
            val enduranceText = stringResource(R.string.exercises_sets, enduranceExercises.list.size, enduranceExercises.totalSets)
            val completedText = stringResource(R.string.exercises_completed, enduranceExercises.exercisesCompleted)
            val setsCompletedText = stringResource(R.string.sets_completed, enduranceExercises.setsCompleted)
            Text(text = enduranceText, style = MaterialTheme.typography.displayMedium)
            Text(text = completedText, style = MaterialTheme.typography.displayMedium)
            Text(text = setsCompletedText, style = MaterialTheme.typography.displayMedium)
        }
    }
}

@Composable
fun ExerciseList(program: ExerciseType, paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = paddingValues) {
        when (program) {
            ExerciseType.STRENGTH -> {
                items(strengthExercises.list) { exercise ->
                    ExerciseItem(
                        exercise = exercise,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
            ExerciseType.ENDURANCE -> {
                items(enduranceExercises.list) { exercise ->
                    ExerciseItem(
                        exercise = exercise,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreningTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.logo_size))
                        .padding(dimensionResource(R.dimen.padding_small))
                        .background(Color.White),
                    painter = painterResource(R.drawable.trening_app_logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        },
        modifier = modifier
    )

}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                ExerciseIcon(exercise.imageResourceId)
                ExerciseInformation(exercise.name)
                Spacer(modifier = Modifier.weight(1f))
                ExerciseItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                ExerciseDescription(
                    exerciseDescription = exercise.description,
                    exercise = exercise,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun ClickableLink(url: String) {
    val uriHandler = LocalUriHandler.current

    val text = stringResource(id = R.string.watch_video_link)
    val annotatedString = AnnotatedString.Builder(text)
        .apply {
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 24.sp
                ),
                start = 0,
                end = text.length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = url,
                start = 10,
                end = 21
            )
        }
        .toAnnotatedString()

    Text(
        text = annotatedString,
        modifier = Modifier.clickable {
            annotatedString.getStringAnnotations("URL", 0, annotatedString.length)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}


@Composable
fun ExerciseDescription(
    @StringRes exerciseDescription: Int,
    exercise: Exercise,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        ClickableLink(url = exercise.link)

        Text(text = stringResource(id = exerciseDescription), style = MaterialTheme.typography.displayMedium)
        SetsForExercise(exercise = exercise)
    }
}

@Composable
fun SetsForExercise(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    Column {
        for (i in 1..exercise.sets) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SwitchSet(exercise = exercise, i - 1)
                Text(text = stringResource(id = R.string.set_done, i), style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun SwitchSet(exercise: Exercise, index: Int) {
    var isChecked by remember { mutableStateOf(exercise.setsSwitches[index]) }

    Switch(
        checked = isChecked,
        onCheckedChange = {
            isChecked = it
            exercise.setsSwitches[index] = it
            exercise.setsCompleted += if (isChecked) 1 else -1
            exercise.completed = exercise.setsCompleted == exercise.sets
            if (exercise.type == ExerciseType.STRENGTH) {
                strengthExercises.setsCompleted += if (isChecked) 1 else -1
                if (exercise.completed) strengthExercises.exercisesCompleted++
            }
            else if (exercise.type == ExerciseType.ENDURANCE) {
                enduranceExercises.setsCompleted += if (isChecked) 1 else -1
                if (exercise.completed) enduranceExercises.exercisesCompleted++
            }

        },
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    )
}

@Composable
private fun ExerciseItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ){
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }

}

@Composable
fun ExerciseIcon(
    @DrawableRes exerciseIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .background(Color.White),
        contentScale = ContentScale.Crop,
        painter = painterResource(exerciseIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

@Composable
fun ExerciseInformation(
    @StringRes exerciseName: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(exerciseName),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
    }
}