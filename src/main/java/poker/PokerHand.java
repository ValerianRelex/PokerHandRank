package poker;

import java.util.ArrayList;
import java.util.List;

public class PokerHand implements Comparable<PokerHand> {
    private List<Card> cards = new ArrayList<>();
    private HandRank handRank;

    public PokerHand(String handOfCards) {
	String[] handCards = handOfCards.split(" ");
	for (String card : handCards) {
	    cards.add(new Card(card));
	}

	this.handRank = new HandRank(cards);
    }

    @Override
    public int compareTo(PokerHand other) {
	if (this.handRank.getHandRank() < other.handRank.getHandRank()) {
	    return 1;
	} else if (this.handRank.getHandRank() > other.handRank.getHandRank()) {
	    return -1;
	}

	if (this.handRank.getComboRank() < other.handRank.getComboRank()) {
	    return -1;
	} else if (this.handRank.getComboRank() > other.handRank.getComboRank()) {
	    return 1;
	}

	if (this.handRank.getHighestFromRemainingCards() < other.handRank.getHighestFromRemainingCards()) {
	    return -1;
	} else if (this.handRank.getHighestFromRemainingCards() > other.handRank.getHighestFromRemainingCards()) {
	    return 1;
	}
	return 0;
    }

    public HandRank getHandRank() {
	return handRank;
    }

    @Override
    public String toString() {
	return cards.toString();
    }
}