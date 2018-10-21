# ComputerGraphics
Basic Computer Graphics Assignments and Projects
## HW1: Animation
Draw a point that moves on a circle and points that bounce in the cirlce. 

This is achieved by:

1. Extends J1_1_Point, so that you have all the drawing and animation mechanism;

2. In init(),  call super's init(), and set to draw only into the back-buffer: gl.glDrawBuffer(GL.GL_BACK);

3. In display(), which will replace the super's display, clear the drawing area to background color,  draw a circle; draw a point on the circle, draw multiple points. In between, you need to update the points to their new positions.

4. You don't need a reshape() since you can inherit the super's reshape(). You may have a new reshape() that will center the coordinates: gl.glOrtho(-width/2, width/2, -height/2, height/2, -1.0, 1.0); this will be easier for you to calculate the circle points;

5. Repeat Step 3. You may use gl.glPointSize(4) to make the points bigger, and gl.glPointSize(1) to restore drawing single-pixel point. You should make the circle and the point different colors.

## HW2: Primitive Functions

1.Draw a line that bounces between a circle and a rectangle. The line can be two moving points, so the length changes

2.Draw a filled triangle that bounces. The triangle can be three moving points, so the length changes

3.Specify a a rectangle between the circle and the rectangle, so that portions outside this rectangle are clipped. You should implement the   algorithms we discussed in class

4. Use bitmap character strings to label the end points of your line and triangle. The endpoints labels are not displayed when they are        clipped. 

## HW3: 2D transformation

In the prevous program, all vertices are really translated from the origin to the current positions. So let's modify the prevous program, so that the points' locations are all at the origin, and your old "points" are translation values for the points.

Specifically, for each point location p, you will consider it as a translation vector from the origin. Given a point at origin as O, you can have the following:

    my2dLoadIdentity();

    my2dTranslatedv(p); // translate according to vector p; 

    transDraw(O);
    
 ## HW4: Bouncing
This homework is about more on math:  1) making sure your line, triangle, and circle don't cut through one another; 2) the triangle should rotate around one vertex with a fixed rotation speed.

Specifically,

1) you may need to calculate: a) any point should not go across inside a line segment; b) the distance from the center of the circle to the inside of a line segment should be larger than the radius if your circle is inside the rectangle.

2) You should translate that chosen vertex to the origin, rotate, and translate back to where it is.

3) Remember to check bouncing and penetrating.

## HW5: Viewing
You should have a box that is visible in perspective project as a wireframe box. The box is rotating slowly so you can see the box and objects bouncing inside.  Your objects include at least a line, a sphere, and a tetrahedron. A tetrahedron is a 4-vertex triangular polygedron. You can use any arbitrary 4 moving vertices to represent a tetrahedron. The tetrahedron should have solid surfaces.

The objects are bouncing in 3D randomly, and they bounce off one another. Hidden surface removal should be enabled.

Specifically, a) you should use perspective projection (glFrustum) for your viewing volume. You should draw the edges of your box boundary. The box can be rotated along y or z axis in the viewing volume; b) you should consider the distance from a sphere to a point, a line, and a plane, so to avoid the sphere penetrating into another sphere, line, or polyhetron.The objects are moving by their respective vertices. You may add spinning after bouncing, but it is not required.

## HW6: Lighting and Transparency
You can extend your HW5 with lighting and transparency. If you have problem with your HW5, you should make an appointment with me to make corrections to your program.

You should have at least two light sources: one fixed light source in the fixed direction (1, 1, 1), so your environment has a globle light source; another light souce, which is displayed as a transparent sphere, can be moved by your mouse or arrow keys on the keyboard, or a combination of them. This will allow you to move your light source in the environment freely. Also, your movable light source's intensity can be adjusted.

#Project
Design a computer game with your current homework result. You are allowed to design your own game, but it should inlcude the following components:

1. Texture mapping on the tetrahedron and the ground; the texture file name(s) should start with your mason login name, so to avoid conflicts;

2. Interaction between your hand-controlled object and objects animated in the box. You may use bounding volume for collision detection;

3. Lighting or texture effects during interaction.

For example, you may implement the following

1. you can have your own picture wrapped around the tetrahedron and mapped on the ground;

2. You can use your hand-controlled movable light source to push the objects, so that the objects will bounce away in the direction of your moving light source. Alternatively, you can use your mouse to touch objects, so the objects will have certain effects as describled below.

3. When in collision, your light source will glitter, change color, or even explode. Alternatively, when in collision, your texture on the ground may spin (using texture transformation). Of course, you can integrate both as well.
