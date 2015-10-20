package galaga.shared;

public interface GameObject {
    //    int id = Craft.id;
    void update(double dt);

    String getImagePath();//novo, selecionar n image paths

    int getX();

    int getY();

    boolean isDrawn(int craftId);

    void draw(int craftId);

    int getId();
}
