import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.*;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
public class H2_ssawant extends Frame implements GLEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int GL_LINE_LOOP=0;
	static GLCanvas canvas0;
	static Animator anim;
	static int dr=1;
	static GL gl0;
	static double cv[]={0,0,0,0,0,0,0,0,0,0,0,0};
	static int clipflag=0,count=0,cvc=0;
	static GLUT glut = new GLUT();
	static double f1[]={200,400};
	static double f2[]={500,250};
	static double f3[]={460,600};
	static double ms1[]=new double[2];
	static double ms2[]=new double[2];
	static double ms3[]=new double[2];
	static double x[]={125,200};
	static double md[]=new double[2];
	static double m1[]={3*0.123,4*0.023};
	static double p[]={155,250};
	static double m2[]={4*0.222,3*0.456};
	static double h=800,w=800,cx=w/2,cy=h/2;
	static double l[][]=new double[100001][2];
	static double rc[]={cx+350,cy+250,cx+350,cy-250,cx-350,cy-250,cx-350,cy+250};
	static double rcc[]={cx+250,cy+150,cx+250,cy-150,cx-250,cy-150,cx-250,cy+150};
	public H2_ssawant(){
		canvas0=new GLCanvas();
		canvas0.addGLEventListener(this);
		this.add(canvas0, BorderLayout.CENTER);
		gl0=canvas0.getGL();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				anim.stop();
				System.exit(0);
			}
		});
	}
	public void init(GLAutoDrawable drawable)
	{

	ms1[0]=4*0.135;
	ms1[1]=4*0.653;
	ms2[0]=3*0.235;
	ms2[1]=3*0.493;
	ms3[0]=5*0.735;
	ms3[1]=5*0.353;
	
		gl0.glColor3f(1.0f, 1.0f, 1.0f);
		gl0.glDrawBuffer(GL.GL_FRONT_AND_BACK);
		anim = new Animator(canvas0);
		anim.start();
		
	}

	public void display(GLAutoDrawable drawable){
		 count++;
		if(clipflag==0)
		{	gl0.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl0.glClear(GL.GL_COLOR_BUFFER_BIT);
		
	
		gl0.glBegin(GL.GL_LINES);
		gl0.glColor3f(0.0f,0.0f,1.0f);
		gl0.glVertex2d(x[0], x[1]);
		gl0.glVertex2d(p[0], p[1]);
	   	gl0.glEnd();
	   	gl0.glWindowPos3d(x[0], x[1], 0);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+x[0]+","+x[1]+")");
	   	
	   	gl0.glWindowPos3d(p[0], p[1], 0);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+p[0]+","+p[1]+")");

	   	linemove(x,m1);
	 	linemove(p,m2);
	 for(int i=0;i<101;i++){
	 	md[0]=(i*x[0]+(100-i)*p[0])/100;
	 	md[1]=(i*x[1]+(100-i)*p[1])/100;
	 	if((dist(md[0],md[1],cx,cy)<55)){
	 		invert(m1);
	 		invert(m2);
	 	}
			
	 	}
		gl0.glPointSize(2);
		DrawCircle(cx,cy,50,1000000);
		gl0.glColor3f(0.50f,0.50f,0.0f);
		DrawRect(rc);
	drawtriangle1(f1,f2,f3);
	gl0.glWindowPos3d(f1[0], f1[1], 0); 
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+f1[0]+","+f1[1]+")");

	gl0.glWindowPos3d(f2[0], f2[1], 0); 
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+f2[0]+","+f2[1]+")");

	gl0.glWindowPos3d(f3[0], f3[1], 0); 
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+f3[0]+","+f3[1]+")");

	tmove(f1,ms1);
	tmove(f2,ms2);
	tmove(f3,ms3);
	if(count%100==0)
		clipflag=1;
	}
		else{
		
		
			gl0.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			gl0.glClear(GL.GL_COLOR_BUFFER_BIT);
			gl0.glColor3f(0.0f,1.0f,1.0f);
			DrawCircle(cx,cy,50,1000000);
			gl0.glColor3f(0.0f,0.50f,0.50f);
			clip(rcc);
			clipline(x,p);
			clipline(p,x);
			for(int i=0;i<12;i++){
				cv[i]=0;
			}
			cvc=0;
			cliptriangle(f1,f2,f3);

	drawpoly(cv);
	tmove(f1,ms1);
	tmove(f2,ms2);
	tmove(f3,ms3);
	linemove(x,m1);
linemove(p,m2);
		 	for(int i=0;i<101;i++){
		 	md[0]=(i*x[0]+(100-i)*p[0])/100;
		 	md[1]=(i*x[1]+(100-i)*p[1])/100;
		 	if((dist(md[0],md[1],cx,cy)<55)){
		 		invert(m1);
		 		invert(m2);
		 	}	
		 	}
			if(count%100==0)
				clipflag=0;
			
		}
	}
	public void drawline(double[]x1,double[]p1){
		gl0.glColor3f(0.0f,1.0f,0.66f);
		gl0.glBegin(GL.GL_LINES);
	   	gl0.glVertex2d(x1[0], x1[1]);
	   	gl0.glVertex2d(p1[0], p1[1]);
	   	gl0.glEnd();
	   	gl0.glWindowPos3d(x1[0], x1[1], 0);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+x1[0]+","+x1[1]+")");
	   	
	   	gl0.glWindowPos3d(p1[0], p1[1], 0);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+p1[0]+","+p1[1]+")");

	}
	public void cliptriangle(double[]x1,double[] p1,double []g){
		cvc=0;
		double tx1[]=new double[2];
		double tx2[]=new double[2];
		double tg1[]=new double[2];
		double tg2[]=new double[2];
		double tp1[]=new double[2];
		double tp2[]=new double[2];
//double t[]=new double[2];
if((p1[0]>650&&x1[0]>650&&g[0]>650)||(p1[0]<150&&x1[0]<150&&g[0]<150)||(p1[1]<250&&x1[1]<250&&g[1]<250)||(p1[1]>550&&x1[1]>550&&g[1]>550)){
	
}//end of if
else
{
if((x1[0]<150)){

	tx1=cliplinet(x1,p1);
	tx2=cliplinet(x1,g);
}
if((g[0]<150)){
	tg1=cliplinet(g,x1);
	tg2=cliplinet(g,p1);
}
if((p1[0]<150)){
	tp1=cliplinet(p1,x1);
	tp2=cliplinet(p1,g);
}
//xmax
if((x1[0]>650)){
	tx1=cliplinet(x1,p1);
	tx2=cliplinet(x1,g);

}
if((g[0]>650)){
	tg1=cliplinet(g,x1);
	tg2=cliplinet(g,p1);
}
if((p1[0]>650)){
	tp2=cliplinet(p1,g);
	tp1=cliplinet(p1,x1);
}
//y
//ymin
if((x1[1]<250)){
	tx1=cliplinet(x1,p1);
	tx2=cliplinet(x1,g);
}
if((g[1]<250)){
	tg1=cliplinet(g,x1);
	tg2=cliplinet(g,p1);
}

if((p1[1]<250)){
	tp1=cliplinet(p1,x1);
	tp2=cliplinet(p1,g);
}
if((x1[1]>550)){
	tx1=cliplinet(x1,p1);
	tx2=cliplinet(x1,g);
}
if((g[1]>550)){
	tg1=cliplinet(g,x1);
	tg2=cliplinet(g,p1);
}
if((p1[1]>550)){
	tp1=cliplinet(p1,x1);
	tp2=cliplinet(p1,g);
}

if((x1[0]>=150&&x1[0]<=650)&&(x1[1]<=550&&x1[1]>=250)){
	tx1=x1;
	tx2=x1;
}
if((g[0]>=150&&g[0]<=650)&&(g[1]<=550&&g[1]>=250)){
	tg1=g;
	tg2=g;
}
if((p1[0]>=150&&p1[0]<=650)&&(p1[1]<=550&&p1[1]>=250)){
	tp1=p1;
	tp2=p1;
}


cv[cvc++]=tx1[0];
cv[cvc++]=tx1[1];
cv[cvc++]=tx2[0];
cv[cvc++]=tx2[1];
cv[cvc++]=tg1[0];
cv[cvc++]=tg1[1];
cv[cvc++]=tg2[0];
cv[cvc++]=tg2[1];
cv[cvc++]=tp2[0];
cv[cvc++]=tp2[1];
cv[cvc++]=tp1[0];
cv[cvc++]=tp1[1];

}
		
	}
	public void drawpoly(double[] dd){
		gl0.glBegin(GL.GL_POLYGON);
		gl0.glColor3f(0.0f,0.660f,0.36f);
		int i=0,j=0,c=0;
		for(i=0;i<12-c;i++){
			if(dd[i]==0){
				c=c+2;
				for(j=i;j<12-c;j++){
					dd[j]=dd[j+2];
					dd[j+2]=0;
				}
			}
		}
	/*	for(j=0;j<12;j++){
	System.err.println(dd[j])		;
		}
		*/
	//System.exit(0);
	
		i=0;
		if(i<12-c)
			gl0.glVertex3d(dd[i++],dd[i++],0);
		if(i<12-c)
			gl0.glVertex3d(dd[i++],dd[i++],0);
		if(i<12-c)
			gl0.glVertex3d(dd[i++],dd[i++],0);
		if(i<12-c)
			gl0.glVertex3d(dd[i++],dd[i++],0);
	if(i<12-c)
			gl0.glVertex3d(dd[i++],dd[i++],0);
		if(i<12-c)
			gl0.glVertex3d(dd[i++],dd[i++],0);

		gl0.glEnd();
		for(i=0;i<12-c;i=i+2){

			gl0.glWindowPos3d(dd[i], dd[i+1], 0); 
			glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+dd[i]+","+dd[i+1]+")");}
		}
	
	public void clipline(double[] x1,double[]p1){
		double tx[]=new double[2];
		double tp[]=new double[2];
double m=(p1[1]-x1[1])/(p1[0]-x1[0]);
if((p1[0]>650&&x1[0]>650)||(p1[0]<150&&x1[0]<150)||(p1[1]>550&&x1[1]>550)||(p1[1]<250&&x1[1]<250)){
	
}
else
{
if((x1[0]<150)){
	tx[0]=150;
	tx[1]=m*(150-p1[0])+p1[1];
}
if((p1[0]<150)){
	tp[0]=150;
	tp[1]=m*(150-x1[0])+x1[1];
}
//xmax
if((x1[0]>650)){
	tx[0]=650;
	tx[1]=m*(650-p1[0])+p1[1];
}
if((p1[0]>650)){
	tp[0]=650;
	tp[1]=m*(650-x1[0])+x1[1];
}
//y
//ymin
if((x1[1]<250)){
	tx[1]=250;
	tx[0]=((250-p1[1])/m)+p1[0];
}
if((p1[1]<250)){
	tp[1]=250;
	tp[0]=((250-x1[1])/m)+x1[0];
}
if((x1[1]>550)){
	tx[1]=550;
	tx[0]=((550-p1[1])/m)+p1[0];
}
if((p1[1]>550)){
	tp[1]=550;
	tp[0]=((550-x1[1])/m)+x1[0];
}
if(x1[0]>150&&x1[0]<650){
	tx[0]=x1[0];
}
if(p1[0]>150&&p1[0]<650){
	tp[0]=p1[0];
}
if(x1[1]<550&&x1[1]>250){
	tx[1]=x1[1];
}
if(p1[1]<550&&p1[1]>250){
	tp[1]=p1[1];
}
drawline(tx,tp);
	}
	}//end of clip line
	
public double[] cliplinet(double[] x1,double[]p1){
		double tx[]={0,0};

		double m=(p1[1]-x1[1])/(p1[0]-x1[0]);

		if((p1[0]>650&&x1[0]>650)||(p1[0]<150&&x1[0]<150)||(p1[1]>550&&x1[1]>550)||(p1[1]<250&&x1[1]<250))
{

	tx=diag(x1,p1);
}
else
{
	if((x1[0]>150&&x1[0]<650)&&(x1[1]<550&&x1[1]>250))
	{
		tx[0]=x1[0];
		tx[1]=x1[1];
	}

		
if((x1[0]<150)){
	tx[0]=150;
	tx[1]=m*(150-p1[0])+p1[1];
	if(tx[1]<250||tx[1]>550)
	tx=diag(x1,p1);
	}

//xmax
if((x1[0]>650)){
	tx[0]=650;
	tx[1]=m*(650-p1[0])+p1[1];
	if(tx[1]<250||tx[1]>550)
	tx=diag(x1,p1);

}
//y
//ymin
if((x1[1]<250)){
	tx[1]=250;
	tx[0]=((250-p1[1])/m)+p1[0];
	if(tx[0]<150||tx[0]>550)
	tx=diag(x1,p1);

}

if((x1[1]>550)){
	tx[1]=550;
	tx[0]=((550-p1[1])/m)+p1[0];
	if(tx[0]<150||tx[0]>650)
	tx=diag(x1,p1);

}
if((x1[0]>150&&x1[0]<650)&&(x1[1]<550&&x1[1]>250)){
	tx[0]=x1[0];
	tx[1]=x1[1];
}

}

return(tx);
	}//end of clip line
public double[] diag(double[] dg,double[] dj){
	double ts[]=new double[2];
	if(dg[0]>=650&&dj[0]>=650&&((dg[1]>=550&&dj[1]<=550)||(dg[1]<=550&&dj[1]>=550))){
		ts[0]=650;
	ts[1]=550;}
	if(dg[0]>=650&&dj[0]>=650&&((dg[1]>=250&&dj[1]<=250)||(dg[1]<=250&&dj[1]>=250))){
		ts[0]=650;
	ts[1]=250;}
	if(dg[0]<=150&&dj[0]<=150&&((dg[1]>=550&&dj[1]<=550)||(dg[1]<=550&&dj[1]>=550))){
		ts[0]=150;
	ts[1]=550;}
	if(dg[0]<=150&&dj[0]<=150&&((dg[1]>=250&&dj[1]<=250)||(dg[1]<=250&&dj[1]>=250))){
		ts[0]=150;
	ts[1]=250;
	}
	
	if(dg[1]>=550&&dj[1]>=550&&((dg[0]<=650&&dj[0]>=650)||(dg[0]>=650&&dj[0]<=650))){
		ts[0]=650;
	ts[1]=550;}
	if(dg[1]>=550&&dj[1]>=550&&((dg[0]<=150&&dj[0]>=150)||(dg[0]>=150&&dj[0]<=150))){
		ts[0]=150;
	ts[1]=550;}
	if(dg[1]<=250&&dj[1]<=250&&((dg[0]<=650&&dj[0]>=650)||(dg[0]>=650&&dj[0]<=650))){
		ts[0]=650;
	ts[1]=250;}
	if(dg[1]<=250&&dj[1]<=250&&((dg[0]<=150&&dj[0]>=150)||(dg[0]>=150&&dj[0]<=150))){
		ts[0]=150;
	ts[1]=250;
	}
	
	return(ts);
}
	public void drawtriangle1(double[] v1, double[] v2, double[] v3) {
		gl0.glBegin(GL.GL_TRIANGLES);
		gl0.glVertex3dv(v1, 0);
		gl0.glVertex3dv(v2, 0);
		gl0.glVertex3dv(v3, 0);
		gl0.glEnd();
	}
	public void invert(double[] k){
		k[0]=-k[0];
		k[1]=-k[1];
	}
public void tmove(double[] f,double[] ms ){
	double xx,yy;
	if((f[0]+4*ms[0]>cx+350))	{
		double n1=700;
		double n2=0;
		double d=normalise(n1,n2);
		n1=n1/d;
		d=normalise(ms[0],ms[1]);
		ms[0]=ms[0]/d;
		ms[1]=ms[1]/d;
		xx=-(2*(n1*ms[0]+n2*ms[1])*n1)+ms[0];
		yy=-(2*(n1*ms[0]+n2*ms[1])*n2)+ms[1];
		ms[0]=xx;
		ms[1]=yy;
	}
	else if((f[0]+4*ms[0]<50)){
		double n1=700;
		double n2=0;
		double d=normalise(n1,n2);
		n1=n1/d;
		d=normalise(ms[0],ms[1]);
		ms[0]=ms[0]/d;
		ms[1]=ms[1]/d;
		xx=-(2*(n1*ms[0]+n2*ms[1])*n1)+ms[0];
		yy=-(2*(n1*ms[0]+n2*ms[1])*n2)+ms[1];
		ms[0]=xx;
		ms[1]=yy;
	}
	if((f[1]+4*ms[1]>650)){
		double n1=0;
		double n2=500;
		double d=normalise(n1,n2);
		n1=n1/d;
		n2=n2/d;
		d=normalise(ms[0],ms[1]);
		ms[0]=ms[0]/d;
		ms[1]=ms[1]/d;
		xx=-(2*(n1*ms[0]+n2*ms[1])*n1)+ms[0];
		yy=-(2*(n1*ms[0]+n2*ms[1])*n2)+ms[1];
		ms[0]=xx;
		ms[1]=yy;
	}
	else if((f[1]+4*ms[1]<=150)){
		double n1=0;
		double n2=500;
		double d=normalise(n1,n2);
		n1=n1/d;
		n2=n2/d;
		d=normalise(ms[0],ms[1]);
		ms[0]=ms[0]/d;
		ms[1]=ms[1]/d;
		xx=-(2*(n1*ms[0]+n2*ms[1])*n1)+ms[0];
		yy=-(2*(n1*ms[0]+n2*ms[1])*n2)+ms[1];
		ms[0]=xx;
		ms[1]=yy;}
	f[0]+=4*ms[0];
	f[1]+=4*ms[1];
}
	public double dist(double x1,double y1,double x2, double y2){
		return(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
	}

	public void linemove(double[] x,double[] m ){
	
		double xx,yy;	
		if((dist(x[0]+4*m[0],x[1]+4*m[1],cx,cy)<50))
		{
			double n1=Math.abs(x[0]-cx);
			double n2=Math.abs(x[1]-cy);
			double d=normalise(n1,n2);
			n1=n1/d;
			n2=n2/d;
			d=normalise(m[0],m[1]);
			m[0]=m[0]/d;
			m[1]=m[1]/d;
			xx=-(2*(n1*m[0]+n2*m[1])*n1)+m[0];
			yy=-(2*(n1*m[0]+n2*m[1])*n2)+m[1];
			m[0]=xx;
			m[1]=yy;
		}
		if((x[0]+4*m[0]>cx+350))	{
			double n1=700;
			double n2=0;
			double d=normalise(n1,n2);
			n1=n1/d;
			d=normalise(m[0],m[1]);
			m[0]=m[0]/d;
			m[1]=m[1]/d;
			xx=-(2*(n1*m[0]+n2*m[1])*n1)+m[0];
			yy=-(2*(n1*m[0]+n2*m[1])*n2)+m[1];
			m[0]=xx;
			m[1]=yy;
			}
		else if((x[0]+4*m[0]<50)){
			double n1=700;
			double n2=0;
			double d=normalise(n1,n2);
			n1=n1/d;
			d=normalise(m[0],m[1]);
			m[0]=m[0]/d;
			m[1]=m[1]/d;
			xx=-(2*(n1*m[0]+n2*m[1])*n1)+m[0];
			yy=-(2*(n1*m[0]+n2*m[1])*n2)+m[1];
			m[0]=xx;
			m[1]=yy;
		}
		if((x[1]+4*m[1]>650)){
			double n1=0;
			double n2=500;
			double d=normalise(n1,n2);
			n1=n1/d;
			n2=n2/d;
			d=normalise(m[0],m[1]);
			m[0]=m[0]/d;
			m[1]=m[1]/d;
			xx=-(2*(n1*m[0]+n2*m[1])*n1)+m[0];
			yy=-(2*(n1*m[0]+n2*m[1])*n2)+m[1];
			m[0]=xx;
			m[1]=yy;
			}
		else if((x[1]+4*m[1]<150)){
			double n1=0;
			double n2=500;
			double d=normalise(n1,n2);
			n1=n1/d;
			n2=n2/d;
			d=normalise(m[0],m[1]);
			m[0]=m[0]/d;
			m[1]=m[1]/d;
			xx=-(2*(n1*m[0]+n2*m[1])*n1)+m[0];
			yy=-(2*(n1*m[0]+n2*m[1])*n2)+m[1];
			m[0]=xx;
			m[1]=yy;
		}
		x[0]+=4*m[0];
		x[1]+=4*m[1];	
	}
	public double normalise(double n1,double n2){
		return(Math.sqrt(n1*n1+n2*n2));
	}
public void DrawCircle(double a,double b,double c,double d){
	gl0.glPointSize(2);
	gl0.glBegin(GL.GL_POINTS);
	gl0.glBegin(GL_LINE_LOOP);; 
	for(int ii = 0; ii < d; ii++) 
	{ 
		double theta = 2.0f * 3.1415926d * ii/d; 

		double x1 = c * Math.cos(theta); 
		double y1 = c * Math.sin(theta); 
		gl0.glVertex2d(x1 + a, y1 + b);

}
	gl0.glEnd();
}
public void DrawRect(double a[]){
	gl0.glPointSize(2);
	gl0.glBegin(GL.GL_LINE_LOOP);
	for(int i=0;i<8;i=i+2){
	gl0.glVertex2d(a[i], a[i+1]);
}
gl0.glEnd();
for(int i=0;i<8;i=i+2){

	gl0.glWindowPos3d(a[i], a[i+1], 0); 
	glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "("+a[i]+","+a[i+1]+")");}
}
public void clip(double[] cc)
{
	DrawRect(cc);
	clipflag=1;
}
public static void main(String args[]){
	H2_ssawant as=new H2_ssawant();
	as.setSize((int)w,(int) h);
	as.setVisible(true);
}
@Override
public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
	// TODO Auto-generated method stub
	
}
@Override
public void reshape(GLAutoDrawable drawable, int arg1, int ARG2, int width, int height) {
	// TODO Auto-generated method stub
	gl0.glMatrixMode(GL.GL_PROJECTION);
	gl0.glLoadIdentity();
	gl0.glOrtho(0, width, 0, height, -1.0, 1.0);
	w= width; 
	h = height;
	gl0.glViewport(0, 0, (int)w, (int)h); 
	gl0.glClear(GL.GL_COLOR_BUFFER_BIT);
}
}// end of class
