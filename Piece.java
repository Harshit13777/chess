package chess;
import java.util.ArrayList;
import chess.Rules;
import java.util.LinkedList;

 public class Piece{
    int xp;
    int yp;
    int x;
    int y;
    
    ArrayList<Integer> xposi=new ArrayList<>();
    ArrayList<Integer> yposi=new ArrayList<>();
    LinkedList <Piece> pa;
    boolean iswhite;
    
    String name;
    public Piece(int xp,int yp,boolean iswhite,String name, LinkedList<Piece> pa)
    {
      this.xp=xp;
      this.yp=yp;
      x=xp*64;
      y=yp*64;
      this.iswhite=iswhite;
      this.pa=pa;
      this.name=name;
      pa.add(this);
    }

//to move location xp and yp are next locations 
public void move (int xp ,int yp){
  Piece p=chessgame.getloc(xp*64, yp*64);//find if there is a piece avail on that location 
  if (p!=null&& p !=this && p.iswhite!=this.iswhite)
  {
        if(p.name=="King")//check if the piece is king 
            {chessgame.Gameoverwrite=1;}//to set gameover string on panel in paint function
     p.kill();
  }
    this.xp=xp;//change piece current location
    this.yp=yp;
 } 
//to remove piece from linkedlist
 public void kill(){
  pa.remove(this);  
 }

 //rules
public void King(){
  xposi.clear();//seting array elements to zero
  yposi.clear();
  Rules.pa=this.pa;//sending pa to rules class 
  int[] arrx={xp+1,xp-1, xp  , xp  , xp+1, xp-1, xp+1, xp-1};
  int[] arry={yp  ,yp  ,yp+1,yp-1,yp+1,yp-1,yp-1,yp+1,};
  Rules.kingnknightrule(arrx,arry,this);
  
}
public void Queen(){
  xposi.clear();
  yposi.clear();
  Rules.pa=this.pa;
  Rules.frontstraightrule(this);
  Rules.backstaightrule(this);
  Rules.upwardstraightrule(this);
  Rules.downwardstraightrule(this);
  Rules.downleftcrossrule(this);
  Rules.dowmrightcrossrule(this);
  Rules.upleftcrossrule(this);
  Rules.uprightcrossrule(this); 
}
public void Bishop(){
  xposi.clear();
  yposi.clear();
  Rules.pa=this.pa;
  Rules.downleftcrossrule(this);
  Rules.dowmrightcrossrule(this);
  Rules.upleftcrossrule(this);
  Rules.uprightcrossrule(this);
}
public void Rook(){
  xposi.clear();
  yposi.clear();
  Rules.pa=this.pa;
  Rules.frontstraightrule(this);
  Rules.backstaightrule(this);
  Rules.upwardstraightrule(this);
  Rules.downwardstraightrule(this);
}
public void Knight(){
  xposi.clear();
  yposi.clear();
  Rules.pa=this.pa;
  int[] arrx={xp+2,xp-1,xp-2,xp+1,xp+2,xp+1,xp-2,xp-1};
  int[] arry={yp+1,yp+2,yp-1,yp-2,yp-1,yp+2,yp+1,yp-2};

  Rules.kingnknightrule(arrx, arry, this);
}
public void Pawn(){
  xposi.clear();
  yposi.clear();
  Rules.pa=this.pa;
  Rules.pawnrule(this);

 
 

}
}