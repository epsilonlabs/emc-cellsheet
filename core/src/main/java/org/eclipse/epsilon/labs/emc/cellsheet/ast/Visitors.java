package org.eclipse.epsilon.labs.emc.cellsheet.ast;

public class Visitors {

    private Visitors() {}

    public static DepthFirstPreOrderVisitor.DepthFirstPreOrderVisitorBuilder depthFirstPreOrder() {
        return new DepthFirstPreOrderVisitor.DepthFirstPreOrderVisitorBuilder();
    }
}
