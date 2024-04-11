//@@author raajamani
package motivationalquotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
    private List<String> quotes;

    /**
     * Constructs a new Quote object which initializes the list of quotes
     * from the {@code MotivationalQuotes} class.
     */
    public Quote() {
        quotes = readQuotesFromMotivationalQuotesClass();
    }

    /**
     * Returns a random quote from the list of motivational quotes.
     * If no quotes are available, a default message indicating the absence
     * of quotes is returned.
     *
     * @return A random motivational quote or a message indicating no quotes are available.
     */
    public String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "No quotes available.";
        }
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }

/*
    public String getChatResponse(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! How can I motivate you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here to motivate you. Just ask!";
        }
    }
*/

    /**
     * Reads and processes the quotes from the {@code MotivationalQuotes} class.
     * The quotes are trimmed for any leading or trailing whitespace.
     *
     * @return A list of cleaned motivational quotes.
     */
    private List<String> readQuotesFromMotivationalQuotesClass() {
        List<String> quotes = new ArrayList<>();
        String[] quotesArray = MotivationalQuotes.getQuotes();
        for (String quote : quotesArray) {
            quotes.add(quote.trim());
        }
        return quotes;
    }

}
