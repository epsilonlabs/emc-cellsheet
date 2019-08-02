package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import com.google.common.collect.Iterables;
import com.google.common.graph.*;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.eclipse.epsilon.labs.emc.cellsheet.TokenFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

public abstract class AbstractAst implements Ast<AbstractAst> {

    protected AstEvaluator evaluator;

    protected Cell cell;

    protected MutableValueGraph<AbstractAst, Integer> graph;

    protected Token token;
    private List<AbstractAst> successors;

    protected AbstractAst() {
        graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
    }

    protected AbstractAst(Token token) {
        super();
        this.token = token;
    }

    protected AbstractAst(String token) {
        super();
        this.token = TokenFactory.getInstance().getToken(token);
    }

    @Override
    public AbstractAst getParent() {
        if (!graph.nodes().contains(this)) return null;

        Set<AbstractAst> parents = graph.predecessors(this);
        return Iterables.getOnlyElement(parents, null);
    }

    @Override
    public List<AbstractAst> getChildren() {
        if (graph.nodes().contains(this))
            return graph.successors(this).stream()
                    .sorted(Comparator.comparingInt(AbstractAst::getPosition))
                    .collect(Collectors.toList());
        return Collections.emptyList();
    }

    @Override
    public int addChild(AbstractAst child) {
        int position = graph.nodes().contains(this) ? graph.successors(this).size() : 0;
        addChild(position, child);
        return position;
    }

    @Override
    public void addChild(int position, AbstractAst child) {
        checkState(child.getParent() == null, "Child already in another structure, do a copy instead");
        graph.addNode(this);
        graph.addNode(child);

        // Shift any other children if needed
        graph.successors(this).stream()
                .filter(v -> v.getPosition() >= position)
                .forEach(v -> v.setPosition(v.getPosition() + 1));

        // Add all contents from child
        graph.putEdgeValue(this, child, position);
        for (EndpointPair<AbstractAst> edge : child.graph.edges()) {
            graph.putEdgeValue(edge, child.graph.edgeValue(edge).orElseThrow(IllegalStateException::new));
            edge.source().graph = graph;
            edge.target().graph = graph;
        }
        child.graph = graph;
    }

    @Override
    public AbstractAst removeChild(int position) {
        checkElementIndex(position, graph.outDegree(this));

        List<AbstractAst> children = getChildren();

        // Shift any siblings
        children.stream()
                .filter(v -> v.getPosition() > position)
                .forEach(v -> v.setPosition(v.getPosition() - 1));

        // Delete successors of the child
        AbstractAst toRemove = children.get(position);
        checkArgument(toRemove.getPosition() == position);

        Iterable<AbstractAst> toRemoveChildren = Traverser.forTree(graph).depthFirstPreOrder(Collections.singleton(toRemove));
        MutableValueGraph<AbstractAst, Integer> newGraph = Graphs.inducedSubgraph(graph, toRemoveChildren);

        for (AbstractAst node : toRemoveChildren) {
            node.graph = newGraph;
        }
        graph.removeNode(toRemove);
        toRemove.graph = newGraph;

        return toRemove;
    }

    @Override
    public Cell getCell() {
        return cell == null ? getParent().getCell() : cell;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public int getPosition() {
        if (getParent() == null) return -1;
        return graph.edgeValue(getParent(), this).orElse(-1);
    }

    @Override
    public void setPosition(int position) {
        checkArgument(position > 0, "Position must be > 0");
        if (getParent() == null) return;
        graph.putEdgeValue(getParent(), this, position);
    }

    @Override
    public Iterator<AbstractAst> iterator() {
        return getChildren().iterator();
    }

    @Override
    public String evaluate() {
        return evaluator.evaluate(this);
    }

    @Override
    public String evaluate(AstEvaluator evaluator) {
        return evaluator.evaluate(this);
    }

    @Override
    public void setEvaluator(AstEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void accept(Visitor<AbstractAst> visitor) {
        visitor.visit(this);
    }
}
