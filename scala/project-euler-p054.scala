/**
  * Problem 54. Poker hands
  *
  * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
  *
  *   High Card: Highest value card.
  *   One Pair: Two cards of the same value.
  *   Two Pairs: Two different pairs.
  *   Three of a Kind: Three cards of the same value.
  *   Straight: All cards are consecutive values.
  *   Flush: All cards of the same suit.
  *   Full House: Three of a kind and a pair.
  *   Four of a Kind: Four cards of the same value.
  *   Straight Flush: All cards are consecutive values of same suit.
  *   Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
  *
  * The cards are valued in the order:
  * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
  *
  * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.
  *
  * Consider the following five hands dealt to two players:
  *
  *
  *    Hand      Player 1          Player 2	 	        Winner
  *
  *     1    5H 5C 6S 7S KD     2C 3S 8S 8D TD       Player 2
  *          Pair of Fives      Pair of Eights
  *
  *     2    5D 8C 9S JS AC     2C 5C 7D 8S QH       Player 1
  *         Highest card Ace  Highest card Queen
  *
  *     3    2D 9C AS AH AC     3D 6D 7D TD QD       Player 2
  *            Three Aces     Flush with Diamonds
  *
  *     4    4D 6S 9H QH QC     3D 6D 7H QD QS       Player 1
  *          Pair of Queens     Pair of Queens
  *         Highest card Nine  Highest card Seven
  *
  *     5    2H 2D 4C 4D 4S     3C 3D 3S 9S 9D       Player 1
  *            Full House          Full House
  *         With Three Fours   With Three Threes
  *
  * The file, p054_poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.
  *
  * How many hands does Player 1 win?
  *
  * https://projecteuler.net/problem=54
  **/

var hands = io.Source.fromFile("p054_poker.txt").mkString.split("\n").map(s=>s.split(" "))

var order = " A23456789TJQKA"

/**
  * Check if cards are in sequential order
  * @param a Array of Int (card value 1 to 14)
  * @return Whether the card values are in a sequential order
  */
def isStraight(a: Array[Int]): Boolean = {
  for (i <- 1 until a.length) {
    if (a(i) - a(i - 1) != 1) {
      return false
    }
  }
  true
}

/**
  *   High Card       Pairs
  * H1 H2 H3 H4 H5   P1   P2   Set Straight Flush FullHouse FourOfKind Straight/Flush
  * 16 16 16 16 16   16   16   16     2       2      2         2            2
  *
  * 2H 2D 4C 4D 4S
  * 2  2  4  4  4     2   0     4     0       0      1         0            0
  *
  *
  * @param hand
  * @return
  */
def handScore(hand: Array[String]): Long = {
  val pair1 = 16L*16*16*16*16
  val pair2 = pair1 * 16
  val set = pair2 * 16
  val straight = set * 16
  val flush = straight * 2
  val fullHouse = flush * 2
  val fourOfAKind = fullHouse * 2
  val straightFlush = fourOfAKind * 2

  var score = 0L

  // Ace is 1
  var sortedHand = hand.sortWith((a,b) => order.indexOf(a(0)) < order.indexOf(b(0))).map(a=>order.indexOf(a(0)))

  // check for a wheel straight A2345
  if (sortedHand(0) == 1 && isStraight(sortedHand)) {
    score += straight
  } else {
    // Ace is 13, ignore A at index 1, so starting indexOf is 2
    sortedHand = hand.sortWith((a,b) => order.indexOf(a(0), 2) < order.indexOf(b(0), 2)).map(a=>order.indexOf(a(0), 2))

    // check for a straight 56789 or TJQKA
    if (isStraight(sortedHand)) {
      score += straight
    } else {

      // check for pairs
      // Card Value -> count
      val pairs = collection.mutable.Map[Int, Int]()

      var p = sortedHand(0)
      for (i <- 1 to 4) {
        if (p == sortedHand(i)) {
          //increment pair/set occurrence count
          pairs(p) = if (pairs isDefinedAt(p)) pairs(p) + 1 else 2
        }
        p = sortedHand(i)
      }

      var (p1, p2, setFound) = (0, 0, false)
      for ((card, count) <- pairs) {
        if (count == 4) {
          score += fourOfAKind * card
        } else if (count == 3) {
          score += set * card
          setFound = true
        } else {
          if (p1 > 0) {
            // make sure the highest pair of 2 pairs placed in p2 slot
            // p2 > p1
            if (p1 > card) {
              p2 = p1
              p1 = card
            } else {
              p2 = card
            }
          } else {
            p1 = card
          }
        }
      }
      score += p1 * pair1 + p2 * pair2
      //FullHouse = set + a pair
      if (setFound && (p1 > 0 || p2 > 0)) {
        score += fullHouse
      }
    }
  }

  //check for flush
  // suite is determined by the second character
  // 5D = 5 of diamonds, 2C = A deuce of clubs
  if (hand.map(_(1)).distinct.length == 1) {
    if (score >= straight && score < fourOfAKind) {
      score += straightFlush
    } else {
      score += flush
    }
  }

  // Update the score with the highest card values
  // Useful if there's no pair/set/fullhouse found
  for (i <- sortedHand.indices) {
    score += sortedHand(i) * math.pow(16, i).toLong
  }
  score
}

println(hands.map { hand =>
  val (player1, player2) = (hand.slice(0, 5), hand.slice(5, 10))

  if (handScore(player1) > handScore(player2)) 1 else 0
}.sum)

/*

376

real    0m1.698s
user    0m1.325s
sys     0m0.134s

 */
