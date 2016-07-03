/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merkletree;

/**
 *
 * @author sujit
 */
public class MerkleNode {
        
        byte[] hashValue;
        String hexString;
//        String fileName;
        
        public MerkleNode(byte[] hv, String hexStr) {
            this.hashValue = hv;
            this.hexString = hexStr;
//            this.fileName = fileName;
        }
    }