
package merkletree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sujit
 */


class NodeBuilder {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws Exception {

        List<MerkleNode> nodeList = new ArrayList<>();
        File folder = new File("/home/sujit/SE-Project/Sample1");
        File[] fileList = folder.listFiles();
        StringBuilder sb = new StringBuilder();

        for (File file : fileList) {
            byte[] x = SHA256.getHashFile(file);
            for (byte b : x) {
                sb.append(String.format("%02x", b));
            }

            nodeList.add(new MerkleNode(x, sb.toString()));
            sb.setLength(0);
        }

        if(nodeList.size() % 2 == 1) {
            // if odd length
            MerkleNode temp = nodeList.get(nodeList.size() - 1);
            nodeList.add(temp);
        }

        System.out.println("size of list : " + nodeList.size());

//        for (MerkleNode node : nodeList) {
//            System.out.println(node.hexString);
//        }

        // Call the treebuilder to built the tree pass the node list as param.
        TreeBuilder tree = new TreeBuilder(nodeList);
    }

}
