import javax.media.opengl.*;

import com.sun.opengl.util.Animator;

public class H4_ssawant extends J1_3_Triangle{
	double ta,tb,tc,lo1=450,lo2=550,lo3=350,lo4=540;
	double to1=150,to2=400,to3=250,to4=400,to5=280,to6=550;
	double mlo1[]={43*0.123,-34*0.023};
	double mlo2[]={-44*0.222,52*0.456};
	double tr[]={-24*0.222,32*0.456};
	
	public void display(GLAutoDrawable drawable){
		gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		drawrect(400,400,350,300);
	DrawCircle(400,400,100,1000000);
		drawtr(to1,to2,to3,to4,to5,to6);
		gl.glBegin(GL.GL_LINES);
		
		gl.glColor3f(0.5f, 0.3f, 1.0f);
		gl.glVertex2d(lo1, lo2);
		gl.glVertex2d(lo3, lo4);
		gl.glEnd();
		linemover(lo1,lo2,lo3,lo4);
		
		mlo1=linemove(lo1,lo2,mlo1);
	mlo2=linemove(lo3,lo4,mlo2);
	tr=linemove(to1,to2,tr);
	tr=linemove(to3,to4,tr);
	tr=linemove(to5,to6,tr);

if(ta<0)
{
	tr[1]=-tr[1];
	tr[0]=-tr[0];
}
if(tb<0)
{
	tr[1]=-tr[1];
	tr[0]=-tr[0];
}
if(tc<0)
{
	tr[1]=-tr[1];
	tr[0]=-tr[0];
}
double t1[]=tmover(to1,to2,to3,to4);
	double t2[]=tmover(to5,to6,to3,to4);
	double t3[]=tmover(to1,to2,to5,to6);
	if((dist(t1[0],t1[1],400,400)<100)){
		tr[1]=-tr[1];
		tr[0]=-tr[0];
		tr[0]=-tr[0];
		tr[1]=-tr[1];	
	}
	else if((dist(t2[0],t2[1],400,400)<100)){
		tr[1]=-tr[1];
		tr[0]=-tr[0];
		tr[0]=-tr[0];
		tr[1]=-tr[1];	
	}
	else if((dist(t3[0],t3[1],400,400)<100)){
		tr[1]=-tr[1];
		tr[0]=-tr[0];
		tr[0]=-tr[0];
		tr[1]=-tr[1];	
	}
	reflectline(to1,to2,lo1,lo2,lo3,lo4);
	reflectline(to3,to4,lo1,lo2,lo3,lo4);
	reflectline(to5,to6,lo1,lo2,lo3,lo4);
	reflectline(lo1,lo2,to1,to2,to3,to4);
	reflectline(lo1,lo2,to1,to2,to5,to6);
	reflectline(lo1,lo2,to5,to6,to3,to4);
	reflectline(lo3,lo4,to1,to2,to3,to4);
	reflectline(lo3,lo4,to1,to2,to5,to6);
	reflectline(lo3,lo4,to5,to6,to3,to4);
	tmove();
	
	lo1=lo1+4*mlo1[0];
	lo2=lo2+4*mlo1[1];
	lo3=lo3+4*mlo2[0];
	lo4=lo4+4*mlo2[1];
	}
	void linemover(double t1, double t2, double t3, double t4){
		double l1,l2,cx,cy,d;
		double v[]=new double[2];
		
		l1=(t3-t1);
		l2=(t4-t2);
		
		d=normalise(l1,l2);
		l1=l1/d;
		l2=l2/d;
		
		cx=(400-t1);
		cy=(400-t2);

		double cl=l1*cx+l2*cy;

		if(cl<0)
		{
			v[0]=t1;
			v[1]=t2;
	
		}
		if(cl>=(dist(t1,t2,t3,t4))){
			v[0]=t3;
			v[1]=t4;
		}
		else{
			
			v[0]=t1+l1*cl;
			v[1]=t2+l2*cl;
	
		}
		gl.glPointSize(5);
		gl.glColor3f(0.5f, 0.3f, 1.0f);
			gl.glBegin(GL.GL_POINTS);	
		gl.glVertex2d(v[0],v[1]);
		gl.glEnd();
		if(dist(v[0],v[1],400,400)<100){
			
			mlo1[1]=-mlo1[1];
			mlo1[0]=-mlo1[0];
			mlo2[0]=-mlo2[0];
			mlo2[1]=-mlo2[1];	
		}
		
	}
	double[] tmover(double t1, double t2, double t3,double t4){
		double l1,l2,cx,cy,d;
		double v[]=new double[2];
		l1=(t3-t1);
		l2=(t4-t2);
		d=normalise(l1,l2);
		l1=l1/d;
		l2=l2/d;
		cx=(400-t1);
		cy=(400-t2);
		
		double cl=l1*cx+l2*cy,ct;
		if(cl<0)
		{
			v[0]=t1;
			v[1]=t2;
	
		}
		else if(cl>dist(t1,t2,t3,t4)){
			v[0]=t3;
			v[1]=t4;
		}
		else{
			v[0]=t1+l1*cl;
			v[1]=t2+l2*cl;
		}
		gl.glPointSize(5);
		gl.glColor3f(1.0f, 0.3f, 1.0f);
			gl.glBegin(GL.GL_POINTS);	
		gl.glVertex2d(v[0],v[1]);
		gl.glEnd();
			return(v);
	}
	void tmove(){
		to1=to1+4*tr[0];
		to2=to2+4*tr[1];
		to3=to3+4*tr[0];
		to4=to4+4*tr[1];
		to5=to5+4*tr[0];
		to6=to6+4*tr[1];
		

	}
	double[] linemove(double lo,double le,double mlo[])
	{ 
	lo=lo+4*mlo[0];
	le=le+4*mlo[1];
	
	
	if(dist(lo,le,400,400)<100){
		while(dist(lo,le,400,400)<103){
			lo=lo-mlo[0];
			le=le-mlo[1];
		}
		mlo=reflectc(lo,le,mlo[0],mlo[1]);
	}
	
	if((lo>750)||(lo<50))
		{
		mlo=reflectr(600,0,mlo[0],mlo[1]);
		}
		if((le>700)||(le<100))
		{		
			mlo=reflectr(0,400,mlo[0],mlo[1]);
		}
		
		
		return(mlo);
			}
	void reflectline(double t1,double t2,double lo1,double lo2, double lo3, double lo4)
	{
	double l1,l2,x,y,pr1,pr2,d;
	l1=t1-lo1;
	l2=t2-lo2;
	d=normalise(l1,l2);
	l1=l1/d;
	l2=l2/d;
	x=t1-lo3;
	y=t2-lo4;
	d=normalise(x,y);
	x/=d;
	y/=d;
	pr1=l1*x+l2*y;
	boolean f=false;
	//System.err.println("b4  \n\n\na+b==c\t"+(int)dist(t1,t2,lo1,lo2)+"\n"+(int)dist(t1,t2,lo3,lo4)+"\n"+(int)dist(lo1,lo2,lo3,lo4));
pr2=(dist(t1,t2,lo1,lo2))-(int)(dist(t1,t2,lo1,lo2));

	if((dist(t1,t2,lo1,lo2)+dist(t1,t2,lo3,lo4))<=dist(lo1,lo2,lo3,lo4)+pr2/4){
		f=true;
	}
if(pr1<0&&f==true){
	System.err.println("a+b==c\t"+(int)dist(t1,t2,lo1,lo2)+"\n"+(int)dist(t1,t2,lo3,lo4)+"\n"+(int)dist(lo1,lo2,lo3,lo4));
	System.err.println(" pr1 is negative ");
	System.err.println(" l1 l2== "+l1+"   "+l2+"\n x y==="+x+"  "+y+"\n pr=="+pr1);
	tr[0]=-tr[0];
	tr[1]=-tr[1];
	mlo1[0]=-mlo1[0];
	mlo2[0]=-mlo2[0];
	mlo1[1]=-mlo1[1];
	mlo2[1]=-mlo2[1];

}
}
	public double dist(double x1,double y1,double x2, double y2){
	
		return(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
	}

	
	double[] reflectc(double x,double y, double p,double q)
	{
		double xx,yy;
		double n1=(x-400);
		double n2=(y-400);
		double d=normalise(n1,n2);
		n1=n1/d;
		n2=n2/d;
		d=normalise(p,q);
		p=p/d;
		q=q/d;
		xx=-(2*(n1*p+n2*q)*n1)+p;
		yy=-(2*(n1*p+n2*q)*n2)+q;
		double m[]={xx,yy};
		return (m);
	
	}
	double[] reflectr(double x,double y, double p, double q){
		double n1=x;
		double n2=y;
		double xx,yy;
		double d=normalise(n1,n2);
		n1=n1/d;
		n2=n2/d;
		d=normalise(p,q);
		p=p/d;
		q=q/d;
		xx=-(2*(n1*p+n2*q)*n1)+p;
		yy=-(2*(n1*p+n2*q)*n2)+q;
		double m[]={xx,yy};
		return (m);
	}
	public double normalise(double n1,double n2){
		return(Math.sqrt(n1*n1+n2*n2));
	}
	void drawrect(int x,int y,int p,int q){
		double r[]={x+p,y+q,x-p,y+q,x-p,y-q,x+p,y-q,x+p,y+q};
		for(int i=0;i<8;i=i+2){
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2d(r[i],r[i+1]);
		gl.glVertex2d(r[i+2],r[i+3]);
		gl.glEnd();
		}
	}
	void drawtr(double a,double b,double c,double d,double e,double f){
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glVertex2d(a,b);
		gl.glVertex2d(c,d);
		gl.glVertex2d(e,f);
		gl.glEnd();
	}
	public void DrawCircle(double a,double b,double c,double d){
		gl.glPointSize(2);
		gl.glBegin(GL.GL_POINTS);
		gl.glBegin(GL.GL_LINE_LOOP);; 
		for(int ii = 0; ii < d; ii++) 
		{ 
			double theta = 2.0f * 3.1415926d * ii/d; 

			double x1 = c* Math.cos(theta); 
			double y1 = c * Math.sin(theta); 
			gl.glVertex2d(x1 + a, y1 + b);

	}
		gl.glEnd();
	}
 public static void main(String arg[]){
		H4_ssawant k= new H4_ssawant();
		k.setSize(800,800);
		k.setVisible(true);
	}

}
