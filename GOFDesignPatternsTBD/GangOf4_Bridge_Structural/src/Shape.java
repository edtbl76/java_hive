abstract class Shape {
    final DrawAPI drawAPI;

    Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
