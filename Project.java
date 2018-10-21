import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.swing.JFrame;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Project extends J2_0_2DTransform implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float bb=300,lx=300,ly=2, g=0,u2=0.5f,f2=0.5f,f=1.0f,u=0.50f,f1=-0.5f,u1=0.3f,cnt2=0.050f,cnt3=0.05f,cnt=0.01f,cntx=0.01f,cnty=0.01f,cnt1=0.01f;
    double a=1,b=1,c=0,d=0,e=1.0,h=0;
    float x=1,y=1,z=-25,cx=0.05f,cy=0.05f,cz=0.1f;
float pos[]={x,y,z,1.0f};
float sh[]={1.0f,1.0f,1.0f,1};
float bh[]={0.30f,0.30f,0.3f,1};
static byte[] img;
static int imgW, imgH, imgType;
int end=0; int pause=0,wd=400,ht=400,score=0,level=1;

static final int[] EARTH_TEX = new int[1];

	float light_ambient[] = { 0.0f, 0.0f, 0.0f, 0.0f };
    float light_diffuse[] = { 0.0f, 1.0f, 1.0f, 1.0f };
    float light_position[] = { -3.0f, 3.0f, 10.0f, 0.0f };
    float mat_ambient[] = { 0.8f, 0.8f, 0.8f, 1.0f };
    public void display(GLAutoDrawable drawable)
	  { { { GL gl = drawable.getGL();
	    GLU glu = new GLU();
	
	    GLUT glut = new GLUT();
	    gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
	    gl.glColor3f(0.93f, 0.2f, 0.6f);
	  if(score>bb-20){
		  gl.glWindowPos2d(400,400);
    	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Level UP "+(level+1));}
	    gl.glWindowPos2d(100,100);
    	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Press Arrow Keys to Move The Tetrahedron");
    	gl.glWindowPos2d(100,75);
    	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Press space to Pause ");
    	gl.glColor3f(0.93f, 0.2f, 0.6f);
    	gl.glWindowPos2d(100,700);
    	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Game Rules: Tetrahedron should not collide with other objects");
    	gl.glWindowPos2d(100,650);
    	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Score="+score);
    	gl.glWindowPos2d(550,550);
    	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Level="+level);
    	if((score)>lx)
    		{
    		
        	
    		lx=ly*300;
    		bb=lx;
    		ly++;
    		level++;
    		cnt+=0.02;
    		cnt1+=0.02;
    		
    		
        	
    		
    		}
	    gl.glPushMatrix();
gl.glLoadIdentity();
gl.glColor3f(1.0f, 0.0f, 1.0f);
glu.gluLookAt(10.0,10.0, -49.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
gl.glLineWidth(4);
gl.glRotatef(g, 0.0f, 1.0f, 0.0f);
glut.glutWireCube(4.0f);
gl.glPopMatrix();	 
gl.glPushMatrix();

gl.glColor3f(1.0f, 0.5f, 0.0f);
gl.glTranslatef(f2, u2,-50f);
gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);
gl.glColor3f(0.9f, 0.5f, 0.6f);
glut.glutSolidSphere(0.322f, 180, 160);
gl.glPopMatrix();
gl.glLoadIdentity();
gl.glPushMatrix();
gl.glPopMatrix();
gl.glPushMatrix();
gl.glLoadIdentity();
gl.glColor3f(0.0f, 0.8f, 0.3f);
gl.glTranslatef(f1, u1, -50.0f);
gl.glScalef(0.50f, 0.50f, 0.50f);
gl.glEnable(GL.GL_TEXTURE_2D);
glut.glutSolidTetrahedron();
gl.glDisable(GL.GL_TEXTURE_2D);
gl.glPopMatrix();
gl.glPushMatrix();
gl.glLoadIdentity();
gl.glTranslatef(f, u, -50.0f);
gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);
gl.glColor3f(0.3f, 0.2f, 0.6f);
glut.glutSolidSphere(0.322f, 160, 160);
gl.glPopMatrix();
gl.glFlush();
if(end==0)
{
	if(pause==0)
refl();
else{
	gl.glColor3f(0.93f, 0.2f, 0.6f);
	gl.glWindowPos2d(400,400);
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Game Paused");
	gl.glWindowPos2d(400,200);
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Press 'Space' to Resume");
}}
else{
	gl.glColor3f(0.93f, 0.2f, 0.6f);
	gl.glWindowPos2d(400,400);
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Game Over");
	gl.glWindowPos2d(400,300);
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Final score="+score);
	gl.glWindowPos2d(400,200);
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Press r to restart");
	gl.glWindowPos2d(400,250);
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Press q to quit");
}
	  }}}
    
    void refl(){
    	
    	
g=g+0.25f;
f=f-cnt;
u=u-cnt1;
if((Math.abs(f)>1.9)){
	cnt=-cnt;}
if(Math.abs(u)>1.9){
	cnt1=-cnt1;
}
    	f2=f2+cntx;
    	u2=u2-cnty;

    	if((Math.abs(f2)>1.9)){
    		cntx=-cntx;}
    	if(Math.abs(u2)>1.9){
    		cnty=-cnty;
    	}
    	score++;
    	if((Math.abs(f2-f1)<0.308)&&(Math.abs(u2-u1)<0.308)){
    		
        	end=1;}
    	if((Math.abs(f-f2)<0.308)&&(Math.abs(u-u2)<0.308)){
    		
        	cntx=-cntx;
        	cnty=-cnty;
        	cnt1=-cnt1;
        	cnt=-cnt;}
    	if((Math.abs(f-f1)<0.308)&&(Math.abs(u-u1)<0.308)){
    		
    	end=1;}
    }
	public double dist(double x1,double y1,double x2, double y2){
		
		return(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
	}
	  public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	  {super.reshape(drawable, x, y, w, h);
	   wd=w;
	   ht=h;
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
	    new GLUT();
	    gl.glDepthFunc(GL.GL_LEQUAL);
	    gl.glDrawBuffer(GL.GL_BACK);
	    gl.glEnable(GL.GL_LIGHTING);
	    gl.glEnable(GL.GL_DEPTH_TEST);
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	    gl.glShadeModel(GL.GL_FLAT);
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient,0);
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse,0);
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position,0);
	    gl.glEnable(GL.GL_LIGHT0);
	    gl.glEnable(GL.GL_COLOR_MATERIAL);
	    gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
	    File f = new File("/D:/joglExamples2013/bin/star.jpg");
	    BufferedImage bufimg;
	    try{
	    	   bufimg = ImageIO.read(f);
	 	      imgW = bufimg.getWidth();
	 	      imgH = bufimg.getHeight();
	 	      imgType = bufimg.getType();
	 	      
	 	      //TYPE_BYTE_GRAY  10
	 	      //TYPE_3BYTE_BGR 	5

	 	      // retrieve the pixel array in raster's databuffer
	 	      Raster raster = bufimg.getData();

	 	      DataBufferByte dataBufByte = (DataBufferByte)raster.
	 	                                   getDataBuffer();
	 	      img = dataBufByte.getData();
	 	     gl.glTexGeni(GL.GL_S, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);
	 	    gl.glTexGeni(GL.GL_T, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);
	 	    gl.glTexGeni(GL.GL_R, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);
	 	    gl.glEnable(GL.GL_TEXTURE_GEN_S);
	 	    gl.glEnable(GL.GL_TEXTURE_GEN_T);
	 	    gl.glEnable(GL.GL_TEXTURE_GEN_R);
	    }
	    catch(Exception e){
	    	
	    }
	    gl.glGenTextures(1, IntBuffer.wrap(EARTH_TEX));
	    gl.glBindTexture(GL.GL_TEXTURE_2D, EARTH_TEX[0]);
	    gl.glTexParameteri(GL.GL_TEXTURE_2D,
	                       GL.GL_TEXTURE_MIN_FILTER,
	                       GL.GL_LINEAR);
	    gl.glTexParameteri(GL.GL_TEXTURE_2D,
	                       GL.GL_TEXTURE_MAG_FILTER,
	                       GL.GL_LINEAR);
	    
	    gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB8,
	                    imgW, imgH, 0, GL.GL_BGR,
	                    GL.GL_UNSIGNED_BYTE, ByteBuffer.wrap(img));

	  }

	
	public static void main(String args[]){
		Project jd=new Project();
		jd.setSize(800, 800);
		jd.setVisible(true);
		jd.addKeyListener(jd);
    	canvas.addKeyListener(jd);
}
	@Override
	public void keyPressed(KeyEvent e) {
		  if (e.getKeyCode() == KeyEvent.VK_UP)
		  {
			  if((u1)>1.7){
	    		u1=u1-2*0.15f;
	    	}
			  else{ u1=u1+cnt3;
		    }}
		  if (e.getKeyCode() == KeyEvent.VK_R)
		    {
			  end=0;
			  u=1.0f;
			  f=1.0f;
			  u2=1.0f;
			  f2=-1.0f;
			  u1=0.0f;
			  f1=0.0f;
			  score=0;
			  level=1;lx=300;
			  cnt=0.01f;
			  cnt1=0.016f;
			  
		    }
		  if (e.getKeyCode() == KeyEvent.VK_SPACE)
		    {
			  if(pause==0)
				  pause=1;
			  else 
				  pause=0;
		    }
		 
		    if (e.getKeyCode() == KeyEvent.VK_DOWN)
		    {if((u1)<-1.7){
		    	u1=u1+2*0.15f;
	    	}
		    	else{
		    	 u1=u1-cnt3;
		    }}
		    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		    {if(((f1)>1.7)){
		    	f1=f1-2*0.15f;	}
		    else{
		    	
		  	  f1=f1+cnt2;
		  
		    }}
		    if (e.getKeyCode() == KeyEvent.VK_LEFT)
		    	if(((f1)<-1.7)){
		    		f1=f1+2*0.15f;
		    	}
		    	else{
		    {
		    	f1=f1-cnt2;}
		    }
		    if (e.getKeyCode() == KeyEvent.VK_Q)
		    	{
		    	System.exit(0);
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