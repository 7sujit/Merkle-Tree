/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merkletree;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 *
 * @author sujit
 */
public final class TreeBuilder {
    
    MerkleNode[] treeList;
    
    public MerkleNode getTreeRoot() {
        return treeList[0];
    }
    
    public void printTreeRoot() {
        System.out.println(treeList[0].hexString);
    }
    
    public void copyLastN(List<MerkleNode> nodeList) {
        int size = nodeList.size();
        int k = treeList.length - 1;
        for(int i = size - 1; i >= 0; --i,--k) {
            treeList[k] = nodeList.get(i);
        }
    }
    
    public MerkleNode calcTreeList(int left, int right) throws NoSuchAlgorithmException {
        
        return null;
    }
    
    public static int leftChild(int idx) {
        
        return 2 * idx + 1;
    }
    public static int rightChild(int idx) {
        
        return 2 * idx + 2;
    }
    
    public static byte[] combine(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
                
        return c;
    }
    
    /**
     *
     * @param idx
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public byte[] getChildVal(int idx) throws NoSuchAlgorithmException {
        
        if(treeList[idx] == null) { // recursively compute this
            
            byte[] lhashVal = getChildVal(leftChild(idx));
            byte[] rhashVal = getChildVal(rightChild(idx));
            byte[] concat = combine(lhashVal, rhashVal);
            
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(concat);
            
            byte[] newHashVal = md.digest();
            for (byte b : newHashVal) {
                sb.append(String.format("%02x", b));
            }
            
            treeList[idx] = new MerkleNode(newHashVal, sb.toString());
            return treeList[idx].hashValue;
        }
        
        
        return treeList[idx].hashValue;
    }
    
    
    public TreeBuilder(List<MerkleNode> nodeList) throws NoSuchAlgorithmException {
        
        StringBuilder sb = new StringBuilder();
        treeList = new MerkleNode[(nodeList.size() * 2)- 1];
        copyLastN(nodeList);
        
        
        byte[] lhashVal = getChildVal(leftChild(0));
        byte[] rhashVal = getChildVal(rightChild(0));
        byte[] concat = combine(lhashVal, rhashVal);
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(concat);

        byte[] newHashVal = md.digest();
        for (byte b : newHashVal) {
            sb.append(String.format("%02x", b));
        }
        
        treeList[0] = new MerkleNode(newHashVal, sb.toString());
        
        System.out.println("Tree size : " + treeList.length);
        
        for (MerkleNode merkleNode : treeList) {
            if(merkleNode != null) {
                System.out.println(merkleNode.hexString);
            }
        }
        
    }
}
