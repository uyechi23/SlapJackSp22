# Proj #c - Requirements

## Official Rules (Source)
The official Rules for Crazy Eights is here: https://bicyclecards.com/how-to-play/crazy-eights/

## Rules (Single Round)
A single round of Crazy Eights starts by dealing 5 cards to each player. The remaining cards in the deck are placed face-down,
and the top card is flipped over to reveal it's value, and placed face-up beside the deck. If that card's value is an eight,
it is shuffled into the deck and the next card is flipped over. The round begins with the player left of the dealer.

The current player either plays a card of the same suit or value, plays an eight of any suit, or draws the next card in the
deck. That player has the choice of drawing from the deck even if they have a playable card in their hand. If the card that
player plays has a value of eight, they must declare a suit that the next person must play. If the stock (the deck in the
center) is empty, then players will pass instead of drawing from the deck. A player wins once they no longer have cards in
their hand.

## Rules (Post-Round)
The winner collects points equal to the values of the cards in every other player's hands at the end of the game. Points
are based on the table below:

| Face Value        | Points    |
|-------------------|-----------|
| Eights            | 50        |
| King, Queen, Jack | 10        |
| All other cards   | Pip count |


## Exceptions to Rules
### [Single Round] Dealer/Player Order
Since this version of the game has no dealers, the Player objects in the game will be stored in a random order prior to
the start of the game. This order will change between rounds, but will essentially establish the turn sequence for a single round.
