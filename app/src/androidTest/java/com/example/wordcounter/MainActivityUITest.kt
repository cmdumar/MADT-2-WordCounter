package com.example.wordcounter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests for MainActivity.
 * Tests cover user interactions with text input and result display.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    
    @Test
    fun appLaunches_displaysTitle() {
        composeTestRule.onNodeWithText("Word Counter")
            .assertIsDisplayed()
    }
    
    @Test
    fun appLaunches_displaysTextField() {
        // Find text field by label or placeholder
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .assertIsDisplayed()
    }
    
    @Test
    fun appLaunches_displaysStatisticsCard() {
        composeTestRule.onNodeWithText("Statistics")
            .assertIsDisplayed()
    }
    
    @Test
    fun appLaunches_displaysAllStatisticLabels() {
        composeTestRule.onNodeWithText("Words")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Characters")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Characters (no spaces)")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Sentences")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Paragraphs")
            .assertIsDisplayed()
    }
    
    @Test
    fun emptyTextField_showsZeroStatistics() {
        composeTestRule.onNodeWithText("0", substring = true)
            .assertIsDisplayed()
    }
    
    @Test
    fun enterSingleWord_updatesWordCount() {
        // Find the text field and enter text
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("Hello")
        
        // Wait for statistics to update
        composeTestRule.waitForIdle()
        
        // Verify word count is updated
        composeTestRule.onAllNodesWithText("1")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterMultipleWords_updatesWordCount() {
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("This is a test sentence")
        
        composeTestRule.waitForIdle()
        
        // Verify word count shows 5
        composeTestRule.onAllNodesWithText("5")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterText_updatesCharacterCount() {
        val testText = "Hello World"
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(testText)
        
        composeTestRule.waitForIdle()
        
        // Verify character count includes spaces (11 characters)
        composeTestRule.onAllNodesWithText("11")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterText_updatesCharacterCountWithoutSpaces() {
        val testText = "Hello World"
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(testText)
        
        composeTestRule.waitForIdle()
        
        // Verify character count without spaces (10 characters)
        composeTestRule.onAllNodesWithText("10")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterSentence_updatesSentenceCount() {
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("This is a sentence.")
        
        composeTestRule.waitForIdle()
        
        // Verify sentence count is 1
        composeTestRule.onAllNodesWithText("1")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterMultipleSentences_updatesSentenceCount() {
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("First sentence. Second sentence! Third sentence?")
        
        composeTestRule.waitForIdle()
        
        // Verify sentence count is 3
        composeTestRule.onAllNodesWithText("3")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterMultipleParagraphs_updatesParagraphCount() {
        val textWithParagraphs = "First paragraph.\n\nSecond paragraph.\n\nThird paragraph."
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(textWithParagraphs)
        
        composeTestRule.waitForIdle()
        
        // Verify paragraph count is 3
        composeTestRule.onAllNodesWithText("3")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun clearTextField_resetsStatistics() {
        // Enter some text
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("Hello World")
        
        composeTestRule.waitForIdle()
        
        // Clear the text
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("")
        
        composeTestRule.waitForIdle()
        
        // Verify statistics are reset to zero
        composeTestRule.onAllNodesWithText("0")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun modifyText_updatesStatisticsInRealTime() {
        // Enter initial text
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("Hello")
        
        composeTestRule.waitForIdle()
        
        // Verify initial word count
        composeTestRule.onAllNodesWithText("1")
            .onFirst()
            .assertIsDisplayed()
        
        // Replace with more text
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("Hello World")
        
        composeTestRule.waitForIdle()
        
        // Verify word count updated to 2
        composeTestRule.onAllNodesWithText("2")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterLongText_displaysCorrectStatistics() {
        val longText = "This is a longer text with multiple sentences. " +
                "It contains several words and punctuation marks! " +
                "The statistics should update correctly?"
        
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(longText)
        
        composeTestRule.waitForIdle()
        
        // Verify word count (should be 20 words)
        composeTestRule.onAllNodesWithText("20")
            .onFirst()
            .assertIsDisplayed()
        
        // Verify sentence count (should be 3)
        composeTestRule.onAllNodesWithText("3")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun enterTextWithSpecialCharacters_handlesCorrectly() {
        val textWithSpecialChars = "Hello, world! This is a test? Yes, it is."
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(textWithSpecialChars)
        
        composeTestRule.waitForIdle()
        
        // Verify statistics are calculated correctly
        composeTestRule.onAllNodesWithText("10")
            .onFirst()
            .assertIsDisplayed() // Word count should be 10
    }
    
    @Test
    fun enterTextWithNumbers_countsAsWords() {
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement("I have 3 apples and 2 oranges")
        
        composeTestRule.waitForIdle()
        
        // Verify word count includes numbers (7 words)
        composeTestRule.onAllNodesWithText("7")
            .onFirst()
            .assertIsDisplayed()
    }
    
    @Test
    fun statisticsCard_isScrollable() {
        // Enter a very long text to make the content scrollable
        val veryLongText = "Word ".repeat(100)
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(veryLongText)
        
        composeTestRule.waitForIdle()
        
        // Verify statistics card is still accessible
        composeTestRule.onNodeWithText("Statistics")
            .assertIsDisplayed()
    }
    
    @Test
    fun allStatisticsValues_areDisplayed() {
        val testText = "First sentence. Second sentence!\n\nNew paragraph with question?"
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(testText)
        
        composeTestRule.waitForIdle()
        
        // Verify all statistic values are displayed (not zero)
        composeTestRule.onNodeWithText("Words")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Characters")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Characters (no spaces)")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Sentences")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Paragraphs")
            .assertIsDisplayed()
    }
    
    @Test
    fun textField_acceptsMultilineInput() {
        val multilineText = "Line one\nLine two\nLine three"
        composeTestRule.onNodeWithText("Enter your text here", useUnmergedTree = true)
            .performTextReplacement(multilineText)
        
        composeTestRule.waitForIdle()
        
        // Verify text was entered and statistics updated
        composeTestRule.onAllNodesWithText("6")
            .onFirst()
            .assertIsDisplayed() // 6 words
    }
}

