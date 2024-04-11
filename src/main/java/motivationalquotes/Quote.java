//@@author raajamani
package motivationalquotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
    private List<String> quotes;

    public Quote() {
        quotes = readQuotesFromMotivationalQuotesClass();
    }

    public String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "No quotes available.";
        }
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }


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


    private List<String> readQuotesFromMotivationalQuotesClass() {
        List<String> quotes = new ArrayList<>();
        String[] quotesArray = MotivationalQuotes.getQuotes();
        for (String quote : quotesArray) {
            quotes.add(quote.trim());
        }
        return quotes;
    }

}
