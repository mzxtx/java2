package comp1721.cwk2;


// Implement PokerHand class here
public class PokerHand{
    //A default constructor that creates an empty hand.
    private String[] hand = new String[5];
    private char[] com = new char[5];
    Deck deck = new Deck();

    public PokerHand() {
        for (int i = 0; i < 5; i++) {
            hand[i] = null;
        }
    }

    //A constructor with a String parameter that specifies the cards that should be added to the hand, using two-character abbreviations for the cards. For example, an argument of "2D JC" should result in the Two of Diamonds and Jack of Clubs being added to the hand.
    public PokerHand(String s) throws CardException{
        if(s.length()>14){
            throw new CardException("Too many hands.");
        }
        String[] spString = s.split("\\s+");
        int i = 0;
        for (String ss : spString) {
            hand[i] = ss;
            i++;
        }
        for (int x = 0; x < size(); x++) {
            for (i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    if (deck.deck[i][j] != null && deck.deck[i][j].equals(hand[x])) {
                        deck.deck[i][j] = null;
                        //System.out.print(deck.deck[i][j]);
                    }
                }
            }
        }

/*
        System.out.print(size() + "\n");
        for (i = 0; i < 5; i++) {
            System.out.print(hand[i]);
        }
        System.out.print("\n");
        System.out.print(toString() + "\n");
        for (i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (deck.deck[i][j] != null) {
                    System.out.print(deck.deck[i][j]);
                }
            }
            System.out.print("\n");
        }
        System.out.print(deck.size() + "\n");
        discardTo(deck);
        for (i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (deck.deck[i][j] != null) {
                    System.out.print(deck.deck[i][j]);
                }
            }
            System.out.print("\n");
        }
        System.out.print(deck.size() + "\n");*/
    }

    //A toString method, overriding the default version, which returns a string in which cards are shown in two-character form, separated by spaces—e.g., "2D JC 7H".
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                sb.append(hand[i] + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        String str = sb.toString();
        return str;
    }

    // Methods size and discard, which behave just like the corresponding methods of Deck.
    public int size() {
        int size = 0;
        for (int i = 0; i < 5; i++) {
            if (hand[i] != null) {
                size++;
            }
        }
        if(hand[0]==null){
            size=0;
        }
        return size;
    }

    public void discard() throws CardException{
        if(size()==0){
            throw new CardException("The hand is empty.");
        }
        for (int i = 0; i < 5; i++) {
            hand[i] = null;
        }
    }

    // Method discardTo, with a Deck parameter, which empties the hand of cards and returns each of them to the specified deck.
    public void discardTo(Deck deck) throws CardException{
        if(size()==0){
            throw new CardException("The hand is empty.");
        }
        int x = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (deck.deck[i][j] == null) {
                    while (x < size()) {
                        deck.deck[i][j] = hand[x];
                        //hand[x] = null;
                        x++;
                        break;
                    }
                }
            }
        }
        for (x = 0; x < size(); x++) {
            hand[x] = null;
        }
    }

    public boolean isPair() {
        for (int i = 0; i < 5; i++) {
            com[i] = hand[i].charAt(0);
        }
        int count = 1;
        int count1 = 1;
        char z1 = ' ';
        char z2 = ' ';
        for (int i = 0; i < 5; i++) {
            if (z1 == com[i] || z2 == com[i]) {
                continue;
            }
            for (int j = i + 1; j < 5; j++) {
                if (com[i] == com[j]) {
                    if (z1 == ' ' || z1 == com[i]) {
                        z1 = com[i];
                        count++;
                    } else {
                        z2 = com[i];
                        count1++;
                    }
                }
            }
        }
        if ((count == 2 && count1 != 2) || (count != 2 && count1 == 2)) {
            return true;
        }
        return false;
    }

    public boolean isFullHouse() {
        for (int i = 0; i < 5; i++) {
            com[i] = hand[i].charAt(0);
        }
        int count = 1;
        int count1 = 1;
        char z1 = ' ';
        char z2 = ' ';
        for (int i = 0; i < 5; i++) {
            if (z1 == com[i] || z2 == com[i]) {
                continue;
            }
            for (int j = i + 1; j < 5; j++) {
                if (com[i] == com[j]) {
                    if (z1 == ' ' || z1 == com[i]) {
                        z1 = com[i];
                        count++;
                    } else {
                        z2 = com[i];
                        count1++;
                    }
                }
            }
        }
        if ((count == 2 && count1 == 3) || (count == 3 && count1 == 2)) {
            return true;
        }
        return false;
    }

    public boolean isTwoPairs() {
        for (int i = 0; i < 5; i++) {
            com[i] = hand[i].charAt(0);
        }
        int count = 1;
        int count1 = 1;
        char z1 = ' ';
        char z2 = ' ';
        for (int i = 0; i < 5; i++) {
            if (z1 == com[i] || z2 == com[i]) {
                continue;
            }
            for (int j = i + 1; j < 5; j++) {
                if (com[i] == com[j]) {
                    if (z1 == ' ' || z1 == com[i]) {
                        z1 = com[i];
                        count++;
                    } else {
                        z2 = com[i];
                        count1++;
                    }
                }
            }
        }
        if (count == 2 && count1 == 2) {
            return true;
        }
        return false;
    }


    public boolean isFlush() {
        return false;
    }

    public boolean isThreeOfAKind() {
        for (int i = 0; i < 5; i++) {
            com[i] = hand[i].charAt(0);
        }
        int count = 1;
        int count1 = 1;
        char z1 = ' ';
        char z2 = ' ';
        for (int i = 0; i < 5; i++) {
            if (z1 == com[i] || z2 == com[i]) {
                continue;
            }
            for (int j = i + 1; j < 5; j++) {
                if (com[i] == com[j]) {
                    if (z1 == ' ' || z1 == com[i]) {
                        z1 = com[i];
                        count++;
                    } else {
                        z2 = com[i];
                        count1++;
                    }
                }
            }
        }
        if (count == 1 || count1 == 1) {
            return true;
        }
        return false;
    }


    public boolean isStraight() {
        return false;
    }

    public boolean isFourOfAKind() {
        for (int i = 0; i < 5; i++) {
            com[i] = hand[i].charAt(0);
        }
        int count = 1;
        int count1 = 1;
        char z1 = ' ';
        char z2 = ' ';
        for (int i = 0; i < 5; i++) {
            if (z1 == com[i] || z2 == com[i]) {
                continue;
            }
            for (int j = i + 1; j < 5; j++) {
                if (com[i] == com[j]) {
                    if (z1 == ' ' || z1 == com[i]) {
                        z1 = com[i];
                        count++;
                    } else {
                        z2 = com[i];
                        count1++;
                    }
                }
            }
        }
        if (count == 4 || count1 == 4) {
            return true;
        }
        return false;
    }
    public void add(Card card) throws CardException{
        String string=card.toString();
        for (int i=0;i<size();i++){
            if(string.equals(hand[i])){
                throw new CardException("Repeat hand.");
            }
        }
        if(size()==5){
            throw new CardException("Hand is full");
        }
        if(size()!=5){
            hand[size()]=card.toString();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    if (deck.deck[i][j] == string) {
                        deck.deck[i][j] = null;
                    }
                }
            }
        }
    }
}