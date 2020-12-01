import edu.duke.*;


public class Part4 {
    
  public void readUrl() {
    URLResource links = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    String toSearch = "youtube";
    String result = "";
    for (String link : links.lines()) {
        int index = link.toLowerCase().indexOf("youtube");
        if (index != -1) {
            String quote = "\"";
            int firstQuote = link.indexOf(quote);
            int lastQuote = link.lastIndexOf(quote);
            result = link.substring(firstQuote, lastQuote + 1);
         
            System.out.println(result);
        }
    }
  }
  
  public static void main (String[] args) {
     Part4 links = new Part4();
     links.readUrl();
  }
}
