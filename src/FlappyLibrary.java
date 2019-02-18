import java.awt.*;

public class FlappyLibrary {
    
    public static int[] drawPipe(int pipe[])
    { //Draw pipe and return new locations
      //pipe[0]=x position, pipe[1]=gap height
   
        //Draw pipe pictures (source cited in readme file)
        StdDraw.picture(pipe[0],pipe[1]+288,"pipe_top.png");
        StdDraw.picture(pipe[0],pipe[1]-286,"pipe_bottom.png");
        
        //calculate new values
        if (pipe[0]>800 && pipe[0]<810) 
            pipe[1] = (int) (Math.random() * 200) + 150;
        if (pipe[0] <= -150) {
            pipe[0] += 800;
            pipe[1] = (int) (Math.random() * 200) + 150;
        }
        else { pipe[0] = pipe[0]-6; }
        return pipe;
    }
     
    public static Projectile drawBird(Projectile ball, char input)
    { //Draw bird and return new values
   
     //Draw ball
        StdDraw.picture(125,ball.ry,"ball.png");//insert ball pic (source in readme)
        ball.vy = ball.vy - 0.5;
        
     //calculate new values
        if (input == ' ') {
           ball.vy = 7;
           StdAudio.play("button-3.wav"); //play jump sound (soure in readme)
        }
        ball.ry += ball.vy;
        if (ball.ry > 499) { ball.ry = 480; ball.vy = 0;};
        if (ball.ry < 0) { ball.ry = 20; ball.vy = 0;}
        return ball;
    }
    
    public static boolean collisionDetection(Projectile ball, int[] pipe)
    { //Detect if ball overlap with given pipe
      
      //ball[0]=y_position, ball[1]=y_velocity, ball[2]=radius
      //pipe[0]=x position, pipe[1]=gap height
        if (125 - ball.radius < pipe[0] + 50 && 125 + ball.radius > pipe[0] -50) {
            if (ball.ry + ball.radius > pipe[1] + 75
                || ball.ry - ball.radius < pipe[1] - 75) return true;
        }
        return false;
    }
    
    public static char inputDetection()
    { //store name of key pressed
      if (StdDraw.hasNextKeyTyped() == true) {
         return StdDraw.nextKeyTyped();
      }
      return 'x';
    }
    
    public static boolean startScreen(boolean collision)
    { //display start screen
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledSquare(250,250,250); //background
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setFont(new Font("Arial",Font.PLAIN,50));
        StdDraw.text(250,300,"Floaty Ball");
        StdDraw.setFont(new Font("Arial",Font.PLAIN,20));
        StdDraw.text(250,200,"Press space to start. Press space to push ball upwards.");       
        if (StdDraw.hasNextKeyTyped() == true) {
           if (StdDraw.nextKeyTyped() == ' ') {
              return false;
           }
        }
        return true;
    }

}