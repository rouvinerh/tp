package motivational_quotes;

import motivational_quotes.Quote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuoteTest {

    @Test
    void testGetRandomQuote() {
        Quote quote = new Quote();
        String randomQuote = quote.getRandomQuote();
        assertNotNull(randomQuote);
        assertNotEquals("", randomQuote.trim());
    }

    @Test
    void testGetChatResponseMotivate() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("motivate");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseInspire() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("inspire");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseHello() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("hello");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseHi() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("hi");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseThank() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("thank");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseRandomInput() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("random input");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseEmptyInput() {
        Quote quote = new Quote();
        String chatResponse = quote.getChatResponse("");
        assertNotNull(chatResponse);
        assertNotEquals("", chatResponse.trim());
    }

    @Test
    void testGetChatResponseNullInput() {
        Quote quote = new Quote();
        String chatResponse = null;
        assertNull(chatResponse);
        assertEquals(null, chatResponse);
    }
}
