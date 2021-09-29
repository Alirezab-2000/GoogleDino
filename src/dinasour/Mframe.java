// IN THE NAME OF GOD
// ALIREZA Binesh
package dinasour;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mframe extends javax.swing.JFrame
{

	JLabel l_lose = new JLabel ();
	Random rand = new Random ();
	static boolean lose = false;
	JLabel cactus1 = new JLabel ();
	JLabel cactus2 = new JLabel ();
	JLabel cactus3 = new JLabel ();
	JLabel cactus4 = new JLabel ();
	JLabel ground1 = new JLabel ();
	JLabel ground2 = new JLabel ();
	JLabel dino = new JLabel ();
	JLabel cloud = new JLabel ();
	Label highrecord = new Label ();
	Label record = new Label ();
	JPanel jPanel1 = new JPanel ();

	static int recordnum = 0;
	//static int highrec = 0;
	Icon loss = new ImageIcon ( getClass ().getResource ( "/sprites/gameOver.png" ) );
	Icon clou = new ImageIcon ( getClass ().getResource ( "/sprites/cloud.png" ) );
	Icon icon = new ImageIcon ( getClass ().getResource ( "/sprites/ground.png" ) );
	Icon dinoimage = new ImageIcon ( getClass ().getResource ( "/sprites/dinosaurInAir.png" ) );
	Icon dinoright = new ImageIcon ( getClass ().getResource ( "/sprites/dinosaurRightFoot.png" ) );
	Icon dinoleft = new ImageIcon ( getClass ().getResource ( "/sprites/dinosaurLeftFoot.png" ) );
	Icon[] cactusa =
	{
		new ImageIcon ( getClass ().getResource ( "/sprites/cactus.png" ) ) , new ImageIcon ( getClass ().getResource ( "/sprites/twoCactus.png" ) ) ,
		new ImageIcon ( getClass ().getResource ( "/sprites/threeCactus.png" ) ) , new ImageIcon ( getClass ().getResource ( "/sprites/fourCactus.png" ) )
	};
	Hedge basic = new Hedge ( cactus1 , 1300 , cactus3 , 1600 );

	public Mframe () throws HeadlessException
	{
		this.add ( highrecord );
		this.add ( l_lose );
		this.add ( dino );
		this.add ( cactus1 );
		this.add ( cactus2 );
		this.add ( cactus3 );
		this.add ( cactus4 );
		this.add ( ground2 );
		this.add ( ground1 );
		this.add ( record );
		this.add ( cloud );
		this.add ( jPanel1 );
		cloud.setIcon ( clou );
		cloud.setBounds ( 800 , 75 , clou.getIconWidth () , clou.getIconHeight () );
		cloud.setBackground ( Color.red );
		highrecord.setBackground ( Color.WHITE );
		highrecord.setBounds ( 600 , 50 , 100 , 25 );
		// l_lose.setBackground(Color.WHITE);
		// l_lose.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
		l_lose.setIcon ( loss );
		l_lose.setBounds ( 400 , 75 , loss.getIconWidth () , loss.getIconHeight () );
		l_lose.setVisible ( false );
		record.setBackground ( Color.WHITE );
		record.setBounds ( 750 , 50 , 150 , 25 );
		jPanel1.setLayout ( null );
		jPanel1.setBackground ( Color.WHITE );
		jPanel1.setSize ( 900 , 300 );
		dino.setBounds ( 100 , 185 , 44 , 47 );
		dino.setIcon ( dinoimage );
		dino.setBackground ( null );
		cactus1.setBounds ( 1000 , 185 , cactusa[ 0 ].getIconWidth () , cactusa[ 0 ].getIconHeight () );
		cactus1.setIcon ( cactusa[ 0 ] );
		cactus2.setBounds ( 1200 , 185 , cactusa[ 1 ].getIconWidth () , cactusa[ 1 ].getIconHeight () );
		cactus2.setIcon ( cactusa[ 1 ] );
		cactus3.setBounds ( 1600 , 185 , cactusa[ 2 ].getIconWidth () , cactusa[ 2 ].getIconHeight () );
		cactus3.setIcon ( cactusa[ 2 ] );
		cactus4.setBounds ( 1700 , 185 , cactusa[ 3 ].getIconWidth () , cactusa[ 3 ].getIconHeight () );
		cactus4.setIcon ( cactusa[ 3 ] );
		ground1.setBounds ( 0 , 200 , 900 , 50 );
		ground2.setBounds ( 900 , 200 , 900 , 50 );
		ground1.setIcon ( icon );
		ground2.setIcon ( icon );

		this.addKeyListener ( new KeyAdapter ()
		{
			@Override
			public void keyPressed ( KeyEvent e )
			{
				if ( basic.isAlive () )
				{
					// System.out.println("yes");
					e.consume ();
				}
				else
				{
					// System.out.println("not");
					l_lose.setVisible ( false );
					lose = false;
					basic = new Hedge ( cactus1 , 1000 , cactus3 , 1700 );
					basic.start ();
					new Thread ( new Runnable ()
					{
						int x1 = ground1.getLocation ().x;
						int y = ground1.getLocation ().y;
						int x2 = ground2.getLocation ().x;
						int xcloud = cloud.getLocation ().x;
						int ycloud = cloud.getLocation ().y;

						@Override
						public void run ()
						{

							for ( int l = 0; true; l++ )
							{
								try
								{
									ground1.setLocation ( x1 , y );
									ground2.setLocation ( x2 , y );
									x2--;
									x1--;
									if ( l % 12 == 0 )
									{
										xcloud--;
										cloud.setLocation ( xcloud , ycloud );

									}
									Thread.sleep ( 2 );
									if ( ground1.getLocation ().x == -900 )
									{
										ground1.setLocation ( 900 , y );
										x1 = 900;
									}
									if ( ground2.getLocation ().x == -900 )
									{
										ground2.setLocation ( 900 , y );
										x2 = 900;
									}
									if ( xcloud == -10 )
										xcloud = 900;

									if ( lose )
									{
										Thread.currentThread ().stop ();
									}
								}
								catch ( InterruptedException ex )
								{
									Logger.getLogger ( Mframe.class.getName () ).log ( Level.SEVERE , null , ex );
								}
							}
						}
					} ).start ();

					new Thread ( new Runnable ()
					{
						@Override
						public void run ()
						{
							while ( true )
							{
								try
								{
									dino.setIcon ( dinoright );
									Thread.sleep ( 100 );
									dino.setIcon ( dinoleft );
									Thread.sleep ( 100 );
									if ( lose )
									{
										Thread.currentThread ().stop ();
									}
								}
								catch ( InterruptedException ex )
								{
									Logger.getLogger ( Mframe.class.getName () ).log ( Level.SEVERE , null , ex );
								}
							}
						}
					} ).start ();

					new Thread ( new Runnable ()
					{
						@Override
						public void run ()
						{
							boolean isequal = false;
							highrecord.setText ( String.format ( " %s : %d" , "HI" , recordnum ) );
							for ( int i = 0; true; i++ )
							{
								try
								{
									record.setText ( String.format ( " %s : %d" , "record" , i ) );
									if ( recordnum == i )
									{
										isequal = true;
									}
									if ( isequal )
									{
										recordnum = i;
										highrecord.setText ( String.format ( " %s : %d" , "HI" , recordnum ) );
									}
									Thread.sleep ( 100 );

									if ( lose )
									{
										isequal = false;
										Thread.currentThread ().stop ();
									}
								}
								catch ( InterruptedException ex )
								{
									Logger.getLogger ( Mframe.class.getName () ).log ( Level.SEVERE , null , ex );
								}
							}
						}

					} ).start ();
				}
			}
		} );
		this.addKeyListener ( new KeyAdapter ()
		{
			int y = dino.getY ();

			//Jump jum = new Jump();
			@Override
			public void keyPressed ( KeyEvent e )
			{

				if ( e.getKeyCode () == 32 )
				{
					// / System.out.println(dino.getLocation().y + " vs " + y);
					// / System.out.println(dino.getY() != y);
					if ( dino.getY () != y )
					{
						// System.out.println("redy");
					}
					else
					{
						//System.out.println(" che shode???");
						new Jump ().start ();
					}
				}
			}
		} );

	}

	class Jump extends Thread
	{

		int e = -1;
		int first = dino.getLocation ().y;
		// int c = -1;

		@Override
		public void run ()
		{
			int y = first;
			int speed = 32;
			int acceleration = -4;
			do
			{
				//dino.setLocation(dino.getLocation().x, y);
				if ( speed == 0 )
				{
					e *= -1;
				}
				for ( int i = 1; i <= Math.abs ( speed ); i++ )
				{
					try
					{
						y += e;
						dino.setLocation ( dino.getLocation ().x , y );
						Thread.sleep ( ( long ) ( ( long ) ( 40 - Math.abs ( speed ) ) / ( 11 ) ) );
					}
					catch ( InterruptedException ex )
					{
						Logger.getLogger ( Mframe.class.getName () ).log ( Level.SEVERE , null , ex );
					}
				}
				// System.out.println(speed);
				if ( speed == -32 )
				{
					break;
				}
				speed += acceleration;
			} while ( true );

			Thread.currentThread ().stop ();
		}
	}

	class Hedge extends Thread
	{

		JLabel cactus;
		int tol;
		JLabel cactus2;
		int tol2;

		public Hedge ( JLabel cactus , int tol , JLabel cactus2 , int tol2 )
		{
			this.cactus = cactus;
			this.tol = tol;
			this.cactus2 = cactus2;
			this.tol2 = tol2;
		}

		@Override
		public void run ()
		{
			int x = tol;
			int y = cactus.getLocation ().y;
			cactus.setIcon ( cactusa[ 1 ] );
			cactus.setSize ( cactusa[ 1 ].getIconWidth () , cactusa[ 1 ].getIconHeight () );

			int x2 = tol2;

			cactus2.setIcon ( cactusa[ 1 ] );
			cactus2.setSize ( cactusa[ 1 ].getIconWidth () , cactusa[ 1 ].getIconHeight () );
			while ( true )
			{
				try
				{
					cactus.setLocation ( x , y );
					cactus2.setLocation ( x2 , y );
					x--;
					x2--;
					Thread.sleep ( 2 );
					if ( x == -50 )
					{
						int ic = rand.nextInt ( 4 );
						x = 1800;
						// System.out.println(x);
						cactus.setIcon ( cactusa[ ic ] );
						cactus.setSize ( cactusa[ ic ].getIconWidth () , cactusa[ ic ].getIconHeight () );
					}
					if ( x2 == -50 )
					{
						int ic2 = rand.nextInt ( 4 );
						x2 = 1800;
						// System.out.println(x);
						cactus2.setIcon ( cactusa[ ic2 ] );
						cactus2.setSize ( cactusa[ ic2 ].getIconWidth () , cactusa[ ic2 ].getIconHeight () );
					}
				}
				catch ( InterruptedException ex )
				{
					Logger.getLogger ( Mframe.class.getName () ).log ( Level.SEVERE , null , ex );
				}

				// System.out.println(" the difrece of x : " + Math.abs((cactus.getX() - dino.getX())));
				if ( Math.abs ( cactus.getY () - dino.getY () ) < 47 )
				{
					if ( Math.abs ( cactus.getX () - dino.getX () ) < 44 )
					{
						lose = true;
						l_lose.setVisible ( true );
						cloud.setLocation ( 800 , 75 );
						basic.stop ();

						Thread.currentThread ().stop ();
						// System.out.println(" first y");
					}
				}
				if ( Math.abs ( cactus.getX () - dino.getX () ) < 44 )
				{
					if ( Math.abs ( cactus.getY () - dino.getY () ) < 47 )
					{
						lose = true;
						l_lose.setVisible ( true );
						cloud.setLocation ( 800 , 75 );
						basic.stop ();

						Thread.currentThread ().stop ();
						// System.out.println("first x");
					}
				}
				if ( Math.abs ( cactus2.getY () - dino.getY () ) < 47 )
				{
					if ( Math.abs ( cactus2.getX () - dino.getX () ) < 44 )
					{
						lose = true;
						l_lose.setVisible ( true );
						cloud.setLocation ( 800 , 75 );
						basic.stop ();

						Thread.currentThread ().stop ();
						// System.out.println(" first y");
					}
				}
				if ( Math.abs ( cactus2.getX () - dino.getX () ) < 44 )
				{
					if ( Math.abs ( cactus2.getY () - dino.getY () ) < 47 )
					{
						lose = true;
						l_lose.setVisible ( true );
						cloud.setLocation ( 800 , 75 );
						basic.stop ();

						Thread.currentThread ().stop ();
						// System.out.println("first x");
					}
				}
			}
		}

	}

}
