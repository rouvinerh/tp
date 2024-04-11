//@@author raajamani
package motivationalquotes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class QuoteTest {

    /**
     * Test to ensure that the getRandomQuote method returns a
     * non-null string and that this string is not just whitespace.
     */
    @Test
    void testGetRandomQuote() {
        Quote quote = new Quote();
        String randomQuote = quote.getRandomQuote();
        assertNotNull(randomQuote);
        assertNotEquals("", randomQuote.trim());
    }

    /**
     * Test to verify that the getQuotes method returns exactly 50 quotes.
     */
    @Test
    public void testLengthOfQuotesArray() {
        String[] quotes = MotivationalQuotes.getQuotes();
        assertEquals(50, quotes.length, "Length of quotes array should be 50");
    }

    /**
     * Test to check that the quotes array is not null, and none of its elements are null or empty.
     */
    @Test
    public void testQuotesContent() {
        String[] quotes = MotivationalQuotes.getQuotes();
        assertNotNull(quotes, "Quotes array should not be null");
        for (String quote : quotes) {
            assertNotNull(quote, "Each quote should not be null");
            assertFalse(quote.isEmpty(), "Each quote should not be empty");
        }
    }

    /**
     * Test to ensure that the getQuotes method returns a non-null array of quotes,
     * with the expected length of 50.
     */
    @Test
    public void testGetQuotesMethod() {
        String[] quotes = MotivationalQuotes.getQuotes();
        assertNotNull(quotes, "Returned quotes array should not be null");
        assertEquals(50, quotes.length, "Length of returned quotes array should be 50");
    }

    /**
     * Test to ensure there are no duplicate quotes in the array.
     */
    @Test
    public void testNoDuplicateQuotes() {
        String[] quotes = MotivationalQuotes.getQuotes();
        for (int i = 0; i < quotes.length; i++) {
            for (int j = i + 1; j < quotes.length; j++) {
                assertNotEquals(quotes[i], quotes[j], "Quotes should not be duplicates");
            }
        }
    }

    /**
     * Test to verify that none of the quotes are empty strings.
     */
    @Test
    public void testNoEmptyQuotes() {
        String[] quotes = MotivationalQuotes.getQuotes();
        for (String quote : quotes) {
            assertFalse(quote.isEmpty(), "Quotes should not be empty");
        }
    }


}
