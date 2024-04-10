package motivational_quotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
    private static final String QUOTES_FILE_PATH = "Quotes.txt";
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
        
    public String getChatResponse1(String userInput) {
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
    
    public String getChatResponse2(String userInput) {
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
    
    public String getChatResponse3(String userInput) {
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
    
    public String getChatResponse4(String userInput) {
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
    
    public String getChatResponse5(String userInput) {
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
    
    public String getChatResponse6(String userInput) {
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
    
    public String getChatResponse7(String userInput) {
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
    
    public String getChatResponse8(String userInput) {
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
    
    public String getChatResponse9(String userInput) {
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
    
    public String getChatResponse10(String userInput) {
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
    
    public String getChatResponse11(String userInput) {
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
    
    public String getChatResponse12(String userInput) {
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
    
    public String getChatResponse14(String userInput) {
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
    
    public String getChatResponse15(String userInput) {
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
    
    public String getChatResponse16(String userInput) {
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
    
    public String getChatResponse17(String userInput) {
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
    
    public String getChatResponse18(String userInput) {
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
    
    public String getChatResponse19(String userInput) {
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
    
    public String getChatResponse19ADAD(String userInput) {
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
        
    public String getChatResponse19SASA(String userInput) {
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
        
    
    public String getChatResponse19KNKM(String userInput) {
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
        
    
    public String getChatResponse19KIUJHY(String userInput) {
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
        
    
    public String getChatResponse19BNHGTYU(String userInput) {
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
        
    
    public String getChatResponse1912QW(String userInput) {
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
        
    
    public String getChatResponse19BGH(String userInput) {
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
        
    
    public String getChatResponse19PLOIKJU(String userInput) {
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
        
    
    public String getChatResponse19BTH(String userInput) {
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
        
    
    public String getChatResponse19LKJUY(String userInput) {
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
        
    
    public String getChatResponse19NHYTG(String userInput) {
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
        
    
    public String getChatResponse1912SS(String userInput) {
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
        
    
    public String getChatResponse19QWSA(String userInput) {
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
        
    
    public String getChatResponse19ZSW(String userInput) {
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
        
    
    public String getChatResponse19CVBF(String userInput) {
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
        
    
    public String getChatResponse19BNM(String userInput) {
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
        
    
    public String getChatResponse19PLKJ(String userInput) {
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
        
    
    public String getChatResponse19J(String userInput) {
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
        
    
    public String getChatResponse19KH(String userInput) {
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
        
    
    public String getChatResponse19IU7(String userInput) {
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
        
    
    public String getChatResponse19DF(String userInput) {
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
        
    
    public String getChatResponse19KJ(String userInput) {
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
        
    
    public String getChatResponse19K(String userInput) {
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
        
    
    public String getChatResponse19UY(String userInput) {
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
        
    
    public String getChatResponse19T7(String userInput) {
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
        
    
    public String getChatResponse19987(String userInput) {
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
        
    
    public String getChatResponse1977(String userInput) {
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
        
    public String getChatResponse1999(String userInput) {
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
        
    public String getChatResponse1912(String userInput) {
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
        
    public String getChatResponse19111(String userInput) {
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
        
    public String getChatResponse1911(String userInput) {
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
        
    public String getChatResponse191(String userInput) {
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
        
    public String getChatResponse19VBGTFFD(String userInput) {
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
        
    public String getChatResponse19JJH(String userInput) {
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
    
    public String getChatResponse19AQWSQ(String userInput) {
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
        
    public String getChatResponse19ASWQASW(String userInput) {
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
