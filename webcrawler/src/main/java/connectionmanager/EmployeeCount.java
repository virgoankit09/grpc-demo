package connectionmanager;

public class EmployeeCount {

    private static int employeeCount = 0;

    public int getEmployeeCount(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + getEmployeeCount(node.left) + getEmployeeCount(node.right);

        //System.out.print(node.value);
        ;
    }

    public static void main(String[] args) {



    }

}

class Node {
    Node left,right;
    int value;
}



