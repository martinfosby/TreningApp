package com.example.treningapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.treningapp.data.ExerciseType
import com.example.treningapp.data.enduranceExercises
import com.example.treningapp.data.strengthExercises

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TreningAppTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testStrengthEnduranceButton() {
        // Set up your test environment
        rule.setContent { TreningApp() }
        val strengthText = rule.activity.getString(R.string.strength)
        val enduranceText = rule.activity.getString(R.string.endurance)



        val strengthButton = rule.onNodeWithText(strengthText)
        val enduranceButton = rule.onNodeWithText(enduranceText)

        strengthButton.assertIsEnabled()
        enduranceButton.assertIsNotEnabled()
    }

    @Test
    fun testStrengthAmount() {
        rule.setContent { AmountText(program = ExerciseType.STRENGTH) }
        val exerciseSetsText = rule.activity.getString(R.string.exercises_sets, strengthExercises.list.size, strengthExercises.totalSets)


        rule.onNodeWithText(exerciseSetsText).assertExists()

    }

    @Test
    fun testEnduranceAmount() {
        rule.setContent { AmountText(program = ExerciseType.ENDURANCE) }
        val exerciseSetsText = rule.activity.getString(R.string.exercises_sets, enduranceExercises.list.size, enduranceExercises.totalSets)

        rule.onNodeWithText(exerciseSetsText).assertExists()
    }

    @Test
    fun testSetExerciseCompletionStrength1() {
        // 1. Set up initial state
        val setInc = 2
        val exerciseInc = 0
        val initialSetsCompleted = strengthExercises.setsCompleted
        val expectedSetsCompletedAfterIncrement = initialSetsCompleted + setInc
        val initialExercisesCompleted = strengthExercises.exercisesCompleted
        val expectedExercisesCompletedAfterIncrement = initialExercisesCompleted + exerciseInc

        // 2. Set content and retrieve initial text
        rule.setContent { AmountText(program = ExerciseType.STRENGTH) }
        val setsCompletedText = rule.activity.getString(R.string.sets_completed, initialSetsCompleted)
        val exercisesCompletedText = rule.activity.getString(R.string.exercises_completed, initialExercisesCompleted)
        rule.onNodeWithText(setsCompletedText).assertExists()
        rule.onNodeWithText(exercisesCompletedText).assertExists()


        // 3. Simulate completion of a set and exercise
        strengthExercises.setsCompleted += setInc
        strengthExercises.exercisesCompleted += exerciseInc


        // 4. Construct expected text after increment
        val expectedCompletedSetsVal = rule.activity.getString(R.string.sets_completed, expectedSetsCompletedAfterIncrement)
        val expectedCompletedExercisesVal = rule.activity.getString(R.string.exercises_completed, expectedExercisesCompletedAfterIncrement)


        // 5. Assert that UI updates correctly
        rule.onNodeWithText(expectedCompletedSetsVal).assertExists()
        rule.onNodeWithText(expectedCompletedExercisesVal).assertExists()
    }

    @Test
    fun testSetExerciseCompletionStrength2() {
        // 1. Set up initial state
        val setInc = 6
        val exerciseInc = 2
        val initialSetsCompleted = strengthExercises.setsCompleted
        val expectedSetsCompletedAfterIncrement = initialSetsCompleted + setInc
        val initialExercisesCompleted = strengthExercises.exercisesCompleted
        val expectedExercisesCompletedAfterIncrement = initialExercisesCompleted + exerciseInc

        // 2. Set content and retrieve initial text
        rule.setContent { AmountText(program = ExerciseType.STRENGTH) }
        val setsCompletedText = rule.activity.getString(R.string.sets_completed, initialSetsCompleted)
        val exercisesCompletedText = rule.activity.getString(R.string.exercises_completed, initialExercisesCompleted)
        rule.onNodeWithText(setsCompletedText).assertExists()
        rule.onNodeWithText(exercisesCompletedText).assertExists()


        // 3. Simulate completion of a set and exercise
        strengthExercises.setsCompleted += setInc
        strengthExercises.exercisesCompleted += exerciseInc


        // 4. Construct expected text after increment
        val expectedCompletedSetsVal = rule.activity.getString(R.string.sets_completed, expectedSetsCompletedAfterIncrement)
        val expectedCompletedExercisesVal = rule.activity.getString(R.string.exercises_completed, expectedExercisesCompletedAfterIncrement)


        // 5. Assert that UI updates correctly
        rule.onNodeWithText(expectedCompletedSetsVal).assertExists()
        rule.onNodeWithText(expectedCompletedExercisesVal).assertExists()
    }

    @Test
    fun testSetExerciseCompletionStrength3() {
        // 1. Set up initial state
        val setInc = 5
        val exerciseInc = 1
        val initialSetsCompleted = strengthExercises.setsCompleted
        val expectedSetsCompletedAfterIncrement = initialSetsCompleted + setInc
        val initialExercisesCompleted = strengthExercises.exercisesCompleted
        val expectedExercisesCompletedAfterIncrement = initialExercisesCompleted + exerciseInc

        // 2. Set content and retrieve initial text
        rule.setContent { AmountText(program = ExerciseType.STRENGTH) }
        val setsCompletedText = rule.activity.getString(R.string.sets_completed, initialSetsCompleted)
        val exercisesCompletedText = rule.activity.getString(R.string.exercises_completed, initialExercisesCompleted)
        rule.onNodeWithText(setsCompletedText).assertExists()
        rule.onNodeWithText(exercisesCompletedText).assertExists()


        // 3. Simulate completion of a set and exercise
        strengthExercises.setsCompleted += setInc
        strengthExercises.exercisesCompleted += exerciseInc


        // 4. Construct expected text after increment
        val expectedCompletedSetsVal = rule.activity.getString(R.string.sets_completed, expectedSetsCompletedAfterIncrement)
        val expectedCompletedExercisesVal = rule.activity.getString(R.string.exercises_completed, expectedExercisesCompletedAfterIncrement)


        // 5. Assert that UI updates correctly
        rule.onNodeWithText(expectedCompletedSetsVal).assertExists()
        rule.onNodeWithText(expectedCompletedExercisesVal).assertExists()
    }


    @Test
    fun testSetExerciseCompletionEndurance1() {
        // 1. Set up initial state
        val setInc = 3
        val exerciseInc = 1
        val initialSetsCompleted = enduranceExercises.setsCompleted
        val expectedSetsCompletedAfterIncrement = initialSetsCompleted + setInc
        val initialExercisesCompleted = enduranceExercises.exercisesCompleted
        val expectedExercisesCompletedAfterIncrement = initialExercisesCompleted + exerciseInc

        // 2. Set content and retrieve initial text
        rule.setContent { AmountText(program = ExerciseType.ENDURANCE) }
        val setsCompletedText = rule.activity.getString(R.string.sets_completed, initialSetsCompleted)
        val exercisesCompletedText = rule.activity.getString(R.string.exercises_completed, initialExercisesCompleted)
        rule.onNodeWithText(setsCompletedText).assertExists()
        rule.onNodeWithText(exercisesCompletedText).assertExists()


        // 3. Simulate completion of a set and exercise
        enduranceExercises.setsCompleted += setInc
        enduranceExercises.exercisesCompleted += exerciseInc


        // 4. Construct expected text after increment
        val expectedCompletedSetsVal = rule.activity.getString(R.string.sets_completed, expectedSetsCompletedAfterIncrement)
        val expectedCompletedExercisesVal = rule.activity.getString(R.string.exercises_completed, expectedExercisesCompletedAfterIncrement)


        // 5. Assert that UI updates correctly
        rule.onNodeWithText(expectedCompletedSetsVal).assertExists()
        rule.onNodeWithText(expectedCompletedExercisesVal).assertExists()
    }

    @Test
    fun testSetExerciseCompletionEndurance2() {
        // 1. Set up initial state
        val setInc = 6
        val exerciseInc = 2
        val initialSetsCompleted = enduranceExercises.setsCompleted
        val expectedSetsCompletedAfterIncrement = initialSetsCompleted + setInc
        val initialExercisesCompleted = enduranceExercises.exercisesCompleted
        val expectedExercisesCompletedAfterIncrement = initialExercisesCompleted + exerciseInc

        // 2. Set content and retrieve initial text
        rule.setContent { AmountText(program = ExerciseType.ENDURANCE) }
        val setsCompletedText = rule.activity.getString(R.string.sets_completed, initialSetsCompleted)
        val exercisesCompletedText = rule.activity.getString(R.string.exercises_completed, initialExercisesCompleted)
        rule.onNodeWithText(setsCompletedText).assertExists()
        rule.onNodeWithText(exercisesCompletedText).assertExists()


        // 3. Simulate completion of a set and exercise
        enduranceExercises.setsCompleted += setInc
        enduranceExercises.exercisesCompleted += exerciseInc


        // 4. Construct expected text after increment
        val expectedCompletedSetsVal = rule.activity.getString(R.string.sets_completed, expectedSetsCompletedAfterIncrement)
        val expectedCompletedExercisesVal = rule.activity.getString(R.string.exercises_completed, expectedExercisesCompletedAfterIncrement)


        // 5. Assert that UI updates correctly
        rule.onNodeWithText(expectedCompletedSetsVal).assertExists()
        rule.onNodeWithText(expectedCompletedExercisesVal).assertExists()
    }

    @Test
    fun testSetExerciseCompletionEndurance3() {
        // 1. Set up initial state
        val setInc = 5
        val exerciseInc = 1
        val initialSetsCompleted = enduranceExercises.setsCompleted
        val expectedSetsCompletedAfterIncrement = initialSetsCompleted + setInc
        val initialExercisesCompleted = enduranceExercises.exercisesCompleted
        val expectedExercisesCompletedAfterIncrement = initialExercisesCompleted + exerciseInc

        // 2. Set content and retrieve initial text
        rule.setContent { AmountText(program = ExerciseType.ENDURANCE) }
        val setsCompletedText = rule.activity.getString(R.string.sets_completed, initialSetsCompleted)
        val exercisesCompletedText = rule.activity.getString(R.string.exercises_completed, initialExercisesCompleted)
        rule.onNodeWithText(setsCompletedText).assertExists()
        rule.onNodeWithText(exercisesCompletedText).assertExists()


        // 3. Simulate completion of a set and exercise
        enduranceExercises.setsCompleted += setInc
        enduranceExercises.exercisesCompleted += exerciseInc


        // 4. Construct expected text after increment
        val expectedCompletedSetsVal = rule.activity.getString(R.string.sets_completed, expectedSetsCompletedAfterIncrement)
        val expectedCompletedExercisesVal = rule.activity.getString(R.string.exercises_completed, expectedExercisesCompletedAfterIncrement)


        // 5. Assert that UI updates correctly
        rule.onNodeWithText(expectedCompletedSetsVal).assertExists()
        rule.onNodeWithText(expectedCompletedExercisesVal).assertExists()
    }


}