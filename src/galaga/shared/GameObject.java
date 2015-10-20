package galaga.shared;

public interface GameObject {
    void update(double dt);

    //String getImagePath();//antigo, funcionando
    String getImagePath(int type);//novo, selecionar n image paths

}
