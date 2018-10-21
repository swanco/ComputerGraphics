/*
 * Created on 2008-1-21
 * @author Jim X. Chen: point clipping inside a window
 */
import javax.media.opengl.*;

import com.sun.opengl.util.*;

//import net.java.games.jogl.*;
//import net.java.games.jogl.util.*;

public class H3_ssawant extends J2_0_2DTransform {
float lLeft[] = { -300, -200, 0 };
	float uRight[] = { 00* 8 / 8, 00 * 4 / 4, 0 };

    static float v[][] = new float[3][3];
	public void display(GLAutoDrawable drawable) {
try{
	Thread.sleep(50);
}
catch(Exception e){}

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		// generate a random triangle and display
		int vi[][] = new int[3][3];

		// clear the frame buffer with the background color
		gl.glClear(GL.GL_COLOR_BUFFER_BIT); 
		
		// generate three random vertices
		for (int i = 0; i < 3; i++) { 
			
			int j=1;
			if(Math.random()>0.3)
				j=-j;
			vi[i][0] = (int) (j*WIDTH * Math.random());
			vi[i][1] = (int) (j*HEIGHT * Math.random());
			vi[i][2] = 0;
		}

		// scan-convert the triangle
		drawtriangle(vi);

		// draw edges of the triangle
		bresenhamLine(vi[0][0], vi[0][1], vi[1][0], vi[1][1]);
		bresenhamLine(vi[1][0], vi[1][1], vi[2][0], vi[2][1]);
		bresenhamLine(vi[2][0], vi[2][1], vi[0][0], vi[0][1]);

		// some how setSwapInterval is not working on all platforms. 
		// so here I duplicate to slow down the process: sleeps 500 ms 
				
		my2dLoadIdentity();	
	    if (cnt<1||cnt>300) {
	      flip = -flip;
	    }
	    cnt = cnt+flip;

gl.glColor3f(1, 1, 1);
//	    my2dScalef(cnt, cnt);
	   // transdraw(lLeft,uRight);
/*
gl.glColor3f(1, 1, 1);
my2dRotatef((float) cnt/15);
transdraw(lLeft,uRight);
*/
gl.glColor3f(1, 1, 1);

my2dTranslatef((float)cnt,(float)cnt);
transdraw(lLeft,uRight);

	}
	public void transdraw(float []v1, float[] v2){
		
		my2dTransformf(v1, v[0]);
		my2dTransformf(v2, v[1]);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2f(v[0][0]-1, v[0][1] - 1);
		gl.glVertex2f(v[1][0] + 1, v[0][1] - 1);
		gl.glVertex2f(v[1][0] + 1, v[1][1] + 1);
		gl.glVertex2f(v[0][0] - 1, v[1][1] + 1);
		gl.glEnd();
	}

	public void drawPoint(double x, double y) {

		
		if (x < v[0][0] || x > v[1][0]) {
			return;
		}
		if (y < v[0][1] || y > v[1][1]) {
			return;
		}
gl.glColor3f(1.0f,0.5f,0.0f);
		super.drawPoint(x, y);

	}

	public static void main(String[] args) {
		H3_ssawant f = new H3_ssawant();

		f.setTitle("JOGL J1_3_windowClipping");
		f.setSize(WIDTH, HEIGHT);
		f.setVisible(true);
	}
}
