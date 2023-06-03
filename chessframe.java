package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;





class chessgame  extends MouseAdapter {
    
    public static LinkedList<Piece> pa=new LinkedList<>();
    public static Piece selectedpiece=null;
    public static Piece changeselectedpiece=null;
    public static Piece sameselectedpiece=null;
    JFrame f;
    static int Gameoverwrite=0;
    int findnextlocation=0;
    int fillrectangle=0;
    
    public chessgame() throws IOException{

    
        
    
    
    
       //image import and set in array
        BufferedImage all=ImageIO.read(new File("C:/Users/sonih/Downloads/chess.png"));
        Image imga[]=new Image[12];
        int ind=0;
        for (int y = 0; y < 400; y+=200) {
            for (int x = 0; x < 1200; x+=200) {
                imga[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        //bishop=cross knight=horse rook=straight pawn=soilder
        Piece wking=new Piece   (3, 0, true, "King", pa);
        Piece wqueen=new Piece  (4, 0, true, "Queen", pa);
        Piece wbishop1=new Piece(2, 0, true, "Bishop", pa);
        Piece wbishop2=new Piece(5, 0, true, "Bishop", pa);
        Piece wknight1=new Piece(1, 0, true, "Knight", pa);
        Piece wknight2=new Piece(6, 0, true, "Knight", pa);
        Piece wrook1=new Piece  (0, 0, true, "Rook", pa);
        Piece wrook2=new Piece  (7, 0, true, "Rook", pa);
        Piece wpawn8=new Piece  (0, 1, true, "Pawn", pa);
        Piece wpawn1=new Piece  (1, 1, true, "Pawn", pa);
        Piece wpawn2=new Piece  (2, 1, true, "Pawn", pa);
        Piece wpawn3=new Piece  (3, 1, true, "Pawn", pa);
        Piece wpawn4=new Piece  (4, 1, true, "Pawn", pa);
        Piece wpawn5=new Piece  (5, 1, true, "Pawn", pa);
        Piece wpawn6=new Piece  (6, 1, true, "Pawn", pa);
        Piece wpawn7=new Piece  (7, 1, true, "Pawn", pa);
        //bishop=cross knight=horse rook=straight pawn=soilder
        Piece bking=new Piece   (3, 7,false, "King", pa);
        Piece bqueen=new Piece  (4, 7,false, "Queen", pa);
        Piece bbishop1=new Piece(2, 7,false, "Bishop", pa);
        Piece bbishop2=new Piece(5, 7,false, "Bishop", pa);
        Piece bknight1=new Piece(1, 7,false, "Knight", pa);
        Piece bknight2=new Piece(6, 7,false, "Knight", pa);
        Piece brook1=new Piece  (0, 7,false, "Rook", pa);
        Piece brook2=new Piece  (7, 7,false, "Rook", pa);
        Piece bpawn8=new Piece  (0, 6,false, "Pawn", pa);
        Piece bpawn1=new Piece  (1, 6,false, "Pawn", pa);
        Piece bpawn2=new Piece  (2, 6,false, "Pawn", pa);
        Piece bpawn3=new Piece  (3, 6,false, "Pawn", pa);
        Piece bpawn4=new Piece  (4, 6,false, "Pawn", pa);
        Piece bpawn5=new Piece  (5, 6,false, "Pawn", pa);
        Piece bpawn6=new Piece  (6, 6,false, "Pawn", pa);
        Piece bpawn7=new Piece  (7, 6,false, "Pawn", pa);
        //frame
        f=new JFrame();
       
       
        JPanel p=new JPanel()
        {  
            boolean white =true;
            @Override
            public void paint(Graphics g)
            {   
              //set chesss board
            for (int i = 0; i <8; i++) 
            {
                for (int j = 0; j < 8; j++) 
                {
                    if (white)
                    {g.setColor(Color.orange);}
                    else {g.setColor(Color.YELLOW);} 
                    g.fillRect(i*64, j*64, 64, 64);  
                    white=!white;
                  
                }
                white=!white;
            }
            //set image location in panel according to piece
            for (Piece pe : pa) {
                int in=0;
                   if (pe.name.equalsIgnoreCase("King"))
                   {in=0;} 
                   if (pe.name.equalsIgnoreCase("Queen"))
                   {in=1;} 
                   if (pe.name.equalsIgnoreCase("Bishop"))
                   {in=2;} 
                   if (pe.name.equalsIgnoreCase("Knight"))
                   {in=3;} 
                   if (pe.name.equalsIgnoreCase("Rook"))
                   {in=4;} 
                   if (pe.name.equalsIgnoreCase("Pawn"))
                   {in=5;}
                   if(!pe.iswhite)
                   {in+=6;} 
                   g.drawImage(imga[in],pe.x, pe.y, this); 
                }
                     // set color for next avaliable position showing red
                    if (selectedpiece!=null&&fillrectangle==1){
                    g.setColor(Color.red);
                    for (int j = 0; j < changeselectedpiece.xposi.size(); j++) {
                        g.fillRect(changeselectedpiece.xposi.get(j)*64, changeselectedpiece.yposi.get(j)*64, 64,64);
                    
                }
                }
                //game over showing when king is killed
                if(Gameoverwrite==1)
                {   g.setColor(Color.BLACK);
                    g.drawString("GAME OVER", 4*64, 4*64);
                   pa.clear();
                }                
            }
        };
        

            p.addMouseListener(this);
            p.addMouseMotionListener(this);
          

                    
                
                //frame
      p.setBounds(0,0,512,512);
      f.add(p);
      f.setSize(520,520);
      f.setLayout(null);
      f.setVisible(true);
      f.setDefaultCloseOperation(3);
    
    }
    //searching which piece has location on x and y
    public static Piece  getloc(int x,int y){
    int xp=x/64;
    int yp=y/64;
    for (Piece p : pa) {
        if (p.xp==xp&&p.yp==yp)
        {return p;}
    }
    return null;
    }

    //check rule for check the location on xpp , ypp are in next piece location in array xposi and yposi
public boolean  issameposition(int xpp,int ypp,Piece obj)
{
    
    for (int i = 0; i < obj.xposi.size(); i++) {
    if (xpp==obj.xposi.get(i)&&ypp==obj.yposi.get(i))
    {return true;}
  }
  return false;
}
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
    }
    
    
    public static boolean whichhasnumber=true;//which player number black or white
    public static Piece chechwhichnumber=null;

    public void mousePressed(java.awt.event.MouseEvent e) {
        
        //when press on first time
        //on pressing on piece find which piece has location and add in changeselected pice
        if(changeselectedpiece==null&&(chechwhichnumber=getloc(e.getX(), e.getY())).iswhite==whichhasnumber)
       {changeselectedpiece=chechwhichnumber;
        findnextlocation=1;}

        //* */
        //when press on second time 
        //run when player click on another available loctions on second press
       if (selectedpiece!=null){
        sameselectedpiece=getloc(e.getX(), e.getY());fillrectangle=1;// sameselectedpiece check whether next location is null 
        }

        //to change position press on second time
       if(selectedpiece!=null&& changeselectedpiece!=null&& issameposition(e.getX()/64,e.getY()/64,changeselectedpiece)&&(sameselectedpiece==null||(sameselectedpiece.iswhite!=changeselectedpiece.iswhite)))
       {    fillrectangle=0;
            changeselectedpiece.move(e.getX()/64, e.getY()/64);
              changeselectedpiece.x=e.getX()-32;
              changeselectedpiece.y=e.getY()-32;
        
              f.repaint();
              changeselectedpiece=null;
              selectedpiece=null; 
              sameselectedpiece=null;
              whichhasnumber=!whichhasnumber;
        }
        //to null ,when player press on same piece second time the selected pice will null
        if(selectedpiece!=null&& getloc(e.getX(), e.getY())==changeselectedpiece){changeselectedpiece=null;
            selectedpiece=null;
            fillrectangle=0;
            f.repaint();
        }
        //* */
    
        
       
        // selectedpiece=getloc(e.getX(), e.getY());
        // TODO Auto-generated method stub
        

        
    }
    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        if(selectedpiece==null&&changeselectedpiece!=null) 
       {
        selectedpiece=getloc(e.getX(), e.getY());//to make mouse pressed work two time
         if(findnextlocation==1){
        getpiecenextlocation(changeselectedpiece);//search next avaliable location of changeselectedpiece and add in array
        
        findnextlocation=0;
        fillrectangle=1;//to show red color of next avaliavble position
        f.repaint();
            }
        }
     
        //  selectedpiece.x=e.getX()-32;
       // selectedpiece.y=e.getY()-32;
       // // TODO Auto-generated method stub
       // selectedpiece.move(e.getX()/64, e.getY()/64);
       //     f.repaint();
       
    }
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        
        // TODO Auto-generated method stub

        
    }
    public static void getpiecenextlocation(Piece pe)//to search which piece name and find find next avaliible position by rules
        {
            
                if (pe.name.equalsIgnoreCase("King"))
                {
                    pe.King();//find next avaliable position by rules and add in array
                } 
                if (pe.name.equalsIgnoreCase("Queen"))
                {
                    pe.Queen();
                } 
                if (pe.name.equalsIgnoreCase("Bishop"))
                {
                    pe.Bishop();
                } 
                if (pe.name.equalsIgnoreCase("Knight"))
                {
                    pe.Knight();
                } 
                if (pe.name.equalsIgnoreCase("Rook"))
                {
                    pe.Rook();
                } 
                if (pe.name.equalsIgnoreCase("Pawn"))
                {
                    pe.Pawn();
                }
            }
        }

   

public class chessframe
{
    public static void main(String[] args) throws IOException {
       chessgame c= new chessgame();
    }
}