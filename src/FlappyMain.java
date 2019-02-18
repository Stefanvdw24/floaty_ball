/*Floaty Ball
 * v1.0
 * 6 April 2014
 * 
 * Daniël S. van der Westhuizen
 * 
 */

import java.awt.*;

public class FlappyMain {
  
    public static void main(String[] args) {
        
        StdDraw.setXscale(25,475);
        StdDraw.setYscale(25,475);
        boolean collision = true;
        
        while(true)
        {//game instance loop
            int[] pipe1 = {800, 250};//pipe1[0]=x position, pipe1[1]=gap height
            int[] pipe2 = {1066, 250};
            int[] pipe3 = {1332, 250};
            int score = 0;
            Projectile bird = new Projectile(0,250,0,0,12);
            
            
            collision = FlappyLibrary.startScreen(collision); //wait for start
            StdDraw.show(20);
            
            while (collision == false) 
            {//cycle for rendering one frame and updating variables
   
                char input = FlappyLibrary.inputDetection();
                
                //drawing
                StdDraw.setPenColor(127,127,200);
                StdDraw.filledSquare(250,250,250); //background
                pipe1 = FlappyLibrary.drawPipe(pipe1); //draw pipe 1
                pipe2 = FlappyLibrary.drawPipe(pipe2); //draw pipe 2
                pipe3 = FlappyLibrary.drawPipe(pipe3); //draw pipe 3
                bird = FlappyLibrary.drawBird(bird, input); //draw bird
                collision = FlappyLibrary.collisionDetection(bird, pipe1)
                            || FlappyLibrary.collisionDetection(bird, pipe2)
                            || FlappyLibrary.collisionDetection(bird, pipe3);
                
                //update score and play sound (source in readme)
                if (pipe1[0]>=121 && pipe1[0]<=126) { score++; StdAudio.play("button-10.wav"); }
                if (pipe2[0]>=121 && pipe2[0]<=126) { score++; StdAudio.play("button-10.wav"); }
                if (pipe3[0]>=121 && pipe3[0]<=126) { score++; StdAudio.play("button-10.wav"); }
                StdDraw.setPenColor(Color.CYAN);
                StdDraw.setFont(new Font("Arial",Font.PLAIN,40));
                StdDraw.text(50,450, Integer.toString(score) );

                StdDraw.show(20);
                
                //test for quit input
                if (input == 'q' || input == 'Q') {
                   collision = true;
                   break;
                }
                
                //game over screen
                if (collision == true) {
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.setFont(new Font("Arial",Font.PLAIN,60));
                    StdDraw.text(250,250,"FAIL");
                    StdDraw.show(1000);
                    StdDraw.picture(350,100,"rage_guy.jpg"); //(source in readme)
                    StdDraw.show(1500);
                }
            }
        }
        
    }
  
}