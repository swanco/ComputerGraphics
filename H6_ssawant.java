import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.FloatBuffer;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.GLUT;

public class H6_ssawant extends J2_0_2DTransform implements KeyListener{
	private static GLUT glut;
	float g=0,lx=0.5f,ly=0.5f,f=1.0f,u=0.50f,f1=-0.5f,u1=0.3f,cnt2=0.020f,cnt3=0.01f,cnt=0.01f,cntx=0.01f,cnty=0.01f,cnt1=0.01f;
    double a=1,b=1,c=-1,d=-1,e=1.0,h=0;
    float x=1,y=1,z=-25,cx=0.05f,cy=0.05f,cz=0.1f;
float pos[]={x,y,z,1.0f};
float sh[]={1.0f,1.0f,1.0f,1};
float bh[]={0.30f,0.30f,0.3f,1};
	float light_ambient[] = { 0.0f, 0.0f, 0.0f, 0.0f };
    float light_diffuse[] = { 0.0f, 1.0f, 1.0f, 1.0f };
    float light_position[] = { -3.0f, 3.0f, 10.0f, 0.0f };
    float mat_ambient[] = { 0.8f, 0.8f, 0.8f, 1.0f };
    public void display(GLAutoDrawable drawable)
	  {   GL gl = drawable.getGL();
	    GLU glu = new GLU();
	    GLUT glut = new GLUT();
	    
	    gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
gl.glDepthFunc(GL.GL_LEQUAL);
f=f-cnt;
u=u-cnt1;
gl.glWindowPos2d(100,100);
glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Press Arrow Keys to Move The Light source");
//System.err.println(z);
if((Math.abs(f)>1.9)){
	cnt=-cnt;}
if(Math.abs(u)>1.9){
	cnt1=-cnt1;
}
gl.glPushMatrix();
gl.glLoadIdentity();
gl.glColor3f(1.0f, 0.0f, 1.0f);
glu.gluLookAt(10.0,10.0, -49.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
gl.glRotatef(g, 0.0f, 1.0f, 0.0f);
glut.glutWireCube(4.0f);
gl.glPopMatrix();	 
g=g+0.25f;
gl.glPushMatrix();
gl.glColor3f(1.0f, 0.0f, 0.0f);
gl.glTranslatef(lx, ly,-50);
gl.glLineWidth(3);
gl.glBegin(GL.GL_LINES);
gl.glVertex3d(a,b,-50);
gl.glVertex3d(c,d, -50);
gl.glEnd();
gl.glPopMatrix();

gl.glLoadIdentity();
gl.glPushMatrix();
pos[0]=x;pos[1]=y;pos[2]=z;
gl.glTranslated(x, y, z);

//gl.glDisable (GL.GL_LIGHTING);
gl.glEnable (GL.GL_BLEND);
gl.glBlendFunc (GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_DST_ALPHA); // I think it was like this, check http://msdn2.microsoft.com/en-us/library/ms537046.aspx

gl.glColor4f (1.0f, 1.0f, 1.0f, 1.f); // blue with 50% opacity

// draw your model

gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, sh,0);

gl.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, sh,0);
gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION,pos,0 );
glut.glutSolidSphere(0.4f, 160, 160);
gl.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, bh,0);
	gl.glPopMatrix();
	gl.glLoadIdentity();
gl.glPushMatrix();
lx=lx+cntx;
ly=ly-cnty;
gl.glDisable (GL.GL_BLEND);

gl.glEnable(GL.GL_LIGHTING);

if((lx<-3)||(lx>3))
{
	cntx=-cntx;
}
 if((ly<-3)||(ly>3))
{
	cnty=-cnty;
}

gl.glPopMatrix();
gl.glPushMatrix();

gl.glLoadIdentity();
gl.glColor3f(0.0f, 0.8f, 0.3f);
gl.glTranslatef(f1, u1, -50.0f);
f1=f1-cnt2;
u1=u1-cnt3;
if((Math.abs(f1)>1.7)){
	cnt2=-cnt2;}
if(Math.abs(u1)>1.7){
	cnt3=-cnt3;
}
gl.glScalef(0.50f, 0.50f, 0.50f);
glut.glutSolidTetrahedron();
gl.glPopMatrix();
gl.glPushMatrix();

gl.glLoadIdentity();
gl.glTranslatef(f, u, -50.0f);
gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);
gl.glColor3f(0.3f, 0.2f, 0.6f);
glut.glutSolidSphere(0.322f, 160, 160);
gl.glPopMatrix();
gl.glFlush();
if((dist(lx,ly,f,u)<0.30)){
	cnt=-cnt;
	cntx=-cntx;
	cnt1=-cnt1;
	cnty=-cnty;
}
if((dist(lx,ly,f1,u1)<0.30)){
	cnt2=-cnt2;
	cntx=-cntx;
	cnt3=-cnt3;
	cnty=-cnty;
}if((dist(lx/2,ly/2,f,u)<0.30)){
	cnt=-cnt;
	cntx=-cntx;
	cnt1=-cnt1;
	cnty=-cnty;
}
if((dist(2*lx,2*ly,f1,u1)<0.30)){
	cnt2=-cnt2;
	cntx=-cntx;
	cnt3=-cnt3;
	cnty=-cnty;
}
if((dist(2*lx,2*ly,f,u)<0.30)){
	cnt=-cnt;
	cntx=-cntx;
	cnt1=-cnt1;
	cnty=-cnty;
}
if((dist(lx/2,ly/2,f1,u1)<0.30)){
	cnt2=-cnt2;
	cntx=-cntx;
	cnt3=-cnt3;
	cnty=-cnty;
}
if((Math.abs(f-f1)<0.308)&&(Math.abs(u-u1)<0.308)){
	
	cnt=-cnt;
	//System.err.println("Collision");
	cnt2=-cnt2;
	
	//System.err.println("Collision");
	cnt3=-cnt3;
	cnt1=-cnt1;
}
	  }
	public double dist(double x1,double y1,double x2, double y2){
		
		return(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
	}
	  public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	  {super.reshape(drawable, x, y, w, h);
	   
	    //
	    gl.glMatrixMode(GL.GL_PROJECTION); /* prepare for and then */
	    gl.glLoadIdentity(); /* define the projection */
	    gl.glFrustum(-1.0, 1.0, -1.0, 1.0, 10.0, 100.0); /* transformation */
	    gl.glMatrixMode(GL.GL_MODELVIEW); /* back to modelview matrix */
	    gl.glViewport(0, 0, w, h); /* define the viewport */
	  }
	  public void init(GLAutoDrawable drawable)
	  {super.init(drawable);
	    GL gl = drawable.getGL(); 
	    glut = new GLUT();
	    //
	    gl.glEnable(GL.GL_DEPTH_TEST);
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	    gl.glShadeModel(GL.GL_SMOOTH);
	   gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient,0);
	   gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse,0);
	   // gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular,0);
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position,0);
	    gl.glEnable(GL.GL_LIGHTING);
	    gl.glEnable(GL.GL_LIGHT0);
	    gl.glEnable(GL.GL_COLOR_MATERIAL);
	  }

	
	public static void main(String args[]){
		H6_ssawant jd=new H6_ssawant();
		jd.setSize(800, 800);
		jd.setVisible(true);
		jd.addKeyListener(jd);
    	canvas.addKeyListener(jd);
}
	@Override
	public void keyPressed(KeyEvent e) {
		  if (e.getKeyCode() == KeyEvent.VK_UP)
		    {
			  y=y+cy;
		    }
		  if (e.getKeyCode() == KeyEvent.VK_Q)
		    {
			  z=z+cz;
		    }
		  if (e.getKeyCode() == KeyEvent.VK_W)
		    {
			  z=z-cz;
		    }
		  if (e.getKeyCode() == KeyEvent.VK_UP)
		    {
			  y=y+cy;
		    }
		    if (e.getKeyCode() == KeyEvent.VK_DOWN)
		    {
		      y=y-cy;
		    }
		    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		    {
			      x=x+cx;
		     }
		    if (e.getKeyCode() == KeyEvent.VK_LEFT)
		    {
			      x=x-cx;
		    }

		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}