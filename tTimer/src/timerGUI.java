import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Timer;
import java.awt.Color;

class display{	
	private static final int POS = 6;
	private static final int SEGMENTS = 7;

	//public Boolean[][] digits = new Boolean[POS][SEGMENTS];
	public static Boolean[][] digits = new Boolean[POS][SEGMENTS];

	private Boolean OFF = false;
    private Boolean ON = true;
	
	private Boolean zero[] = { ON, ON, ON, ON, ON, ON, OFF };
    private Boolean one[] = { OFF, ON, ON, OFF, OFF, OFF, OFF };
    private Boolean two[] = { ON, ON, OFF, ON, ON, OFF, ON };
    private Boolean three[] = { ON, ON, ON, ON, OFF, OFF, ON };
    private Boolean four[] = { OFF, ON, ON, OFF, OFF, ON, ON };
    private Boolean five[] = { ON, OFF, ON, ON, OFF, ON, ON };
    private Boolean six[] = { ON, OFF, ON, ON, ON, ON, ON };
    private Boolean seven[] = { ON, ON, ON, OFF, OFF, OFF, OFF };
    private Boolean eight[] = { ON, ON, ON, ON, ON, ON, ON };
    private Boolean nine[] = { ON, ON, ON, ON, OFF, ON, ON };

 
    public display(int initValue[])
    {
    	for(int i = 0; i < POS; i++)
    	{
    		System.out.println("setting pos " + i + " to "  + initValue[i] );
    		this.setValue(i, initValue[i]);	
    	}		
    }
    
    public void setTime(int time[])
    {
    	for(int i = 0; i < POS; i++)
    	{
    		this.setValue(i, time[i]);
    	}
    }
    
    public void printValueStdOut()
    { 	
    	for(int i = 0; i < POS; i++)
    	{
    		for(int seg = 0; seg < SEGMENTS; seg++)
    			if(digits[i][seg])
    				System.out.printf("1");
    			else
    				System.out.printf("0");
    		System.out.printf("\n");
    	}	
		System.out.printf("-------------------------\n");    	
    }
    
	public void setValue(int Pos, int value)
	{
		Boolean[] number = new Boolean[SEGMENTS];
		switch (value)
        {
            case 0:
                number = zero;
                break;
            case 1:
                number = one;
                break;
            case 2:
                number = two;
                break;
            case 3:
                number = three;
                break;
            case 4:
                number = four;
                break;
            case 5:
                number = five;
                break;
            case 6:
                number = six;
                break;
            case 7:
                number = seven;
                break;
            case 8:
                number = eight;
                break;
            case 9:
                number = nine;
                break;
            default: /* other number */
                number = zero;
                break;
        }
		digits[Pos] = number;
		System.out.print(digits[Pos][0]);
		System.out.print(digits[Pos][1]);
		System.out.print(digits[Pos][2]);
		System.out.print(digits[Pos][3]);
		System.out.print(digits[Pos][4]);
		System.out.print(digits[Pos][5]);
		System.out.print(digits[Pos][6]);
		System.out.println("--------------------");
    }			
}

public class timerGUI {

	private JFrame frame;
	public static Timer mainTimer = new Timer(); 
	
	public static int[] init = new int[]{1, 2, 3, 4, 5, 6, 7, 1};
	public static display timeDisplay = new display(init);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					timerGUI window = new timerGUI();
					window.frame.setVisible(true);
					
					mainTimer.scheduleAtFixedRate(new myTimer(), 0, 1000);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public timerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("EXIT");
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit, BorderLayout.NORTH);
	}

	public static void update() {
		Calendar cal = Calendar.getInstance();
		
		int[] tmpArr = new int[8];
		int tmp;
		
		tmp = cal.get(Calendar.HOUR);
		tmpArr[4] = tmp % 10;
		tmpArr[5] = (tmp - tmpArr[4]) / 10;
		System.out.printf("%d : ", tmp);
		
		tmp = cal.get(Calendar.MINUTE);
		tmpArr[2] = tmp % 10;
		tmpArr[3] = (tmp - tmpArr[2]) / 10;
		System.out.printf("%d : ", tmp);
		
		tmp = cal.get(Calendar.SECOND);
		tmpArr[0] = tmp % 10;
		tmpArr[1] = (tmp - tmpArr[0]) / 10;
		System.out.printf("%d\n", tmp);
		
		timeDisplay.setTime(tmpArr);
		timeDisplay.printValueStdOut();

	}

}
