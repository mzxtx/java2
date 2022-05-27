package comp1721.cwk2;


// Implement Deck class here
/**
 * A class to represents the entire deck.
 *
 * <p>Build a card pile, realize from the card pile in the licensing, hand back to the card pile and other functions.</p>
 *
 * @author Fu Xinyi
 */
//A default constructor that creates a deck containing the standard 52 playing cards, arranged by suits and then in rank order.
public class Deck{
    public String[][] deck = new String[4][13];

    public Deck() throws CardException {
        String[] r = new String[13];
        String[] s = new String[4];
        int i = 0, j = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            j = 0;
            for (Card.Rank rank : Card.Rank.values()) {
                r[j] = String.valueOf(rank.getSymbol());
                s[i] = String.valueOf(suit.getSymbol());
                deck[i][j] = r[j] + s[i];
                //System.out.print(deck[i][j]);
                j++;
            }
            //System.out.print("\n");
            i++;
        }

        /*
        System.out.print(deck[3][12] + "\n");
        System.out.print(size() + "\n");
        System.out.print(isEmpty() + "\n");
        Card aceClubs = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        System.out.print(contains(aceClubs) + "\n");
        System.out.print(deal() + "\n");
        shuffle();
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 13; j++) {
                System.out.print(deck[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print(size() + "\n");
        */
    }

    //A size method that returns the number of cards in the deck.
    public int size() {
        int size = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (deck[i][j] != null) {
                    size++;
                }
            }
        }
        return size;
    }

    //An isEmpty method that returns true if the deck is empty of cards, false otherwise.
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    //A contains method with a Card parameter that returns true if the deck contains the specified card, false otherwise.
    public boolean contains(Card card) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (card.toString().equals(deck[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    //A discard method that empties the deck of all its cards.
    public void discard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deck[i][j] = null;
            }
        }
    }

    //A deal method that removes the first card in the deck and returns it.
    public Card deal() throws CardException {
        if (isEmpty() == true) {
            throw new CardException("The deck is empty.");
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (deck[i][j] != null) {
                    Card de = new Card(deck[i][j]);
                    deck[i][j] = null;
                    return de;
                }
            }
        }
        return null;
    }

    //A shuffle method that rearranges cards in the deck randomly
    public void shuffle() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                int x = (int) (Math.random() * (i + 1));
                int y = (int) (Math.random() * (j + 1));
                String radom = deck[i][j];
                deck[i][j] = deck[x][y];
                deck[x][y] = radom;
            }
        }
    }
    public void add(Card card) throws CardException {
        String string = card.toString();
        /*
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (string.equals(deck[i][j])) {
                    throw new CardException("Repeat deck.");
                }
            }
        }*/
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (deck[i][j] == null) {
                    deck[i][j] = string;
                }
            }
        }
    }
}