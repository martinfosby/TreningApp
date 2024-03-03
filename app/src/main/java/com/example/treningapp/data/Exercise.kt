/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.treningapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.treningapp.R

/**
 * A data class to represent the information presented in the dog card
 */
enum class ExerciseType {
    STRENGTH(),
    ENDURANCE()
}

data class Exercise(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val sets: Int,
    @StringRes val description: Int,
    var completed: Boolean = false,
    var setsCompleted: Int = 0,
    var setsSwitches: MutableList<Boolean> = MutableList(sets) { false }, // Initialize the list with the size of sets
    val type: ExerciseType,
    val link: String = "www.uit.no"
)

object StrengthExercises {
    val list by mutableStateOf(mutableListOf<Exercise>())
    var exercisesCompleted by mutableStateOf(0)
    val totalSets: Int
        get() = list.sumOf { it.sets }
    var setsCompleted by mutableStateOf(0)

    fun addExercise(exercise: Exercise) {
        list.add(exercise)
    }

    fun removeExercise(exercise: Exercise) {
        list.remove(exercise)
    }

    fun clearExercises() {
        list.clear()
    }
}

object EnduranceExercises {
    val list by mutableStateOf(mutableListOf<Exercise>())
    var exercisesCompleted by mutableStateOf(0)
    val totalSets: Int
        get() = list.sumOf { it.sets }
    var setsCompleted by mutableStateOf(0)

    fun addExercise(exercise: Exercise) {
        list.add(exercise)
    }

    fun removeExercise(exercise: Exercise) {
        list.remove(exercise)
    }

    fun clearExercises() {
        list.clear()
    }
}


val strengthExercises = StrengthExercises.apply {
    addExercise(Exercise(R.drawable.bench_press, R.string.bench_press_name, 3, R.string.bench_press_description, type = ExerciseType.STRENGTH, link = "https://www.youtube.com/shorts/em8CUzILWxA"))
    addExercise(Exercise(R.drawable.shoulder_press, R.string.shoulder_press_name, 3, R.string.shoulder_press_description, type = ExerciseType.STRENGTH, link = "https://www.youtube.com/shorts/s8NWuW7cPoo") )
    addExercise(Exercise(R.drawable.lat_pulldown, R.string.lat_pulldown_name, 3, R.string.lat_pulldown_description, type = ExerciseType.STRENGTH, link = "https://www.youtube.com/shorts/2QyBnqLYvJA") )
    addExercise(Exercise(R.drawable.barbell_row, R.string.barbell_row_name, 3, R.string.barbell_row_description, type = ExerciseType.STRENGTH, link = "https://www.youtube.com/shorts/Ymyi_zYmgZE") )
    addExercise(Exercise(R.drawable.bicep_curl, R.string.bicep_curl_name, 3, R.string.bicep_curl_description, type = ExerciseType.STRENGTH, link = "https://www.youtube.com/shorts/oCoYJCDc-uc") )
    addExercise(Exercise(R.drawable.tricep_pushdown, R.string.tricep_pushdown_name, 3, R.string.tricep_pushdown_description, type = ExerciseType.STRENGTH, link = "https://www.youtube.com/shorts/M8KDwDTVQeA") )
}

val enduranceExercises = EnduranceExercises.apply {
    addExercise(
        Exercise(
            R.drawable.light_run,
            R.string.light_run_name,
            1,
            R.string.light_run_description,
            type = ExerciseType.ENDURANCE
        )
    )
    addExercise(
        Exercise(
            R.drawable.baseline_pedal_bike_24,
            R.string.bicycle_name,
            1,
            R.string.bicycle_description,
            type = ExerciseType.ENDURANCE
        )
    )
    addExercise(
        Exercise(
            R.drawable.baseline_rowing_24,
            R.string.rowing_name,
            1,
            R.string.rowing_description,
            type = ExerciseType.ENDURANCE
        )
    )
    addExercise(
        Exercise(
            R.drawable.baseline_pool_24,
            R.string.swimming_name,
            1,
            R.string.swimming_description,
            type = ExerciseType.ENDURANCE
        )
    )

}

