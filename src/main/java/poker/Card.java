package poker;

public class Card implements Comparable<Card> {
    private Character face;
    private Character suit;
    private Character rank;

    private String rankOrder = "23456789TJQKA";

    public Card(String cardStr) {
	face = cardStr.charAt(0);
	suit = cardStr.charAt(1);
    }

    public Character getRank() {
	int charIndex = rankOrder.indexOf(face);
	rank = Character.toChars(77 - charIndex)[0];
	return rank;
    }

    @Override
    public int compareTo(Card other) {
	if (this.getRank() < other.getRank()) {
	    return -1;
	} if (this.getRank() > other.getRank()) {
	    return 1;
	}
	return 0;
    }

    public Character getSuit() {
	return suit;
    }

    @Override
    public String toString() {
	return "" + face + suit;
    }
}
