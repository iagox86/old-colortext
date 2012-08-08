import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JTextPane;

import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorText extends JTextPane
{
    public static final char COLOR 				= 0x8000;

    public static final String RED        = COLOR + "FF0000";
    public static final String GREEN      = COLOR + "00FF00";
    public static final String BLUE       = COLOR + "0000FF";
    public static final String MAJENTA    = COLOR + "FF0080";
    public static final String PURPLE     = COLOR + "FF00FF";
    public static final String CYAN       = COLOR + "00FFFF";
    public static final String YELLOW     = COLOR + "FFFF00";
    public static final String GRAY       = COLOR + "808080";
    public static final String DARKGRAY   = COLOR + "404040";
    public static final String LIGHTGRAY  = COLOR + "C0C0C0";
    public static final String ORANGE     = COLOR + "FF8000";
    
    public static final String BLACK      = COLOR + "000000";
    public static final String WHITE      = COLOR + "FFFFFF";
    
    
    public static final String AQUAMARINE = COLOR + "7FFFD0";
    public static final String BEIGE =      COLOR + "F5F5E0";
    public static final String CHARTREUSE = COLOR + "7FFF00";
    public static final String DARKCYAN =   COLOR + "008D8D";
    public static final String DARKBLUE = COLOR + "00008D";
    public static final String DARKRED = COLOR + "8D0000";
    public static final String DARKGREEN = COLOR + "006400";
    public static final String GOLD = COLOR + "FFd700";
    public static final String GREENYELLOW = COLOR + "ADFF2F";
    public static final String HOTPINK = COLOR + "FF69B4";
    public static final String PLUM = COLOR + "DDA0DD";
    public static final String ROYALBLUE = COLOR + "4169E1";

    private Color foreground;
    private Color background;
    private int styleNum = 0;
    
    public ColorText()
    {
    	this(Color.BLACK, Color.WHITE);
    }
    
	public ColorText(Color background, Color foreground)
	{
		super();
		        
        this.background = background;
        this.foreground = foreground;
        
        this.setBackground(background);
        this.setForeground(foreground);
	}
	
	
	public void addText(String str)
	{
		StyledDocument document = getStyledDocument();
		Style styles = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		StringBuffer s = new StringBuffer();
        StringBuffer toLog = new StringBuffer();
        String style = "style" + (++styleNum);
		
		// This adds text to the textbox in parts, so I'm making it synchronized so that threads don't get their parts shuffled around
		synchronized(this)
		{

            try
	        {
	            StyleConstants.setForeground(document.addStyle(style, styles), foreground);
	
	            for(int i = 0; i < str.length(); i++)
	            {
	                if(str.charAt(i) == COLOR)
	                {
	                    document.insertString(document.getLength(), s.toString(), document.getStyle("style"));
	
	                    s = new StringBuffer();
	
	                    int red     = Integer.parseInt("" + str.charAt(++i) + str.charAt(++i), 16);
	                    int green   = Integer.parseInt("" + str.charAt(++i) + str.charAt(++i), 16);
	                    int blue    = Integer.parseInt("" + str.charAt(++i) + str.charAt(++i), 16);
	
	                    StyleConstants.setForeground(document.addStyle(style, styles), new Color(red, green, blue));
	                }
	                else
	                {
			            document.insertString(document.getLength(), "" + str.charAt(i), document.getStyle(style));
	                }
	            }
	
	            document.removeStyle(style);
	        }
	        catch(Exception e)
	        {
	            // Ignore bad messages
	            try
	            {
	                document.insertString(document.getLength(), "\n", document.getStyle(style));
	            }
	            catch(javax.swing.text.BadLocationException ignored) { }
	        }
		}
	}
	
	
	
	public static void main(String args[])
	{
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(p);
		p.setLayout(new BorderLayout());
		
		ColorText c = new ColorText();
	    c.addText(RED        + "This is RED\n");
	    c.addText(GREEN      + "This is GREEN\n");
	    c.addText(BLUE       + "This is BLUE\n");
	    c.addText(MAJENTA    + "This is MAJENTA\n");
	    c.addText(PURPLE     + "This is PURPLE\n");
	    c.addText(CYAN       + "This is CYAN\n");
	    c.addText(YELLOW     + "This is YELLOW\n");
	    c.addText(GRAY       + "This is GRAY\n");
	    c.addText(DARKGRAY   + "This is DARKGRAY\n");
	    c.addText(LIGHTGRAY  + "This is LIGHTGRAY\n");
	    c.addText(ORANGE     + "This is ORANGE\n");

	    c.addText(AQUAMARINE     + "This is AQUAMARINE\n");
	    c.addText(BEIGE     + "This is BEIGE\n");
	    c.addText(CHARTREUSE     + "This is CHARTREUSE\n");
	    c.addText(DARKCYAN     + "This is DARKCYAN\n");
	    c.addText(DARKBLUE     + "This is DARKBLUE\n");
	    c.addText(DARKRED     + "This is DARKRED\n");
	    c.addText(DARKGREEN     + "This is DARKGREEN\n");
	    c.addText(GOLD     + "This is GOLD\n");
	    c.addText(GREENYELLOW     + "This is GREENYELLOW\n");
	    c.addText(HOTPINK     + "This is HOTPINK\n");
	    c.addText(PLUM     + "This is PLUM\n");
	    c.addText(ROYALBLUE     + "This is ROYALBLUE\n");
	    
		p.add(c);
		
		f.pack();
		f.show();
		
		
	}
}
