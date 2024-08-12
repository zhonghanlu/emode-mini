package com.mini.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mini.common.exception.service.EModeServiceException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 树形结构工具类，如：菜单、部门等
 *
 * @author zhl
 * @source_auth renrenfast
 */
public class TreeUtils {

    private TreeUtils() {
    }

    /**
     * stream 根据pid构建树节点
     */
    public static <T extends TreeNode> List<T> streamToTree(List<T> treeList, Long parentId) {
        return treeList.stream()
                // 过滤父节点
                .filter(parent -> parent.getParentId().equals(parentId))
                // 把父节点children递归赋值成为子节点
                .peek(child -> {
                    child.setChildren(streamToTree(treeList, child.getId()));
                }).collect(Collectors.toList());
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
            if (pid.equals(treeNode.getParentId())) {
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
            if (rootNode.getId().equals(treeNode.getParentId())) {
                rootNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    /**
     * 根据page构建树节点
     */
    public static <T extends TreeNode> IPage<T> buildByPage(IPage<T> voPage) {
        List<T> build = build(voPage.getRecords());
        return voPage.setRecords(build);
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
            T parent = nodeMap.get(node.getParentId());
            if (parent != null && !(node.getId().equals(parent.getId()))) {
                parent.getChildren().add(node);
                continue;
            }

            result.add(node);
        }

        return result;
    }

}