package cero;

import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * The SwingPlayer class represents the graphical user interface that
 * allows the human to interact with the game.
 *
 * @author Raymond Wong - Javadocs
 * @author Ilya Seletsky - Pseudocode
 * @version 1.0 - February 28, 2010
 */
public class SwingPlayer extends FrameView implements I_Player {

    //////////////////////////////////////////////
    //Own definitions
    ///////////////////////////////////////////////

    /** Max number of cards shown within a computer panel */
    private static final int kMaxComputerCards = 47;

    /**
     * The maximum distance each card is seperated.
     * @see "Constant Field Values"
     */
    private static final int kMaxCardOffset = 33;

    /**
     * The width of the panel that displays the cards
     * (For panels that are rotated 90 degrees this is actually the height of
     * the panel)
     */
    private static final int kCardDisplayWidth = 375;

    /**
     * The height of the panel that displays the cards
     * (For panels that are rotated 90 degrees this is actually the width of the
     * panel)
     */
    private static final int kCardDisplayHeight = 135;

    /**
     * Width of the card image
     * (Height of the card image is also kCardDisplayHeight)
     */
    private static final int kCardWidth = 101;
    
    /**
     * How many pixels to offset the card when drawing it inside the hand panel
     */
    private static final int kHandPadding = 10;

    /**
     * How many human cards are visible
     */
    private static final int kMaxHumanCardsVisible = 14;

    /**
     * How many messages should be visible at once
     */
    private static final int kNumMessages = 3;

    /**
     * The background color of the panel holding the hand of a player when
     * it's not their turn
     */
    private static final java.awt.Color kHandBackroundColor =
            new java.awt.Color(80, 45, 180);

    /**
     * The background color of the panel holding the hand of a player when
     * it is highlighting that it is their turn
     */
    private static final java.awt.Color kHandTurnBackroundColor =
            new java.awt.Color(170, 130, 210);

    /**
     * The background color of the panel holding the hand of a player when
     * it is highlighting that that player is the winner
     */
    private static final java.awt.Color kHandWinBackroundColor =
            new java.awt.Color(200, 200, 118);

    /**
     * The main background color of the actual board/main panel.
     */
    private static final java.awt.Color kMainBackgroundColor =
            new java.awt.Color(140, 50, 250);

    //////////////////////////////
    //Resources

    /** Image of top left arrow pointing in clockwise direction. */
    private ImageIcon arrowTopLeftCW;

    /** Image of top left arrow pointing in counter-clockwise direction. */
    private ImageIcon arrowTopLeftCCW;

    /** Image of top right arrow pointing in clockwise direction. */
    private ImageIcon arrowTopRightCW;

    /** Image of top right arrow pointing in counter-clockwise direction. */
    private ImageIcon arrowTopRightCCW;

    /** Image of bottom left arrow pointing in clockwise direction. */
    private ImageIcon arrowBottomLeftCW;

    /** Image of bottom left arrow pointing in counter-clockwise direction. */
    private ImageIcon arrowBottomLeftCCW;

    /** Image of bottom right arrow pointing in clockwise direction. */
    private ImageIcon arrowBottomRightCW;

    /** Image of bottom right arrow pointing in counter-clockwise direction. */
    private ImageIcon arrowBottomRightCCW;

    /**
     * Map of card images with the key being the string returned by toString in
     * card
     */
    private Map<String, ImageIcon> cardImages;

    /**
     * Map of card back images with the key being the player to who the card
     * back belongs.
     * This is so the right rotated image is returned when drawing a card back.
     */
    private Map<Players, ImageIcon> cardBacks;

    /** The window to display miscelleneous information about application.*/
    private JDialog aboutBox;

    /** The window to display game rules and instructions. */
    private JDialog howToPlayBox;

    /** The window to display the game options. */
    private JDialog optionsMenu;

    /** The controller that controls the game play logic */
    private I_Controller gameController;

    /** The table that holds the state of the cards and hands */
    private I_Table gameTable;

    /** Keeps track of the currently moused over card in the human hand */
    private int mousedOverCard;

    /** Keeps track of all visible cards in the human player's hand */
    private JLabel visibleHumanCards[]; //fixed defect 275

    /** Keeps track of the mouse event listeners that make the human player
     hand selection possible*/
    private JLabel cardSelectionAreas[];

    /** Listens to changes in the scrollbar that controls the human hand */
    private AdjustmentListener humanScrollbarListener;

    /** Keeps track of the instance of the application */
    private SingleFrameApplication app;

    /** Keeps track of whether or not the player has drawn a card, only useful
     if the single draw option is enabled */
    private boolean hasDrawn; //See defect 270.

    /** Keeps track of the last messages sent to the UI to display */
    private String messages[];  //Implemented improvement 282

    /**
     * The HumanScrollbarListener listens to scroll events in the scrollbar
     * that controls the view of the human hand
     *
     * @author Ilya Seletsky
     */
    private class HumanScrollbarListener implements AdjustmentListener
    {
        /**
         * Whenever the scrollbar value is changed, this event is caught here
         * and updates the human hand
         * @param e The scrollbar value change event caught.
         */
        public void adjustmentValueChanged(AdjustmentEvent e)
        {
            //CALL updateHand
            updateHand();
        }
    }

    /**
     * The CardAreaMouseListener listens to mouse over and mouse click events in
     * the card selection areas used by the human hand display.  This allows the
     * human hand display to update properly and select the right card when
     * playing a card.
     *
     * @author Ilya Seletsky
     */
    private class CardAreaMouseListner implements MouseListener
    {
        /** the number in the panel (used to help determine
         which card is selected)*/
        private int areaNum;

        /**
         * Creates the mouse listener and specifies it's number in the panel
         * @param areaNum the number in the panel (used to help determine
         * which card is selected)
         * @pre areaNum is greater than zero
         */
        public CardAreaMouseListner(int areaNum)
        {
            super();

            this.areaNum = areaNum;
        }

        /**
         * Returns the number of the listener in the panel
         * @return The number of the listener in the panel
         */
        public int getAreaNum()
        {
            return areaNum;
        }

        /**
         * When a mouse area is clicked when it's the player's turn and the card
         * is playable, this plays the card that is currently moused over.
         * @param e The mouse event caught.
         */
        public void mouseClicked(MouseEvent e)
        {            
            //IF the game is currently playing and 
            //it's not game it's the human player's turn and
            //the card is playable
            if(mousedOverCard != -1 &&
                    gameController.getState() == GameController.State.playing &&
                    gameController.getCurrentPlayer() == Players.kHuman &&
                    gameTable.isPlayableCard(
                        gameTable.getHand(Players.kHuman).get(mousedOverCard)))
            {
                //See defect 270
                hasDrawn = false;

                //CALL actionPerformed in the game controller
                    //with a play card command
                gameController.actionPerformed(new ActionEvent(this, 0,
                        "" + mousedOverCard));

                updateHand();
            }
            //END IF
        }

        /**
         * When a mouse area is moused over, this detects which card in the hand
         * should be shown in front based on the view port in the human hand
         * player.
         * @param e The mouse event caught.
         */
        public void mouseEntered(MouseEvent e)
        {
            //IF the game is currently playing
            if(gameController.getState() == GameController.State.playing)
            {
                //COMPUTE which card in the hand is currently moused over
                mousedOverCard = (areaNum - 2) +
                        (humanPanelScrollbar.getValue() / kMaxCardOffset);

                //CALL updateHand
                updateHand();
            }
            //END IF
        }

        /**Does nothing         *
         */
        public void mouseExited(MouseEvent e)
        {}

        /**Does nothing         *
         */
        public void mousePressed(MouseEvent e)
        {}

        /**Does nothing         *
         */
        public void mouseReleased(MouseEvent e)
        {}
    }

    /** Construct and setup the GUI interface.
     *  @param app is the game application
     */
    public SwingPlayer(SingleFrameApplication app) {

        super(app);
        this.app = app;

        cardImages = new HashMap<String, ImageIcon>();
        cardBacks = new HashMap<Players, ImageIcon>();
        visibleHumanCards = new JLabel[kMaxHumanCardsVisible + 1];
        cardSelectionAreas = new JLabel[kMaxHumanCardsVisible];
        messages = new String[kNumMessages];

        for(int messageInd = 0; messageInd < kNumMessages; messageInd++)
        {
            messages[messageInd] = "";
        }

        loadMedia();

        initComponents();
        initComputerPanels();
        initHandPanel();

        drawButton.setActionCommand("draw");
        passButton.setActionCommand("pass");
        colChooserRedButton.setActionCommand("colorred");
        colChooserGreenButton.setActionCommand("colorgreen");
        colChooserBlueButton.setActionCommand("colorblue");
        colChooserYellowButton.setActionCommand("coloryellow");

        deckBackLabel.setIcon(cardBacks.get(Players.kHuman));

        this.getFrame().setResizable(false); //Fixed defect 284
    }

    /**
     * A convenience method that allows for unit testing.  This calls
     * getApplication from CeroApp normally but calls it from
     * FakeSingleFrameApplication during unit testing.  This is kindof a hack
     * but is the only way to allow the UI to be unit tested.  I even had to
     * edit the autogenerated code by netbeans to make this work.
     *
     * @return A SingleFrameApplication instance
     */
    //TODO: this can probably be removed
    private SingleFrameApplication getApplicationContext()
    {
        if(app instanceof FakeSingleFrameApplication)
        {
            return FakeSingleFrameApplication.getApplication();
        }
        else
        {
            return CeroApp.getApplication();
        }
    }

    /**
     * Draws and displays the window that provides miscellaneous information
     * about the application.
     * @post aboutBox is not null.
     */
    @Action
    public void showAboutBox() {
        //This is auto generated
        if (aboutBox == null) {
            JFrame mainFrame = getApplicationContext().getMainFrame();
            aboutBox = new SwingAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }

        getApplicationContext().show(aboutBox);
    }

    /**
     * Draw and displays a window that provides the instructions
     * on how to play Cero.
     * @post howToPlayBox is not null.
     */
    @Action
    public void showHowToPlayBox() {
        //IF howToPlayBox is null THEN
        if (howToPlayBox == null) {
            //CREATE howToPlayBox with its parent being mainFrame
            JFrame mainFrame = getApplicationContext().getMainFrame();
            howToPlayBox = new SwingHowToPlay(mainFrame);

            //SET howToPlayBox location relative to the mainFrame
            howToPlayBox.setLocationRelativeTo(mainFrame);
        }
        //END IF

        //SHOW howToPlayBox
        getApplicationContext().show(howToPlayBox);
    }

    /**
     * Draws and displays the window that allows the player to customize
     * the features of the game.
     * @post optionsMenu is not null.
     */
    @Action
    public void showOptionsMenu() {
        //IF optionsMenu is null THEN
        if (optionsMenu == null) {

            // Fixes Defect #266
            //CREATE optionsMenu with its parent being mainFrame
            JFrame mainFrame = getApplicationContext().getMainFrame();
            optionsMenu = new SwingOptionsMenu(mainFrame, gameController);

            //SET optionsMenu location relative to the mainFrame
            optionsMenu.setLocationRelativeTo(mainFrame);
        }
        //END IF

        //SHOW optionsMenu
        getApplicationContext().show(optionsMenu);
    }

    /** Starts a new game. */
    @Action
    public void newGameAction()
    {
        // Fixes Defect # 273
        //CALL resetGame in the controller
        gameController.resetGame();

        //Temporarily fixes defect 276
        for(int messageInd = 0; messageInd < kNumMessages; messageInd++)
        {
            messages[messageInd] = "";
        }

        messageTextLabel.setText("");

        gameController.startGame();
    }
    
    ////////////////////////////////////////////////
    //
    //             Swing Player Methods
    //
    ////////////////////////////////////////////////

    /**
     * Loads all graphics resources
     * @post All ImageIcon resources are not null and are loaded.
     */
    public void loadMedia()
    {
        //LOAD the clockwise top left arrow image
        arrowTopLeftCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowTLCW.png")));

        //LOAD the clockwise top right arrow image
        arrowTopRightCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowTRCW.png")));

        //LOAD the clockwise bottom left arrow image
        arrowBottomLeftCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowBLCW.png")));

        //LOAD the clockwise bottom right arrow image
        arrowBottomRightCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowBRCW.png")));

        //LOAD the counter clockwise top left arrow image
        arrowTopLeftCCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowTLCCW.png")));

        //LOAD the counter clockwise top right arrow image
        arrowTopRightCCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowTRCCW.png")));

        //LOAD the counter clockwise bottom left arrow image
        arrowBottomLeftCCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowBLCCW.png")));

        //LOAD the counter clockwise bottom right arrow image
        arrowBottomRightCCW = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(this.getClass().
                getResource("resources/arrowimages/arrowBRCCW.png")));

        //FOR each card        
            //create an ImageIcon by loading the image for that card
            //put the ImageIcon into a map with the key being a
                //string that would be returned by toString when called on that
                //card
        for(Color color: Color.values())
        {
            for(Rank rank: Rank.values())
            {
                if((color == Color.wild && 
                        (rank == Rank.wild || rank == Rank.wilddrawfour)) ||
                        (color != Color.wild && rank != Rank.wild &&
                        rank != Rank.wilddrawfour))
                {
                    String cardName = "" +
                            ((color.toString()).toUpperCase()).charAt(0) +
                            rank.symbol();

                    cardImages.put(cardName,
                            new ImageIcon(Toolkit.getDefaultToolkit().
                                getImage(this.getClass().getResource(
                                "resources/cardimages/" + cardName + ".png"))));
                }
            }
        }
        //END FOR

        //FOR each player in the Players Enum
        for(Players player: Players.values())
        {
            //create an ImageIcon by loading the image for that card back
            //put the ImageIcon into a map with the key being a player enum
            cardBacks.put(player,
                    new ImageIcon(Toolkit.getDefaultToolkit().
                        getImage(this.getClass().getResource(
                        "resources/cardimages/" + player.toString() +
                        ".png"))));
        }
        //END FOR
    }
    
    /**
     * Initializes all of the computer player panels
     */
    private void initComputerPanels()
    {
        // Call removeAll for each comptuer panel
        computerPanelL.removeAll();
        computerPanelT.removeAll();
        computerPanelR.removeAll();
    }
    
    /**
     * Draws the card images in each computer's respective panels.
     * The max number of cards each panel may display is kMaxDisplayedCards.
     * @pre initComputerPanels has been called
     * @pre player is a computer player not the human player
     * @param player The computer player for who to update the computer hand
     */
    private void updateComputerHand(Players player)
    {
        //Determine which panel is the player's
        JPanel playerPanel = null;
        int cardsDisplayNum = 0;
        int cardOffset = 0;
        int cardX = 0;
        int cardY = 0;

        switch(player)
        {
            case kComputer1:
                playerPanel = computerPanelL;
                cardX = 0;
                cardY = 1;
                break;
            case kComputer2:
                playerPanel = computerPanelT;
                cardX = 1;
                cardY = 0;
                break;
            case kComputer3:
                playerPanel = computerPanelR;
                cardX = 0;
                cardY = 1;
                break;
            default:
                throw new UnsupportedOperationException(
                        "UpdateComputerHand was given an invalid player enum");
        }

        //IF it's the current player's turn
        if(gameController.getCurrentPlayer() == player)
        {
            //SET the panel background to kHandTurnBackroundColor to show that
                //it's the current player's turn
            playerPanel.setBackground(kHandTurnBackroundColor);
        }
        //ELSE
        else
        {
            //SET the panel background to kHandBackroundColor
            playerPanel.setBackground(kHandBackroundColor);
        }
        //END IF

        //Remove all elements from the specified computer player panel
        playerPanel.removeAll();

        //COMPUTE number of cardsDisplayNum to the size of the computer player's
            //hand
        cardsDisplayNum = gameTable.getHand(player).size();

        //IF there are not zero cards, or else div by 0 error will happen
        if(cardsDisplayNum > 0)
        {
            //IF number of cardsDisplayNum > kMaxComputerCards
            if(cardsDisplayNum > kMaxComputerCards)
            {
                //SET cardsDisplayNum = kMaxComputerCards
                cardsDisplayNum = kMaxComputerCards;
            }
            //ENDIF

            //COMPUTE cardOffset to kCardDisplayWidth / cardsDisplayNum
            cardOffset = (kCardDisplayWidth -
                    ((kCardWidth - (kCardDisplayWidth / cardsDisplayNum)))  )
                    / cardsDisplayNum;

            //IF cardOffset > kMaxCardOffset
            if(cardOffset > kMaxCardOffset)
            {
                //SET cardOffset = kMaxCardOffset
                cardOffset = kMaxCardOffset;
            }
            //ENDIF

            //FOR cardsDisplayNum number of cards
            for(int card = 0; card < cardsDisplayNum; card ++)
            {
                //Add an ImageIcon with the card back for the specified player
                    //at a position that is cardOffset pixels off from the last
                    //card

                JLabel newCardImage = new JLabel(cardBacks.get(player));

                playerPanel.add(newCardImage, new AbsoluteConstraints(
                        (card * cardOffset * cardX) + kHandPadding,
                        (card * cardOffset * cardY) + kHandPadding));

                newCardImage.setVisible(true);
                playerPanel.setVisible(true);
            }
            //ENDFOR
        }
        // END IF
    }

    /**
     * Creates a panel for the human player's hand.
     */
    private void initHandPanel()
    {
        //Set moused over card to 0
        mousedOverCard = 0;

        //Remove all elements in the human hand panel
        //FIXED DEFECT 256
        humanPanel.removeAll();

        //IF humanScrollbarListener = null
        if(humanScrollbarListener == null)
        {
            //SET humanScrollbarListener to a new instance of
                //HumanScrollbarLister
            humanScrollbarListener = new HumanScrollbarListener();
        }
        //END IF

        //Set the scrollbar to not visible
        humanPanelScrollbar.setVisible(false);

        //Set the scrollbar page size to the width of the card viewer
        humanPanelScrollbar.setVisibleAmount(kCardDisplayWidth);

        //Add a scrollbar AdjustmentListener to the human card selection
            //scrollbar
        humanPanelScrollbar.addAdjustmentListener(humanScrollbarListener);

        //FOR kMaxHumanCardsVisible number of card selection
            //areas
        for(int area = 0; area < kMaxHumanCardsVisible; area++)
        {
            //Add a JLabel with height kCardDisplayHeight and width
                //kMaxCardOffset to the human hand panel
            cardSelectionAreas[area] = new JLabel();
            cardSelectionAreas[area].setPreferredSize(
                    new java.awt.Dimension(kMaxCardOffset, kCardDisplayHeight +
                    kHandPadding));

            //Add a CardAreaMouseListener to the JPanel
            cardSelectionAreas[area].
                    addMouseListener(new CardAreaMouseListner(area));
        }
        //END FOR
    }

    /**
     * Draw the human player's cards in the human's hand panel.
     * @pre initHandPanel has been called.
     */
    private void updateHand()
    {
        int totalViewWidth = 0;
        int maxViewPos = 0;
        int firstVisCard = 0;
        int lastVisCard = 0;
        int drawPosInd = 0;
        int cardIndex = 0;
        int arrayInd = 0;

        humanPanel.removeAll();

        //GET human hand
        ArrayList<Card> humanHand = gameTable.getHand(Players.kHuman);

        //SORT cards in human hand in order of Color then Rank according to
            //their order in the Color and Rank enums
        java.util.Collections.sort(humanHand);

        //CALCULATE total width of the cards view
            //number of cards * kMaxCardOffset + (kCardWidth - kMaxCardOffset)
        totalViewWidth = (humanHand.size() * kMaxCardOffset) +
                (kCardWidth - kMaxCardOffset);

        //CALCULATE the maximum position of the view within the human cards
            //total width - kCardDisplayWidth
        maxViewPos = totalViewWidth - kCardDisplayWidth;

        //IF maxViewPos < 0
        if(maxViewPos < 0)
        {
            //SET maxViewPos to 0
            maxViewPos = 0;
        }
        //END IF

        //SET humanPanelScrollbar visibility to IF maxViewPos is > 0
        humanPanelScrollbar.setVisible(maxViewPos > 0);

        //SET humanPanelScrollbar maximum value to the right viewport
        humanPanelScrollbar.setMaximum(maxViewPos);

        //IF scrollbar is scrolled to a position beyond the maximum
        if(humanPanelScrollbar.getValue() > maxViewPos)
        {
            //Remove the scroll bar AdjustmentEventListener (This prevents this
                //exact function from being called again by the listener)
            humanPanelScrollbar.removeAdjustmentListener(
                    humanScrollbarListener);
            //SET scroll bar viewport to a view at the right boundary of the
                //view
            humanPanelScrollbar.setValue(maxViewPos);
            humanPanelScrollbar.setMaximum(maxViewPos);
            //Add the scroll bar AdjustmentEventListener back
            humanPanelScrollbar.addAdjustmentListener(humanScrollbarListener);
        }
        //END IF

        //FOR all card selection areas
        int areaIndex = 0;
        for(JLabel area: cardSelectionAreas)
        {
            //Removing the element and readding it is such a dirty hack but
                //this is the only way both cards and mouse area positions
                //update, I've spent hours on this so don't change it unless
                //a more efficient way that actually works is doable

            area.setVisible(false);

            //SET x position to (where it was put in drawHandPanel) - ((viewport
                //position) modulo (width of card selection area))

            humanPanel.add(area,
                new AbsoluteConstraints(
                        ((areaIndex - 1) * kMaxCardOffset + kHandPadding) -
                        (humanPanelScrollbar.getValue() % kMaxCardOffset),
                kHandPadding));

            area.setVisible(true);

            areaIndex++;
        }
        //END FOR

        //IF it's currently the human's turn
        if(gameController.getCurrentPlayer() == Players.kHuman)
        {
            //SET the panel background to kHandTurnBackroundColor to show that
                //it's the current player's turn
            humanPanel.setBackground(kHandTurnBackroundColor);
        }
        //ELSE
        else
        {
            //SET the panel background to kHandBackroundColor
            humanPanel.setBackground(kHandBackroundColor);
        }
        //END IF

        //IF hand size is not empty
        if(humanHand.size() > 0)
        {
            //GET first card in hand that is visible in the cards viewport
            //(view port left boundary - kCardWidth - kMaxCardOffset) /
                // kMaxCardOffset
            firstVisCard =
                    (humanPanelScrollbar.getValue() -
                        (kCardWidth - kMaxCardOffset) - kHandPadding)
                    / kMaxCardOffset;

            //IF firstVisCard < 0
            if(firstVisCard < 0)
            {
                //SET firstVisCard to 0
                firstVisCard = 0;
            }
            //END IF

            //GET last card in hand that is visible in the cards viewport
                //firstVisCard + number of cards that fit in the view
            lastVisCard = firstVisCard + kMaxHumanCardsVisible;

            //IF lastVisCard > size of hand - 1
            if(lastVisCard > humanHand.size() - 1)
            {
                //SET lastVisCard to size of hand - 1
                lastVisCard = humanHand.size() - 1;
            }
            //END IF

            //IF mousedOverCard < 0
            if(mousedOverCard < 0)
            {
                //SET mousedOverCard = 0
                mousedOverCard = 0;
            }
            //ELSE IF mousedOverCard > lastVisCard
            else if(mousedOverCard > lastVisCard)
            {
                //SET mousedOverCard = lastVisCard
                
                //Fixed defect 294
                if(mousedOverCard == lastVisCard + 1)
                {
                    mousedOverCard = lastVisCard;
                }
                else
                {
                    mousedOverCard = -1;
                }
            }
            //END IF

            //This will make the cards draw stacked up in such a way that the 
            //moused over card will show up on top

            //IF moused over card < last visible card &&
                //mousedOverCard > firstVisCard
            if(mousedOverCard <= lastVisCard && mousedOverCard >= firstVisCard)
            {
                //Draw the moused over card
                addCard(humanHand, mousedOverCard, arrayInd ++,
                        (((mousedOverCard - firstVisCard) * kMaxCardOffset) +
                                kHandPadding) -
                            (humanPanelScrollbar.getValue() -
                            (firstVisCard * kMaxCardOffset)));

                drawPosInd = 0;
                cardIndex = mousedOverCard - 1;

                //FOR each card from first visible hand to moused over card - 1
                while(cardIndex >= firstVisCard)
                {
                    addCard(humanHand, cardIndex, arrayInd ++,
                            ((-drawPosInd * kMaxCardOffset) + kHandPadding) -
                                (humanPanelScrollbar.getValue() -
                                ((mousedOverCard - 1) * kMaxCardOffset)));

                    drawPosInd ++;
                    cardIndex --;
                }
                //END FOR

                //FOR each card from last visible card to moused over card + 1
                cardIndex = mousedOverCard + 1;
                drawPosInd = mousedOverCard - firstVisCard + 1;

                while(cardIndex <= lastVisCard)
                {
                    addCard(humanHand, cardIndex, arrayInd ++,
                            (drawPosInd * kMaxCardOffset + kHandPadding) -
                                (humanPanelScrollbar.getValue() -
                                (firstVisCard * kMaxCardOffset)));

                    drawPosInd ++;
                    cardIndex ++;
                }
                //END FOR
            }
            //ELSE if mousedOverCard < lastVisCard
            else if(mousedOverCard < lastVisCard)
            {
                drawPosInd = 0;
                cardIndex = firstVisCard;

                while(cardIndex <= lastVisCard)
                {
                    addCard(humanHand, cardIndex, arrayInd ++,
                            (drawPosInd * kMaxCardOffset + kHandPadding) -
                                (humanPanelScrollbar.getValue() -
                                (firstVisCard * kMaxCardOffset)));

                    drawPosInd ++;
                    cardIndex ++;
                }
            }
            //ELSE
            else
            {
                drawPosInd = 0;
                cardIndex = lastVisCard;

                while(cardIndex >= firstVisCard)
                {
                    addCard(humanHand, cardIndex, arrayInd ++,
                            ((-drawPosInd * kMaxCardOffset) + kHandPadding) - 
                                (humanPanelScrollbar.getValue() -
                                (lastVisCard * kMaxCardOffset)));

                    drawPosInd ++;
                    cardIndex --;
                }

                //END FOR
            }
            //END IF
        }
        //END IF
    }

    /**
     * Adds a card to the human hand panel
     * @param hand The ArrayList containing the hand contents
     * @param cardIndex Which card in the array list to draw
     * @param arrayInd Array index of visible card
     * @param x position in the panel
     */
    private void addCard(ArrayList<Card> hand, int cardIndex, int arrayInd,
            int x)
    {
        Card card = hand.get(cardIndex);

        //CREATE the JLabel for that card image
        visibleHumanCards[arrayInd] =
                new JLabel(cardImages.get(card.toString()));

        //Add it to the human hand panel at the right position
            //if the game is currently in playing mode
            //if it's the human player's turn and the card is playable offset it
            //up by kHandPadding + 3 pixels
        humanPanel.add(visibleHumanCards[arrayInd],
                new AbsoluteConstraints(x ,
                gameController.getState() == GameController.State.playing &&
                gameController.getCurrentPlayer() == Players.kHuman && 
                        gameTable.isPlayableCard(card)
                    ? kHandPadding - 3 : kHandPadding << 1));
    }

    /**
     * Used for debugging only.  This draws a solid color box with a number
     *      label of the card in the deck.  Was VERY useful in the development
     *      of the human card chooser.
     * @param hand This isn't used but it's convenient to leave so I don't
     *      have to remove this argument when debugging
     * @param cardIndex Which card is being drawn (labels the box)
     * @param arrayInd Array index of visible card
     * @param x position in the panel
     * @param color the color with which to draw the box
     */
    private void addCard(ArrayList<Card> hand, int cardIndex,
            int arrayInd, int x, java.awt.Color color)
    {
        //CREATE the JLabel for that card but make it the debug thing
        visibleHumanCards[arrayInd] =
                new JLabel("" + cardIndex);
        visibleHumanCards[arrayInd].setBackground(color);
        visibleHumanCards[arrayInd].setOpaque(true);

        //Add it to the human hand panel at the right position
        humanPanel.add(visibleHumanCards[arrayInd],
                new AbsoluteConstraints(x ,0));
    }
    
    /** Refresh the panel that displays the draw deck pile. */
    private void updateDeckPanel()
    {
        //SET the deck panel to visible
        deckPanel.setVisible(true);

        //GET the card that is currently discarded on the table
        Card discardedCard = gameTable.getDiscardCard();
        
        //IF there is a card discarded
        if(discardedCard != null)
        {
            //SET the image displayed as the discard card to the proper card
                //image
            deckDiscardLabel.setVisible(true);
            deckDiscardLabel.setIcon(cardImages.get(discardedCard.toString()));
        }
        //ELSE
        else
        {
            //SET there to be no image displayed as the current discard card
            deckDiscardLabel.setVisible(false);
        }
        //END IF

        //IF it's not the player's turn
        if(gameController.getCurrentPlayer() != Players.kHuman)
        {
            //Set the draw button to not visible
            drawButton.setVisible(false);
            //Set the pass button to not visible
            passButton.setVisible(false);
        }
        //ELSE
        else
        {
            boolean playableCard = false;

            for(Card card: gameTable.getHand(Players.kHuman))
            {
                if(gameTable.isPlayableCard(card))
                {
                    playableCard = true;

                    break;
                }
            }

            //See defect 270.  Show pass button when no playable cards,
            // has drawn, and multiple draws not allowed. 
            //IF there is no playable card and the draw deck is empty OR
            // there is no playable card and hasDrawn and multiple draws not
            // allowed THEN
            if ((!playableCard && gameTable.getDrawDeckSize() <= 0) ||
                (!playableCard && hasDrawn && !
                gameController.getOptions().multipleDrawsAllowed()))
            {
                //Set the draw button to not visible
                drawButton.setVisible(false);
                //Set the pass button to visible
                passButton.setVisible(true);
            }
            //ELSE
            else
            {
                //Fixes defect 292
                //IF draw deck is not empty OR
                // discard pile has more than 1 card THEN
                if (gameTable.getDrawDeckSize() > 0 ||
                     gameTable.getDiscardDeckSize() > 1)
                {
                    //Set the draw button to visible
                    drawButton.setVisible(true);
                }
                //END IF
                //Set the pass button to not visible
                passButton.setVisible(false);
            }
            //END IF
        }
        //END IF

        //Fixes defect 292
        //SET the card back image representing the deck visibility to whether or
        //not the deck is empty OR if discard pile has more than 1 card
        deckBackLabel.setVisible(gameTable.getDrawDeckSize() > 0 ||
           gameTable.getDiscardDeckSize() > 1);

        //CALL hideColorChooser
        hideColorChooser();

        //SET deck panel background to table color
        switch(gameTable.getColor())
        {
            case red:
                deckPanel.setBackground(java.awt.Color.RED);
                break;
            case green:
                //The green cards have a specific shade of green
                deckPanel.setBackground(new java.awt.Color(0, 192, 0));
                break;
            case blue:
                deckPanel.setBackground(java.awt.Color.BLUE);
                break;
            case yellow:
                deckPanel.setBackground(java.awt.Color.YELLOW);
                break;
            case wild:  //this should never happen, but just in case
                //The wild cards have a specific shade of purple
                deckPanel.setBackground(new java.awt.Color(50, 10, 115));
                break;
        }
    }
    
    /**
     * Refresh direction arrows from clockwise to counterclockwise
     * and vice versa.
     */
    private void updateArrows()
    {
        //SET all arrows to visible
        arrowLabelTL.setVisible(true);
        arrowLabelTR.setVisible(true);
        arrowLabelBL.setVisible(true);
        arrowLabelBR.setVisible(true);

        //IF table direction is clockwise
        if(gameTable.getDirection())
        {
            //SET top left arrow image to the top left clockwise arrow
            arrowLabelTL.setIcon(arrowTopLeftCW);
            //SET top right arrow image to the top right clockwise arrow
            arrowLabelTR.setIcon(arrowTopRightCW);
            //SET bottom left arrow image to the bottom left clockwise arrow
            arrowLabelBL.setIcon(arrowBottomLeftCW);
            //SET bottom right arrow image to the bottom right clockwise arrow
            arrowLabelBR.setIcon(arrowBottomRightCW);
        }
        //ELSE
        else
        {
            //SET top left arrow image to the top left counter clockwise arrow
            arrowLabelTL.setIcon(arrowTopLeftCCW);
            //SET top right arrow image to the top right counter clockwise arrow
            arrowLabelTR.setIcon(arrowTopRightCCW);
            //SET bottom left arrow image to the bottom left counter clockwise 
                //arrow
            arrowLabelBL.setIcon(arrowBottomLeftCCW);
            //SET bottom right arrow image to the bottom right counter clockwise
                //arrow
            arrowLabelBR.setIcon(arrowBottomRightCCW);
        }
        //ENDIF
    }
    
    /**
     * Hides all of the color chooser buttons.
     */
    private void hideColorChooser()
    {
        //FOR each color chooser button
            //SET it to not visible
        //END FOR
        colChooserRedButton.setVisible(false);
        colChooserGreenButton.setVisible(false);
        colChooserBlueButton.setVisible(false);
        colChooserYellowButton.setVisible(false);
    }

    /**
     * Updates all player name labels
     */
    private void updatePlayerNames()
    {
        nameLabel1.setText(gameController.getOptions().getName(Players.kHuman));
        nameLabel2.setText(gameController.getOptions().getName(
                Players.kComputer1));
        nameLabel3.setText(gameController.getOptions().getName(
                Players.kComputer2));
        nameLabel4.setText(gameController.getOptions().getName(
                Players.kComputer3));
    }

    ////////////////////////////////////////////////
    //
    //          I_Player Implementations
    //
    ////////////////////////////////////////////////

    /** {@inheritDoc} */
    public void setControl(I_Controller gc)
    {
        //SET game controller reference to gc
        gameController = gc;
    }

    /** {@inheritDoc} */
    public void setTable(I_Table tbl)
    {
        //SET table reference to tbl
        gameTable = tbl;
    }

    /** {@inheritDoc} */
    public void setup()
    {
        //CALL update
        for(int messageInd = 0; messageInd < kNumMessages; messageInd++)
        {
            messages[messageInd] = "";
        }

        messageTextLabel.setText("");
        updatePlayerNames();
        update(null, null);
    }

    /** {@inheritDoc} */
    public void resetDeck()
    {
        // Do nothing
    }

    /** {@inheritDoc} */
    public void doTurn()
    {
        //Call Update
        update(null, null);
    }

    /** {@inheritDoc} */
    public void showGameOver(Players winner)
    {
        //CALL hideColorChooser
        hideColorChooser();

        //SET deck panel to not visible
        deckPanel.setVisible(false);

        //Set the draw button to not visible
        drawButton.setVisible(false);

        //Set the pass button to not visible
        passButton.setVisible(false);

        //SET all direction arrow images to not visible
        arrowLabelTL.setVisible(false);
        arrowLabelTR.setVisible(false);
        arrowLabelBL.setVisible(false);
        arrowLabelBR.setVisible(false);

        //Update the human card chooser and make it unplayable
        updateHand();

        //FOR each computer player
            //Update the deck panel for that player
        //END FOR
        updateComputerHand(Players.kComputer1);
        updateComputerHand(Players.kComputer2);
        updateComputerHand(Players.kComputer3);

        //SET all hand panels to be kHandBackroundColor
        humanPanel.setBackground(kHandBackroundColor);
        computerPanelL.setBackground(kHandBackroundColor);
        computerPanelT.setBackground(kHandBackroundColor);
        computerPanelR.setBackground(kHandBackroundColor);

        //CASE winner OF
        switch(winner)
        {
            //Human:            set the human card chooser background to 
                //kHandWinBackroundColor to show he won
            case kHuman:
                humanPanel.setBackground(kHandWinBackroundColor);
                break;
            //Computer 1:       set the Computer 1 deck panel background to
                //kHandWinBackroundColor to show he won
            case kComputer1:
                computerPanelL.setBackground(kHandWinBackroundColor);
                break;
            //Computer 2:       set the Computer 2 deck panel background to
                //kHandWinBackroundColor to show he won
            case kComputer2:
                computerPanelT.setBackground(kHandWinBackroundColor);
                break;
            //Computer 3:       set the Computer 3 deck panel background to
                //kHandWinBackroundColor to show he won
            case kComputer3:
                computerPanelR.setBackground(kHandWinBackroundColor);
                break;
        }
        //ENDCASE
    }

    /** {@inheritDoc} */
    public void showColorChooser()
    {
        //Set the deck panel background to the main background color
        deckPanel.setBackground(kMainBackgroundColor);

        //SET the draw button to not visible
        drawButton.setVisible(false);

        //SET the pass button to not visible
        passButton.setVisible(false);

        //SET the deck back panel to not visible
        deckBackLabel.setVisible(false);

        //FOR each color chooser button
            //SET it to visible
        //END FOR
        colChooserRedButton.setVisible(true);
        colChooserGreenButton.setVisible(true);
        colChooserBlueButton.setVisible(true);
        colChooserYellowButton.setVisible(true);

        //See defect 270
        hasDrawn = false;
    }

    /** {@inheritDoc} */
    public void update(java.util.Observable obs, Object obj)
    {
        //CALL updateHand
        updateHand();

        //FOR each computer player
            //Update the deck panel for that player
        //END FOR
        updateComputerHand(Players.kComputer1);
        updateComputerHand(Players.kComputer2);
        updateComputerHand(Players.kComputer3);

        //CALL updateDeckPanel
        updateDeckPanel();

        //CALL updateArrows
        updateArrows();

        // CALL update player names
        updatePlayerNames();
    }

    /** Make this user interface visible.
     * @param visible Parameter is ignored.
     */
    public void setVisible(boolean visible)
    {
        update(null, null);
    }

    /** {@inheritDoc} */
    public void showStatusMessage(String txt)
    {
        String finalMessage;
        int messageInd;

        //FOR kNumMessages - 1 down to 1
        for(messageInd = kNumMessages - 1; messageInd > 0; messageInd--)
        {
            //Set the current message to the message at the last index
            messages[messageInd] = messages[messageInd - 1];
        }
        //END FOR

        //SET the first message to txt
        messages[0] = txt;

        //FOR all kNumMessages concatenate the messages separated by newlines
            //and set the message text label to that text
        finalMessage = "<html>";

        //Fixed customer defect 293
        for(messageInd = kNumMessages - 1; messageInd >= 0; messageInd--)
        {
            finalMessage += messages[messageInd] + "<br>";
        }

        finalMessage += "</html>";

        messageTextLabel.setText(finalMessage);
    }

    //Fixes defect 291, window size fits on 1024x768 monitors
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        computerPanelT = new javax.swing.JPanel();
        computerPanelL = new javax.swing.JPanel();
        humanPanel = new javax.swing.JPanel();
        humanPanelScrollbar = new javax.swing.JScrollBar();
        arrowLabelTL = new javax.swing.JLabel();
        arrowLabelTR = new javax.swing.JLabel();
        arrowLabelBR = new javax.swing.JLabel();
        drawButton = new javax.swing.JButton();
        passButton = new javax.swing.JButton();
        messageTextLabel = new javax.swing.JLabel();
        deckPanel = new javax.swing.JPanel();
        deckDiscardLabel = new javax.swing.JLabel();
        deckBackLabel = new javax.swing.JLabel();
        colChooserYellowButton = new javax.swing.JButton();
        colChooserGreenButton = new javax.swing.JButton();
        colChooserBlueButton = new javax.swing.JButton();
        colChooserRedButton = new javax.swing.JButton();
        computerPanelR = new javax.swing.JPanel();
        arrowLabelBL = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        nameLabel4 = new javax.swing.JLabel();
        nameLabel3 = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        newGameMenuItem = new javax.swing.JMenuItem();
        optionsMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        howToPlayMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(cero.CeroApp.class).getContext().getResourceMap(SwingPlayer.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        computerPanelT.setBackground(resourceMap.getColor("computerPanelT.background")); // NOI18N
        computerPanelT.setName("computerPanelT"); // NOI18N
        computerPanelT.setPreferredSize(new java.awt.Dimension(400, 160));
        computerPanelT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainPanel.add(computerPanelT, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        computerPanelL.setBackground(resourceMap.getColor("computerPanelL.background")); // NOI18N
        computerPanelL.setName("computerPanelL"); // NOI18N
        computerPanelL.setPreferredSize(new java.awt.Dimension(160, 400));
        computerPanelL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainPanel.add(computerPanelL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        humanPanel.setBackground(resourceMap.getColor("humanPanel.background")); // NOI18N
        humanPanel.setName("humanPanel"); // NOI18N
        humanPanel.setPreferredSize(new java.awt.Dimension(400, 165));
        humanPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainPanel.add(humanPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, -1, -1));

        humanPanelScrollbar.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        humanPanelScrollbar.setName("humanPanelScrollbar"); // NOI18N
        mainPanel.add(humanPanelScrollbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 400, -1));

        arrowLabelTL.setText(resourceMap.getString("arrowLabelTL.text")); // NOI18N
        arrowLabelTL.setName("arrowLabelTL"); // NOI18N
        arrowLabelTL.setPreferredSize(new java.awt.Dimension(135, 135));
        mainPanel.add(arrowLabelTL, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 120, 110));

        arrowLabelTR.setText(resourceMap.getString("arrowLabelTR.text")); // NOI18N
        arrowLabelTR.setName("arrowLabelTR"); // NOI18N
        arrowLabelTR.setPreferredSize(new java.awt.Dimension(135, 135));
        mainPanel.add(arrowLabelTR, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 120, 110));

        arrowLabelBR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        arrowLabelBR.setText(resourceMap.getString("arrowLabelBR.text")); // NOI18N
        arrowLabelBR.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        arrowLabelBR.setName("arrowLabelBR"); // NOI18N
        arrowLabelBR.setPreferredSize(new java.awt.Dimension(135, 135));
        mainPanel.add(arrowLabelBR, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 150, 120));

        drawButton.setText(resourceMap.getString("drawButton.text")); // NOI18N
        drawButton.setToolTipText(resourceMap.getString("drawButton.toolTipText")); // NOI18N
        drawButton.setName("drawButton"); // NOI18N
        drawButton.setPreferredSize(new java.awt.Dimension(55, 32));
        drawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawButtonActionPerformed(evt);
            }
        });
        mainPanel.add(drawButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 90, -1));

        passButton.setText(resourceMap.getString("passButton.text")); // NOI18N
        passButton.setToolTipText(resourceMap.getString("passButton.toolTipText")); // NOI18N
        passButton.setName("passButton"); // NOI18N
        passButton.setPreferredSize(new java.awt.Dimension(55, 32));
        passButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passButtonActionPerformed(evt);
            }
        });
        mainPanel.add(passButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 90, -1));

        messageTextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageTextLabel.setText(resourceMap.getString("messageTextLabel.text")); // NOI18N
        messageTextLabel.setName("messageTextLabel"); // NOI18N
        mainPanel.add(messageTextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 440, 90));

        deckPanel.setBackground(resourceMap.getColor("deckPanel.background")); // NOI18N
        deckPanel.setName("deckPanel"); // NOI18N
        deckPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deckDiscardLabel.setText(resourceMap.getString("deckDiscardLabel.text")); // NOI18N
        deckDiscardLabel.setMaximumSize(new java.awt.Dimension(101, 135));
        deckDiscardLabel.setName("deckDiscardLabel"); // NOI18N
        deckDiscardLabel.setPreferredSize(new java.awt.Dimension(101, 135));
        deckPanel.add(deckDiscardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        deckBackLabel.setMaximumSize(new java.awt.Dimension(101, 135));
        deckBackLabel.setName("deckBackLabel"); // NOI18N
        deckBackLabel.setPreferredSize(new java.awt.Dimension(101, 135));
        deckPanel.add(deckBackLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        colChooserYellowButton.setText(resourceMap.getString("colChooserYellowButton.text")); // NOI18N
        colChooserYellowButton.setToolTipText(resourceMap.getString("colChooserYellowButton.toolTipText")); // NOI18N
        colChooserYellowButton.setName("colChooserYellowButton"); // NOI18N
        colChooserYellowButton.setPreferredSize(new java.awt.Dimension(55, 32));
        colChooserYellowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colChooserYellowButtonActionPerformed(evt);
            }
        });
        deckPanel.add(colChooserYellowButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 90, -1));

        colChooserGreenButton.setText(resourceMap.getString("colChooserGreenButton.text")); // NOI18N
        colChooserGreenButton.setToolTipText(resourceMap.getString("colChooserGreenButton.toolTipText")); // NOI18N
        colChooserGreenButton.setName("colChooserGreenButton"); // NOI18N
        colChooserGreenButton.setPreferredSize(new java.awt.Dimension(55, 32));
        colChooserGreenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colChooserGreenButtonActionPerformed(evt);
            }
        });
        deckPanel.add(colChooserGreenButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 90, 30));

        colChooserBlueButton.setText(resourceMap.getString("colChooserBlueButton.text")); // NOI18N
        colChooserBlueButton.setToolTipText(resourceMap.getString("colChooserBlueButton.toolTipText")); // NOI18N
        colChooserBlueButton.setName("colChooserBlueButton"); // NOI18N
        colChooserBlueButton.setPreferredSize(new java.awt.Dimension(55, 32));
        colChooserBlueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colChooserBlueButtonActionPerformed(evt);
            }
        });
        deckPanel.add(colChooserBlueButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 90, -1));

        colChooserRedButton.setText(resourceMap.getString("colChooserRedButton.text")); // NOI18N
        colChooserRedButton.setToolTipText(resourceMap.getString("colChooserRedButton.toolTipText")); // NOI18N
        colChooserRedButton.setName("colChooserRedButton"); // NOI18N
        colChooserRedButton.setPreferredSize(new java.awt.Dimension(55, 32));
        colChooserRedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colChooserRedButtonActionPerformed(evt);
            }
        });
        deckPanel.add(colChooserRedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 90, -1));

        mainPanel.add(deckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 240, 160));

        computerPanelR.setBackground(resourceMap.getColor("computerPanelR.background")); // NOI18N
        computerPanelR.setName("computerPanelR"); // NOI18N
        computerPanelR.setPreferredSize(new java.awt.Dimension(160, 400));
        computerPanelR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainPanel.add(computerPanelR, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        arrowLabelBL.setText(resourceMap.getString("arrowLabelBL.text")); // NOI18N
        arrowLabelBL.setName("arrowLabelBL"); // NOI18N
        arrowLabelBL.setPreferredSize(new java.awt.Dimension(135, 135));
        mainPanel.add(arrowLabelBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 120, 110));

        nameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel2.setName("nameLabel2"); // NOI18N
        mainPanel.add(nameLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 160, -1));

        nameLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel4.setName("nameLabel4"); // NOI18N
        mainPanel.add(nameLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 530, 160, -1));

        nameLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel3.setName("nameLabel3"); // NOI18N
        mainPanel.add(nameLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 400, -1));

        nameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel1.setName("nameLabel1"); // NOI18N
        mainPanel.add(nameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 400, -1));

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(cero.CeroApp.class).getContext().getActionMap(SwingPlayer.class, this);
        newGameMenuItem.setAction(actionMap.get("newGameAction")); // NOI18N
        newGameMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newGameMenuItem.setMnemonic('n');
        newGameMenuItem.setText(resourceMap.getString("newGameMenuItem.text")); // NOI18N
        newGameMenuItem.setName("newGameMenuItem"); // NOI18N
        fileMenu.add(newGameMenuItem);

        optionsMenuItem.setAction(actionMap.get("showOptionsMenu")); // NOI18N
        optionsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        optionsMenuItem.setMnemonic('o');
        optionsMenuItem.setText(resourceMap.getString("optionsMenuItem.text")); // NOI18N
        optionsMenuItem.setName("optionsMenuItem"); // NOI18N
        fileMenu.add(optionsMenuItem);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        howToPlayMenuItem.setAction(actionMap.get("showHowToPlayBox")); // NOI18N
        howToPlayMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        howToPlayMenuItem.setMnemonic('h');
        howToPlayMenuItem.setText(resourceMap.getString("howToPlayMenuItem.text")); // NOI18N
        howToPlayMenuItem.setName("howToPlayMenuItem"); // NOI18N
        helpMenu.add(howToPlayMenuItem);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    /* Account for having a playable card but choosing to draw, and send
     * default action to GameController */
    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawButtonActionPerformed
        //IF playing with the rule that allows only one card to be drawn per
            //turn
        if(gameController.getOptions().multipleDrawsAllowed())
        {
            //Disable the draw button
            drawButton.setVisible(false);
        }
        //ENDIF

        //See defect 270
        hasDrawn = true;
        
        // Forward draw event to (GameController)control
        gameController.actionPerformed(evt);
    }//GEN-LAST:event_drawButtonActionPerformed

    /**
     * The action performed after pressing the pass button
     * @param evt Event that triggered the action
     */
    private void passButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passButtonActionPerformed
        //See defect 270
        hasDrawn = false;

        //Forward event to game controller
        gameController.actionPerformed(evt);
    }//GEN-LAST:event_passButtonActionPerformed

    /**
     * The action performed after choosing red color after playing a wild
     * @param evt Event that triggered the action
     */
    private void colChooserRedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colChooserRedButtonActionPerformed
        //Forward event to game controller
        gameController.actionPerformed(evt);
    }//GEN-LAST:event_colChooserRedButtonActionPerformed

    /**
     * The action performed after choosing blue color after playing a wild
     * @param evt Event that triggered the action
     */
    private void colChooserBlueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colChooserBlueButtonActionPerformed
        //Forward event to game controller
        gameController.actionPerformed(evt);
    }//GEN-LAST:event_colChooserBlueButtonActionPerformed

    /**
     * The action performed after choosing green color after playing a wild
     * @param evt Event that triggered the action
     */
    private void colChooserGreenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colChooserGreenButtonActionPerformed
        //Forward event to game controller
        gameController.actionPerformed(evt);
    }//GEN-LAST:event_colChooserGreenButtonActionPerformed

    /**
     * The action performed after choosing yellow color after playing a wild
     * @param evt Event that triggered the action
     */
    private void colChooserYellowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colChooserYellowButtonActionPerformed
        //Forward event to game controller
        gameController.actionPerformed(evt);
    }//GEN-LAST:event_colChooserYellowButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrowLabelBL;
    private javax.swing.JLabel arrowLabelBR;
    private javax.swing.JLabel arrowLabelTL;
    private javax.swing.JLabel arrowLabelTR;
    private javax.swing.JButton colChooserBlueButton;
    private javax.swing.JButton colChooserGreenButton;
    private javax.swing.JButton colChooserRedButton;
    private javax.swing.JButton colChooserYellowButton;
    private javax.swing.JPanel computerPanelL;
    private javax.swing.JPanel computerPanelR;
    private javax.swing.JPanel computerPanelT;
    private javax.swing.JLabel deckBackLabel;
    private javax.swing.JLabel deckDiscardLabel;
    private javax.swing.JPanel deckPanel;
    private javax.swing.JButton drawButton;
    private javax.swing.JMenuItem howToPlayMenuItem;
    private javax.swing.JPanel humanPanel;
    private javax.swing.JScrollBar humanPanelScrollbar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel messageTextLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel4;
    private javax.swing.JMenuItem newGameMenuItem;
    private javax.swing.JMenuItem optionsMenuItem;
    private javax.swing.JButton passButton;
    // End of variables declaration//GEN-END:variables
}
