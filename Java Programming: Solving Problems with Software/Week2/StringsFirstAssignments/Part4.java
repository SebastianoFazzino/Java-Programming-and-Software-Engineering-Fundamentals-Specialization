import edu.duke.*;


public class Part4 {
    
  public void readUrl() {
    // Using URLResource we scan a given link and we create an object
    URLResource links = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    String result = "";
    // We iterate through the lines in the wabpage
    for (String link : links.lines()) {
        // We check if the word 'youtube' is present in the link
        int index = link.toLowerCase().indexOf("youtube");
        if (index != -1) {
            // We extract the whole url
            String quote = "\"";
            int firstQuote = link.indexOf(quote);
            int lastQuote = link.lastIndexOf(quote);
            result = link.substring(firstQuote, lastQuote + 1);
            // Print the urls
            System.out.println(result);
        }
    }
  }
  
  public static void main (String[] args) {
     Part4 links = new Part4();
     links.readUrl();
  }
}
