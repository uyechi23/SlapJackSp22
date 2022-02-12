# Proj #c - Requirements

## Official Rules (Source)
The official Rules for Crazy Eights is here: https://bicyclecards.com/how-to-play/crazy-eights/

## Rules (Mid-Round)
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
### [Mid-Round] Dealer/Player Order
Since this version of the game has no dealers, the Player objects in the game will be stored in a random order prior to
the start of the game. This order will change between rounds, but will essentially establish the turn sequence for a single round.

### [Mid-Round] When the Deck Runs Out
When play-testing this game, we thought it was more fun if we kept shuffling the cards back into the deck once the deck runs out.
Our game will support this method, re-filling the deck using the cards that were played previously (shuffled, of course).

### [Mid-Round] Playing multiple cards
A player can play multiple cards if they have another card of the same value. At least one of the cards played must be a valid
card. To handle this, our game will use a tap-and-slide feature to play multiple cards. The player must tap the card(s) they want
to play, and then slide upward to confirm that turn.

### [Post-Round] Point System
Points awarded will only be saved on Player accounts (either Local or Proxy). AI Players will not have the option of storing points
on their Player object. How this will be achieved depends on what we're able to do - but possibly a resource file that consists
of data on a particular Player (with a unique username). This may include point total, cosmetics, level, boosts/upgrades, and more.
