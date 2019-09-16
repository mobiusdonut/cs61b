import java.io.*;
public class NBody {
  public static double readRadius(String u) {
    double rad = 0.0;
    File universe = new File(u);
    In in = new In("/data/planets.txt");
    in.readLine();
    rad = in.readDouble();
    return rad;
  }
  public static Body[] readBodies(String u) {
    File universe = new File(u);
    In in = new In("data/planets.txt");
    int pcount = in.readInt();
    Body[] planets = new Body[pcount];
    //System.out.println(in.readLine());
    //System.out.println(in.readLine());
    in.readLine();
    in.readLine();
    for (int i = 0; i < pcount; i++) {
      double xxPos2 = in.readDouble();
      double yyPos2 = in.readDouble();
      double xxVel2 = in.readDouble();
      double yyVel2 = in.readDouble();
      double mass2 = in.readDouble();
      String imgFileName2 = in.readString();
      //System.out.println(xxPos2);
      //System.out.println(yyPos2);
      //System.out.println(xxVel2);
      //System.out.println(yyVel2);
      //System.out.println(mass2);
      //System.out.println(imgFileName2);
      planets[i] = new Body(xxPos2, yyPos2, xxVel2, yyVel2, mass2, imgFileName2);
    }
    return planets;
  }
  public static void main(String[] args) {
      double T = Double.parseDouble(args[0]);
      double dt = Double.parseDouble(args[1]);
      String filename = args[2];
      double radius = readRadius(filename);
      Body[] planets = readBodies(filename);
      double time = 0;
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      StdDraw.enableDoubleBuffering();
      StdDraw.setScale(-1 * radius, radius);
      StdAudio.play("audio/2001.mid");
      while (time <= T) {
        for (int i = 0; i < planets.length; i++) {
          xForces[i] = planets[i].NetForceExertedByX(planets);
          yForces[i] = planets[i].NetForceExertedByY(planets);
        }
        for (int i = 0; i < planets.length; i++) {
          planets[i].update(dt, xForces[i], yForces[i]);
        }
    		StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Body body : planets) {
          body.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);
        time += dt;
      }
  }
}
