package poker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final List<Card> cards;
    private final HandRank handRank;

    public PokerHand(String handOfCards) {
	cards = Arrays.stream(handOfCards.split(" "))
			.map(Card::new)
			.collect(Collectors.toList());
	handRank = new HandRank(cards);
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