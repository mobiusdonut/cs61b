public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  final double G = 6.67e-11;
  public Body(double xP, double yP, double xV, double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }
  public Body(Body b) {
    this.xxPos = b.xxPos;
    this.yyPos = b.yyPos;
    this.xxVel = b.xxVel;
    this.yyVel = b.yyVel;
    this.mass = b.mass;
    this.imgFileName = b.imgFileName;
  }
  public double calcDistance(Body b) {
    return Math.sqrt(Math.pow((this.xxPos - b.xxPos), 2)+Math.pow((this.yyPos - b.yyPos), 2));
  }
  public double calcForceExertedBy(Body b) {
    return G * this.mass * b.mass / Math.pow(calcDistance(b), 2);
  }
  public double calcForceExertedByX(Body b) {
    return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
  }
  public double calcForceExertedByY(Body b) {
    return calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
  }
  public double NetForceExertedByX(Body[] allBodys) {
    double xxNet = 0.0;
    for (Body b : allBodys) {
      if (!this.equals(b)) {
        xxNet += calcForceExertedByX(b);
      }
    }
    return xxNet;
  }
  public double NetForceExertedByY(Body[] allBodys) {
    double yyNet = 0.0;
    for (Body b : allBodys) {
      if (!this.equals(b)) {
        yyNet += calcForceExertedByY(b);
      }
    }
    return yyNet;
  }
  public void update(double dt, double fX, double fY) {
    double xxAcc = fX / this.mass;
    double yyAcc = fY / this.mass;
    this.xxVel += dt * xxAcc;
    this.yyVel += dt * yyAcc;
    this.xxPos += dt * xxVel;
    this.yyPos += dt * yyVel;
  }
  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "/images/" + this.imgFileName);
  }
}
