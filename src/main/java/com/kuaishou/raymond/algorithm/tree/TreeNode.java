package com.kuaishou.raymond.algorithm.tree;

import lombok.Data;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-15 09:47
 */
public class TreeNode {

    TreeNode left;

    TreeNode right;

    private int key;

    public int getKey() {
        return key;
    }

    public TreeNode(int key) {
        this.key = key;
    }

}
