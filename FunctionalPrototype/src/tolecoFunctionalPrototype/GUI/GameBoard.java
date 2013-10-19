/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.GUI;
import tolecoFunctionalPrototype.Core.*;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;

/**
 *
 * @author eirq
 */
public class GameBoard extends JPanel {
    private static int kCellHeight = 40;
    private static int kCellWidth = 40;
    private static int kTransparentColor = -1;

    private static String kSelectPath = "/tolecoFunctionalPrototype/GUI/Images/selectOutline.gif";
    private static String kHighlightPath = "/tolecoFunctionalPrototype/GUI/Images/attackOutline.gif";
    private static String kPlainPath = "/tolecoFunctionalPrototype/GUI/Images/plains.gif";
    private static String kMountainPath = "/tolecoFunctionalPrototype/GUI/Images/mountains.gif";
    private static String kClubPath = "/tolecoFunctionalPrototype/GUI/Images/club_caveman.gif";
    private static String kSpearPath = "/tolecoFunctionalPrototype/GUI/Images/spear_caveman.gif";
    private static String kTeamOverlay1Path = "/tolecoFunctionalPrototype/GUI/Images/team1Overlay.gif";
    private static String kTeamOverlay2Path = "/tolecoFunctionalPrototype/GUI/Images/team2Overlay.gif";

    private BoardCell[][] board;
    private String[] columns = {"","","","","","","","","","",};
    private JTable dispBoard;
    private GUI_Controller myController;

    private int selectedX;
    private int selectedY;

    private BufferedImage selectBuffImage;
    private BufferedImage highlightImage;
    private BufferedImage plainImage;
    private BufferedImage mountainImage;
    private BufferedImage clubImage;
    private BufferedImage spearImage;
    private BufferedImage teamOverlay1Image;
    private BufferedImage teamOverlay2Image;

    public GameBoard(/*GUI_Controller controller*/)
    {
        //myController = controller;

        board = new BoardCell[Logic.nMapSize][Logic.nMapSize];
        for (int i = 0; i < Logic.nMapSize; i++)
        {
            for (int j = 0; j < Logic.nMapSize; j++)
            {
                board[i][j] = new BoardCell(null, null, null);
            }
        }

        dispBoard = new JTable(board, columns)
        {
            @Override
            public boolean isCellEditable(int x, int y)
            {
                return false;
            }
        };

        dispBoard.setShowGrid(false);
        dispBoard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dispBoard.setCellSelectionEnabled(false);
        dispBoard.setRowHeight(kCellHeight);
        dispBoard.setCellEditor(null);

        for (int i = 0; i < Logic.nMapSize; i++)
        {
            dispBoard.getColumnModel().getColumn(i).setPreferredWidth(kCellWidth);
            dispBoard.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
        }

        dispBoard.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent ev)
            {
                int col = dispBoard.getSelectedColumn();
                int row = dispBoard.getSelectedRow();

                // Is it a left mouse click?
                if (SwingUtilities.isLeftMouseButton(ev))
                {
                    row = (int) (ev.getPoint().getY()/kCellHeight);
                    col = (int) (ev.getPoint().getX()/kCellWidth);
                    
                    myController.cellSelected(row, col);
                    System.out.println("(" + row + ", " + col + ")");
                }
            }
        }
        );

        add(dispBoard);
        setVisible(true);

        selectedX = -1;
        selectedY = -1;

        try
        {
            selectBuffImage = ImageIO.read(getClass().getResource(kSelectPath));
            highlightImage = ImageIO.read(getClass().getResource(kHighlightPath));
            plainImage = ImageIO.read(getClass().getResource(kPlainPath));
            mountainImage = ImageIO.read(getClass().getResource(kMountainPath));
            clubImage = ImageIO.read(getClass().getResource(kClubPath));
            spearImage = ImageIO.read(getClass().getResource(kSpearPath));
            teamOverlay1Image = ImageIO.read(getClass().getResource(kTeamOverlay1Path));
            teamOverlay2Image = ImageIO.read(getClass().getResource(kTeamOverlay2Path));
        }
        catch (Exception ex)
        {
            System.err.println("Unable to load images in GameBoard(): " + ex);
            //System.exit(1);
        }
    }
    
    public void setController(GUI_Controller controller)
    {
        myController = controller;
    }

    class MyDefaultTableModel extends DefaultTableModel
    {
        @Override
        public boolean isCellEditable(int row, int col)
        {
            return false;
        }
    }

    class ImageRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
          boolean hasFocus, int row, int column)
        {
            JLabel rtn = new JLabel();
            BufferedImage dispImg;

            if ((value instanceof BoardCell))
            {
                dispImg = ((BoardCell)value).getDispImage();
             
                if (dispImg != null)
                {
                    rtn.setIcon(new ImageIcon(dispImg));
                    return rtn;
                }
                else
                {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }

            }
            else
            {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

            //return rtn;
        }
    }

    private class BoardCell
    {
        private BufferedImage unitImg;
        private BufferedImage terrainImg;
        private BufferedImage selectImg;
        private BufferedImage teamOverlayImg;

        private BufferedImage dispImg;
        private boolean isValid;
        private boolean isGreyed;

        /*public BoardCell()
        {
            unitImg = null;
            terrainImg = null;
            selectImg = null;

            isValid = false;
        }*/

        public BoardCell(BufferedImage unit, BufferedImage teamOverlay, BufferedImage terrain)
        {
            unitImg = unit;
            teamOverlayImg = teamOverlay;
            terrainImg = terrain;
            selectImg = null;

            isValid = false;
            isGreyed = false;
        }

        public boolean isSelected()
        {
            return (selectImg != null);
        }

        public void removeSelection()
        {
            if (selectImg != null)
            {
                isValid = false;
                selectImg = null;
            }
        }

        public void setSelection(BufferedImage newSelect)
        {
            // "!=" is adequite here, because only one bufferedimage object should exist.
            if (newSelect != selectImg)
            {
                isValid = false;
                selectImg = newSelect;
            }
        }

        public void setGreyed(boolean newGreyed)
        {
            if (newGreyed != isGreyed)
            {
                isValid = false;
                isGreyed = newGreyed;
            }
        }

        public void setUnit(BufferedImage newUnit)
        {
            // "!=" is adequite here, because only one bufferedimage object should exist.
            if (newUnit != unitImg)
            {
                isValid = false;
                unitImg = newUnit;
            }
        }

        public void setTeamOverlay(BufferedImage newTeamOverlay)
        {
            // "!=" is adequite here, because only one bufferedimage object should exist.
            if (newTeamOverlay != teamOverlayImg)
            {
                isValid = false;
                teamOverlayImg = newTeamOverlay;
            }
        }

        public void setTerrain(BufferedImage newTerrain)
        {
            // "!=" is adequite here, because only one bufferedimage object should exist.
            //NEVER have terrain null.
            if (newTerrain != null && newTerrain != terrainImg)
            {
                isValid = false;
                terrainImg = newTerrain;
            }
        }

        //Terrain should never! be null!
        public BufferedImage getDispImage()
        {
            ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
            BufferedImageOp op;


            if (!isValid)
            {
                if (terrainImg != null)
                {
                    dispImg = (BufferedImage) createImage(terrainImg.getWidth(), terrainImg.getHeight());
                }
                else
                {
                    return null;
                }

                if (terrainImg != null)
                {
                    imgs.add(terrainImg);
                }

                if (unitImg != null)
                {
                    if (isGreyed)
                    {
                        op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                        unitImg = op.filter(unitImg, null);
                    }
                    imgs.add(unitImg);
                }

                if (teamOverlayImg != null)
                {
                    imgs.add(teamOverlayImg);
                }

                if (selectImg != null)
                {
                    imgs.add(selectImg);
                }

                dispImg = overlayImages(dispImg, imgs);
            }

            isValid = true;
            return dispImg;
        }
    }

    //Overlay images ontop of one another with the lower indices at the base.
    //Returns the buffredimage, and writes that image into dest
    //ALL BufferdImages must be of the same size.
    //Overylay is not sone in any fancy way. Just copy pixels onto the dest image if 
    // they are != kTransparentColor
    //PRE: dest must be atleast as large as all the BufferedImages in imgs
    public BufferedImage overlayImages(BufferedImage dest, ArrayList<BufferedImage> imgs)
    {
        int curImage, x, y, rgb;
        int deltaX, deltaY;
        int centeredX, centeredY;

        for(x = 0; x < dest.getWidth(); x++)
        {
            for(y = 0; y < dest.getHeight(); y++)
            {
                for (curImage = 0; curImage < imgs.size(); curImage++)
                {
                    deltaX = dest.getWidth() - imgs.get(curImage).getWidth();
                    deltaY = dest.getHeight() - imgs.get(curImage).getHeight();

                    centeredX = x - (deltaX / 2);
                    centeredY = y - (deltaY / 2);

                    //If we are inbounds of the smaller image.
                    if ((centeredX >= 0 && centeredX < imgs.get(curImage).getWidth())
                        && (centeredY >= 0 && centeredY < imgs.get(curImage).getHeight()) )
                    {
                        rgb = imgs.get(curImage).getRGB(centeredX, centeredY);
                        if (rgb != kTransparentColor)
                        {
                            dest.setRGB(x, y, rgb);
                        }
                    }
                }
            }
        }

        return dest;
    }

    public void drawMap(Location[][] map)
    {
        int i, j;
        BufferedImage locImage = plainImage;
        BufferedImage unitTeamOverlay = null;
        BufferedImage unitImage = null;

        for (i = 0; i < Logic.nMapSize; i++)
        {
            //Length of second dimension?
            for (j = 0; j < Logic.nMapSize; j++)
            {
                unitImage = getUnitImg(map[i][j]);
                unitTeamOverlay = getTeamOverlayImg(map[i][j]);
                locImage = getTerrainImg(map[i][j]);

                board[i][j].setGreyed( !(hasAction(map[i][j].myUnit)) );
                board[i][j].setTerrain(locImage);
                board[i][j].setUnit(unitImage);
                board[i][j].setTeamOverlay(unitTeamOverlay);
            }
        }

        validate();
        repaint();
    }

    //Return true if null
    public boolean hasAction(Unit unit)
    {
        if (unit == null)
        {
            return true;
        }

        if (unit.canAttack || unit.movePointsRemaining > 0)
        {
            return true;
        }

        return false;
    }
    
    public BufferedImage getUnitImg(Location loc)
    {
        BufferedImage rtn = null;
        
        if (loc.myUnit instanceof UnitClubman)
        {
            rtn = clubImage;
        }
        else if(loc.myUnit instanceof UnitSpearman)
        {
            rtn = spearImage;
        }

        return rtn;
    }

    public BufferedImage getTeamOverlayImg(Location loc)
    {
        BufferedImage rtn = null;

        if (loc.myUnit != null)
        {
            if (loc.myUnit.playerNum == 1)
            {
                rtn = teamOverlay1Image;
            }
            else
            //else if(loc.myUnit.playerNum == 2)
            {
                rtn = teamOverlay2Image;
            }
        }

        return rtn;
    }

    public BufferedImage getTerrainImg(Location loc)
    {
        BufferedImage rtn = plainImage;

        if (loc.myTerrain instanceof TerrainMountains)
        {
            rtn = mountainImage;
        }
        else if(loc.myTerrain instanceof TerrainPlains)
        {
            rtn = plainImage;
        }

        return rtn;
    }

    public void selectCell(int x, int y)
    {
        if(x == -1)
        {
            if (selectedX != -1)
            {
                board[selectedX][selectedY].removeSelection();
                selectedX = selectedY = -1;
            }
        }
        else
        {
            selectCell(-1, -1);

            selectedX = x;
            selectedY = y;

            board[selectedX][selectedY].setSelection(selectBuffImage);
        }

        dispBoard.repaint();
    }

    public void highlightLocationsPoint(ArrayList<Point> locs)
    {
        for (Point temp : locs)
        {
            board[temp.x][temp.y].setSelection(highlightImage);
        }
    }

    public void highlightLocations(ArrayList<Location> locs)
    {
        for (Location temp : locs)
        {
            board[temp.xLoc][temp.yLoc].setSelection(highlightImage);
        }

        dispBoard.repaint();
    }

    public void lowlightLocations(ArrayList<Point> locs)
    {
        for (Point temp : locs)
        {
            board[temp.x][temp.y].removeSelection();
        }

        dispBoard.repaint();
    }

    public void lowlightLocations()
    {
        int i, j;

        for (i = 0; i < Logic.nMapSize; i++)
        {
            for (j = 0; j < Logic.nMapSize; j++)
            {
                board[i][j].removeSelection();
            }
        }

        board[selectedX][selectedY].setSelection(selectBuffImage);
        dispBoard.repaint();
    }
}
