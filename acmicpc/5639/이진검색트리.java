import java.util.*;
import java.io.*;

public class 이진검색트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Tree BST;
    public static void main(String[] args) throws IOException {
        String in;
        BST = new Tree();

        while((in = br.readLine()) != null){
            int val = Integer.parseInt(in);
            Node newNode = new Node(val);
            BST.insert(newNode);
        }
        BST.postOrder(BST.head);
        System.out.print(BST.ans);
    }

    static class Tree{
        static Node head;
        static StringBuilder ans = new StringBuilder();

        public void postOrder(Node node){
            if(node.l != null) postOrder(node.l);
            if(node.r != null) postOrder(node.r);
            ans.append(node.val).append('\n');
        }

        public void insert(Node node){
            if(head == null){
                head = node;
                return;
            }
            Node parent = head;
            while(true){
                if(parent.val > node.val){
                    if(parent.l == null) {
                        parent.l = node;
                        break;
                    }else parent = parent.l;
                }else if(parent.val < node.val){
                    if(parent.r == null){
                        parent.r = node;
                        break;
                    }else parent = parent.r;
                }
            }
        }
    }

    static class Node{
        Node l,r;
        int val;
        public Node(){}
        public Node(int val){
            this.val = val;
        }
    }
}
