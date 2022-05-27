package comp1721.cwk2;


import java.lang.reflect.Array;
import java.util.Arrays;

// Implement PokerHand class here
public class PokerHand {
    //A default constructor that creates an empty hand.
    private String[] hand = new String[5];
    private char[] com = new char[5];
    private char[] com1 = new char[5];
    int[] in = new int[5];
    Deck deck = new Deck();

    public PokerHand() {
        for (int i = 0; i < 5; i++) {
            hand[i] = null;
        }
    }

    //A constructor with a String parameter that specifies the cards that should be added to the hand, using two-character abbreviations for the cards. For example, an argument of "2D JC" should result in the Two of Diamonds and Jack of Clubs being added to the hand.
    public PokerHand(String s) throws CardException {
        if (s.length() > 14) {
            throw new CardException("Too many hands.");
        }
        String[] spString = s.split("\\s+");
        int i = 0;
        for (String ss : spString) {
            hand[i] = ss;
            i++;
        }
        for (i = 0; i < size(); i++) {
            com[i] = hand[i].charAt(0);
            com1[i] = hand[i].charAt(0);
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
        String w1;
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                Card c1 = new Card(hand[i]);
                w1 = c1.toString();
                sb.append(w1 + " ");
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
        if (hand[0] == null) {
            size = 0;
        }
        return size;
    }

    public void discard() throws CardException {
        if (size() == 0) {
            throw new CardException("The hand is empty.");
        }
        for (int i = 0; i < 5; i++) {
            hand[i] = null;
        }
    }

    // Method discardTo, with a Deck parameter, which empties the hand of cards and returns each of them to the specified deck.
    public void discardTo(Deck deck) throws CardException {
        if (size() == 0) {
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
        if (size() == 5) {
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
        }
        return false;
    }

    public boolean isFullHouse() {
        if (size() == 5) {
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
        }
        return false;
    }

    public boolean isTwoPairs() {
        if (size() == 5) {
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
        }
        return false;
    }


    public boolean isFlush() {
        if (size() == 5) {
            for (int i = 0; i < 5; i++) {
                com[i] = hand[i].charAt(1);
            }
            if (com[0] == com[1] && com[0] == com[2] && com[0] == com[3] && com[0] == com[4]) {
                return true;
            }
        }
        return false;
    }

    public boolean isThreeOfAKind() {
        if (size() == 5) {
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
            if ((count == 3 && count1 == 1) || (count == 1 && count1 != 3)) {
                return true;
            }
        }
        return false;
    }


    public boolean isStraight() {
        if (size() == 5) {
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
            if (count == 1 && count1 == 1) {
                for (int i = 0; i < 5; i++) {
                    if (com[i] != 'A' && com[i] != 'J' && com[i] != 'Q' && com[i] != 'K' && com[i] != 'T') {
                        in[i] = com[i]-'0';
                    }
                    if (com[i] == 'T') {
                        in[i] = 10;
                    }
                    if (com[i] == 'J') {
                        in[i] = 11;
                    }
                    if (com[i] == 'Q') {
                        in[i] = 12;
                    }
                    if (com[i] == 'K') {
                        in[i] = 13;
                    }
                    if (com[i] == 'A') {
                        in[i] = 1;
                    }
                }
                Arrays.sort(in);

                if (com[0] != 'A' && com[1] != 'A' && com[2] != 'A' && com[3] != 'A' && com[4] != 'A') {
                    if (in[4] - in[0] == 4) {
                        return true;
                    }
                }
                if (com[0] != 'A' || com[1] != 'A' || com[2] != 'A' || com[3] != 'A' || com[4] != 'A') {
                    if((in[0]==1&&in[1]==2&&in[2]==3&&in[3]==4&&in[4]==5)||(in[0]==1&&in[1]==10&&in[2]==11&&in[3]==12&&in[4]==13)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isFourOfAKind() {
        if (size() == 5) {
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
        }
        return false;
    }

    public void add(Card card) throws CardException {
        String string = card.toString();
        for (int i = 0; i < size(); i++) {
            if (string.equals(hand[i])) {
                throw new CardException("Repeat hand.");
            }
        }
        if (size() == 5) {
            throw new CardException("Hand is full");
        }
        if (size() != 5) {
            hand[size()] = card.toString();
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