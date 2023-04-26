@SuppressWarnings("unused")
public class StudentVO {

    private String name;
    private int idNo;

    StudentVO(String name, int idNo) {
        this.name = name;
        this.idNo = idNo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getIdNo() {
        return idNo;
    }

    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }
}
