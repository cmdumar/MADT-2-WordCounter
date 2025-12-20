package com.example.wordcounter

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the WordCounter class.
 * Tests cover all methods and various edge cases.
 */
class WordCounterTest {
    
    private lateinit var wordCounter: WordCounter
    
    @Before
    fun setUp() {
        wordCounter = WordCounter()
    }
    
    // ========== countWords() Tests ==========
    
    @Test
    fun countWords_emptyString_returnsZero() {
        assertEquals(0, wordCounter.countWords(""))
    }
    
    @Test
    fun countWords_blankString_returnsZero() {
        assertEquals(0, wordCounter.countWords("   "))
    }
    
    @Test
    fun countWords_singleWord_returnsOne() {
        assertEquals(1, wordCounter.countWords("Hello"))
    }
    
    @Test
    fun countWords_multipleWords_returnsCorrectCount() {
        assertEquals(5, wordCounter.countWords("This is a test sentence"))
    }
    
    @Test
    fun countWords_wordsWithMultipleSpaces_returnsCorrectCount() {
        assertEquals(3, wordCounter.countWords("Word1    Word2     Word3"))
    }
    
    @Test
    fun countWords_wordsWithTabsAndNewlines_returnsCorrectCount() {
        assertEquals(4, wordCounter.countWords("Word1\tWord2\nWord3 Word4"))
    }
    
    @Test
    fun countWords_leadingAndTrailingSpaces_returnsCorrectCount() {
        assertEquals(3, wordCounter.countWords("   Hello World Test   "))
    }
    
    @Test
    fun countWords_specialCharacters_returnsCorrectCount() {
        assertEquals(3, wordCounter.countWords("Hello, world! Test?"))
    }
    
    @Test
    fun countWords_numbersAsWords_returnsCorrectCount() {
        assertEquals(4, wordCounter.countWords("I have 3 apples and 2 oranges"))
    }
    
    // ========== countCharacters() Tests ==========
    
    @Test
    fun countCharacters_emptyString_returnsZero() {
        assertEquals(0, wordCounter.countCharacters(""))
    }
    
    @Test
    fun countCharacters_singleCharacter_returnsOne() {
        assertEquals(1, wordCounter.countCharacters("A"))
    }
    
    @Test
    fun countCharacters_stringWithSpaces_includesSpaces() {
        assertEquals(11, wordCounter.countCharacters("Hello World"))
    }
    
    @Test
    fun countCharacters_stringWithSpecialCharacters_includesAll() {
        assertEquals(13, wordCounter.countCharacters("Hello, World!"))
    }
    
    @Test
    fun countCharacters_multilineString_countsAllCharacters() {
        val text = "Line1\nLine2"
        assertEquals(11, wordCounter.countCharacters(text))
    }
    
    // ========== countCharactersWithoutSpaces() Tests ==========
    
    @Test
    fun countCharactersWithoutSpaces_emptyString_returnsZero() {
        assertEquals(0, wordCounter.countCharactersWithoutSpaces(""))
    }
    
    @Test
    fun countCharactersWithoutSpaces_stringWithSpaces_excludesSpaces() {
        assertEquals(10, wordCounter.countCharactersWithoutSpaces("Hello World"))
    }
    
    @Test
    fun countCharactersWithoutSpaces_stringWithMultipleSpaces_excludesAllSpaces() {
        assertEquals(10, wordCounter.countCharactersWithoutSpaces("Hello    World"))
    }
    
    @Test
    fun countCharactersWithoutSpaces_stringWithSpecialCharacters_includesSpecialChars() {
        assertEquals(12, wordCounter.countCharactersWithoutSpaces("Hello, World!"))
    }
    
    // ========== countSentences() Tests ==========
    
    @Test
    fun countSentences_emptyString_returnsZero() {
        assertEquals(0, wordCounter.countSentences(""))
    }
    
    @Test
    fun countSentences_blankString_returnsZero() {
        assertEquals(0, wordCounter.countSentences("   "))
    }
    
    @Test
    fun countSentences_singleSentence_returnsOne() {
        assertEquals(1, wordCounter.countSentences("This is a sentence."))
    }
    
    @Test
    fun countSentences_multipleSentences_returnsCorrectCount() {
        assertEquals(3, wordCounter.countSentences("First sentence. Second sentence! Third sentence?"))
    }
    
    @Test
    fun countSentences_sentenceWithoutEnding_returnsOne() {
        assertEquals(1, wordCounter.countSentences("This is a sentence without ending"))
    }
    
    @Test
    fun countSentences_multiplePunctuationMarks_countsAsOne() {
        assertEquals(1, wordCounter.countSentences("Wow!!! That's amazing."))
    }
    
    @Test
    fun countSentences_sentencesWithSpacesAfterPunctuation_handlesCorrectly() {
        assertEquals(2, wordCounter.countSentences("First.   Second!"))
    }
    
    @Test
    fun countSentences_textWithOnlyPunctuation_returnsOne() {
        assertEquals(1, wordCounter.countSentences("..."))
    }
    
    // ========== countParagraphs() Tests ==========
    
    @Test
    fun countParagraphs_emptyString_returnsZero() {
        assertEquals(0, wordCounter.countParagraphs(""))
    }
    
    @Test
    fun countParagraphs_blankString_returnsZero() {
        assertEquals(0, wordCounter.countParagraphs("   "))
    }
    
    @Test
    fun countParagraphs_singleParagraph_returnsOne() {
        assertEquals(1, wordCounter.countParagraphs("This is a single paragraph."))
    }
    
    @Test
    fun countParagraphs_multipleParagraphs_returnsCorrectCount() {
        val text = "First paragraph.\n\nSecond paragraph.\n\nThird paragraph."
        assertEquals(3, wordCounter.countParagraphs(text))
    }
    
    @Test
    fun countParagraphs_paragraphsWithMultipleNewlines_returnsCorrectCount() {
        val text = "First paragraph.\n\n\nSecond paragraph."
        assertEquals(2, wordCounter.countParagraphs(text))
    }
    
    @Test
    fun countParagraphs_singleLineBreak_returnsOne() {
        assertEquals(1, wordCounter.countParagraphs("Line1\nLine2"))
    }
    
    @Test
    fun countParagraphs_paragraphsWithSpaces_handlesCorrectly() {
        val text = "First paragraph.\n  \nSecond paragraph."
        assertEquals(2, wordCounter.countParagraphs(text))
    }
    
    // ========== getStatistics() Tests ==========
    
    @Test
    fun getStatistics_emptyString_returnsAllZeros() {
        val stats = wordCounter.getStatistics("")
        assertEquals(0, stats.wordCount)
        assertEquals(0, stats.characterCount)
        assertEquals(0, stats.characterCountWithoutSpaces)
        assertEquals(0, stats.sentenceCount)
        assertEquals(0, stats.paragraphCount)
    }
    
    @Test
    fun getStatistics_complexText_returnsCorrectStatistics() {
        val text = "This is the first sentence. This is the second sentence!\n\nThis is a new paragraph with a question?"
        val stats = wordCounter.getStatistics(text)
        
        assertEquals(18, stats.wordCount)
        assertTrue(stats.characterCount > 0)
        assertTrue(stats.characterCountWithoutSpaces > 0)
        assertEquals(3, stats.sentenceCount)
        assertEquals(2, stats.paragraphCount)
    }
    
    @Test
    fun getStatistics_singleWord_returnsCorrectStatistics() {
        val stats = wordCounter.getStatistics("Hello")
        
        assertEquals(1, stats.wordCount)
        assertEquals(5, stats.characterCount)
        assertEquals(5, stats.characterCountWithoutSpaces)
        assertEquals(1, stats.sentenceCount)
        assertEquals(1, stats.paragraphCount)
    }
    
    @Test
    fun getStatistics_textWithOnlySpaces_returnsCorrectStatistics() {
        val stats = wordCounter.getStatistics("     ")
        
        assertEquals(0, stats.wordCount)
        assertEquals(5, stats.characterCount)
        assertEquals(0, stats.characterCountWithoutSpaces)
        assertEquals(0, stats.sentenceCount)
        assertEquals(0, stats.paragraphCount)
    }
    
    @Test
    fun getStatistics_multilineText_returnsCorrectStatistics() {
        val text = "Line one.\nLine two.\nLine three."
        val stats = wordCounter.getStatistics(text)
        
        assertEquals(6, stats.wordCount)
        assertTrue(stats.characterCount > 0)
        assertEquals(3, stats.sentenceCount)
        assertEquals(1, stats.paragraphCount)
    }
    
    // ========== Edge Cases and Integration Tests ==========
    
    @Test
    fun countWords_unicodeCharacters_handlesCorrectly() {
        assertEquals(2, wordCounter.countWords("Hello 世界"))
    }
    
    @Test
    fun countCharacters_unicodeCharacters_countsCorrectly() {
        assertEquals(8, wordCounter.countCharacters("Hello 世界"))
    }
    
    @Test
    fun getStatistics_veryLongText_handlesCorrectly() {
        val longText = "Word ".repeat(1000)
        val stats = wordCounter.getStatistics(longText)
        
        assertEquals(1000, stats.wordCount)
        assertTrue(stats.characterCount > 0)
    }
    
    @Test
    fun countWords_mixedWhitespace_handlesCorrectly() {
        assertEquals(4, wordCounter.countWords("Word1\tWord2\nWord3 Word4"))
    }
    
    @Test
    fun countSentences_abbreviations_handlesCorrectly() {
        // Note: This is a limitation - abbreviations like "Dr." will be counted as sentence endings
        // In a production app, you might want to handle this more intelligently
        assertEquals(2, wordCounter.countSentences("Dr. Smith went to the U.S.A. He was happy."))
    }
}

