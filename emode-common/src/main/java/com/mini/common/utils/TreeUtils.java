package com.mini.common.utils;

import com.mini.common.exception.service.EModeServiceException;

import java.util.*;

/**
 * 树形结构工具类，如：菜单、部门等
 *
 * @author zhl
 */
public class TreeUtils {

    public TreeUtils() {
    }

    /**
     * 根据pid，构建树节点
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodes, Long pid) {
        //pid不能为空
        if (Objects.isNull(pid)) {
            throw new EModeServiceException("parentId不可为空");
        }

        List<T> treeList = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (pid.equals(treeNode.getPid())) {
                treeList.add(findChildren(treeNodes, treeNode));
            }
        }

        return treeList;
    }

    /**
     * 查找子节点
     */
    private static <T extends TreeNode> T findChildren(List<T> treeNodes, T rootNode) {
        for (T treeNode : treeNodes) {
            if (rootNode.getId().equals(treeNode.getPid())) {
                rootNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    /**
     * 构建树节点
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodes) {
        List<T> result = new ArrayList<>();

        //list转map
        Map<Long, T> nodeMap = new LinkedHashMap<>(treeNodes.size());
        for (T treeNode : treeNodes) {
            nodeMap.put(treeNode.getId(), treeNode);
        }

        for (T node : nodeMap.values()) {
            T parent = nodeMap.get(node.getPid());
            if (parent != null && !(node.getId().equals(parent.getId()))) {
                parent.getChildren().add(node);
                continue;
            }

            result.add(node);
        }

        return result;
    }

}