package poker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HandRank {
    private final List<Card> pokerHand;
    private final Map<Character, Integer> cardsDup = new HashMap<>();

    private static final int ROYAL_FLUSH = 10;
    private static final int STRAIGHT_FLUSH = 9;
    private static final int FOUR_OF_A_KIND = 8;
    private static final int FULL_HOUSE = 7;
    private static final int FLUSH = 6;
    private static final int STRAIGHT = 5;
    private static final int THREE_OF_A_KIND = 4;
    private static final int TWO_PAIRS = 3;
    private static final int PAIR = 2;
    private static final int HIGH_CARD = 1;

    public HandRank(List<Card> pokerHand) {
	Collections.sort(pokerHand);
	this.pokerHand = pokerHand;
	for (Card card : pokerHand) {
	    cardsDup.put(card.getRank(), cardsDup.getOrDefault(card.getRank(), 0) + 1);
	}
    }

    public boolean isRoyalFlush() {
	return "A".equals(pokerHand.stream().findFirst().get().getRank().toString()) && isStraightFlush();
    }

    public boolean isStraightFlush() {
	return isStraight() && isFlush();
    }

    public boolean isFourOfAKind() {
	return cardsDup.values().contains(4);
    }

    public boolean isFullHouse() {
	return isOnePair() && isThreeOfAKind();
    }

    public boolean isFlush() {
	Set<Character> suits = pokerHand.stream().map(Card::getSuit).collect(Collectors.toSet());
	return suits.size() == 1;
    }

    public boolean isStraight() {
	Set<Character> faces = pokerHand.stream().map(Card::getRank).collect(Collectors.toSet());
	if (faces.size() != 5) {
	    return false;
	}
	return Integer.valueOf(pokerHand.get(0).getRank()) == Integer.valueOf((pokerHand.get(4).getRank() - 4));
    }

    public boolean isThreeOfAKind() {
	return cardsDup.values().contains(3);
    }

    public boolean isTwoPairs() {
	int pairCards = 0;
	for (int val : cardsDup.values()) {
	    if (val == 2) {
		pairCards++;
	    }
	}
	return pairCards == 2;
    }

    public boolean isOnePair() {
	return cardsDup.values().contains(2);
    }

    public int getHandRank() {
	if (isRoyalFlush()) {
	    return ROYAL_FLUSH;
	}
	if (isStraightFlush()) {
	    return STRAIGHT_FLUSH;
	}
	if (isFourOfAKind()) {
	    return FOUR_OF_A_KIND;
	}
	if (isFullHouse()) {
	    return FULL_HOUSE;
	}
	if (isFlush()) {
	    return FLUSH;
	}
	if (isStraight()) {
	    return STRAIGHT;
	}
	if (isThreeOfAKind()) {
	    return THREE_OF_A_KIND;
	}
	if (isTwoPairs()) {
	    return TWO_PAIRS;
	}
	if (isOnePair()) {
	    return PAIR;
	}
	return HIGH_CARD;
    }

    public int getComboRank() {
	switch (getHandRank()) {
	case FOUR_OF_A_KIND:
	    return cardsDup.entrySet().stream().filter(card -> card.getValue() == 4).findFirst().get().getKey();

	case FULL_HOUSE: // оцениваем старшинство по сету (THREE_OF_A_KIND)

	case THREE_OF_A_KIND:
	    return cardsDup.entrySet().stream().filter(card -> card.getValue() == 3).findFirst().get().getKey();

	case TWO_PAIRS:
	    return cardsDup.entrySet().stream().filter(card -> card.getValue() == 2).map(card -> card.getKey())
			    .max(Integer::compare).get();

	case PAIR:
	    return cardsDup.entrySet().stream().filter(card -> card.getValue() == 2).findFirst().get().getKey();

	default:
	    return getHighestCard();
	}
    }

    private Character getHighestCard() {
	char ch = Character.toChars(100)[0];
	Character bin = ch;
	return  pokerHand.stream().map(Card::getRank).min(Integer::compare).get();
    }

    public Character getHighestFromRemainingCards() {
	return cardsDup.entrySet().stream().filter(card -> card.getValue() == 1)
			.map(card -> card.getKey().charValue()).min(Integer::compare).get();
    }
}
