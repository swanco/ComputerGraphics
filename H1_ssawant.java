import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import com.sun.opengl.util.Animator;
public class H1_ssawant extends Frame implements GLEventListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int GL_LINE_LOOP = 0;
	static double HEIGHT = 800, WIDTH = 800;
	static double xn,yn,x,d,y,r; 
	static double theta=0.0;
	static GL gl1;
	static int i=1;
	static GLCanvas canvas1;
	static Animator  animator1;
	static float c[][]=new float[10][3];//color variable for points;
	static double dr[][]=new double[10][2];
	static double v[][]=new double[10][2];
	static double n[][]=new double[10][2];
	static double xo[]=new double[10];
	static double yo[]=new double[10];
		
	public H1_ssawant(){
		canvas1 = new GLCanvas();
		canvas1.addGLEventListener(this);
		this.add(canvas1, BorderLayout.CENTER);
		gl1 = canvas1.getGL();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				animator1.stop();
				System.exit(0);
			}
		});
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
		gl1.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl1.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl1.glColor3f(1, 1, 1);
		DrawCircle(WIDTH/2, HEIGHT/2, HEIGHT/4,10000);
		gl1.glPointSize(8); 
		gl1.glBegin(GL.GL_POINTS);
		getnew();
		
		gl1.glVertex2d(x, y);
		gl1.glEnd();
		gl1.glPointSize(4); 
		gl1.glBegin(GL.GL_POINTS);
		
		for(int j=0;j<10;j++){
			getnewvec(j);	
			
			gl1.glVertex2d(xo[j],yo[j]);
		}
		gl1.glEnd();
		}
	
static void getnewvec(int j){	
	gl1.glColor3f(c[j][0], c[j][1], c[j][2]);
	
		
	if(dist(xo[j]+(0.001*dr[j][0]),yo[j]+(0.001*dr[j][1]),WIDTH/2,HEIGHT/2)<r){
			xo[j]=xo[j]+(0.001*dr[j][0]);
			yo[j]=yo[j]+(0.001*dr[j][1]);
			}
		else 
		{
			xn=Math.abs(xo[j]-v[j][0]);
			yn=Math.abs(yo[j]-v[j][1]);
			n[j][0]=Math.abs(xo[j]-WIDTH/2);
			n[j][1]=Math.abs(yo[j]-HEIGHT/2);
			d=normalise(n[j][0],n[j][1]);
			n[j][0]=n[j][0]/d;
			n[j][1]=n[j][1]/d;
		dr[j][0]=-(2*((n[j][0]*xn+n[j][1]*yn)*n[j][0]))+xn;
			dr[j][1]=-(2*((n[j][0]*xn+n[j][1]*yn)*n[j][1]))+yn;
		v[j][0]=xo[j];
		v[j][1]=yo[j];
		while((Math.abs(dr[j][0]))>200||(Math.abs(dr[j][0])<20))
		{if(Math.abs(dr[j][0])>200)
			dr[j][0]/=10;
		if(Math.abs(dr[j][0])<20)
			dr[j][0]*=10;
		}
		while((Math.abs(dr[j][1]))>200||(Math.abs(dr[j][1])<20))
		{
		if(Math.abs(dr[j][1])>200)
			dr[j][1]/=100;
		if(Math.abs(dr[j][1])<20)
			dr[j][1]*=10;
		}
		if(dist(xo[j]+(0.001*dr[j][0]),yo[j]+(0.001*dr[j][1]),WIDTH/2,HEIGHT/2)<r)
		{
			
			xo[j]=xo[j]+(0.001*dr[j][0]);
			yo[j]=yo[j]+(0.001*dr[j][1]);
		}
		else{
			
		dr[j][0]=-dr[j][0];
		dr[j][1]=-dr[j][1];
			xo[j]=xo[j]+(0.001*dr[j][0]);
		yo[j]=yo[j]+(0.001*dr[j][1]);
		}
			}
			}
		
	
	static double normalise(double n1,double n2){
		d= Math.sqrt(n1*n1+n2*n2);
			return(d);
		
	}
	static double dist(double a,double b,double c,double d){
		double z=Math.sqrt((a-c)*(a-c)+(b-d)*(b-d));
		return(z);
	}
	static void generate(int j){
		r=HEIGHT/4;
		int x2=0;
		dr[j][0]=33.33*Math.random();	
		dr[j][1]=33.33*Math.random();	
		c[j][0]=(float)Math.random();
		c[j][1]=(float)Math.random();
		c[j][2]=(float)Math.random();
		while(x2==0){
			v[j][0]=Math.random()*r*10;
			v[j][1]=Math.random()*r*10;
			if(dist(WIDTH/2,HEIGHT/2,v[j][0],v[j][1])<r){
				x2=1;
				xo[j]=v[j][0];
				yo[j]=v[j][1];
				
			}	}
		
	}
	@Override	
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}
	void getnew(){
		r=HEIGHT/4;
		gl1.glColor3f(0.0f, 0.0f, 1.0f);
		double DELTA=1/(r*2);
		
		if(theta>2*Math.PI)
		{
			theta=0;
		}
		x=r*Math.cos(theta)+WIDTH/2;
		y=r*Math.sin(theta)+HEIGHT/2;
		theta=theta+DELTA;
		
		
	}
	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		gl1.glColor3f(1.0f, 1.0f, 1.0f);
		gl1.glDrawBuffer(GL.GL_FRONT_AND_BACK);
		animator1 = new Animator(canvas1);
		animator1.start();
for(int j=0;j<10;j++)
{
	generate(j);
}
	}
	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int ARG2, int width, int height) {
		gl1.glMatrixMode(GL.GL_PROJECTION);
		gl1.glLoadIdentity();
		gl1.glOrtho(0, width, 0, height, -1.0, 1.0);
		WIDTH = width; 
		HEIGHT = height;
		gl1.glViewport(0, 0, (int)WIDTH, (int)HEIGHT); 
		gl1.glClear(GL.GL_COLOR_BUFFER_BIT);
		for(int j=0;j<10;j++)
		{
			generate(j);
		}
	}
	void DrawCircle(double cx, double cy, double r1, double num_segments) 
	{ gl1.glPointSize(2);
		gl1.glBegin(GL_LINE_LOOP); 
		for(int ii = 0; ii < num_segments; ii++) 
		{ 
			double theta = 2.0f * 3.1415926d * ii /(num_segments); 

			double x1 = r1 * Math.cos(theta); 
			double y1 = r1 * Math.sin(theta); 

			gl1.glVertex2d(x1 + cx, y1 + cy);

		} 
		gl1.glEnd(); 
	}	public static void main(String args[]){
		H1_ssawant as=new H1_ssawant();
		as.setSize((int)WIDTH,(int)HEIGHT);
		as.setVisible(true);
		
			
		}
	}
