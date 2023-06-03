package chess;
import java.util.LinkedList;



public class Rules   {

    static int same;//to check whether next avaliable position piece is black or white
    static LinkedList <Piece> pa=null;
    //check wheter the next avaliable position has piece already
    public static  boolean checkpositfree(Piece obj,int cx,int cy){
        for (Piece p : pa) {
          
          if (p.xp==cx&&p.yp==cy)
        {
          if(obj.iswhite!=p.iswhite){same=0;}//if next avaliable position piece is not equal to that piece then same =0
          else{same=1;}

          return false;
        }

      
        }
        same=2;
        return true;
      }

      public static void pawnrule( Piece obj){
        int cx1,cx2,cy1,cy2,cx3,cy3;
        if (obj.iswhite==true){
          cx1=obj.xp-1;cy1=obj.yp+1;
          cx2=obj.xp+1;cy2=obj.yp+1;
          cx3=obj.xp;  cy3=obj.yp+1;
          //for moving 
          if(obj.yp==1){for (int j = 0; j < 2; j++) {
            if (checkpositfree(obj,cx3, cy3))
          {
              obj.xposi.add(cx3);obj.yposi.add(cy3+j);
          }
          }}
          
          else{
            if (checkpositfree(obj,cx3, cy3))
            {
                obj.xposi.add(cx3);obj.yposi.add(cy3);
            }
            }
          
          //for killing croess left
          if (!checkpositfree(obj,cx1, cy1)&&same==0)
          {
            obj.xposi.add(cx1);obj.yposi.add(cy1);
          }
          //for killing cross right
          if (!checkpositfree(obj,cx2, cy2)&&same==0)
          {
            obj.xposi.add(cx2);obj.yposi.add(cy2);
          }
          
           
        }
        else {
          cx1=obj.xp-1;cy1=obj.yp-1;
          cx2=obj.xp+1;cy2=obj.yp-1;
          cx3=obj.xp;  cy3=obj.yp-1;
           //for moving 
           if(obj.yp==6){ for (int j = 0; j < 2; j++) {
            if (checkpositfree(obj,cx3, cy3))
          {
              obj.xposi.add(cx3);obj.yposi.add(cy3-j);
          }
          }}
          
          else{
            if (checkpositfree(obj,cx3, cy3))
            {
                obj.xposi.add(cx3);obj.yposi.add(cy3);
            }}
           
          //for killing croess left
          if (!checkpositfree(obj,cx1, cy1)&&same==0)
          {
              obj.xposi.add(cx1);obj.yposi.add(cy1);
          }
           //for killing cross right
          if (!checkpositfree(obj,cx2, cy2)&&same==0)
          {
             obj.xposi.add(cx2);obj.yposi.add(cy2);
          }
           
      
        }
      }
      public static void kingnknightrule(int[] arrx, int[] arry,Piece obj){
        for (int i =0; i < 8; i++) {
          int chx=arrx[i];int chy=arry[i];
          if (!(checkpositfree(obj,chx,chy))&&same==0){
              obj.xposi.add(arrx[i]);obj.yposi.add(arry[i]);continue;}
              if(same==1){continue;}
              obj.xposi.add(arrx[i]);obj.yposi.add(arry[i]);
              
            } 
      }

    public static int frontstraightrule( Piece obj)
    {

      int num=1;
      for (int i = obj.xp; i < 7; i++) 
      {
          int chx=obj.xp+num;int chy=obj.yp;//find next location 
          if (!(checkpositfree(obj,chx,chy))&&same==0)// next location has already has piece or in different color
          {
              obj.xposi.add(obj.xp+num);obj.yposi.add(obj.yp);return 0;//add the next location in array of piece
          }
          if(same==1){return 0;}//if next location has piece and in same color then not add location in array
          obj.xposi.add(obj.xp+num);obj.yposi.add(obj.yp);//add next avaliable empty location in array
          num++;
      }
            return 0;
    }
    public static int backstaightrule(Piece obj){

     int  num=1;
      for (int i = obj.xp; i >0; i--) {
          int chx=obj.xp-num;int chy=obj.yp;
          if (!(checkpositfree(obj,chx,chy))&&same==0){
              obj.xposi.add(obj.xp-num);obj.yposi.add(obj.yp);return 0;}
              if(same==1){return 0;}
              obj.xposi.add(obj.xp-num);obj.yposi.add(obj.yp);
              num++;
            }
            return 0;
        }
  

    public static int upwardstraightrule(Piece obj){

    int num=1;
    for (int i = obj.yp; i >0; i--) {
    int chx=obj.xp;int chy=obj.yp-num;
    if (!(checkpositfree(obj,chx,chy))&&same==0){
    obj.xposi.add(obj.xp);obj.yposi.add(obj.yp-num);return 0;}
    if(same==1){return 0;}
    obj.xposi.add(obj.xp);obj.yposi.add(obj.yp-num);
    num++;
  }
  return 0;
}

      //
      public static int downwardstraightrule(Piece obj){

        int num=1;
  for (int i = obj.yp; i < 7; i++) {
    int chx=obj.xp;int chy=obj.yp+num;
    if (!(checkpositfree(obj,chx,chy))&&same==0){
    obj.xposi.add(obj.xp);obj.yposi.add(obj.yp+num);return 0;}
    if(same==1){return 0;}
    obj.xposi.add(obj.xp);obj.yposi.add(obj.yp+num);
    num++;
  }return 0;}

  //
  public static int dowmrightcrossrule(Piece obj){

    int num=1;
  int max;
  if(obj.xp>obj.yp){max=obj.xp;}
  else{max=obj.yp;}
  for (int i = max; i < 7; i++) {
    int chx=obj.xp+num;int chy=obj.yp+num;
    if (!(checkpositfree(obj,chx,chy))&&same==0){
    obj.xposi.add(obj.xp+num);obj.yposi.add(obj.yp+num);return 0;}
    if(same==1){return 0;}
    obj.xposi.add(obj.xp+num);obj.yposi.add(obj.yp+num);
    num++;
  }return 0;}
  static int max;
  public static int uprightcrossrule(Piece obj){

    int num=1;
    
  if(obj.xp>obj.yp){max=obj.xp;}
  else{max=obj.yp;}
  for (int i = max; i >0; i--) {
    int chx=obj.xp+num;int chy=obj.yp-num;
    if (!(checkpositfree(obj,chx,chy))&&same==0){
    obj.xposi.add(obj.xp+num);obj.yposi.add(obj.yp-num);return 0;}
    if(same==1){return 0;}
    obj.xposi.add(obj.xp+num);obj.yposi.add(obj.yp-num);
    num++;}return 0;
  }
    
    public static int downleftcrossrule(Piece obj){

        int num=1;
    if(obj.xp>obj.yp){max=obj.xp;}
    else{max=obj.yp;}
    for (int i = max; i <= 7; i++) {
    int chx=obj.xp-num;int chy=obj.yp+num;
    if (!(checkpositfree(obj,chx,chy))&&same==0){
    obj.xposi.add(obj.xp-num);obj.yposi.add(obj.yp+num);return 0;}
    if(same==1){return 0;}
    obj.xposi.add(obj.xp-num);obj.yposi.add(obj.yp+num);
    num++;}return 0;
  }
    
    public static int upleftcrossrule(Piece obj){
    int num=1;
  
    if(obj.xp>obj.yp){max=obj.xp;}
    else{max=obj.yp;}
    for (int i = max; i >0; i--) {
    int chx=obj.xp-num;int chy=obj.yp-num;
    if (!(checkpositfree(obj,chx,chy))&&same==0){
    obj.xposi.add(obj.xp-num);obj.yposi.add(obj.yp-num);return 0;}
    if(same==1){return 0;}
    obj.xposi.add(obj.xp-num);obj.yposi.add(obj.yp-num);
    num++;}return 0;
   } 
}
