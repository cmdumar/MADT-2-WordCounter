package com.example.wordcounter

/**
 * Utility class for counting words, characters, sentences, and paragraphs in text.
 */
class WordCounter {
    
    /**
     * Counts the number of words in the given text.
     * A word is defined as a sequence of characters separated by whitespace.
     * 
     * @param text The input text to count words from
     * @return The number of words in the text
     */
    fun countWords(text: String): Int {
        if (text.isBlank()) return 0
        return text.trim()
            .split(Regex("\\s+"))
            .filter { it.isNotBlank() }
            .size
    }
    
    /**
     * Counts the total number of characters in the text (including spaces).
     * 
     * @param text The input text to count characters from
     * @return The total number of characters
     */
    fun countCharacters(text: String): Int {
        return text.length
    }
    
    /**
     * Counts the number of characters excluding spaces.
     * 
     * @param text The input text to count characters from
     * @return The number of characters without spaces
     */
    fun countCharactersWithoutSpaces(text: String): Int {
        return text.replace(" ", "").length
    }
    
    /**
     * Counts the number of sentences in the text.
     * A sentence is defined as text ending with '.', '!', or '?' followed by whitespace or end of string.
     * 
     * @param text The input text to count sentences from
     * @return The number of sentences
     */
    fun countSentences(text: String): Int {
        if (text.isBlank()) return 0
        val sentenceEndings = Regex("[.!?]+[\\s]*")
        val sentences = text.trim().split(sentenceEndings)
            .filter { it.isNotBlank() }
        return if (sentences.isEmpty() && text.isNotBlank()) 1 else sentences.size
    }
    
    /**
     * Counts the number of paragraphs in the text.
     * A paragraph is defined as text separated by double newlines or multiple newlines.
     * 
     * @param text The input text to count paragraphs from
     * @return The number of paragraphs
     */
    fun countParagraphs(text: String): Int {
        if (text.isBlank()) return 0
        return text.trim()
            .split(Regex("\\n\\s*\\n+"))
            .filter { it.isNotBlank() }
            .size
    }
    
    /**
     * Gets comprehensive statistics about the text.
     * 
     * @param text The input text to analyze
     * @return A TextStatistics object containing all counts
     */
    fun getStatistics(text: String): TextStatistics {
        return TextStatistics(
            wordCount = countWords(text),
            characterCount = countCharacters(text),
            characterCountWithoutSpaces = countCharactersWithoutSpaces(text),
            sentenceCount = countSentences(text),
            paragraphCount = countParagraphs(text)
        )
    }
}

/**
 * Data class to hold text statistics.
 */
data class TextStatistics(
    val wordCount: Int,
    val characterCount: Int,
    val characterCountWithoutSpaces: Int,
    val sentenceCount: Int,
    val paragraphCount: Int
)

