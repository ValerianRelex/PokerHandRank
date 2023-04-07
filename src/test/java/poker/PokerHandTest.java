package poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandTest {
    @Test
    public void testHandRankCreation() {
	PokerHand pokerHand = new PokerHand("AH QH JH KH TH");
	assertNotNull(pokerHand);
    }

    @Test
    public void testHandRankCompare() {
	PokerHand hand1 = new PokerHand("3H 3D 3S JC KD");
	PokerHand hand2 = new PokerHand("AC AH 8S 8C QH");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testHighCardCompare() {
	PokerHand hand1 = new PokerHand("2C 3H 4S 8C AH");
	PokerHand hand2 = new PokerHand("2H 3D 5S 9C KD");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testOnePairCompare() {
	PokerHand hand1 = new PokerHand("3C 3H 4S 8C AH");
	PokerHand hand2 = new PokerHand("2H 2D 5S 9C KD");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testTwoPairCompare() {
	PokerHand hand1 = new PokerHand("3C 3H 4S 4C AH");
	PokerHand hand2 = new PokerHand("2H 2D 5S 5C KD");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testThreeOfAKindCompare() {
	PokerHand hand1 = new PokerHand("3C 3H 3S 8C AH");
	PokerHand hand2 = new PokerHand("2H 2D 2S 9C KD");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testStraightCompare() {
	PokerHand hand1 = new PokerHand("3C 4H 5S 6C 7H");
	PokerHand hand2 = new PokerHand("2H 3D 4S 5C 6D");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testFlushCompare() {
	PokerHand hand1 = new PokerHand("3C 4C 6C 8C JC");
	PokerHand hand2 = new PokerHand("2H 4H 6H 8H TH");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testFullHouseCompare() {
	PokerHand hand1 = new PokerHand("3C 3H 3S 4C 4H");
	PokerHand hand2 = new PokerHand("2H 2D 2S 3C 3D");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testFourOfAKindCompare() {
	PokerHand hand1 = new PokerHand("3C 3H 3S 3D AH");
	PokerHand hand2 = new PokerHand("2H 2D 2S 2C KD");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testStraightFlushCompare() {
	PokerHand hand1 = new PokerHand("3C 4C 5C 6C 7C");
	PokerHand hand2 = new PokerHand("2H 3H 4H 5H 6H");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testRoyalFlushCompare() {
	PokerHand hand1 = new PokerHand("TH JH QH KH AH");
	PokerHand hand2 = new PokerHand("9C TC JC QC KC");
	assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void testRoyalFlush() {
	PokerHand pokerHand = new PokerHand("AH KH QH JH TH");
	assertTrue(pokerHand.getHandRank().isRoyalFlush());
    }

    @Test
    public void testStraightFlush() {
	PokerHand pokerHand = new PokerHand("9H 8H 7H 6H 5H");
	assertTrue(pokerHand.getHandRank().isStraightFlush());
    }

    @Test
    public void testFourOfAKind() {
	PokerHand pokerHand = new PokerHand("TH QH QH QH QH");
	assertTrue(pokerHand.getHandRank().isFourOfAKind());
    }

    @Test
    public void testFullHouse() {
	PokerHand pokerHand = new PokerHand("KH KS KD 4H 4D");
	assertTrue(pokerHand.getHandRank().isFullHouse());
    }

    @Test
    public void testFlush() {
	PokerHand pokerHand = new PokerHand("KH QH 9H 2H 4H");
	assertTrue(pokerHand.getHandRank().isFlush());
    }

    @Test
    public void testStraight() {
	PokerHand pokerHand = new PokerHand("5H 6C 7H 8H 9H");
	assertTrue(pokerHand.getHandRank().isStraight());
    }

    @Test
    public void testThreeOfAKind() {
	PokerHand pokerHand = new PokerHand("TC TH TD KH KS");
	assertTrue(pokerHand.getHandRank().isThreeOfAKind());
    }

    @Test
    public void testTwoPairs() {
	PokerHand pokerHand = new PokerHand("QH AD JS JC AH");
	assertTrue(pokerHand.getHandRank().isTwoPairs());
    }

    @Test
    public void testPair() {
	PokerHand pokerHand = new PokerHand("AH AS QD 9H 8S");
	assertTrue(pokerHand.getHandRank().isOnePair());
    }

    @Test
    public void testGetHandRank() {
	final int FULL_HOUSE = 7;
	PokerHand pokerHand = new PokerHand("9H 9S 9D KH KD");
	assertEquals(FULL_HOUSE, pokerHand.getHandRank().getHandRank());
    }

    @Test
    public void testGetComboRank() {
	Card queen = new Card("QH");
	Character rank = queen.getRank();
	PokerHand pokerHand = new PokerHand("QH QS QD JD 9S");
	assertEquals(rank.charValue(), pokerHand.getHandRank().getComboRank());
    }

    @Test
    public void testGetHighestFromRemainingCards() {
	Card queen = new Card("KH");
	Character rank = queen.getRank();
	PokerHand pokerHand = new PokerHand("AH AC 4H KD 9S");
	assertEquals(rank.charValue(), pokerHand.getHandRank().getHighestFromRemainingCards());
    }
}
