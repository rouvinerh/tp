package motivational_quotes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
    private static final String QUOTES_FILE_PATH = "/src/main/java/motivational_quotes/Quotes.txt";
    private List<String> quotes;

    public Quote() {
        quotes = readQuotesFromFile();
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
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse1(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse2(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse3(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse4(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse5(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse6(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse7(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse8(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse9(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse10(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse11(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse12(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse14(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse15(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse16(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse17(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse18(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse19(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse19ADAD(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse19SASA(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19KNKM(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19KIUJHY(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19BNHGTYU(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse1912QW(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19BGH(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19PLOIKJU(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19BTH(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19LKJUY(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19NHYTG(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse1912SS(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19QWSA(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19ZSW(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19CVBF(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19BNM(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19PLKJ(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19J(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19KH(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19IU7(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19DF(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19KJ(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19K(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19UY(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19T7(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse19987(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    
    public String getChatResponse1977(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse1999(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse1912(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse19111(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse1911(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse191(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse19VBGTFFD(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse19JJH(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
    
    public String getChatResponse19AQWSQ(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
    public String getChatResponse19ASWQASW(String userInput) {
        userInput = userInput.toLowerCase();
        if (userInput.contains("motivate") || userInput.contains("inspire")) {
            return getRandomQuote();
        } else if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! What can I do for you today?";
        } else if (userInput.contains("thank")) {
            return "You're welcome! Keep pushing forward!";
        } else {
            return "I'm here for you. Just ask!";
        }
    }
        
        
    
        
    private List<String> readQuotesFromFile() {
        List<String> quotes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(QUOTES_FILE_PATH))) {
            String line;
            StringBuilder sb = new StringBuilder();
            boolean readingQuote = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("Quote")) {
                    // Start reading a new quote
                    if (readingQuote) {
                        // Save the previous quote
                        quotes.add(sb.toString().trim());
                        sb.setLength(0); // Clear the StringBuilder for the next quote
                    }
                    readingQuote = true;
                } else if (readingQuote) {
                    // Add line to the current quote
                    sb.append(line.trim()).append(" ");
                }
            }
            // Add the last quote (if any)
            if (sb.length() > 0) {
                quotes.add(sb.toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quotes;
    }


}
